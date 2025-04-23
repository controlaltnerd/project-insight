package com.assemblers.app.UI;

import javax.swing.*;

import com.assemblers.app.APIController.Report;
import com.assemblers.app.Models.EmployeePayInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeePage extends JFrame {
    private JPanel empPanel;
    private JLabel label, text, query, reports;
    private JButton viewInfo;
    private int employeeID;

    public EmployeePage(int empId) {
        // Panel Setup
        //this.setLayout(new BorderLayout());
        employeeID = empId;

        setTitle("Login Form");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // Labels for the left panel
        label = new JLabel("💻");
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Adds padding

        text = new JLabel("Welcome [employee_name]!");
        text.setFont(new Font("Helvetica", Font.PLAIN, 15));

        query = new JLabel("View Your Personalized Information!");
        query.setFont(new Font("Helvetica", Font.PLAIN, 20));

        // Creating the left panel
        empPanel.setBackground(Color.ORANGE);
        empPanel.setPreferredSize(new Dimension(400, 500));

        label.setBounds(150, 20, label.getPreferredSize().width, label.getPreferredSize().height);
        text.setBounds(120, 60, 200, 20);
        query.setBounds(50, 100, 300, 30);

        empPanel.add(label);
        empPanel.add(text);
        empPanel.add(query);

        // Keep components centered in the panel
        
        // Selection employeeButtons for right panel
        viewInfo = new JButton("View Report");

        // Adding employeeButtons to right panel, also adds action listeners to them
        viewInfo.setFocusable(false);
        viewInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayInfo();
            }
        });
        empPanel.add(viewInfo);

        // View Reports label
        reports = new JLabel("");
        empPanel.add(reports);
        add(empPanel);
    }
    public void setEmpID(int id)
    {
        employeeID = id;
    }

    public int getEmpID()
    {
        return employeeID;
    }

    public void displayInfo()
    {
        String rep = "";
        //reports.setText(Double.toString(Report.getEmployeePayByEmpid(employeeID).getEarning()));
        for (EmployeePayInfo empPay : Report.getEmployeePayByEmpid(employeeID)) 
        {
            rep.concat(empPay.getFname() + " " + empPay.getLname() + "'s earnings is $" + Double.toString(empPay.getEarning()) + '\n');
        }
        reports.setText(rep);
        System.out.println("Displaying Info"); //Debug
    }

}
