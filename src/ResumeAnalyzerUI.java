import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ResumeAnalyzerUI implements ActionListener {
    private JFrame panel;
    private JTextField nameField, emailField, phoneField, skillsField;

    public ResumeAnalyzerUI() {
        panel = new JFrame("Resume Analyzer & Job Finder");
        panel.setSize(1400, 800);  // Set window size
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLocationRelativeTo(null);  // Center the window

        // Create a custom JPanel for background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set background image (ensure this path is correct)
                ImageIcon icon = new ImageIcon("C:\\Users\\Admin\\Desktop\\53003230043\\Job\\src\\background.jpg");  // Background image
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);  // Disable layout manager to use setBounds
        backgroundPanel.setBackground(Color.WHITE);

        // Create components (labels, fields, buttons)
        JLabel descriptionLabel = new JLabel("<html> <b>Please fill in the following details to complete your profile. The information you provide will help us analyze and match it with suitable job opportunities. <b> </html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionLabel.setForeground(Color.BLACK);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel headingLabel = new JLabel("Enter your Details:");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setForeground(Color.BLACK);
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setForeground(Color.BLACK);

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        emailLabel.setForeground(Color.BLACK);

        JLabel phoneLabel = new JLabel("Phone: ");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        phoneLabel.setForeground(Color.BLACK);

        JLabel skillsLabel = new JLabel("Skills: ");
        skillsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        skillsLabel.setForeground(Color.BLACK);

        // Create JTextFields for input fields
        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        nameField.setBackground(new Color(240, 240, 240));
        nameField.setForeground(Color.BLACK);
        nameField.setPreferredSize(new Dimension(400, 30));  // Increased width

        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 18));
        emailField.setBackground(new Color(240, 240, 240));
        emailField.setForeground(Color.BLACK);
        emailField.setPreferredSize(new Dimension(400, 30));  // Increased width

        phoneField = new JTextField();
        phoneField.setFont(new Font("Arial", Font.PLAIN, 18));
        phoneField.setBackground(new Color(240, 240, 240));
        phoneField.setForeground(Color.BLACK);
        phoneField.setPreferredSize(new Dimension(400, 30));  // Increased width

        skillsField = new JTextField();
        skillsField.setFont(new Font("Arial", Font.PLAIN, 18));
        skillsField.setBackground(new Color(240, 240, 240));
        skillsField.setForeground(Color.BLACK);
        skillsField.setPreferredSize(new Dimension(400, 30));  // Increased width

        // Create Buttons and adjust sizes
        JButton skipButton = new JButton("Skip");
        skipButton.setFont(new Font("Arial", Font.BOLD, 18));
        skipButton.setBackground(new Color(60, 179, 113));  // Green color
        skipButton.setForeground(Color.WHITE);
        skipButton.setPreferredSize(new Dimension(250, 50));  // Increased button height and width

        JButton addButton = new JButton("Add Candidate");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setBackground(new Color(70, 130, 180));  // Blue color
        addButton.setForeground(Color.WHITE);
        addButton.setPreferredSize(new Dimension(250, 50));  // Increased button height and width

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(new Color(255, 69, 0));  // Red color
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(250, 50));  // Increased button height and width

        // Layout all components using setBounds for absolute positioning
        descriptionLabel.setBounds(50, 100, 2000, 30);  // Position description
        headingLabel.setBounds(700, 150, 400, 40);  // Position heading
        nameLabel.setBounds(600, 210, 100, 30);
        nameField.setBounds(700, 210, 400, 30);
        emailLabel.setBounds(600, 260, 100, 30);
        emailField.setBounds(700, 260, 400, 30);
        phoneLabel.setBounds(600, 310, 100, 30);
        phoneField.setBounds(700, 310, 400, 30);
        skillsLabel.setBounds(600, 360, 100, 30);
        skillsField.setBounds(700, 360, 400, 30);

        skipButton.setBounds(500, 450, 250, 50);
        addButton.setBounds(800, 450, 250, 50);
        backButton.setBounds(1100, 450, 250, 50);

        // Add components to the background panel
        backgroundPanel.add(descriptionLabel);
        backgroundPanel.add(headingLabel);
        backgroundPanel.add(nameLabel);
        backgroundPanel.add(nameField);
        backgroundPanel.add(emailLabel);
        backgroundPanel.add(emailField);
        backgroundPanel.add(phoneLabel);
        backgroundPanel.add(phoneField);
        backgroundPanel.add(skillsLabel);
        backgroundPanel.add(skillsField);
        backgroundPanel.add(skipButton);
        backgroundPanel.add(addButton);
        backgroundPanel.add(backButton);

        // Add background panel to the frame
        panel.add(backgroundPanel);

        // Skip button action
        skipButton.addActionListener(this);

        // Add Candidate button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String skills = skillsField.getText();

                Candidate newCandidate = new Candidate(name, email, phone, skills);
                try {
                    DBConnection.addCandidate(newCandidate);
                    JOptionPane.showMessageDialog(null, "Candidate Added!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding candidate.");
                }
            }
        });

        // Back button action
        backButton.addActionListener(this);

        // Make the frame visible
        panel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Back".equals(command)) {
            new DashboardPage();  // Redirect to DashboardPage
            panel.setVisible(false);  // Close current frame
        } else if ("Skip".equals(command)) {
            new DashboardPage();  // Redirect to DashboardPage
            panel.setVisible(false);  // Close current frame
        }
    }

    public static void main(String[] args) {
        new ResumeAnalyzerUI();  // Start the ResumeAnalyzer UI
    }
}