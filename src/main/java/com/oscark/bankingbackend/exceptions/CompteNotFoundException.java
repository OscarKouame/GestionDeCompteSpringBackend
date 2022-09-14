package com.oscark.bankingbackend.exceptions;

public class CompteNotFoundException extends RuntimeException {
    public CompteNotFoundException(String message) {
        super(message);
    }
}
