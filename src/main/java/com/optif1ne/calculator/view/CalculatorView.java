package com.optif1ne.calculator.view;

import com.optif1ne.calculator.controller.CalculatorController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Представление калькулятора на Swing.
 * Создаёт окно, дисплей и кнопки, а также предоставляет методы для обновления дисплея
 * и показа сообщений об ошибках.
 */

public class CalculatorView {
    private JFrame frame;
    private JLabel display;
    private List<JButton> buttons;
    private CalculatorController controller;

    public CalculatorView() {
        frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout(5, 5));

        display = new JLabel("0", SwingConstants.RIGHT);
        display.setFont(new Font("Monospaced", Font.BOLD, 32));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttons = new ArrayList<>();

        String[] labels = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0",".","=","+",
                "C"
        };

        for (String string : labels) {
            JButton btn = new JButton(string);
            btn.setFont(new Font("Arial", Font.PLAIN, 24));
            buttonPanel.add(btn);
            buttons.add(btn);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setUpKeyBindings(CalculatorController controller) {
        this.controller = controller;

        BiConsumer<KeyStroke, String> bindKeys = getKeyStrokeStringBiConsumer(controller);

        for (char i = '0'; i <= '9'; i++) {
            bindKeys.accept(KeyStroke.getKeyStroke(i), String.valueOf(i));
        }

        bindKeys.accept(KeyStroke.getKeyStroke('.'), ".");

        bindKeys.accept(KeyStroke.getKeyStroke('/'), "/");
        bindKeys.accept(KeyStroke.getKeyStroke('*'), "*");
        bindKeys.accept(KeyStroke.getKeyStroke('-'), "-");
        bindKeys.accept(KeyStroke.getKeyStroke('+'), "+");
        bindKeys.accept(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "=");
        bindKeys.accept(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "C");
    }

    private BiConsumer<KeyStroke, String> getKeyStrokeStringBiConsumer(CalculatorController controller) {
        JComponent root = (JComponent) frame.getContentPane();

        InputMap im = root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = root.getActionMap();

        return (keyStroke, name) -> {
            im.put(keyStroke, name);
            am.put(name, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.handleInput(name);
                }
            });
        };
    }

    public List<JButton> getAllButtons() {
        return buttons;
    }

    public void updateDisplay(String text) {
        display.setText(text);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}