package com.optif1ne.calculator.model;

import java.math.BigDecimal;

/**
 * Класс модели калькулятора, реализует логику ввода цифр и операций.
 * Хранит текущий ввод в виде строки и «левый» операнд как BigDecimal.
 */

public class Calculator {
    private StringBuilder inputBuffer = new StringBuilder("0");
    private BigDecimal storedValue = BigDecimal.ZERO;
    private Operation operation;
    private boolean isNewInput = true;

    public void enterDigit(char digit) {
        // Если ввод начался заново — очищаем буфер перед добавлением новой цифры
        if (isNewInput) {
            inputBuffer.setLength(0);
            isNewInput = false;
        }
        // Убираем ведущий ноль, чтобы не получить "05"
        if (inputBuffer.toString().equals("0")) {
            inputBuffer.setLength(0);
        }
        if (inputBuffer.length() < 12) {
            inputBuffer.append(digit);
        }
    }

    public void enterDecimalPoint() {
        if (inputBuffer.indexOf(".") > -1) return;
        if (isNewInput) {
            inputBuffer.setLength(0);
            inputBuffer.append("0.");
            isNewInput = false;
            return;
        }
        inputBuffer.append(".");
    }

    private BigDecimal parseInputBuffer() {
        try {
            return new BigDecimal(inputBuffer.toString());
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal calculate() {
        BigDecimal current = parseInputBuffer();
        BigDecimal result;

        switch (operation) {
            case ADD:
                result = storedValue.add(current);
                break;
            case SUBTRACT:
                result = storedValue.subtract(current);
                break;
            case MULTIPLY:
                result = storedValue.multiply(current);
                break;
            case DIVIDE:
                if (current.compareTo(BigDecimal.ZERO) == 0) {
                    throw new DivideByZeroException();
                }
                result = storedValue.divide(current);
                break;

            default: result = current;
        }

        storedValue = result;
        operation = null;
        inputBuffer.setLength(0);
        inputBuffer.append(result.stripTrailingZeros().toPlainString());
        isNewInput = true;

        return result;
    }

    public void setOperation(Operation op) {
        if (operation != null) {
            calculate();
        } else {
            storedValue = parseInputBuffer();
        }
        operation = op;
        isNewInput = true;
    }

    public String getDisplayString() {
        return inputBuffer.length() == 0
                ? "0"
                : inputBuffer.toString();
    }

    public void clear() {
        inputBuffer.setLength(0);
        inputBuffer.append("0");
        storedValue = BigDecimal.ZERO;
        operation = null;
        isNewInput = true;
    }
}