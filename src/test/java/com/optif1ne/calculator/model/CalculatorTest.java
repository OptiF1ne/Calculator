package com.optif1ne.calculator.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void additionTest() {
        calculator.enterDigit('5');
        calculator.enterDigit('1');
        calculator.setOperation(Operation.ADD);
        calculator.enterDigit('2');
        Assertions.assertEquals(new BigDecimal("53"), calculator.calculate());
    }

    @Test
    void subtractionTest() {
        calculator.enterDigit('1');
        calculator.enterDigit('0');
        calculator.setOperation(Operation.SUBTRACT);
        calculator.enterDigit('5');
        Assertions.assertEquals(new BigDecimal("5"), calculator.calculate());
    }

    @Test
    void multiplicationTest() {
        calculator.enterDigit('4');
        calculator.setOperation(Operation.MULTIPLY);
        calculator.enterDigit('3');
        Assertions.assertEquals(new BigDecimal("12"), calculator.calculate());
    }

    @Test
    void divisionTest() {
        calculator.enterDigit('6');
        calculator.setOperation(Operation.DIVIDE);
        calculator.enterDigit('3');
        Assertions.assertEquals(new BigDecimal("2"), calculator.calculate());
    }

    @Test
    void divisionByZeroTest() {
        calculator.enterDigit('5');
        calculator.setOperation(Operation.DIVIDE);
        calculator.enterDigit('0');
        Assertions.assertThrows(DivideByZeroException.class, () -> calculator.calculate());
    }
}