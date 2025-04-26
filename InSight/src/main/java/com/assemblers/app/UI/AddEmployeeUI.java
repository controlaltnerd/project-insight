package com.assemblers.app.UI;

import com.assemblers.app.APIController.AddEmployee;
import com.assemblers.app.Models.Employee;

import javax.swing.*;
import java.awt.*;

public class AddEmployeeUI {
    public AddEmployeeUI() {
        JFrame frame = new JFrame("Add Employee");
        frame.setSize(450, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Background panel
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = new ImageIcon(getClass().getResource("/background.png")).getImage();
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        // Shared styles
        Font labelFont = new Font("Monospaced", Font.PLAIN, 14);
        Font fieldFont = new Font("Monospaced", Font.PLAIN, 12);
        Color fieldBackground = new Color(255, 255, 255, 200);
        Color buttonBackground = new Color(70, 130, 180);
        Color buttonTextColor = Color.BLACK;

        // Create form fields
        JTextField empIdField = createStyledTextField(fieldFont, fieldBackground);
        JTextField fnameField = createStyledTextField(fieldFont, fieldBackground);
        JTextField lnameField = createStyledTextField(fieldFont, fieldBackground);
        JTextField emailField = createStyledTextField(fieldFont, fieldBackground);
        JTextField salaryField = createStyledTextField(fieldFont, fieldBackground);
        JTextField ssnField = createStyledTextField(fieldFont, fieldBackground);

        String[] jobTitles = {
            "Software Manager", "Software Architect", "Software Engineer", "Software Developer",
            "Marketing Manager", "Marketing Associate", "Marketing Assistant",
            "HR Manager", "HR Analyst",
            "Chief Executive Officer", "Chief Financial Officer", "Chief Information Officer"
        };
        JComboBox<String> jobTitleComboBox = new JComboBox<>(jobTitles);
        jobTitleComboBox.setFont(fieldFont);
        jobTitleComboBox.setBackground(fieldBackground);
        jobTitleComboBox.setBounds(160, 330, 200, 25);

        // Labels and positioning
        addLabelAndField(backgroundPanel, "Employee ID:", empIdField, 30, labelFont);
        addLabelAndField(backgroundPanel, "First Name:", fnameField, 80, labelFont);
        addLabelAndField(backgroundPanel, "Last Name:", lnameField, 130, labelFont);
        addLabelAndField(backgroundPanel, "Email:", emailField, 180, labelFont);
        addLabelAndField(backgroundPanel, "Salary:", salaryField, 230, labelFont);
        addLabelAndField(backgroundPanel, "SSN:", ssnField, 280, labelFont);

        JLabel jobTitleLabel = new JLabel("Job Title:");
        jobTitleLabel.setFont(labelFont);
        jobTitleLabel.setForeground(Color.WHITE);
        jobTitleLabel.setBounds(40, 330, 120, 25);
        backgroundPanel.add(jobTitleLabel);
        backgroundPanel.add(jobTitleComboBox);

        // Buttons
        JButton saveBtn = createStyledButton("Save", buttonBackground, buttonTextColor, fieldFont);
        saveBtn.setBounds(100, 400, 100, 30);
        backgroundPanel.add(saveBtn);

        JButton cancelBtn = createStyledButton("Cancel", buttonBackground, buttonTextColor, fieldFont);
        cancelBtn.setBounds(230, 400, 100, 30);
        backgroundPanel.add(cancelBtn);

        // Button actions
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
                    ImageIcon customIcon = new ImageIcon(getClass().getResource("/logo.png"));
                    JOptionPane.showMessageDialog(frame, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE, customIcon);
                } else {
                    ImageIcon customIcon = new ImageIcon(getClass().getResource("/ERROR.jpeg"));
                    JOptionPane.showMessageDialog(frame, "Employee ID exists", "Fail", JOptionPane.ERROR_MESSAGE, customIcon);
                }                
            } catch (Exception ex) {
                ex.printStackTrace();
                ImageIcon customIcon = new ImageIcon(getClass().getResource("/ERROR.jpeg"));
                JOptionPane.showMessageDialog(frame, "Invalid inputs. Please check your entries", "Fail", JOptionPane.INFORMATION_MESSAGE, customIcon);
            }
        });

        cancelBtn.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    private JTextField createStyledTextField(Font font, Color backgroundColor) {
        JTextField field = new JTextField();
        field.setFont(font);
        field.setBackground(backgroundColor);
        field.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        return field;
    }

    private JButton createStyledButton(String text, Color backgroundColor, Color textColor, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        return button;
    }

    private void addLabelAndField(JPanel panel, String labelText, JTextField textField, int yPosition, Font labelFont) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(Color.WHITE);
        label.setBounds(40, yPosition, 120, 25);
        textField.setBounds(160, yPosition, 200, 25);
        panel.add(label);
        panel.add(textField);
    }
    /*public static void main(String[] args) {
        new AddEmployeeUI();
    }*/
}
