package com.assemblers.app.UI;

import javax.swing.*;
import java.awt.*;

public class ReportUI {
    private JFrame frame;

    public ReportUI() {
        frame = new JFrame("Reports");
        frame.setSize(500, 220);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1, 10, 10));
        frame.setLocationRelativeTo(null);

        // Panel for Division
        JPanel divisionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel divisionLabel = new JLabel("Division ID:");
        JTextField divisionField = new JTextField(10);
        JButton divisionBtn = new JButton("View Total Pay by Division");
        divisionPanel.add(divisionLabel);
        divisionPanel.add(divisionField);
        divisionPanel.add(divisionBtn);

        // Panel for Job Title
        JPanel jobTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel jobTitleLabel = new JLabel("Job Title ID:");
        JTextField jobTitleField = new JTextField(10);
        JButton jobTitleBtn = new JButton("View Total Pay by Job Title");
        jobTitlePanel.add(jobTitleLabel);
        jobTitlePanel.add(jobTitleField);
        jobTitlePanel.add(jobTitleBtn);

        // Panel for All Employee Pay Info
        JPanel allInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton allPayInfoBtn = new JButton("View All Employee Pay Info");
        allPayInfoBtn.setPreferredSize(new Dimension(250, 30));
        allInfoPanel.add(allPayInfoBtn);

        // Add panels to frame
        frame.add(divisionPanel);
        frame.add(jobTitlePanel);
        frame.add(allInfoPanel);

        // Button actions
        divisionBtn.addActionListener(e -> {
            try {
                int divisionId = Integer.parseInt(divisionField.getText().trim());
                viewTotalPayByDivision(divisionId);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Division ID");
            }
        });

        jobTitleBtn.addActionListener(e -> {
            try {
                int jobTitleId = Integer.parseInt(jobTitleField.getText().trim());
                viewTotalPayByJobtitles(jobTitleId);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Job Title ID");
            }
        });

        allPayInfoBtn.addActionListener(e -> viewAllEmployeePayInfo());

        frame.setVisible(true);
    }

    public void viewAllEmployeePayInfo() {
        JOptionPane.showMessageDialog(frame, "Displaying all employee pay info...");
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



