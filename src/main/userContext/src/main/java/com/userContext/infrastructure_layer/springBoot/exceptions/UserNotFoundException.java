package com.userContext.infrastructure_layer.springBoot.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
