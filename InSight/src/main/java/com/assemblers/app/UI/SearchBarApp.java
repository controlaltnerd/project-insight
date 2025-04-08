package com.assemblers.app.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.assemblers.app.APIController.EmployeeInfo;
import com.assemblers.app.APIController.Report;
import com.assemblers.app.Models.Employee;
import com.assemblers.app.Models.EmployeePayInfo;

public class SearchBarApp {
    private JFrame frame;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel, optionsPanel, topPanel;
    private JButton backButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                SearchBarApp window = new SearchBarApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SearchBarApp() {
        frame = new JFrame("Search Employee");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 400, 300);
        frame.getContentPane().setLayout(new BorderLayout());

        // Top panel for the Back button (left-aligned)
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        // Back button (hidden initially)
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30)); // Same size as other buttons
        backButton.setVisible(false);
        topPanel.add(backButton);

        // Search panel (contains Employee ID label, search field, and search button)
        searchPanel = new JPanel(new FlowLayout());
        frame.getContentPane().add(searchPanel, BorderLayout.CENTER);

        JLabel searchLabel = new JLabel("Search by ID, Name, or SSN:"); // Label for search bar
        searchPanel.add(searchLabel);

        searchField = new JTextField(15);
        searchPanel.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 30)); // Match other buttons
        searchPanel.add(searchButton);

        // Options panel (initially hidden)
        optionsPanel = new JPanel(new FlowLayout());
        frame.getContentPane().add(optionsPanel, BorderLayout.SOUTH);
        optionsPanel.setVisible(false);

        // Search button action
        searchButton.addActionListener(e -> handleSearch());

        // Back button action
        backButton.addActionListener(e -> goBackToSearch());
    }

    private void handleSearch() {
        String searchText = searchField.getText().trim();

        // Check if the search text is empty
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a search term.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Try searching by Employee ID
            if (isNumeric(searchText)) {
                System.out.println("Searching by Employee ID: " + searchText);  // Debugging line
                Employee employee = EmployeeInfo.viewEmployeeInfoById(Integer.parseInt(searchText));  // Corrected DAO

                if (employee != null) {
                    showEmployeeInfo(employee);
                } else {
                    JOptionPane.showMessageDialog(frame, "No employee found with ID: " + searchText, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } 
            // Try searching by Name (First and Last Name)
            else if (searchText.contains(" ")) {
                String[] nameParts = searchText.split(" ");
                if (nameParts.length == 2) {
                    System.out.println("Searching by Name: " + nameParts[0] + " " + nameParts[1]);  // Debugging line
                    Employee employee = EmployeeInfo.viewEmployeeInfoByName(nameParts[0], nameParts[1]);  // Corrected DAO

                    if (employee != null) {
                        showEmployeeInfo(employee);
                    } else {
                        JOptionPane.showMessageDialog(frame, "No employee found with name: " + searchText, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter both first and last name.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } 
            // Try searching by SSN
            else {
                System.out.println("Searching by SSN: " + searchText);  // Debugging line
                Employee employee = EmployeeInfo.viewEmployeeInfoBySsn(searchText);  // Corrected DAO

                if (employee != null) {
                    showEmployeeInfo(employee);
                } else {
                    JOptionPane.showMessageDialog(frame, "No employee found with SSN: " + searchText, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Print exception details for debugging
            JOptionPane.showMessageDialog(frame, "An error occurred while searching.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);  // Try to parse as an integer (for empid)
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showEmployeeInfo(Employee employee) {
    searchPanel.setVisible(false);  // Hide the search bar
    backButton.setVisible(true);    // Show the back button

    optionsPanel.removeAll();      // Clear any previous options

    Dimension buttonSize = new Dimension(150, 40); // Set uniform button size

    // View Employee Info button
    JButton viewButton = new JButton("View Employee Info");
    viewButton.setPreferredSize(buttonSize);
    optionsPanel.add(viewButton);

    // View Report button
    JButton reportButton = new JButton("View Report");
    reportButton.setPreferredSize(buttonSize);
    optionsPanel.add(reportButton);

    optionsPanel.setVisible(true);   // Show the options panel
    frame.revalidate();
    frame.repaint();

    // View Employee Info button action
    viewButton.addActionListener(e -> {
        String[] columnNames = {"ID", "Name", "SSN", "Job Title", "Email", "Salary"};
        Object[][] data = {
            {
                employee.getEmpid(),
                employee.getFname() + " " + employee.getLname(),
                employee.getSsn(),
                employee.getJob_title(),
                employee.getEmail(),
                employee.getSalary()
            }
        };

        JTable table = new JTable(data, columnNames);
        table.setEnabled(false);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 70));

        JOptionPane.showMessageDialog(frame, scrollPane, "Employee Info", JOptionPane.INFORMATION_MESSAGE);
    });

    // View Report button action
    reportButton.addActionListener(e -> {
        EmployeePayInfo report = Report.report(employee.getEmpid());
        if (report == null) {
            JOptionPane.showMessageDialog(frame, "No payroll report found for employee ID: " + employee.getEmpid(), "Report Not Found", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] columnNames = {
            "EmpID", "First Name", "Last Name", "Pay Date", "Earnings", "Fed Tax", "Fed Med", "Fed SS",
            "State Tax", "401k", "Healthcare"
        };

        Object[][] data = {
            {
                report.getEmpid(),
                report.getFname(),
                report.getLname(),
                report.getPayDate(),
                report.getEarning(),
                report.getFed_tax(),
                report.getFed_med(),
                report.getFed_SS(),
                report.getState_tax(),
                report.getRetire_401K(),
                report.getHealth_care()
            }
        };

        JTable reportTable = new JTable(data, columnNames);
        reportTable.setEnabled(false);
        reportTable.setRowHeight(25);
        JScrollPane reportScrollPane = new JScrollPane(reportTable);
        reportScrollPane.setPreferredSize(new Dimension(1000, 70));

        JOptionPane.showMessageDialog(frame, reportScrollPane, "Payroll Report", JOptionPane.INFORMATION_MESSAGE);
    });
}


    private void goBackToSearch() {
        searchPanel.setVisible(true); // Show search bar
        searchButton.setVisible(true);
        backButton.setVisible(false); // Hide back button
        optionsPanel.setVisible(false);  // Hide options panel
    }
}
