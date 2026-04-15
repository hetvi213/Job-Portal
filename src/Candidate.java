public class Candidate {
    private String name;
    private String email;
    private String phone;
    private String skills;

    // Constructor
    public Candidate(String name, String email, String phone, String skills) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return name + " | " + email + " | " + phone + " | " + skills;
    }
}
