package com.yanulio.restaurant.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

public class UserNotLoggedInException extends AuthenticationServiceException {
    public UserNotLoggedInException(String msg) {
        super(msg);
    }
}
