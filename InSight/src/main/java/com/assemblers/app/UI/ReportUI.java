package com.assemblers.app.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.assemblers.app.APIController.Report;
import com.assemblers.app.DatabaseAccess.ReportDAO;
import com.assemblers.app.Models.EmployeePayInfo;
import java.awt.*;
import java.util.List;

public class ReportUI {
    private JFrame frame;

    public ReportUI() {
        frame = new JFrame("Reports");
        frame.setSize(750, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create background panel
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

        // Common style settings
        Font labelFont = new Font("Monospaced", Font.PLAIN, 14);
        Font buttonFont = new Font("Monospaced", Font.PLAIN, 12);
        Color buttonColor = new Color(70, 130, 180); // Steel blue

        // Division Panel
        JLabel divisionLabel = new JLabel("Division:");
        divisionLabel.setFont(labelFont);
        divisionLabel.setForeground(Color.WHITE);
        divisionLabel.setBounds(30, 20, 100, 25);

        JComboBox<String> divisionDropdown = createStyledComboBox(new String[]{
            "Technology Engineering", "Marketing", "Human Resources", "HQ"
        });
        divisionDropdown.setBounds(140, 20, 200, 25);

        JLabel divisionMonthLabel = new JLabel("Month:");
        divisionMonthLabel.setFont(labelFont);
        divisionMonthLabel.setForeground(Color.WHITE);
        divisionMonthLabel.setBounds(370, 20, 100, 25);

        JComboBox<String> divisionMonthDropdown = createStyledComboBox(new String[]{
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        });
        divisionMonthDropdown.setBounds(450, 20, 200, 25);

        JButton divisionBtn = createStyledButton("View Total Pay by Division");
        divisionBtn.setBounds(250, 60, 220, 30);

        // Job Title Panel
        JLabel jobTitleLabel = new JLabel("Job Title:");
        jobTitleLabel.setFont(labelFont);
        jobTitleLabel.setForeground(Color.WHITE);
        jobTitleLabel.setBounds(30, 110, 100, 25);

        JComboBox<String> jobTitleDropdown = createStyledComboBox(new String[]{
            "Software Manager", "Software Architect", "Software Engineer", "Software Developer",
            "Marketing Manager", "Marketing Associate", "Marketing Assistant",
            "HR Manager", "HR Analyst",
            "Chief Executive Officer", "Chief Financial Officer", "Chief Information Officer"
        });
        jobTitleDropdown.setBounds(140, 110, 200, 25);

        JLabel jobTitleMonthLabel = new JLabel("Month:");
        jobTitleMonthLabel.setFont(labelFont);
        jobTitleMonthLabel.setForeground(Color.WHITE);
        jobTitleMonthLabel.setBounds(370, 110, 100, 25);

        JComboBox<String> jobTitleMonthDropdown = createStyledComboBox(new String[]{
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        });
        jobTitleMonthDropdown.setBounds(450, 110, 200, 25);

        JButton jobTitleBtn = createStyledButton("View Total Pay by Job Title");
        jobTitleBtn.setBounds(250, 150, 220, 30);

        // All Info Button
        JButton allPayInfoBtn = createStyledButton("View All Employee Pay Info");
        allPayInfoBtn.setBounds(200, 200, 300, 40);

        // Add components to backgroundPanel
        backgroundPanel.add(divisionLabel);
        backgroundPanel.add(divisionDropdown);
        backgroundPanel.add(divisionMonthLabel);
        backgroundPanel.add(divisionMonthDropdown);
        backgroundPanel.add(divisionBtn);

        backgroundPanel.add(jobTitleLabel);
        backgroundPanel.add(jobTitleDropdown);
        backgroundPanel.add(jobTitleMonthLabel);
        backgroundPanel.add(jobTitleMonthDropdown);
        backgroundPanel.add(jobTitleBtn);

        backgroundPanel.add(allPayInfoBtn);

        // Division and Job Title IDs
        int[] divisionIds = {123, 345, 456, 567};
        int[] jobTitleIds = {100, 101, 102, 103, 200, 201, 202, 300, 301, 900, 901, 902};

        // Button actions
        divisionBtn.addActionListener(e -> {
            int selectedIndex = divisionDropdown.getSelectedIndex();
            int divisionId = divisionIds[selectedIndex];
            String selectedMonth = (String) divisionMonthDropdown.getSelectedItem();
            viewTotalPayByDivision(divisionId, selectedMonth);
        });

        jobTitleBtn.addActionListener(e -> {
            int selectedIndex = jobTitleDropdown.getSelectedIndex();
            int jobTitleId = jobTitleIds[selectedIndex];
            String selectedMonth = (String) jobTitleMonthDropdown.getSelectedItem();
            viewTotalPayByJobtitles(jobTitleId, selectedMonth);
        });

        allPayInfoBtn.addActionListener(e -> viewAllEmployeePayInfo());

        frame.setVisible(true);
    }

    private JComboBox<String> createStyledComboBox(String[] options) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Monospaced", Font.PLAIN, 12));
        comboBox.setBackground(new Color(255, 255, 255, 200)); // Semi-transparent white
        comboBox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        return comboBox;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Monospaced", Font.PLAIN, 12));
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
    }

    public void viewAllEmployeePayInfo() {
        List<EmployeePayInfo> employeePayInfoList = ReportDAO.getAllEmployeePayInfo();

        if (employeePayInfoList == null || employeePayInfoList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No employee pay info found.");
            return;
        }

        String[] columnNames = {
            "Emp ID", "First Name", "Last Name", "Pay Date", 
            "Earnings", "Fed Tax", "Fed Med", "Fed SS", 
            "State Tax", "Retire 401k", "Health Care"
        };

        Object[][] data = new Object[employeePayInfoList.size()][columnNames.length];

        for (int i = 0; i < employeePayInfoList.size(); i++) {
            EmployeePayInfo payInfo = employeePayInfoList.get(i);
            data[i][0] = payInfo.getEmpid();
            data[i][1] = payInfo.Fname();
            data[i][2] = payInfo.Lname();
            data[i][3] = payInfo.getPayDate();
            data[i][4] = payInfo.getEarning();
            data[i][5] = payInfo.getFed_tax();
            data[i][6] = payInfo.getFed_med();
            data[i][7] = payInfo.getFed_SS();
            data[i][8] = payInfo.getState_tax();
            data[i][9] = payInfo.getRetire_401K();
            data[i][10] = payInfo.getHealth_care();
        }

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);

        JDialog dialog = new JDialog(frame, "All Employee Pay Info", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(scrollPane);
        dialog.setSize(1000, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    public void viewTotalPayByDivision(int division_id, String monthName) {
        int monthIndex = java.util.Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ).indexOf(monthName) + 1;

        float totalPay = Report.getTotalPayByDivision(division_id, monthIndex);

        String[] divisionNames = {"Technology Engineering", "Marketing", "Human Resources", "HQ"};
        int[] divisionIds = {123, 345, 456, 567};
        String divisionName = "Unknown";

        for (int i = 0; i < divisionIds.length; i++) {
            if (divisionIds[i] == division_id) {
                divisionName = divisionNames[i];
                break;
            }
        }

        ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png"));
        JOptionPane.showMessageDialog(
        frame,
        "The total pay for " + divisionName + " in " + monthName + " is $" + totalPay,
        "Total Pay", // Title
        JOptionPane.INFORMATION_MESSAGE, // Message type
        icon); // Icon
    }

    public void viewTotalPayByJobtitles(int jobtitle_id, String monthName) {
        int monthIndex = java.util.Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ).indexOf(monthName) + 1;

        float totalPay = Report.getTotalPayByJobtitle(jobtitle_id, monthIndex);

        String[] jobTitleNames = {
            "Software Manager", "Software Architect", "Software Engineer", "Software Developer",
            "Marketing Manager", "Marketing Associate", "Marketing Assistant",
            "HR Manager", "HR Analyst",
            "Chief Executive Officer", "Chief Financial Officer", "Chief Information Officer"
        };
        int[] jobTitleIds = {
            100, 101, 102, 103,
            200, 201, 202,
            300, 301,
            900, 901, 902
        };
        String jobTitleName = "Unknown";

        for (int i = 0; i < jobTitleIds.length; i++) {
            if (jobTitleIds[i] == jobtitle_id) {
                jobTitleName = jobTitleNames[i];
                break;
            }
        }
        ImageIcon icon = new ImageIcon(getClass().getResource("/logo.png"));
        JOptionPane.showMessageDialog(
        frame,
        "The total pay for " + jobTitleName + " in " + monthName + " is $" + totalPay,
        "Total Pay", // Title
        JOptionPane.INFORMATION_MESSAGE, // Message type
        icon // Icon
);

    }

    /*public static void main(String[] args) {
        new ReportUI();
    }*/
}
