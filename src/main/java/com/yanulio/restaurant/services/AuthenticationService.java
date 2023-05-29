package com.yanulio.restaurant.services;

import com.yanulio.restaurant.authentification.AuthenticationRequest;
import com.yanulio.restaurant.authentification.AuthenticationResponse;
import com.yanulio.restaurant.authentification.RegisterRequest;
import com.yanulio.restaurant.authentification.UserInfoResponse;
import com.yanulio.restaurant.exceptions.UserNotLoggedInException;
import com.yanulio.restaurant.exceptions.DataAlreadyExistsException;
import com.yanulio.restaurant.models.user.User;
import com.yanulio.restaurant.models.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder encoder;
    private final UserRepo repo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        if (repo.existsByUsername(request.getUsername())) {
            throw new DataAlreadyExistsException("A user with this username already exists.");
        }
        if (repo.existsByEmail(request.getEmail())) {
            throw new DataAlreadyExistsException("A user with this email already exists.");
        }
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password_hash(encoder.encode(request.getPassword()))
                .role("user")
                .created_at(new Date(System.currentTimeMillis()))
                .updated_at(new Date(System.currentTimeMillis()))
                .build();
        repo.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = repo.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User was not found"));
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }

    public UserInfoResponse getUserInfo(UserDetails userDetails) {
        if (userDetails == null) throw new UserNotLoggedInException("User is not logged in. Please, log in before trying to get user information.");
        var user = repo.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User was not found"));
        return UserInfoResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getAuthorities().iterator().next().getAuthority())
                .build();
    }
}
