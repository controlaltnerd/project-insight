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

            // Use the UpdateRangeSalary class to handle the database operation
            int updated = UpdateRangeSalary.updateRangeSalary(min, max, percent);

            
            JOptionPane.showMessageDialog(this, updated + " employee(s) salary updated by " + percent + "%.");

        } catch (NumberFormatException ex) {
            // Show error dialog if input values are not valid numbers
            JOptionPane.showMessageDialog(this, "Error: Please enter valid numbers for all fields.");
        } catch (Exception ex) {
            // Show error dialog if something else goes wrong
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}



