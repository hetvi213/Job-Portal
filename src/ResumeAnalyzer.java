import java.sql.*;

public class ResumeAnalyzer {
    
    // Simulate saving a resume to the database
    public static void addCandidate(Candidate candidate) throws SQLException {
        Connection connection = DBConnection.connect();
        String query = "INSERT INTO candidates (name, email, phone, skills) VALUES (?, ?, ?, ?)";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, candidate.getName());
        statement.setString(2, candidate.getEmail());
        statement.setString(3, candidate.getPhone());
        statement.setString(4, candidate.getSkills());
        
        statement.executeUpdate();
        connection.close();
    }
    
    // Simulate searching for candidates based on skills
    public static ResultSet findCandidatesBySkills(String skill) throws SQLException {
        Connection connection = DBConnection.connect();
        String query = "SELECT * FROM candidates WHERE skills LIKE ?";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + skill + "%");
        
        return statement.executeQuery();
    }
}
