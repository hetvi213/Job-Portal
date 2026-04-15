
/*
import java.sql.*;
import java.util.*;

public class DBConnection {
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/resume_analyzer", "root", "root");
    }

    public static void addCandidate(Candidate candidate) throws SQLException {
        String query = "INSERT INTO candidates (name, email, phone, skills) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getEmail());
            stmt.setString(3, candidate.getPhone());
            stmt.setString(4, candidate.getSkills());
            stmt.executeUpdate();
        }
    }

    public static List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM candidates";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Candidate candidate = new Candidate(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("skills")
                );
                candidates.add(candidate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }
}
*/

import java.sql.*;
import java.util.*;

public class DBConnection {

    // Establishing the database connection
    public static Connection connect() throws SQLException {
        try {
            // Replace with your actual database connection credentials
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/resume_analyzer", "root", "root");
        } catch (SQLException e) {
            // Handle connection errors here
            System.err.println("Connection failed: " + e.getMessage());
            throw e;
        }
    }

    // Add a new candidate to the database
    public static void addCandidate(Candidate candidate) throws SQLException {
        String query = "INSERT INTO candidates (name, email, phone, skills) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getEmail());
            stmt.setString(3, candidate.getPhone());
            stmt.setString(4, candidate.getSkills());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Handle potential errors during insertion
            System.err.println("Error adding candidate: " + e.getMessage());
            throw e;
        }
    }

    // Fetch all candidates from the database
    public static List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM candidates";
        
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Candidate candidate = new Candidate(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("skills")
                );
                candidates.add(candidate);
            }
        } catch (SQLException e) {
            // Handle errors while fetching data
            System.err.println("Error retrieving candidates: " + e.getMessage());
        }
        return candidates;
    }

    // Registration method - add a new user
    public static boolean registerUser(String username, String password) throws SQLException {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);  // In a real application, you should hash the password before storing it
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Error during registration: " + e.getMessage());
            return false;
        }
    }

    // Login method - check if user credentials are valid
    public static boolean loginUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);  // Again, hash the password in a real application
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // If user exists, the result set will have data
        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
            return false;
        }
    }
}
