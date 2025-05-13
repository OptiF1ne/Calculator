package com.optif1ne.calculator.controller;

import com.optif1ne.calculator.model.Calculator;
import com.optif1ne.calculator.model.DivideByZeroException;
import com.optif1ne.calculator.model.Operation;
import com.optif1ne.calculator.view.CalculatorView;

import javax.swing.*;
import java.util.List;

/**
 * Контроллер.
 * Навешивает слушатели на кнопки и обновляет дисплей после каждого действия.
 */

public class CalculatorController {
    private final Calculator model;
    private final CalculatorView view;


    public CalculatorController(Calculator model, CalculatorView view) {
        this.model = model;
        this.view = view;
        attachListeners();
    }

    private void attachListeners() {
        List<JButton> buttons = view.getAllButtons();
        for (JButton btn : buttons) {
            btn.addActionListener(e -> handleButtonClick(btn.getText()));
        }
    }

    private void handleButtonClick(String label) {
        switch (label) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                model.enterDigit(label.charAt(0));
                break;
            case ".":
                model.enterDecimalPoint();
                break;

            case "+": case "-": case "*": case "/":
                model.setOperation(Operation.fromSymbol(label));
                break;

            case "=":
                try {
                    model.calculate();
                } catch (DivideByZeroException e) {
                    view.showError("Деление на ноль!");
                } catch (NullPointerException ex) {
                    view.showError("Нельзя!");
                }
                break;

            case "C":
                model.clear();
                break;

            default:
                return;
        }

        view.updateDisplay(model.getDisplayString());
    }
}
