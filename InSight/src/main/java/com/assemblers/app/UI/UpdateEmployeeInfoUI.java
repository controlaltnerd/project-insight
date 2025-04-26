package com.assemblers.app.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import com.assemblers.app.APIController.EmployeeInfo;
import com.assemblers.app.Models.Employee;
import com.assemblers.app.APIController.UpdateEmployeeInfo;
import java.awt.*;
import java.util.List;

public class UpdateEmployeeInfoUI {
    public UpdateEmployeeInfoUI() {
        JFrame frame = new JFrame("Admin Panel - Employee List");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLayout(new BorderLayout());

        List<Employee> employees = EmployeeInfo.viewAllEmployee();
        String[] columnNames = {"ID", "First Name", "Last Name", "Job Title", "Email", "Salary", "SSN", "Edit"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 7;
            }
        };

        for (Employee emp : employees) {
            model.addRow(new Object[]{
                emp.getEmpid(),
                emp.getFname(),
                emp.getLname(),
                emp.getJob_title(),
                emp.getEmail(),
                emp.getSalary(),
                emp.getSsn(),
                "Edit"
            });
        }

        JTable table = new JTable(model);
        table.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), model, table));

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setText("Edit");
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private DefaultTableModel model;
    private int row;

    private static final String[] JOB_TITLES = {
        "Software Manager", "Software Architect", "Software Engineer", "Software Developer",
        "Marketing Manager", "Marketing Associate", "Marketing Assistant",
        "HR Manager", "HR Analyst",
        "Chief Executive Officer", "Chief Financial Officer", "Chief Information Officer"
    };

    public ButtonEditor(JCheckBox checkBox, DefaultTableModel model, JTable table) {
        super(checkBox);
        this.model = model;
        this.table = table;
        button = new JButton("Edit");

        button.addActionListener(e -> openEditDialog(row));
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        return button;
    }

    private void openEditDialog(int row) {
        JDialog dialog = new JDialog((Frame) null, "Edit Employee", true);
        dialog.setSize(400, 400);
    
        // Create a panel with background image
        JPanel backgroundPanel = new JPanel() {
            @Override
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
        backgroundPanel.setLayout(new GridLayout(9, 2, 10, 10)); // with padding between components
        backgroundPanel.setOpaque(false);
    
        // Create fields
        JTextField fnameField = createStyledTextField(model.getValueAt(row, 1).toString());
        JTextField lnameField = createStyledTextField(model.getValueAt(row, 2).toString());
        JComboBox<String> jobTitleComboBox = new JComboBox<>(JOB_TITLES);
        jobTitleComboBox.setSelectedItem(model.getValueAt(row, 3).toString());
        styleComboBox(jobTitleComboBox);
        JTextField emailField = createStyledTextField(model.getValueAt(row, 4).toString());
        JTextField salaryField = createStyledTextField(model.getValueAt(row, 5).toString());
        JTextField ssnField = createStyledTextField(model.getValueAt(row, 6).toString());
    
        // Add labels and fields
        backgroundPanel.add(createStyledLabel("First Name:")); backgroundPanel.add(fnameField);
        backgroundPanel.add(createStyledLabel("Last Name:")); backgroundPanel.add(lnameField);
        backgroundPanel.add(createStyledLabel("Job Title:")); backgroundPanel.add(jobTitleComboBox);
        backgroundPanel.add(createStyledLabel("Email:")); backgroundPanel.add(emailField);
        backgroundPanel.add(createStyledLabel("Salary:")); backgroundPanel.add(salaryField);
        backgroundPanel.add(createStyledLabel("SSN:")); backgroundPanel.add(ssnField);
    
        // Save and Cancel buttons
        JButton saveBtn = createStyledButton("Save");
        JButton cancelBtn = createStyledButton("Cancel");
    
        // Save action
        saveBtn.addActionListener(e -> {
            try {
                // First: Check for empty fields
                if (fnameField.getText().trim().isEmpty() ||
                    lnameField.getText().trim().isEmpty() ||
                    emailField.getText().trim().isEmpty() ||
                    salaryField.getText().trim().isEmpty() ||
                    ssnField.getText().trim().isEmpty()) {
                    ImageIcon customIcon = new ImageIcon(getClass().getResource("/ERROR.jpeg"));
                    JOptionPane.showMessageDialog(dialog, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE,customIcon);
                    return; // stop the save operation
                }
        
                int empId = Integer.parseInt(model.getValueAt(row, 0).toString());
                double salary = Double.parseDouble(salaryField.getText());
                String ssn = ssnField.getText();
        
                if (salary < 0) {
                    throw new IllegalArgumentException("Salary cannot be negative.");
                }
        
                if (!ssn.matches("\\d{3}-\\d{2}-\\d{4}")) {
                    throw new IllegalArgumentException("SSN must be in the format XXX-XX-XXXX.");
                }
        
                Employee updated = new Employee(
                    empId,
                    fnameField.getText(),
                    lnameField.getText(),
                    (String) jobTitleComboBox.getSelectedItem(),
                    emailField.getText(),
                    salary,
                    ssn
                );
        
                int rowUpdates = UpdateEmployeeInfo.updateInfo(updated);
        
                if (rowUpdates > 0) {
                    model.setValueAt(updated.getFname(), row, 1);
                    model.setValueAt(updated.getLname(), row, 2);
                    model.setValueAt(updated.getJob_title(), row, 3);
                    model.setValueAt(updated.getEmail(), row, 4);
                    model.setValueAt(updated.getSalary(), row, 5);
                    model.setValueAt(updated.getSsn(), row, 6);
                    
                    ImageIcon customIcon = new ImageIcon(getClass().getResource("/logo.png"));
                    JOptionPane.showMessageDialog(dialog, "Employee Info Saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE, customIcon);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Update failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                ImageIcon customIcon = new ImageIcon(getClass().getResource("/ERROR.jpeg"));
                JOptionPane.showMessageDialog(dialog, "Salary must be a valid number.", "ERROR", JOptionPane.ERROR_MESSAGE, customIcon);
            } catch (IllegalArgumentException ex) {
                ImageIcon customIcon = new ImageIcon(getClass().getResource("/ERROR.jpeg"));
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE, customIcon);
            }
        });
        
    
        // Cancel action
        cancelBtn.addActionListener(e -> dialog.dispose());
    
        backgroundPanel.add(cancelBtn);
        backgroundPanel.add(saveBtn);
    
        dialog.setContentPane(backgroundPanel);
        dialog.setLocationRelativeTo(null); // Center dialog
        dialog.setVisible(true);
    }
    
    // Helper to create styled text fields
    private JTextField createStyledTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textField.setBackground(new Color(255, 255, 255, 200)); // Semi-transparent white
        textField.setForeground(Color.BLACK);
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return textField;
    }
    
    // Helper to create styled labels
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Monospaced", Font.BOLD, 12));
        label.setForeground(Color.WHITE);
        return label;
    }
    
    // Helper to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Monospaced", Font.PLAIN, 12));
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
    }
    
    // Helper to style combo box
    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Monospaced", Font.PLAIN, 12));
        comboBox.setBackground(new Color(255, 255, 255, 200));
        comboBox.setForeground(Color.BLACK);
    }
    
    /*public static void main(String[] args) {
        new UpdateEmployeeInfoUI();
    }*/
    
}
