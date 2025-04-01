import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class EmployeeLogin implements ActionListener{
    JFrame frame;
    JPanel panel1, panel2, query;
    JLabel label, text;
    JButton[] buttons = JButton[3];

    EmployeeLogin(){
        // Frame setup
        frame = new JFrame("Employee");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Labels for the left panel
        JLabel label = new JLabel("💻");
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Adds padding
        JLabel text = new JLabel("Welcome [employee_name]!");
        text.setFont(new Font("Helvetica", Font.PLAIN, 15));
        JLabel query = new JLabel("Select Which Option You Would Like To View!");
        query.setFont(new Font("Helvetica", Font.PLAIN, 20));

        // Creating the left panel
        panel1 = new JPanel(); 
        panel1.setLayout(null);
        panel1.setBackground(Color.ORANGE); // You had this set to BLACK and then BLUE
        panel1.setBounds(0, 0, (int)(frame.getSize().width * 0.5), frame.getSize().height);
        label.setBounds((int)(panel1.getWidth() * 0.5 - 0.5*(label.getPreferredSize().width)), 20, label.getPreferredSize().width, label.getPreferredSize().height);
        text.setBounds((int)(panel1.getWidth() * 0.5 - 0.5*(text.getPreferredSize().width)), 60, text.getPreferredSize().width, text.getPreferredSize().height);
        query.setBounds((int)(panel1.getWidth() * 0.5 - 0.5*(query.getPreferredSize().width)), 200, query.getPreferredSize().width, query.getPreferredSize().height);

        panel1.add(label);
        panel1.add(text);
        panel1.add(query);

        // Keep components centered in the panel
        panel1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                label.setBounds((int)(panel1.getWidth() * 0.5 - 0.5*(label.getPreferredSize().width)), 20, label.getPreferredSize().width, label.getPreferredSize().height);
                text.setBounds((int)(panel1.getWidth() * 0.5 - 0.5*(text.getPreferredSize().width)), 60, text.getPreferredSize().width, text.getPreferredSize().height);
                query.setBounds((int)(panel1.getWidth() * 0.5 - 0.5*(query.getPreferredSize().width)), 80, query.getPreferredSize().width, query.getPreferredSize().height);
            }
        });

        // Creating right panel
        panel2 = new JPanel(new BorderLayout());
        panel2.setBackground(Color.LIGHT_GRAY); // This was never set
        panel2.setBounds((int)(frame.getSize().width * 0.5), 0, (int)(frame.getSize().width * 0.5), frame.getSize().height);
        
        // Selection Buttons for right panel
        buttons[0] = new JButton("Salary");
        buttons[1] = new JButton("DOB");
        buttons[2] = new JButton("Hours Worked");

        // Adding buttons to right panel, also adds action listeners to them
        for (int i = 0; i < buttons.length; i++)
        {
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            panel2.add(buttons[i]);
        }

        // Adding and centering panels to frame
        frame.add(panel1);
        frame.add(panel2);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel1.setBounds(0, 0, frame.getWidth()/2, frame.getHeight());
                panel2.setBounds(frame.getWidth()/2, 0, frame.getWidth()/2, frame.getHeight());
            }
        });

        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == buttons[0])
        {
            // do stuff
        } else if (e.getSource() == buttons[1])
        {
            // do stuff
        } else if (e.getSource() == buttons[2])
        {
            // do stuff
        }
    }

}
