package com.assemblers.app.UI;

import javax.swing.*;
import java.awt.*;

public class AdminPage {
    private JFrame frame;

    public AdminPage() {
        frame = new JFrame("WELCOME TO Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

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
        backgroundPanel.setLayout(new GridLayout(6, 1, 10, 10));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Padding

        Font buttonFont = new Font("Monospaced", Font.PLAIN, 12);
        Color buttonBgColor = new Color(70, 130, 180); // Steel blue
        Color buttonFgColor = Color.BLACK;

        // Create buttons
        JButton searchEmployeeBtn = createLoginStyledButton("Search Employee", buttonFont, buttonBgColor, buttonFgColor);
        JButton updateInfoBtn = createLoginStyledButton("Update Employee Info", buttonFont, buttonBgColor, buttonFgColor);
        JButton updateSalaryBtn = createLoginStyledButton("Update Salary Range", buttonFont, buttonBgColor, buttonFgColor);
        JButton viewReportBtn = createLoginStyledButton("View Report", buttonFont, buttonBgColor, buttonFgColor);
        JButton addEmployeeBtn = createLoginStyledButton("Add New Employee", buttonFont, buttonBgColor, buttonFgColor);
        JButton exitBtn = createLoginStyledButton("Log Out", buttonFont, buttonBgColor, buttonFgColor);

        // Add actions
        searchEmployeeBtn.addActionListener(e -> openSearchEmployeePage());
        updateInfoBtn.addActionListener(e -> openUpdateEmployeeInfoPage());
        updateSalaryBtn.addActionListener(e -> openUpdateSalaryRangePage());
        viewReportBtn.addActionListener(e -> openViewReportPage());
        addEmployeeBtn.addActionListener(e -> openAddEmployeePage());
        exitBtn.addActionListener(e -> 
            {frame.dispose();
             new Login();});

        // Add buttons to panel
        backgroundPanel.add(wrapButton(searchEmployeeBtn));
        backgroundPanel.add(wrapButton(updateInfoBtn));
        backgroundPanel.add(wrapButton(updateSalaryBtn));
        backgroundPanel.add(wrapButton(viewReportBtn));
        backgroundPanel.add(wrapButton(addEmployeeBtn));
        backgroundPanel.add(wrapButton(exitBtn));

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    private JButton createLoginStyledButton(String text, Font font, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 30));
        return button;
    }
    private JPanel wrapButton(JButton button) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        panel.add(button);
        return panel;
    }
    

    public void openSearchEmployeePage() {
        new SearchUI();
    }

    public void openUpdateEmployeeInfoPage() {
        new UpdateEmployeeInfoUI();
    }

    public void openUpdateSalaryRangePage() {
        new UpdateSalaryUI();
    }

    public void openViewReportPage() {
        new ReportUI();
    }

    public void openAddEmployeePage() {
        new AddEmployeeUI();
    }

    /*public static void main(String[] args) {
        new AdminPage();
    }*/
}
