import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginPage() {
        setTitle("Login Page");
        setSize(1400, 800);  // Set window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // Create a custom JPanel for background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set background image (make sure the image path is correct)
                ImageIcon icon = new ImageIcon("C:\\Users\\Admin\\Desktop\\53003230043\\Job\\src//bg.jpg");  // Background image
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new GridBagLayout());  // Use GridBagLayout for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components
        gbc.anchor = GridBagConstraints.CENTER; // Anchor to center

        // Create components (labels, fields, buttons)
        JLabel headingLabel = new JLabel("Login Page");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(15);  // Reduced width of text field
        passwordField = new JPasswordField(15);  // Reduced width of password field
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Set larger font for labels, text fields, and buttons
        Font headingFont = new Font("Arial", Font.BOLD, 24);  // Larger font for heading
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);  // Decreased font size slightly for fields
        Font buttonFont = new Font("Arial", Font.BOLD, 20);  // Increased font size for buttons

        headingLabel.setFont(headingFont);
        usernameLabel.setFont(fieldFont);
        passwordLabel.setFont(fieldFont);
        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        loginButton.setFont(buttonFont);  // Larger button font
        registerButton.setFont(buttonFont);  // Larger button font

        // Set text color of username and password fields to white
        usernameField.setForeground(Color.WHITE);
        passwordField.setForeground(Color.WHITE);
        
        headingLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        
        // Set button background color and text color
        loginButton.setBackground(new Color(70, 130, 180)); // Steel blue color
        loginButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(60, 179, 113)); // Medium sea green color
        registerButton.setForeground(Color.WHITE);

        // Place heading label at the top, centered
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Span across two columns to center it
        backgroundPanel.add(headingLabel, gbc);

        // Place username label and text field in the same row
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Occupy one column
        backgroundPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(usernameField, gbc);

        // Place password label and text field in the same row
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundPanel.add(passwordField, gbc);

        // Place login and register buttons in the same row, bigger and closer
        gbc.gridx = 0;
        gbc.gridy = 3;
        backgroundPanel.add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        backgroundPanel.add(registerButton, gbc);

        // Add background panel to the frame
        add(backgroundPanel);

        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    boolean loginSuccess = DBConnection.loginUser(username, password);
                    if (loginSuccess) {
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                        // Redirect to dashboard or main page
                        new DashboardPage();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error during login!");
                }
            }
        });

        // Register button action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterPage();
                dispose();
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage(); // Start the LoginPage UI
    }
}
