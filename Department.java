import java.sql.*;

public class Department {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    // JDBC variables for opening, closing and managing connection
    private static Connection connection;
    private static PreparedStatement preparedStatement;

    // Method to insert a Department object into the database
    public static void insertDepartment(Department department) {
        try {
            // Open a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // SQL query to insert a new department
            String sql = "INSERT INTO departments (id, name) VALUES (?, ?)";

            // Create a PreparedStatement object to execute SQL query
            preparedStatement = connection.prepareStatement(sql);

            // Set parameters for PreparedStatement
            preparedStatement.setInt(1, department.getId());
            preparedStatement.setString(2, department.getName());

            // Execute PreparedStatement to insert data
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new department was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close PreparedStatement and connection
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Example usage:
        Department department = new Department(1, "IT");
        insertDepartment(department);
    }
}

class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
