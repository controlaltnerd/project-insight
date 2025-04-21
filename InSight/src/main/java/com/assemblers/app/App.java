package com.assemblers.app;

import javax.swing.SwingUtilities;

// import com.assemblers.app.UI.LoginForTestingOnly;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // new LoginForTestingOnly().setVisible(true);
                System.out.println("Hello, world!");
            }
        });
    }
}
