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
        empPanel = new JPanel();

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

        // View Reports label
        reports = new JLabel("");
        
        // Adding employeeButtons to right panel, also adds action listeners to them
        viewInfo = new JButton("View Reports");
        viewInfo.setFocusable(false);
        viewInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayInfo();
            }
        });

        empPanel.setLayout(new BoxLayout(empPanel, BoxLayout.Y_AXIS));
        empPanel.setBackground(Color.ORANGE);
        empPanel.setPreferredSize(new Dimension(400, 600));

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        query.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        reports.setAlignmentX(Component.CENTER_ALIGNMENT);

        empPanel.add(Box.createVerticalStrut(20));
        empPanel.add(label);
        empPanel.add(Box.createVerticalStrut(20));
        empPanel.add(text);
        empPanel.add(Box.createVerticalStrut(10));
        empPanel.add(query);
        empPanel.add(Box.createVerticalStrut(20));
        empPanel.add(viewInfo);
        empPanel.add(reports);

        // Keep components centered in the panel
        
        // Selection employeeButtons for right panel
        

        add(empPanel);
        setVisible(true);
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
            System.out.println("Processing");
        }
        reports.setText(rep);
        System.out.println(rep); //Debug
    }

}
