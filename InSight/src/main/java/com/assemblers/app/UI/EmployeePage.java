package com.assemblers.app.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class EmployeePage extends JPanel implements ActionListener {
    private JPanel panel1, panel2;
    private JLabel label, text, query;
    private JButton[] employeeButtons = new JButton[3];

    public EmployeePage(ActionListener listener) {
        // Panel Setup
        this.setLayout(new BorderLayout());

        // Labels for the left panel
        label = new JLabel("💻");
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Adds padding

        text = new JLabel("Welcome [employee_name]!");
        text.setFont(new Font("Helvetica", Font.PLAIN, 15));

        query = new JLabel("Select Which Option You Would Like To View!");
        query.setFont(new Font("Helvetica", Font.PLAIN, 20));

        // Creating the left panel
        panel1 = new JPanel(null);
        panel1.setBackground(Color.ORANGE);
        panel1.setPreferredSize(new Dimension(400, 500));

        label.setBounds(150, 20, label.getPreferredSize().width, label.getPreferredSize().height);
        text.setBounds(120, 60, 200, 20);
        query.setBounds(50, 100, 300, 30);

        panel1.add(label);
        panel1.add(text);
        panel1.add(query);

        // Keep components centered in the panel
        panel1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel1.getWidth();
                label.setBounds((width - label.getPreferredSize().width) / 2, 20, label.getPreferredSize().width, label.getPreferredSize().height);
                text.setBounds((width - text.getPreferredSize().width) / 2, 60, text.getPreferredSize().width, text.getPreferredSize().height);
                query.setBounds((width - query.getPreferredSize().width) / 2, 100, query.getPreferredSize().width, query.getPreferredSize().height);
            }
        });

        // Creating right panel
        panel2 = new JPanel(new GridLayout(3, 1, 10, 10));
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setPreferredSize(new Dimension(400, 500));

        // Selection employeeButtons for right panel
        employeeButtons[0] = new JButton("Salary");
        employeeButtons[1] = new JButton("DOB");
        employeeButtons[2] = new JButton("Hours Worked");

        // Adding employeeButtons to right panel, also adds action listeners to them
        for (JButton button : employeeButtons) {
            button.addActionListener(listener); // Delegate actions to the provided listener
            button.setFocusable(false);
            panel2.add(button);
        }

        // Adding panels to the EmployeePage
        this.add(panel1, BorderLayout.WEST);
        this.add(panel2, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
