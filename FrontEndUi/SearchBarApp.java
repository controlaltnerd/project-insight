import javax.swing.*;
import java.awt.*;

public class SearchBarApp {
    private JFrame frame;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel, optionsPanel, topPanel;
    private JButton backButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                SearchBarApp window = new SearchBarApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SearchBarApp() {
        frame = new JFrame("Search Employee");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 400, 300);
        frame.getContentPane().setLayout(new BorderLayout());

        // Top panel for the Back button (left-aligned)
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        // Back button (hidden initially)
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30)); // Same size as other buttons
        backButton.setVisible(false);
        topPanel.add(backButton);

        // Search panel (contains Employee ID label, search field, and search button)
        searchPanel = new JPanel(new FlowLayout());
        frame.getContentPane().add(searchPanel, BorderLayout.CENTER);

        JLabel searchLabel = new JLabel("Employee ID:"); // Label for search bar
        searchPanel.add(searchLabel);

        searchField = new JTextField(15);
        searchPanel.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 30)); // Match other buttons
        searchPanel.add(searchButton);

        // Options panel (initially hidden)
        optionsPanel = new JPanel(new FlowLayout());
        frame.getContentPane().add(optionsPanel, BorderLayout.SOUTH);
        optionsPanel.setVisible(false);

        // Search button action
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim();
            if (!searchText.isEmpty()) {
                showOptions();
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter an Employee ID.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Back button action
        backButton.addActionListener(e -> goBackToSearch());
    }

    private void showOptions() {
        searchPanel.setVisible(false);
        searchButton.setVisible(false);
        backButton.setVisible(true);

        optionsPanel.removeAll(); // Clear previous buttons

        // Create buttons
        JButton viewButton = new JButton("View");
        JButton insertButton = new JButton("Insert");
        JButton updateButton = new JButton("Update");

        Dimension buttonSize = new Dimension(120, 40); // Set uniform button size
        viewButton.setPreferredSize(buttonSize);
        insertButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);

        // Add buttons
        optionsPanel.add(viewButton);
        optionsPanel.add(insertButton);
        optionsPanel.add(updateButton);

        optionsPanel.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    private void goBackToSearch() {
        searchPanel.setVisible(true); // Show search bar
        searchButton.setVisible(true);
        backButton.setVisible(false); // Hide back button
        optionsPanel.setVisible(false);
    }
}
