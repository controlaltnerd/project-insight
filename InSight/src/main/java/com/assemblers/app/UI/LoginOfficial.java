/*
 * THIS IS THE OFFICIAL LOGIN SCREEN
*/
package com.assemblers.app.UI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.assemblers.app.APIController.UserLogin;
import com.assemblers.app.Models.User;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginOfficial extends JFrame {
    private JButton button;
    private CardLayout cardLayout;
    private JPanel login, mainLayout;
    private EmployeePage employeePage;
    private JLabel userLab, passLab, messageLabel;
    private JTextField userText;
    private JPasswordField passText;
    
    public LoginOfficial()
    {
        login = new JPanel();
        cardLayout = new CardLayout();
        mainLayout = new JPanel(cardLayout);
        employeePage = new EmployeePage();

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        login.setLayout(null);

        userLab = new JLabel("User Name: ");
        userLab.setBounds(10, 20, 80, 25);
        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        passLab = new JLabel("Password: ");
        passLab.setBounds(10, 60, 80, 25);
        passText = new JPasswordField();
        passText.setBounds(100, 60, 165, 25);

        messageLabel = new JLabel("Enter Username and Password");
        messageLabel.setBounds(100, 100, 165, 25);

        button = new JButton("ENTER");
        button.setBackground(Color.CYAN);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Futura", Font.BOLD, 10));
        button.setBounds(100, 120, 80, 20);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });
        
        button.setFocusable(false);


        login.add(userLab);
        login.add(userText);
        login.add(passLab);
        login.add(passText);
        login.add(button);

        mainLayout.add(login, "Login");
        mainLayout.add(employeePage, "Employee Page");

        add(mainLayout);
        setVisible(true);
    }

    private void loginAction() {
        String username = userText.getText();
        String password = new String(passText.getPassword());
        
        User user = UserLogin.login(username, password);

        if (user != null) {
            if (user.getRole() == 1) {
                openAdminPage();
            } else if (user.getRole() == 0) {
                openEmployeePage(user.getEmpid());
            }
        } else {
            messageLabel.setText("Invalid username or password.");
        }       
    }
    private void openAdminPage() {
            
    }

    private void openEmployeePage(int empId) {
        employeePage.setEmpID(empId);
        System.out.println("Login Successfull!");
        cardLayout.show(mainLayout, "Employee Page");        
    }
}
