package com.assemblers.app.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.assemblers.app.APIController.EmployeeInfo;
import com.assemblers.app.APIController.Report;
import com.assemblers.app.Models.Employee;
import com.assemblers.app.Models.EmployeePayInfo;

public class SearchUI {
    private JFrame frame;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel, optionsPanel, topPanel;
    private JButton backButton;
    private JPanel backgroundPanel;

    public SearchUI() {
        frame = new JFrame("Search Employee");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 500, 200);
        frame.setLocation(0, 0);

        // Create background panel with image
        backgroundPanel = new JPanel() {
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
        backgroundPanel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundPanel);

        // Shared style
        Font buttonFont = new Font("Monospaced", Font.PLAIN, 12);
        Color buttonBgColor = new Color(70, 130, 180); // Steel blue
        Color buttonFgColor = Color.BLACK;

        // Top panel for the Back button (left-aligned)
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false); // transparent background
        backgroundPanel.add(topPanel, BorderLayout.NORTH);

        backButton = createStyledButton("Back", buttonFont, buttonBgColor, buttonFgColor);
        backButton.setVisible(false);
        topPanel.add(backButton);

        // Search panel
        searchPanel = new JPanel(new FlowLayout());
        searchPanel.setOpaque(false);
        backgroundPanel.add(searchPanel, BorderLayout.CENTER);

        JLabel searchLabel = new JLabel("Search by ID, Name, or SSN:");
        searchLabel.setForeground(Color.WHITE); // Make text readable
        searchPanel.add(searchLabel);

        searchField = new JTextField(15);
        searchField.setFont(buttonFont);
        searchField.setBackground(new Color(255, 255, 255, 200)); // Slightly transparent white
        searchPanel.add(searchField);

        searchButton = createStyledButton("Search", buttonFont, buttonBgColor, buttonFgColor);
        searchPanel.add(searchButton);

        // Options panel
        optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setOpaque(false);
        backgroundPanel.add(optionsPanel, BorderLayout.SOUTH);
        optionsPanel.setVisible(false);

        // Actions
        searchButton.addActionListener(e -> handleSearch());
        backButton.addActionListener(e -> goBackToSearch());

        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Font font, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(140, 30));
        return button;
    }

    private void handleSearch() {
    String searchText = searchField.getText().trim();

    if (searchText.isEmpty()) {
        ImageIcon customIcon = new ImageIcon(getClass().getResource("/ERROR.jpeg"));
        JOptionPane.showMessageDialog(frame, "Please enter a search term.", "Warning", JOptionPane.WARNING_MESSAGE, customIcon);
        return;
    }

    try {
        if (isNumeric(searchText)) {
            Employee employee = EmployeeInfo.viewEmployeeInfoById(Integer.parseInt(searchText));
            if (employee != null) {
                showEmployeeInfo(employee);
            } else {
                ImageIcon customIcon = new ImageIcon(getClass().getResource("/404.jpeg"));
                JOptionPane.showMessageDialog(frame, "No employee found with ID: " + searchText, "Error", JOptionPane.ERROR_MESSAGE, customIcon);
            }
        } else if (searchText.contains(" ")) {
            String[] nameParts = searchText.trim().split("\\s+");
            if (nameParts.length == 2) {
                List<Employee> employees = EmployeeInfo.viewEmployeeInfoByFullName(nameParts[0], nameParts[1]);
                if (!employees.isEmpty()) {
                    showEmployeeSelectionDialog(employees);
                } else {
                    ImageIcon customIcon = new ImageIcon(getClass().getResource("/404.jpeg"));
                    JOptionPane.showMessageDialog(frame, "No employee found with name: " + searchText, "Error", JOptionPane.ERROR_MESSAGE, customIcon);
                }
            } else {
                ImageIcon customIcon = new ImageIcon(getClass().getResource("/ERROR.jpeg"));
                JOptionPane.showMessageDialog(frame, "Please enter both first and last name.", "Warning", JOptionPane.WARNING_MESSAGE, customIcon);
            }
        } else {
            List<Employee> employees = EmployeeInfo.viewEmployeeInfoName(searchText);
            if (!employees.isEmpty()) {
                showEmployeeSelectionDialog(employees);
            } else {
                ImageIcon customIcon = new ImageIcon(getClass().getResource("/404.jpeg"));
                JOptionPane.showMessageDialog(frame, "No employee found with name: " + searchText, "Error", JOptionPane.ERROR_MESSAGE, customIcon);
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        ImageIcon customIcon = new ImageIcon(getClass().getResource("/ERROR.jpeg"));
        JOptionPane.showMessageDialog(frame, "An error occurred while searching.", "Error", JOptionPane.ERROR_MESSAGE, customIcon);
    }
}

private void showWarning(String message) {
    ImageIcon icon = new ImageIcon(getClass().getResource("/ERROR.jpeg"));
    JOptionPane.showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE, icon);
}

private void showNotFound(String type, String value) {
    ImageIcon icon = new ImageIcon(getClass().getResource("/404.jpeg"));
    JOptionPane.showMessageDialog(frame, "No employee found with " + type + ": " + value, "Error", JOptionPane.ERROR_MESSAGE, icon);
}

private void showEmployeeSelectionDialog(List<Employee> employees) {
    String[] options = new String[employees.size()];
    for (int i = 0; i < employees.size(); i++) {
        Employee e = employees.get(i);
        options[i] = e.getEmpid() + " - " + e.getFname() + " " + e.getLname();
    }

    String selected = (String) JOptionPane.showInputDialog(
        frame,
        "Select an employee:",
        "Employee List",
        JOptionPane.PLAIN_MESSAGE,
        new ImageIcon(getClass().getResource("/logo.png")),
        options,
        options[0]
    );

    if (selected != null) {
        int selectedId = Integer.parseInt(selected.split(" - ")[0]);
        Employee selectedEmployee = null;
        for (Employee e : employees) {
            if (e.getEmpid() == selectedId) {
                selectedEmployee = e;
                break;
            }
        }

        if (selectedEmployee != null) {
            showEmployeeInfo(selectedEmployee);
        }
    }
}



    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showEmployeeInfo(Employee employee) {
        searchPanel.setVisible(false);
        backButton.setVisible(true);

        optionsPanel.removeAll();
        Dimension buttonSize = new Dimension(160, 40);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // View Employee Info Button
        JButton viewButton = createStyledButton("View Employee Info", new Font("Monospaced", Font.PLAIN, 12),
                new Color(70, 130, 180), Color.BLACK);
        viewButton.setPreferredSize(buttonSize);
        optionsPanel.add(viewButton, gbc);

        gbc.gridy = 1;
        optionsPanel.add(createSpacingLabel(), gbc);

        // View Report Button
        JButton reportButton = createStyledButton("View Report", new Font("Monospaced", Font.PLAIN, 12),
                new Color(70, 130, 180), Color.BLACK);
        reportButton.setPreferredSize(buttonSize);
        gbc.gridy = 2;
        optionsPanel.add(reportButton, gbc);

        gbc.gridy = 3;
        optionsPanel.add(createSpacingLabel(), gbc);

        optionsPanel.setVisible(true);
        frame.revalidate();
        frame.repaint();

        viewButton.addActionListener(e -> {
            String[] columnNames = {"ID", "Name", "SSN", "Job Title", "Email", "Salary"};
            Object[][] data = {{
                    employee.getEmpid(),
                    employee.getFname() + " " + employee.getLname(),
                    employee.getSsn(),
                    employee.getJob_title(),
                    employee.getEmail(),
                    employee.getSalary()
            }};
        
            JTable table = new JTable(data, columnNames);
            table.setEnabled(false);
            table.setRowHeight(25);
            table.setFont(new Font("Monospaced", Font.PLAIN, 12));
            table.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 12));
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(1000, 70));
        
            // Load and scale the custom icon
            ImageIcon customIcon = new ImageIcon(getClass().getResource("/logo.png"));
            Image img = customIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
        
            // Use showOptionDialog instead of showMessageDialog
            JOptionPane.showOptionDialog(
                    frame,
                    scrollPane,              // content
                    "Employee Info",          // title
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    scaledIcon,               // custom icon here
                    new Object[]{},           // no options (just OK)
                    null
            );
        });
        

        reportButton.addActionListener(e -> {
            List<EmployeePayInfo> reports = Report.getEmployeePayByEmpid(employee.getEmpid());
            if (reports == null || reports.isEmpty()) {
                ImageIcon warningIcon = new ImageIcon(getClass().getResource("404.jpge"));
                Image warningImg = warningIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledWarningIcon = new ImageIcon(warningImg);
        
                JOptionPane.showOptionDialog(
                    frame,
                    "No payroll report found for employee ID: " + employee.getEmpid(),
                    "Report Not Found",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    scaledWarningIcon,
                    new Object[]{},
                    null
                );
                return;
            }
        
            String[] columnNames = {
                "EmpID", "First Name", "Last Name", "Pay Date", "Earnings", 
                "Fed Tax", "Fed Med", "Fed SS", "State Tax", "401k", "Healthcare"
            };
        
            Object[][] data = new Object[reports.size()][columnNames.length];
            for (int i = 0; i < reports.size(); i++) {
                EmployeePayInfo r = reports.get(i);
                data[i][0] = r.getEmpid();
                data[i][1] = r.getFname();
                data[i][2] = r.getLname();
                data[i][3] = r.getPayDate();
                data[i][4] = r.getEarning();
                data[i][5] = r.getFed_tax();
                data[i][6] = r.getFed_med();
                data[i][7] = r.getFed_SS();
                data[i][8] = r.getState_tax();
                data[i][9] = r.getRetire_401K();
                data[i][10] = r.getHealth_care();
            }
        
            JTable reportTable = new JTable(data, columnNames);
            reportTable.setEnabled(false);
            reportTable.setRowHeight(25);
            reportTable.setFont(new Font("Monospaced", Font.PLAIN, 12));
            reportTable.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 12));
            JScrollPane reportScrollPane = new JScrollPane(reportTable);
            reportScrollPane.setPreferredSize(new Dimension(1000, 150));
        
            // Load and scale custom info icon
            ImageIcon customIcon = new ImageIcon(getClass().getResource("/logo.png"));
            Image img = customIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
        
            // Use showOptionDialog instead of showMessageDialog
            JOptionPane.showOptionDialog(
                frame,
                reportScrollPane,
                "Payroll Report",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                scaledIcon,
                new Object[]{},
                null
            );
        });
        
    }

    private void goBackToSearch() {
        searchPanel.setVisible(true);
        searchButton.setVisible(true);
        backButton.setVisible(false);
        optionsPanel.setVisible(false);
    }

    private JLabel createSpacingLabel() {
        JLabel label = new JLabel("");
        label.setPreferredSize(new Dimension(1, 10));
        return label;
    }
    /*public static void main(String[] args) {
        new SearchUI();
    }*/
}
