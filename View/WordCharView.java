package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class WordCharView extends JFrame {
    private JTextField textField;
    private JButton[] button = new JButton[3];
    private JButton countButton, TextColor, PadColor;
    private JLabel wordCountLabel, characterCountLabel;
    private JPanel panel, buttonPanel, counterPanel;

    Font myFont = new Font("Times New Roman", Font.BOLD, 16);
    Font myFont1 = new Font("Times New Roman", Font.BOLD, 20);

    public WordCharView() {
        setTitle("Character Counter!");
        setSize(600, 500);
        getContentPane().setBackground((new Color(255, 223, 206)));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        textField = new JTextField();
        textField.setFont(myFont);
        textField.setBounds(15, 100, 550, 250);
        textField.setBorder(new EmptyBorder(10, 10, 10, 10));

        countButton = new JButton("Count");
        TextColor = new JButton("Text Color");
        PadColor = new JButton("Pad Color");

        button[0] = countButton;
        button[1] = TextColor;
        button[2] = PadColor;

        for (int i = 0; i < 3; i++) {
            button[i].setFont(myFont1);
        }

        wordCountLabel = new JLabel("Word Count: 0");
        wordCountLabel.setFont(myFont);
        wordCountLabel.setBorder(new EmptyBorder(0, 30, 20, 0));
        characterCountLabel = new JLabel("Character count: 0");
        characterCountLabel.setFont(myFont);
        characterCountLabel.setBorder(new EmptyBorder(0, 30, 20, 0));

        // Layout manager for panel
        panel = new JPanel();
        panel.setBounds(20, 50, 200, 150);
        panel.setLayout(new GridLayout(4, 1, 2, 2)); // rows, cols, hgap, vgap

        counterPanel = new JPanel();
        counterPanel.setLayout(new BoxLayout(counterPanel, BoxLayout.PAGE_AXIS));
        counterPanel.setBounds(15, 30, 550, 70);
        counterPanel.add(wordCountLabel);
        counterPanel.add(characterCountLabel);

        // Button panel for horizontal alignment
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBounds(15, 350, 550, 100);
        buttonPanel.add(countButton);
        buttonPanel.add(PadColor);
        buttonPanel.add(TextColor);

        add(textField);
        add(counterPanel);
        add(buttonPanel);
        add(panel);
        setVisible(true);

        // Add action listeners for color changes
        TextColor.addActionListener(e -> changeTextColor());
        PadColor.addActionListener(e -> changePadColor());
    }

    private void changeTextColor() {
        Color newColor = JColorChooser.showDialog(null, "Choose Text Color", textField.getForeground());
        if (newColor != null) {
            textField.setForeground(newColor);
        }
    }

    private void changePadColor() {
        Color newColor = JColorChooser.showDialog(null, "Choose Background Color", textField.getBackground());
        if (newColor != null) {
            textField.setBackground(newColor);
        }
    }

    public String getText() {
        return textField.getText();
    }

    public void setWordCount(int wordCounter) {
        wordCountLabel.setText("Word count: " + wordCounter);
    }

    public void setCharCount(int charCounter) {
        characterCountLabel.setText("Character count: " + charCounter);
    }

    public void addCountButtonListener(ActionListener listener) {
        countButton.addActionListener(listener);
    }

    public void printWordCharDetails(int wordCount, int charCount) {
        System.out.println("Characters: " + charCount);
        System.out.println("Words: " + wordCount);
    }
}
