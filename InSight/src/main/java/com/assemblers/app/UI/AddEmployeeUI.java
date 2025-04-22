package com.assemblers.app.UI;

import com.assemblers.app.APIController.AddEmployee;
import com.assemblers.app.Models.Employee;

import javax.swing.*;
import java.awt.*;

public class AddEmployeeUI {
    public AddEmployeeUI() {
        JFrame frame = new JFrame("Add Employee");
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(9, 2, 10, 10));

        // Form fields
        JTextField empIdField = new JTextField();
        JTextField fnameField = new JTextField();
        JTextField lnameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField salaryField = new JTextField();
        JTextField ssnField = new JTextField();

        String[] jobTitles = {
            "Software Manager", "Software Architect", "Software Engineer", "Software Developer",
            "Marketing Manager", "Marketing Associate", "Marketing Assistant",
            "HR Manager", "HR Analyst",
            "Chief Executive Officer", "Chief Financial Officer", "Chief Information Officer"
        };
        JComboBox<String> jobTitleComboBox = new JComboBox<>(jobTitles);

        // Buttons
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        // Add components to the frame
        frame.add(new JLabel("Employee ID:"));
        frame.add(empIdField);
        frame.add(new JLabel("First Name:"));
        frame.add(fnameField);
        frame.add(new JLabel("Last Name:"));
        frame.add(lnameField);
        frame.add(new JLabel("Email:"));
        frame.add(emailField);
        frame.add(new JLabel("Salary:"));
        frame.add(salaryField);
        frame.add(new JLabel("SSN:"));
        frame.add(ssnField);
        frame.add(new JLabel("Job Title:"));
        frame.add(jobTitleComboBox);
        frame.add(saveBtn);
        frame.add(cancelBtn);

        // Save button action
        saveBtn.addActionListener(e -> {
            try {
                int empId = Integer.parseInt(empIdField.getText().trim());
                String fname = fnameField.getText().trim();
                String lname = lnameField.getText().trim();
                String email = emailField.getText().trim();
                double salary = Double.parseDouble(salaryField.getText().trim());
                String ssn = ssnField.getText().trim();
                String jobTitle = (String) jobTitleComboBox.getSelectedItem();

                Employee newEmp = new Employee(empId, fname, lname, jobTitle, email, salary, ssn);

                int result = AddEmployee.addNewEmployee(newEmp);
                if (result == 1) {
                    JOptionPane.showMessageDialog(frame, "Employee added successfully!");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to add employee.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Invalid input. Please check your entries.");
            }
        });

        // Cancel button action
        cancelBtn.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }
}
