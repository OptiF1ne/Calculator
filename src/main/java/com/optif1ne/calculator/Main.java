package com.optif1ne.calculator;

import com.optif1ne.calculator.controller.CalculatorController;
import com.optif1ne.calculator.model.Calculator;
import com.optif1ne.calculator.view.CalculatorView;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        CalculatorView calculatorView = new CalculatorView();
        new CalculatorController(calculator, calculatorView);
    }
}