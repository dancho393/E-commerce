package com.projects.ecommerce.api.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
         super(message);
    }
}
