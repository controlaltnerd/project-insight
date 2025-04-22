package com.assemblers.app.UI;

import javax.swing.*;

import com.assemblers.app.APIController.Report;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class EmployeePage extends JPanel {
    private JLabel label, text, query, reports;
    private JButton viewInfo;
    private int employeeID;

    public EmployeePage() {
        // Panel Setup
        //this.setLayout(new BorderLayout());

        // Labels for the left panel
        label = new JLabel("💻");
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Adds padding

        text = new JLabel("Welcome [employee_name]!");
        text.setFont(new Font("Helvetica", Font.PLAIN, 15));

        query = new JLabel("View Your Personalized Information!");
        query.setFont(new Font("Helvetica", Font.PLAIN, 20));

        // Creating the left panel
        this.setBackground(Color.ORANGE);
        this.setPreferredSize(new Dimension(400, 500));

        label.setBounds(150, 20, label.getPreferredSize().width, label.getPreferredSize().height);
        text.setBounds(120, 60, 200, 20);
        query.setBounds(50, 100, 300, 30);

        this.add(label);
        this.add(text);
        this.add(query);

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
        this.add(viewInfo);

        // View Reports label
        reports = new JLabel("");
        this.add(reports);
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
        String reports = "";
        //reports.setText(Double.toString(Report.getEmployeePayByEmpid(employeeID).getEarning()));
        System.out.println("Displaying Info");
    }

}
