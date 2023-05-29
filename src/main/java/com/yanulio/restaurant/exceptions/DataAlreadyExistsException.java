package com.yanulio.restaurant.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

public class DataAlreadyExistsException extends AuthenticationServiceException {
    public DataAlreadyExistsException(String msg) {
        super(msg);
    }
}
