package com.exercise.account_service.exception;

public class SaldoInsuficienteException  extends RuntimeException {
    public SaldoInsuficienteException(String message) {
        super(message);
    }

}
