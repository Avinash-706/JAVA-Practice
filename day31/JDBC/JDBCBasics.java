package JDBC;

import java.sql.*;

// JDBC Basics: Connection, Statement, ResultSet
public class JDBCBasics {
    public static void main(String[] args) {
        
        // JDBC URL format: jdbc:mysql://hostname:port/database
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "password";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Step 1: Load and Register Driver (automatic since JDBC 4.0)
            // Class.forName("com.mysql.cj.jdbc.Driver"); // Not needed in modern JDBC
            
            // Step 2: Establish Connection
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("✓ Database connected successfully");
            System.out.println("Connection Object: " + connection.getClass().getName());
            
            // Step 3: Create Statement
            statement = connection.createStatement();
            System.out.println("✓ Statement created");
            
            // Step 4: Execute Query - DDL (Data Definition Language)
            String createTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50), " +
                    "age INT, " +
                    "grade VARCHAR(2))";
            statement.execute(createTable);
            System.out.println("✓ Table 'students' created");
            
            // Step 5: Execute Query - DML (Data Manipulation Language)
            // INSERT operations
            String insert1 = "INSERT INTO students (name, age, grade) VALUES ('Alice', 20, 'A')";
            String insert2 = "INSERT INTO students (name, age, grade) VALUES ('Bob', 21, 'B')";
            String insert3 = "INSERT INTO students (name, age, grade) VALUES ('Charlie', 22, 'A')";
            
            int rows1 = statement.executeUpdate(insert1);
            int rows2 = statement.executeUpdate(insert2);
            int rows3 = statement.executeUpdate(insert3);
            System.out.println("✓ Inserted " + (rows1 + rows2 + rows3) + " rows");
            
            // Step 6: Execute SELECT Query
            String selectQuery = "SELECT * FROM students";
            resultSet = statement.executeQuery(selectQuery);
            
            // Step 7: Process ResultSet
            System.out.println("\n--- Student Records ---");
            System.out.println("ID\tName\tAge\tGrade");
            System.out.println("----------------------------");
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String grade = resultSet.getString("grade");
                
                System.out.println(id + "\t" + name + "\t" + age + "\t" + grade);
            }
            
            // UPDATE operation
            String updateQuery = "UPDATE students SET age = 23 WHERE name = 'Alice'";
            int updatedRows = statement.executeUpdate(updateQuery);
            System.out.println("\n✓ Updated " + updatedRows + " row(s)");
            
            // DELETE operation
            String deleteQuery = "DELETE FROM students WHERE name = 'Bob'";
            int deletedRows = statement.executeUpdate(deleteQuery);
            System.out.println("✓ Deleted " + deletedRows + " row(s)");
            
            // Verify changes
            resultSet = statement.executeQuery(selectQuery);
            System.out.println("\n--- Updated Records ---");
            System.out.println("ID\tName\tAge\tGrade");
            System.out.println("----------------------------");
            
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "\t" + 
                        resultSet.getString("name") + "\t" + 
                        resultSet.getInt("age") + "\t" + 
                        resultSet.getString("grade"));
            }
            
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Step 8: Close Resources (in reverse order)
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("\n✓ Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
