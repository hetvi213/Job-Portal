
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class HrPageUI {
    private JFrame hrPanel;
    private JTable candidatesTable;
    private JComboBox<String> skillsComboBox;
    private List<Candidate> allCandidates;
    private JPanel filterPanel;
    private JLabel descriptionLabel; 

    public HrPageUI() {
        // Initialize the candidates list (mock data for now)
        allCandidates = DBConnection.getAllCandidates();  // Ensure this returns some mock data or actual candidates

        hrPanel = new JFrame("HR Page - Select Candidates");
        hrPanel.setSize(1400, 800);  // Set the window size
        hrPanel.setLocationRelativeTo(null);  // Center the window
        hrPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\Admin\\Desktop\\53003230043\\Job\\src\\background.jpg"); // Path to your background image
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        hrPanel.setContentPane(backgroundPanel);  // Set the background panel as the content pane

        // Create the filter panel with ComboBox for selecting a skill
        filterPanel = new JPanel();
        filterPanel.setOpaque(false);  // Make filter panel transparent
        filterPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        descriptionLabel = new JLabel("<html><h2>Select Candidates for Interview</h2><p>Use the filter below to choose candidates based on their skills.</p></html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        descriptionLabel.setForeground(Color.BLACK);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
           
        JLabel skillsLabel = new JLabel("Use the filter below to choose candidates based on their skills.   Select a skill:");
        skillsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        skillsLabel.setForeground(Color.BLACK);
        filterPanel.add(skillsLabel);

        // Skill options for filtering
        String[] availableSkills = {"ASP.Net", "C", "CSS", "C++", "C#", "Dart", "Flutter", "Go", "HTML", "Java", "JS", "Kotlin", "MongoDB", "Node JS", "Perl", "PHP", "Python", "React JS", "React Native", "Ruby", "Rust", "Swift", "SQL", "TomCat", "TypeScript", "Web Development"};
        skillsComboBox = new JComboBox<>(availableSkills);
        skillsComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        skillsComboBox.setBackground(new Color(240, 240, 240));
        filterPanel.add(skillsComboBox);

        // Button to filter candidates
        JButton filterButton = new JButton("Filter Candidates");
        filterButton.setFont(new Font("Arial", Font.BOLD, 18));
        filterButton.setBackground(new Color(70, 130, 180));  // Blue color
        filterButton.setForeground(Color.WHITE);
        filterPanel.add(filterButton);

        // Back Button to go back to the Dashboard
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(new Color(255, 69, 0));  // Red color
        backButton.setForeground(Color.WHITE);
        filterPanel.add(backButton);

        hrPanel.add(filterPanel, BorderLayout.NORTH);

        // Initially load all candidates in the table
        updateCandidateTable(allCandidates);

        // Filter Button ActionListener
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSkill = (String) skillsComboBox.getSelectedItem();

                if (selectedSkill == null || selectedSkill.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a skill to filter by.");
                    return;
                }

                // Filter candidates based on the selected skill
                List<Candidate> filteredCandidates = filterCandidatesBySkill(selectedSkill);

                // Update the table with the filtered candidates
                updateCandidateTable(filteredCandidates);
            }
        });

        // Back Button ActionListener - Redirect to Dashboard
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hrPanel.dispose();  // Close current HR page window
                new DashboardPage();  // Open DashboardPage
            }
        });

        hrPanel.setVisible(true);
    }

    // Method to update the table with filtered candidates
    private void updateCandidateTable(List<Candidate> candidates) {
        String[][] data = new String[candidates.size()][4];
        for (int i = 0; i < candidates.size(); i++) {
            Candidate candidate = candidates.get(i);
            data[i][0] = candidate.getName();
            data[i][1] = candidate.getEmail();
            data[i][2] = candidate.getPhone();
            data[i][3] = candidate.getSkills();
        }

        String[] columns = {"Name", "Email", "Phone", "Skills"};
        DefaultTableModel model = new DefaultTableModel(data, columns);
        candidatesTable = new JTable(model);

        // Table formatting - Set preferred column widths
        TableColumn column = null;
        for (int i = 0; i < 4; i++) {
            column = candidatesTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(150); // Name column
            } else if (i == 1) {
                column.setPreferredWidth(200); // Email column
            } else if (i == 2) {
                column.setPreferredWidth(150); // Phone column
            } else if (i == 3) {
                column.setPreferredWidth(250); // Skills column
            }
        }

        candidatesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Align all columns to center (optional)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 4; i++) {
            candidatesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set the preferred size of the table to remove the scrollbar
        candidatesTable.setPreferredScrollableViewportSize(new Dimension(1300, 800));

        // Center the table by setting a FlowLayout for the center content
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);  // Make center panel transparent
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(candidatesTable);

        // Remove the previous content and add the new components
        hrPanel.getContentPane().removeAll();
        hrPanel.add(descriptionLabel, BorderLayout.NORTH);
        hrPanel.add(centerPanel, BorderLayout.CENTER);  // Add the table to center
        hrPanel.add(filterPanel, BorderLayout.NORTH);  // Keep the filter panel on top
        hrPanel.revalidate();
        hrPanel.repaint();
    }

    // Method to filter candidates based on selected skill
    private List<Candidate> filterCandidatesBySkill(String selectedSkill) {
        List<Candidate> filteredCandidates = new ArrayList<>();
        for (Candidate candidate : allCandidates) {
            String[] candidateSkills = candidate.getSkills().split(",\\s*");  // Split skills by comma and space

            for (String skill : candidateSkills) {
                if (skill.equalsIgnoreCase(selectedSkill)) {
                    filteredCandidates.add(candidate);
                    break;  // No need to check further skills for this candidate
                }
            }
        }
        return filteredCandidates;
    }

    public static void main(String[] args) {
        new HrPageUI();  // Start the HR Page UI
    }
}

/*
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class HrPageUI {
    private JFrame hrPanel;
    private JTable candidatesTable;
    private JComboBox<String> skillsComboBox;
    private List<Candidate> allCandidates;
    private JPanel filterPanel;
    private JLabel descriptionLabel;

    public HrPageUI() {
        // Initialize the candidates list (mock data for now)
        allCandidates = DBConnection.getAllCandidates();  // Ensure this returns some mock data or actual candidates

        hrPanel = new JFrame("HR Page - Select Candidates");
        hrPanel.setSize(1400, 800);  // Set the window size
        hrPanel.setLocationRelativeTo(null);  // Center the window
        hrPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       // Create the description label
descriptionLabel = new JLabel("<html><h2>Select Candidates for Interview</h2><p>Use the filter below to choose candidates based on their skills.</p></html>");
descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 22));
descriptionLabel.setForeground(Color.BLACK);
descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

// Set up the background panel
JPanel backgroundPanel = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("C:\\Users\\Admin\\Desktop\\53003230043\\Job\\src\\background.jpg"); // Background image path
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
};
backgroundPanel.setLayout(new BorderLayout());
hrPanel.setContentPane(backgroundPanel);  // Set the background panel as the content pane

// Add description label to the top (NORTH)
backgroundPanel.add(descriptionLabel, BorderLayout.NORTH);

// Create the filter panel and other components (ComboBox, Buttons)
filterPanel = new JPanel();
filterPanel.setOpaque(false);  // Make filter panel transparent
filterPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

JLabel skillsLabel = new JLabel("Select a skill:");
skillsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
skillsLabel.setForeground(Color.BLACK);
filterPanel.add(skillsLabel);

// Skill options for filtering
String[] availableSkills = {"ASP.Net", "C", "CSS", "C++", "C#", "Dart", "Flutter", "Go", "HTML", "Java", "JS", "Kotlin", "MongoDB", "Node JS", "Perl", "PHP", "Python", "React JS", "React Native", "Ruby", "Rust", "Swift", "SQL", "TomCat", "TypeScript", "Web Development"};
skillsComboBox = new JComboBox<>(availableSkills);
skillsComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
skillsComboBox.setBackground(new Color(240, 240, 240));
filterPanel.add(skillsComboBox);

// Button to filter candidates
JButton filterButton = new JButton("Filter Candidates");
filterButton.setFont(new Font("Arial", Font.BOLD, 18));
filterButton.setBackground(new Color(70, 130, 180));  // Blue color
filterButton.setForeground(Color.WHITE);
filterPanel.add(filterButton);

// Back Button to go back to the Dashboard
JButton backButton = new JButton("Back");
backButton.setFont(new Font("Arial", Font.BOLD, 18));
backButton.setBackground(new Color(255, 69, 0));  // Red color
backButton.setForeground(Color.WHITE);
filterPanel.add(backButton);

// Add the filterPanel below the description label (CENTER)
backgroundPanel.add(filterPanel, BorderLayout.SOUTH);

// Initially load all candidates in the table
updateCandidateTable(allCandidates);


        // Back Button ActionListener - Redirect to Dashboard
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hrPanel.dispose();  // Close current HR page window
                new DashboardPage();  // Open DashboardPage
            }
        });

        hrPanel.setVisible(true);
    }

    // Method to update the table with filtered candidates
    private void updateCandidateTable(List<Candidate> candidates) {
        String[][] data = new String[candidates.size()][4];
        for (int i = 0; i < candidates.size(); i++) {
            Candidate candidate = candidates.get(i);
            data[i][0] = candidate.getName();
            data[i][1] = candidate.getEmail();
            data[i][2] = candidate.getPhone();
            data[i][3] = candidate.getSkills();
        }

        String[] columns = {"Name", "Email", "Phone", "Skills"};
        DefaultTableModel model = new DefaultTableModel(data, columns);
        candidatesTable = new JTable(model);

        // Table formatting - Set preferred column widths
        TableColumn column = null;
        for (int i = 0; i < 4; i++) {
            column = candidatesTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(150); // Name column
            } else if (i == 1) {
                column.setPreferredWidth(200); // Email column
            } else if (i == 2) {
                column.setPreferredWidth(150); // Phone column
            } else if (i == 3) {
                column.setPreferredWidth(250); // Skills column
            }
        }

        candidatesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Align all columns to center (optional)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 4; i++) {
            candidatesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set the preferred size of the table to remove the scrollbar
        candidatesTable.setPreferredScrollableViewportSize(new Dimension(1300, 500));

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(candidatesTable);
        scrollPane.setPreferredSize(new Dimension(1300, 500));

        // Center the table and place the filter panel below it
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Remove the previous content and add the new components
        hrPanel.getContentPane().removeAll();
        hrPanel.add(descriptionLabel, BorderLayout.NORTH);  // Add description label back
        hrPanel.add(centerPanel, BorderLayout.CENTER);  // Add the table in the center
        hrPanel.add(filterPanel, BorderLayout.SOUTH);  // Place filter panel below the table
        hrPanel.revalidate();
        hrPanel.repaint();
    }

    // Method to filter candidates based on selected skill
    private List<Candidate> filterCandidatesBySkill(String selectedSkill) {
        List<Candidate> filteredCandidates = new ArrayList<>();
        for (Candidate candidate : allCandidates) {
            String[] candidateSkills = candidate.getSkills().split(",\\s*");  // Split skills by comma and space

            for (String skill : candidateSkills) {
                if (skill.equalsIgnoreCase(selectedSkill)) {
                    filteredCandidates.add(candidate);
                    break;  // No need to check further skills for this candidate
                }
            }
        }
        return filteredCandidates;
    }

    public static void main(String[] args) {
        new HrPageUI();  // Start the HR Page UI
    }
}
*/