import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardPage {
    private JFrame frame;
    private JButton resumeAnalyzerButton;
    private JButton hrPageButton;

    public DashboardPage() {
        frame = new JFrame("Dashboard");
        frame.setSize(1400, 800);  // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the window

        // Create a custom JPanel for background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set background image (ensure this path is correct)
                ImageIcon icon = new ImageIcon("C:\\Users\\Admin\\Desktop\\53003230043\\Job\\src\\bg.jpg");  // Background image
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new GridBagLayout());  // Use GridBagLayout for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Add spacing between components
        gbc.anchor = GridBagConstraints.CENTER;  // Anchor to center

        // Create components (labels, buttons)
        JLabel headingLabel = new JLabel("Dashboard Page");
        resumeAnalyzerButton = new JButton("Go to Employee Page");
        hrPageButton = new JButton("Go to HR Page");

        // Set font for heading and buttons
        Font headingFont = new Font("Arial", Font.BOLD, 28);  // Larger font size for heading
        Font buttonFont = new Font("Arial", Font.BOLD, 20);  // Larger font size for buttons

        // Apply fonts to the components
        headingLabel.setFont(headingFont);
        resumeAnalyzerButton.setFont(buttonFont);
        hrPageButton.setFont(buttonFont);

        // Set text color for heading, buttons and background
        headingLabel.setForeground(Color.WHITE);
        resumeAnalyzerButton.setBackground(new Color(60, 179, 113));  // Green color
        resumeAnalyzerButton.setForeground(Color.WHITE);
        hrPageButton.setBackground(new Color(70, 130, 180));  // Blue color
        hrPageButton.setForeground(Color.WHITE);

        // Place heading label at the top, centered
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Span across two columns to center it
        backgroundPanel.add(headingLabel, gbc);

        // Place resume analyzer button below heading label
        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(resumeAnalyzerButton, gbc);

        // Place HR page button below resume analyzer button
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(hrPageButton, gbc);

        // Add background panel to the frame
        frame.add(backgroundPanel);

        // Action for Resume Analyzer Button
        resumeAnalyzerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ResumeAnalyzerUI();  // Open ResumeAnalyzerUI
                frame.setVisible(false);  // Close DashboardPage
            }
        });

        // Action for HR Page Button
        hrPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HrPageUI();  // Open HrPageUI
                frame.setVisible(false);  // Close DashboardPage
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new DashboardPage();  // Start the DashboardPage UI
    }
}
