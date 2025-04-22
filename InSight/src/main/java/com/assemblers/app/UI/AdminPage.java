package com.assemblers.app.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage {
    private JFrame frame;

    public AdminPage() {
        frame = new JFrame("WELCOME TO Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 1, 10, 10));

        JButton searchEmployeeBtn = new JButton("Search Employee");
        JButton updateInfoBtn = new JButton("Update Employee Info");
        JButton updateSalaryBtn = new JButton("Update Salary Range");
        JButton viewReportBtn = new JButton("View Report");
        JButton addEmployeeBtn = new JButton("Add New Employee");
        JButton exitBtn = new JButton("Exit");

        searchEmployeeBtn.addActionListener(e -> openSearchEmployeePage());
        updateInfoBtn.addActionListener(e -> openUpdateEmployeeInfoPage());
        updateSalaryBtn.addActionListener(e -> openUpdateSalaryRangePage());
        viewReportBtn.addActionListener(e -> openViewReportPage());
        addEmployeeBtn.addActionListener(e -> openAddEmployeePage());
        exitBtn.addActionListener(e -> frame.dispose());

        frame.add(searchEmployeeBtn);
        frame.add(updateInfoBtn);
        frame.add(updateSalaryBtn);
        frame.add(viewReportBtn);
        frame.add(addEmployeeBtn);
        frame.add(exitBtn);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void openSearchEmployeePage() {
       //LINK YOUR SEARCH PAGE HERE
       //I CAN'T CALL it like this
       new SearchBarApp();
    }

    public void openUpdateEmployeeInfoPage() {
       new UpdateEmployeeInfoUI();
    }

    public void openUpdateSalaryRangePage() {
       new UpdateSalary();
    }

    public void openViewReportPage() {
        new ReportUI();
    }

    public void openAddEmployeePage() {
       new AddEmployeeUI();
    }

    public static void main(String[] args) {
        new AdminPage();
    }
}