


//this is ONLY FOR MY TESTING PURPOSE. DONOT REFER THIS ONE TO DO ANYTHING



package com.assemblers.app.UI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.assemblers.app.APIController.Report;
import com.assemblers.app.APIController.EmployeeInfo;
import com.assemblers.app.APIController.UserLogin;
import com.assemblers.app.Models.Employee;
import com.assemblers.app.Models.EmployeePayInfo;
import com.assemblers.app.Models.User;
import com.assemblers.app.APIController.UpdateRangeSalary;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class LoginForTestingOnly extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    public LoginForTestingOnly() {
        setTitle("Login Form");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = new ImageIcon(getClass().getResource("/background.png")).getImage();
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace(); // Handle any exceptions related to image loading
                }
            }
        };
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(150, 180, 250, 30);
        panel.add(welcomeLabel);

        JLabel instructionLabel = new JLabel("Sign in to continue");
        instructionLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setBounds(120, 210, 200, 20);
        panel.add(instructionLabel);

        usernameField = new JTextField();
        usernameField.setBounds(50, 320, 300, 40);
        usernameField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        usernameField.setBackground(new Color(0, 0, 0, 120));  // Semi-transparent dark background
        usernameField.setForeground(Color.WHITE);  // White text color
        usernameField.setCaretColor(Color.WHITE);  // White caret
        panel.add(usernameField);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50, 295, 100, 20);
        usernameLabel.setForeground(Color.WHITE);
        panel.add(usernameLabel);

        // Create a glowing effect for the password field
        passwordField = new JPasswordField();
        passwordField.setBounds(50, 390, 300, 40);
        passwordField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        passwordField.setBackground(new Color(0, 0, 0, 120));  // Semi-transparent dark background
        passwordField.setForeground(Color.WHITE);  // White text color
        passwordField.setCaretColor(Color.WHITE);  // White caret
        panel.add(passwordField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 365, 100, 20);
        passwordLabel.setForeground(Color.WHITE);
        panel.add(passwordLabel);

        loginButton = new JButton("Login");
        loginButton.setBounds(160, 460, 80, 40);
        loginButton.setBackground(new Color(70, 130, 180)); // Steel blue color
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panel.add(loginButton);
        
        messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setFont(new Font("Monospaced", Font.PLAIN, 9));
        messageLabel.setForeground(Color.RED); // Red color for error messages
        messageLabel.setBounds(-30, 420, 300, 30);
        panel.add(messageLabel);


        JLabel forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setBounds(240, 435, 120, 20);
        forgotPasswordLabel.setForeground(Color.WHITE);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panel.add(forgotPasswordLabel);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(130, 520, 140, 30);
        createAccountButton.setForeground(Color.BLACK);
        createAccountButton.setBackground(new Color(70, 130, 180));
        createAccountButton.setFocusPainted(false);
        createAccountButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panel.add(createAccountButton);
        add(panel);
        setVisible(true);
        // Login button action
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });
    }
    
    private void loginAction() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        User user = UserLogin.login(username, password);

        if (user != null) {
            if (user.getRole() == 1) {
                openAdminPage(user.getEmpid());
            } else if (user.getRole() == 0) {
                openEmployeePage(user.getEmpid());
            }
        } else {
            messageLabel.setText("Invalid username or password.");
        }       
    }
    private void openAdminPage(int empId) {
        
        }
    private void openEmployeePage(int empId) {
        }
//TESTING UPDATE RANGE SALARY UpdateRangeSalary.updateRangeSalary(15000, 17000, 10);
//TESTING EMPLOYEE PAY INFO
/*List<EmployeePayInfo> payInfoList = Report.EmployeeReport(empId);

        if (payInfoList == null || payInfoList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No payroll records found for Employee ID: " + empId,
                    "No Data", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Get employee details from first record
        EmployeePayInfo firstRecord = payInfoList.get(0);
        String fullName = firstRecord.getFname() + " " + firstRecord.getLname();

        // Create label for Employee info
        JLabel titleLabel = new JLabel("Pay Report for: " + fullName + " (Emp ID: " + empId + ")", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Define table columns
        String[] columns = {"Pay Date", "Earnings", "Federal Tax", "Medicare", "Social Security",
                            "State Tax", "401K Retirement", "Health Care"};

        // Create table model
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Populate table with employee pay data
        for (EmployeePayInfo payInfo : payInfoList) {
            Object[] row = {
                    payInfo.getPayDate(),
                    payInfo.getEarning(),
                    payInfo.getFed_tax(),
                    payInfo.getFed_med(),
                    payInfo.getFed_SS(),
                    payInfo.getState_tax(),
                    payInfo.getRetire_401K(),
                    payInfo.getHealth_care()
            };
            tableModel.addRow(row);
        }

        // Create JTable
        JTable table = new JTable(tableModel);
        table.setEnabled(false);
        table.setRowHeight(25);

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(table);

        // Create a frame
        JFrame frame = new JFrame("Employee Payroll Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

        // Create a panel to hold label and table
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add panel to frame
        frame.add(panel);

        // Display the frame
        frame.setVisible(true); */
//TESTING view total pay by job titles
/*iint jobTitleId = 100;
        // Get total pay from database
        float totalPay = Report.getTotalPayByJobtitle(jobTitleId);

        // Create JFrame
        JFrame frame = new JFrame("Total Pay Report");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        // Create labels
        JLabel titleLabel = new JLabel("Total Pay for Job Title ID: " + jobTitleId, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel payLabel = new JLabel("$" + totalPay, JLabel.CENTER);
        payLabel.setFont(new Font("Arial", Font.BOLD, 20));
        payLabel.setForeground(Color.BLUE);

        // Add components to panel
        panel.add(titleLabel);
        panel.add(payLabel);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true); */
//TESTING TOTAL PAY BY DIVISION ID
/*int div_ID = 123;
        // Get total pay from database
        float totalPay = Report.GetTotalPayByDivision(div_ID);

        // Create JFrame
        JFrame frame = new JFrame("Total Pay Report");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        // Create labels
        JLabel titleLabel = new JLabel("Total Pay for Division ID: " + div_ID, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel payLabel = new JLabel("$" + totalPay, JLabel.CENTER);
        payLabel.setFont(new Font("Arial", Font.BOLD, 20));
        payLabel.setForeground(Color.BLUE);

        // Add components to panel
        panel.add(titleLabel);
        panel.add(payLabel);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true); */
}
