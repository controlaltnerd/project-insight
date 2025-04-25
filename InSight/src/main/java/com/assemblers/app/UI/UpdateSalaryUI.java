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
        setTitle("Salary Range Update (Admin Only)");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
     
        // Min Salary Label and TextField
        JLabel minLabel = new JLabel("Min Salary:");
        minLabel.setBounds(30, 30, 100, 25);
        add(minLabel);
        minSalaryField = new JTextField();
        minSalaryField.setBounds(150, 30, 180, 25);
        add(minSalaryField);

        // Max Salary Label and TextField
        JLabel maxLabel = new JLabel("Max Salary:");
        maxLabel.setBounds(30, 70, 100, 25);
        add(maxLabel);
        maxSalaryField = new JTextField();
        maxSalaryField.setBounds(150, 70, 180, 25);
        add(maxSalaryField);

        // Percentage Increase Label and TextField
        JLabel percentLabel = new JLabel("Increase %:");
        percentLabel.setBounds(30, 110, 100, 25);
        add(percentLabel);
        percentageField = new JTextField();
        percentageField.setBounds(150, 110, 180, 25);
        add(percentageField);

        
        // Apply button
        applyButton = new JButton("Apply Increase");
        applyButton.setBounds(120, 160, 150, 30);
        add(applyButton);

        // Add action listener to button
        applyButton.addActionListener(e -> applySalaryUpdate());
        setVisible(true);
    }

    
    private void applySalaryUpdate() {
        try {
            double min = Double.parseDouble(minSalaryField.getText());
            double max = Double.parseDouble(maxSalaryField.getText());
            double percent = Double.parseDouble(percentageField.getText());
    
            // Perform the salary update
            int updated = UpdateRangeSalary.updateRangeSalary(min, max, percent);
    
            // Load custom icon from resources
            ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png")); // replace with your actual image name
            JOptionPane.showMessageDialog(
                this,
                updated + " employee(s) salary updated by " + percent + "%.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE,
                icon
            );
        } catch (NumberFormatException ex) {
            ImageIcon errorIcon = new ImageIcon(getClass().getResource("/error.png")); // replace with your error image
            JOptionPane.showMessageDialog(
                this,
                "Input only numbers, YOUR STOOPID ",
                "Input Error",
                JOptionPane.ERROR_MESSAGE,
                errorIcon
            );
        } catch (Exception ex) {
            ImageIcon errorIcon = new ImageIcon(getClass().getResource("/error.png")); // reuse error image
            JOptionPane.showMessageDialog(
                this,
                "Error: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE,
                errorIcon
            );
        }
    }
    
}



