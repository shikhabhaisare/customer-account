package com.snb.customeraccount.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(String.format("Customer '%d' not found", id));
    }
}