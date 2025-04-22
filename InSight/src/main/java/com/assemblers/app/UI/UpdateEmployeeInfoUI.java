package com.assemblers.app.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import com.assemblers.app.APIController.EmployeeInfo;
import com.assemblers.app.Models.Employee;
import com.assemblers.app.APIController.UpdateEmployeeInfo;
import java.awt.*;
import java.awt.event.*;
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
        dialog.setLayout(new GridLayout(9, 2));

        JTextField fnameField = new JTextField(model.getValueAt(row, 1).toString());
        JTextField lnameField = new JTextField(model.getValueAt(row, 2).toString());
        JComboBox<String> jobTitleComboBox = new JComboBox<>(JOB_TITLES);
        jobTitleComboBox.setSelectedItem(model.getValueAt(row, 3).toString());
        JTextField emailField = new JTextField(model.getValueAt(row, 4).toString());
        JTextField salaryField = new JTextField(model.getValueAt(row, 5).toString());
        JTextField ssnField = new JTextField(model.getValueAt(row, 6).toString());

        dialog.add(new JLabel("First Name:")); dialog.add(fnameField);
        dialog.add(new JLabel("Last Name:")); dialog.add(lnameField);
        dialog.add(new JLabel("Job Title:")); dialog.add(jobTitleComboBox);
        dialog.add(new JLabel("Email:")); dialog.add(emailField);
        dialog.add(new JLabel("Salary:")); dialog.add(salaryField);
        dialog.add(new JLabel("SSN:")); dialog.add(ssnField);

        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        saveBtn.addActionListener(e -> {
            try {
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

                    JOptionPane.showMessageDialog(dialog, "Employee updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Update failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Salary must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.add(cancelBtn);
        dialog.add(saveBtn);
        dialog.setVisible(true);
    }
}
