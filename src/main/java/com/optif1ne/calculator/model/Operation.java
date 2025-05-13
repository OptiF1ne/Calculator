package com.optif1ne.calculator.model;

/**
 * Перечисление арифметических операций
 */

public enum Operation {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Operation fromSymbol(String sym) {
        for (Operation operation : values()) {
            if (operation.symbol.equals(sym)) return operation;
        }
        throw new IllegalArgumentException("Неизвестный оператор " + sym);
    }
}
