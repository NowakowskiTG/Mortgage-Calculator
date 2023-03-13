package com.project.service;

public class MortgageException extends RuntimeException {
    public MortgageException() {
        super("Case not handled");
    }
}
