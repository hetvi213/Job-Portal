# Job Portal

A desktop job portal built with Java Swing and MySQL. Candidates can create an account, sign in, add their profile and skills, while HR users can browse candidates and filter them by skill.

## Features

- User registration and login
- Candidate profile creation with contact details and skills
- HR candidate directory
- Skill-based candidate filtering
- Java Swing desktop interface
- MySQL persistence through JDBC

## Tech stack

- Java 8+
- Java Swing
- MySQL
- JDBC (MySQL Connector/J 5.1.26 is included in `lib/`)
- Apache Ant / NetBeans

## Prerequisites

- JDK 8 or newer
- MySQL Server
- Apache Ant, or NetBeans with Java SE support

## Database setup

Create the database and required tables in MySQL:

```sql
CREATE DATABASE resume_analyzer;
USE resume_analyzer;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE candidates (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(30),
    skills TEXT
);
```

The application currently connects with these defaults:

```text
URL:      jdbc:mysql://localhost:3306/resume_analyzer
Username: root
Password: root
```

If your MySQL settings differ, update the connection values in `src/DBConnection.java`.

> Note: Passwords are currently stored as plain text. Hash passwords and move database credentials out of the source code before using this project in production.

## Run the project

### NetBeans

1. Open the repository as a project in NetBeans.
2. Confirm that the MySQL connector under `lib/` is available in the project libraries.
3. Start MySQL and create the database tables shown above.
4. Run the project. The configured main class is `Main`.

### Apache Ant

From the project directory, run:

```bash
ant clean jar
ant run
```

The packaged application is generated at `dist/Job.jar`.

## Project structure

```text
Job-Portal/
|-- src/                    Java source files and UI images
|   |-- Main.java           Application entry point
|   |-- LoginPage.java      Login screen
|   |-- RegisterPage.java   Registration screen
|   |-- DashboardPage.java  Navigation dashboard
|   |-- ResumeAnalyzerUI.java
|   |-- HrPageUI.java
|   |-- DBConnection.java   JDBC database operations
|   `-- Candidate.java      Candidate model
|-- lib/                    MySQL JDBC driver and Ant libraries
|-- nbproject/              NetBeans project configuration
`-- build.xml               Ant build configuration
```

## Known configuration note

Some Swing screens reference background images with an absolute Windows path. If an image does not appear, replace that path in the relevant class with the path to `src/bg.jpg` or `src/background.jpg` on your machine.

## License

No license has been specified for this project.
