package com.assemblers.app.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.assemblers.app.APIController.AddEmployee;
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
        optionsPanel = new JPanel(new GridBagLayout());
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

        Dimension buttonSize = new Dimension(130, 40); // Set uniform button size

        // Set up GridBagConstraints with spacing between the buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;  // Column position
        gbc.weightx = 1.0;  // Make sure the buttons stretch across the panel // Make buttons fill the width
        gbc.insets = new Insets(10, 10, 10, 10);  // Add space around each button (top, left, bottom, right)

        // View Employee Info button
        JButton viewButton = new JButton("View Employee Info");
        viewButton.setPreferredSize(buttonSize);
        optionsPanel.add(viewButton, gbc);

        // Add space between the buttons (adjust vertical spacing)
        gbc.gridy = 1;  // Move down to the next row
        optionsPanel.add(createSpacingLabel(), gbc); // Adding a "dummy" component for spacing

        // Report button
        JButton reportButton = new JButton("View Report");
        reportButton.setPreferredSize(buttonSize);
        gbc.gridy = 2;
        optionsPanel.add(reportButton, gbc);

        // Add more space between the buttons
        gbc.gridy = 3;
        optionsPanel.add(createSpacingLabel(), gbc);  // Adding a "dummy" component for spacing

        // Add Employee button
        JButton addButton = new JButton("Add Employee");
        addButton.setPreferredSize(buttonSize);
        gbc.gridy = 4;
        optionsPanel.add(addButton, gbc);

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

        addButton.addActionListener(e -> {
            JPanel inputPanel = new JPanel(new FlowLayout());

            JTextField idField = new JTextField(5);
            JTextField fnameField = new JTextField(10);
            JTextField lnameField = new JTextField(10);
            JTextField ssnField = new JTextField(10);
            JTextField titleField = new JTextField(10); // Must match existing job title in DB
            JTextField emailField = new JTextField(15);
            JTextField salaryField = new JTextField(8);

            inputPanel.add(new JLabel("ID:"));
            inputPanel.add(idField);
            inputPanel.add(new JLabel("First Name:"));
            inputPanel.add(fnameField);
            inputPanel.add(new JLabel("Last Name:"));
            inputPanel.add(lnameField);
            inputPanel.add(new JLabel("SSN:"));
            inputPanel.add(ssnField);
            inputPanel.add(new JLabel("Job Title:"));
            inputPanel.add(titleField);
            inputPanel.add(new JLabel("Email:"));
            inputPanel.add(emailField);
            inputPanel.add(new JLabel("Salary:"));
            inputPanel.add(salaryField);

            int result = JOptionPane.showConfirmDialog(frame, inputPanel, "Add New Employee", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    int id = Integer.parseInt(idField.getText().trim());
                    String fname = fnameField.getText().trim();
                    String lname = lnameField.getText().trim();
                    String ssn = ssnField.getText().trim();
                    String jobTitle = titleField.getText().trim();
                    String email = emailField.getText().trim();
                    double salary = Double.parseDouble(salaryField.getText().trim());

                    if (fname.isEmpty() || lname.isEmpty() || ssn.isEmpty() || jobTitle.isEmpty() || email.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Employee newEmp = new Employee(id, fname, lname, jobTitle, email, salary, ssn);

                    int rowsAffected = AddEmployee.addNewEmployee(newEmp);

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to add employee. Make sure job title exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frame, "Invalid number format for ID or Salary.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(frame, iae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // View Report button action
        reportButton.addActionListener(e -> {
            List<EmployeePayInfo> reports = Report.getEmployeePayByEmpid(employee.getEmpid());

            if (reports == null || reports.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No payroll report found for employee ID: " + employee.getEmpid(), "Report Not Found", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String[] columnNames = {
                "EmpID", "First Name", "Last Name", "Pay Date", "Earnings", "Fed Tax", "Fed Med", "Fed SS",
                "State Tax", "401k", "Healthcare"
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
            JScrollPane reportScrollPane = new JScrollPane(reportTable);
            reportScrollPane.setPreferredSize(new Dimension(1000, 150));

            JOptionPane.showMessageDialog(frame, reportScrollPane, "Payroll Report", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void goBackToSearch() {
        searchPanel.setVisible(true); // Show search bar
        searchButton.setVisible(true);
        backButton.setVisible(false); // Hide back button
        optionsPanel.setVisible(false);  // Hide options panel
    }

    // Helper method to create a spacing label (empty JLabel) for vertical spacing
    private JLabel createSpacingLabel() {
        JLabel label = new JLabel("");  // Empty label used for spacing
        label.setPreferredSize(new Dimension(1, 10));  // Set the height for the spacing
        return label;
    }
}
