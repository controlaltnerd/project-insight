package com.assemblers.app.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.assemblers.app.APIController.Report;
import com.assemblers.app.DatabaseAccess.ReportDAO;
import com.assemblers.app.Models.EmployeePayInfo;
import java.util.List;
import java.awt.*;

public class ReportUI {
    private JFrame frame;

    public ReportUI() {
        frame = new JFrame("Reports");
        frame.setSize(750, 220); // Wider to accommodate new dropdowns
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1, 10, 10));
        frame.setLocationRelativeTo(null);
    
        // Division options
        String[] divisionNames = {
            "Technology Engineering", "Marketing", "Human Resources", "HQ"
        };
        int[] divisionIds = {123, 345, 456, 567};
    
        // Job title options
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
    
        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
    
        // Division Panel
        JPanel divisionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel divisionLabel = new JLabel("Division:");
        JComboBox<String> divisionDropdown = new JComboBox<>(divisionNames);
        JLabel divisionMonthLabel = new JLabel("Month:");
        JComboBox<String> divisionMonthDropdown = new JComboBox<>(months);
        JButton divisionBtn = new JButton("View Total Pay by Division");
        divisionPanel.add(divisionLabel);
        divisionPanel.add(divisionDropdown);
        divisionPanel.add(divisionMonthLabel);
        divisionPanel.add(divisionMonthDropdown);
        divisionPanel.add(divisionBtn);
    
        // Job Title Panel
        JPanel jobTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel jobTitleLabel = new JLabel("Job Title:");
        JComboBox<String> jobTitleDropdown = new JComboBox<>(jobTitleNames);
        JLabel jobTitleMonthLabel = new JLabel("Month:");
        JComboBox<String> jobTitleMonthDropdown = new JComboBox<>(months);
        JButton jobTitleBtn = new JButton("View Total Pay by Job Title");
        jobTitlePanel.add(jobTitleLabel);
        jobTitlePanel.add(jobTitleDropdown);
        jobTitlePanel.add(jobTitleMonthLabel);
        jobTitlePanel.add(jobTitleMonthDropdown);
        jobTitlePanel.add(jobTitleBtn);
    
        // All Info Panel
        JPanel allInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton allPayInfoBtn = new JButton("View All Employee Pay Info");
        allPayInfoBtn.setPreferredSize(new Dimension(250, 30));
        allInfoPanel.add(allPayInfoBtn);
    
        // Add to frame
        frame.add(divisionPanel);
        frame.add(jobTitlePanel);
        frame.add(allInfoPanel);
    
        // Button actions
        divisionBtn.addActionListener(e -> {
            int selectedIndex = divisionDropdown.getSelectedIndex();
            int divisionId = divisionIds[selectedIndex];
            String selectedMonth = (String) divisionMonthDropdown.getSelectedItem();
            viewTotalPayByDivision(divisionId); // You can update to pass selectedMonth later
        });
    
        jobTitleBtn.addActionListener(e -> {
            int selectedIndex = jobTitleDropdown.getSelectedIndex();
            int jobTitleId = jobTitleIds[selectedIndex];
            String selectedMonth = (String) jobTitleMonthDropdown.getSelectedItem();
            viewTotalPayByJobtitles(jobTitleId); // You can update to pass selectedMonth later
        });
    
        allPayInfoBtn.addActionListener(e -> viewAllEmployeePayInfo());
    
        frame.setVisible(true);
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
    
        // Create a dialog with a larger size
        JDialog dialog = new JDialog(frame, "All Employee Pay Info", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(scrollPane);
        dialog.setSize(1000, 500); // Set width and height
        dialog.setLocationRelativeTo(frame); // Center relative to main frame
        dialog.setVisible(true);
    }
    public void viewTotalPayByDivision(int division_id) {
        JOptionPane.showMessageDialog(frame, "Displaying total pay for division ID: " + division_id);
    }

    public void viewTotalPayByJobtitles(int jobtitle_id) {
        JOptionPane.showMessageDialog(frame, "Displaying total pay for job title ID: " + jobtitle_id);
    }
    public static void main(String[] args) {
        new ReportUI();
    }
}



