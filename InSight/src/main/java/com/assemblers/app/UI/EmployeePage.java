package com.assemblers.app.UI;

import javax.swing.*;

import com.assemblers.app.APIController.Report;
import com.assemblers.app.Models.EmployeePayInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeePage extends JFrame {
    private JPanel empPanel;
    private JLabel text, query;
    private JButton viewInfo, toLogin;
    private int employeeID;
    private boolean isReport;

    public EmployeePage(int empId) {
        // Panel Setup
        //this.setLayout(new BorderLayout());
        setTitle("Login Form");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        Font empFont = new Font("Monospaced", Font.BOLD, 24);
        Color beige = new Color(37, 144, 232);

        employeeID = empId;
        isReport = false;
        empPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = new ImageIcon(getClass().getResource("/dog.jpg")).getImage();
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace(); // Handle any exceptions related to image loading
                }
            }
        };
        empPanel.setOpaque(false);

        text = new JLabel("Welcome " + Report.getEmployeePayByEmpid(employeeID).get(0).getFname() + "!");
        text.setFont(empFont);
        text.setForeground(beige);

        query = new JLabel("View Your Personalized Information!");
        query.setFont(empFont);
        query.setForeground(beige);
        
        // Adding employeeButtons to right panel, also adds action listeners to them
        viewInfo = new JButton("View Reports");
        viewInfo.setFocusable(false);
        viewInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayInfo();
            }
        });

        toLogin = new JButton("Return to Login");
        toLogin.setFocusable(false);
        toLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gotoLogin();
            }
        });

        empPanel.setLayout(new BoxLayout(empPanel, BoxLayout.Y_AXIS));
        //empPanel.setBackground(Color.ORANGE);
        empPanel.setPreferredSize(new Dimension(400, 600));

        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        query.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        toLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        empPanel.add(Box.createVerticalStrut(20));
        empPanel.add(text);
        empPanel.add(Box.createVerticalStrut(10));
        empPanel.add(query);
        empPanel.add(Box.createVerticalStrut(20));
        empPanel.add(toLogin);
        empPanel.add(Box.createVerticalStrut(10));
        empPanel.add(viewInfo);
        // Keep components centered in the panel
        
        // Selection employeeButtons for right panel
        
        empPanel.repaint();
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
        if (!isReport) {
            String[] columnNames = {
                "EmpID", "First Name", "Last Name", "Pay Date", "Earnings", "Fed Tax", "Fed Med", "Fed SS",
                "State Tax", "401k", "Healthcare"
            };        

            Object[][] data = new Object[Report.getEmployeePayByEmpid(employeeID).size()][columnNames.length];

            //String rep = "";
            
            for (int i = 0; i < Report.getEmployeePayByEmpid(employeeID).size(); i++) 
            {
                EmployeePayInfo r = Report.getEmployeePayByEmpid(employeeID).get(i);
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
            JTable reportings = new JTable(data, columnNames);
            reportings.setEnabled(false);
            reportings.setRowHeight(25);
            JScrollPane reportScrollPane = new JScrollPane(reportings);
            reportScrollPane.setPreferredSize(new Dimension(empPanel.getWidth(), 25));
            reportScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            JButton closeRep = new JButton("Close Report");

            closeRep.setFocusable(false);
            closeRep.setAlignmentX(Component.CENTER_ALIGNMENT);
            closeRep.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    closeDisplayWindow();
                }
            });
            
            empPanel.add(Box.createVerticalStrut(20));
            empPanel.add(reportScrollPane, BorderLayout.CENTER);
            empPanel.add(Box.createVerticalStrut(20));
            empPanel.add(closeRep, BorderLayout.CENTER);
            empPanel.revalidate();
            empPanel.repaint();
            isReport = true;
        }
    }

    public void closeDisplayWindow()
    {
        if (isReport)
        {
            empPanel.remove(empPanel.getComponentCount() - 1);
            empPanel.remove(empPanel.getComponentCount() - 1);
            empPanel.remove(empPanel.getComponentCount() - 1);
            empPanel.remove(empPanel.getComponentCount() - 1);


            empPanel.revalidate();
            empPanel.repaint();
            isReport = false;
        }
    }

    public void gotoLogin()
    {
        dispose();
        new Login();
    }

}
