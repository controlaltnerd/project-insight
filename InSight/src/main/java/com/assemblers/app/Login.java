import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Login implements ActionListener{
    JButton button;
    Login()
    {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(null);

        JLabel userLab = new JLabel("User Name: ");
        userLab.setBounds(10, 20, 80, 25);
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        JLabel passLab = new JLabel("Password: ");
        passLab.setBounds(10, 60, 80, 25);
        JPasswordField passText = new JPasswordField();
        passText.setBounds(100, 60, 165, 25);

        button = new JButton("ENTER");
        button.setBackground(Color.CYAN);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Futura", Font.BOLD, 10));
        button.setBounds(100, 120, 80, 20);

        button.addActionListener(this);
        button.setFocusable(false);


        panel.add(userLab);
        panel.add(userText);
        panel.add(passLab);
        panel.add(passText);
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }
    public static void main(String[] args) 
    {
        Login app = new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == button)
        {
            System.out.println("Login Button Works!!!");
        }
    }
}
