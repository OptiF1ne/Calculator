package com.optif1ne.calculator.model;

public class DivideByZeroException extends RuntimeException {
    public DivideByZeroException() {
        super("Division by zero");
    }
}
