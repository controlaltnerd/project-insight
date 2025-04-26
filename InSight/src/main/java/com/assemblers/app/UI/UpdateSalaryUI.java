package com.assemblers.app.UI;

import javax.swing.*;
import java.awt.*;
import com.assemblers.app.APIController.UpdateRangeSalary;

public class UpdateSalaryUI extends JFrame {
    // Input fields for salary range and percentage
    JTextField minSalaryField, maxSalaryField, percentageField;
    JButton applyButton;

    // Constructor to set up the UI
    public UpdateSalaryUI() {
        // Set the title and size of the window
        setTitle("Salary Range Update (Admin Only)");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create background panel with image
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Set the Starry Night background image
                    Image backgroundImage = new ImageIcon(getClass().getResource("/background.png")).getImage();
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundPanel.setLayout(null); // Set layout to null for manual positioning
        setContentPane(backgroundPanel);

        // Shared style for buttons and text fields
        Font buttonFont = new Font("Monospaced", Font.PLAIN, 12);
        Color buttonBgColor = new Color(70, 130, 180); // Steel blue
        Color buttonFgColor = Color.BLACK;

        // Min Salary Label and TextField
        JLabel minLabel = new JLabel("Min Salary:");
        minLabel.setBounds(30, 30, 100, 25);
        minLabel.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Matching font
        minLabel.setForeground(Color.WHITE);  // White text for better contrast
        backgroundPanel.add(minLabel);
        
        minSalaryField = createStyledTextField();
        minSalaryField.setBounds(150, 30, 180, 25);
        backgroundPanel.add(minSalaryField);

        // Max Salary Label and TextField
        JLabel maxLabel = new JLabel("Max Salary:");
        maxLabel.setBounds(30, 70, 100, 25);
        maxLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        maxLabel.setForeground(Color.WHITE);  // White text
        backgroundPanel.add(maxLabel);

        maxSalaryField = createStyledTextField();
        maxSalaryField.setBounds(150, 70, 180, 25);
        backgroundPanel.add(maxSalaryField);

        // Percentage Increase Label and TextField
        JLabel percentLabel = new JLabel("Increase %:");
        percentLabel.setBounds(30, 110, 100, 25);
        percentLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        percentLabel.setForeground(Color.WHITE);  // White text
        backgroundPanel.add(percentLabel);

        percentageField = createStyledTextField();
        percentageField.setBounds(150, 110, 180, 25);
        backgroundPanel.add(percentageField);

        // Apply button
        applyButton = createStyledButton("Apply Increase");
        applyButton.setBounds(120, 160, 150, 30);
        backgroundPanel.add(applyButton);

        // Add action listener to button
        applyButton.addActionListener(e -> applySalaryUpdate());

        setVisible(true);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textField.setBackground(new Color(255, 255, 255, 200)); // Slightly transparent white
        textField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1)); // Dark border
        return textField;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Monospaced", Font.PLAIN, 12));
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setForeground(Color.BLACK); // Dark text
        button.setFocusPainted(false); // Remove focus border
        button.setPreferredSize(new Dimension(140, 30)); // Matching button size
        return button;
    }

    private void applySalaryUpdate() {
        try {
            double min = Double.parseDouble(minSalaryField.getText());
            double max = Double.parseDouble(maxSalaryField.getText());
            double percent = Double.parseDouble(percentageField.getText());
    
            // Perform the salary update
            int updated = UpdateRangeSalary.updateRangeSalary(min, max, percent);
    
            // Load custom icon from resources
            if (updated == 0) {
                ImageIcon icon = new ImageIcon(getClass().getResource("/404.jpeg"));
                JOptionPane.showMessageDialog(
                    this,
                    "No employee was found in this salary range",
                    "Fail",
                    JOptionPane.INFORMATION_MESSAGE,
                    icon
                );
            } else {
                ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png"));
                JOptionPane.showMessageDialog(
                    this,
                    updated + " employee(s) salary updated by " + percent + "%",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE,
                    icon
                );
            }
        } catch (NumberFormatException ex) {
            ImageIcon errorIcon = new ImageIcon(getClass().getResource("/ERROR.jpeg"));
            JOptionPane.showMessageDialog(
                this,
                "Invalid Inputs",
                "Input Error",
                JOptionPane.ERROR_MESSAGE,
                errorIcon
            );
        } catch (Exception ex) {
            ImageIcon errorIcon = new ImageIcon(getClass().getResource("/ERROR.jpeg"));
            JOptionPane.showMessageDialog(
                this,
                "Error: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE,
                errorIcon
            );
        }
    }

    /*public static void main(String[] args) {
        new UpdateSalaryUI();
    }*/
}
