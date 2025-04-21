package com.assemblers.app;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Hello, world!");
            }
        });
    }
}
