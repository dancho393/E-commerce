package com.projects.ecommerce.api.exception;

public class AccountNotVerifiedException extends RuntimeException{
    public AccountNotVerifiedException(String message){
        super(message);
    }
}
