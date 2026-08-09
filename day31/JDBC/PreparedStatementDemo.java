package JDBC;

import java.sql.*;

// PreparedStatement: Precompiled SQL with parameter binding (SQL Injection Safe)
public class PreparedStatementDemo {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "password";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("✓ Connected to database");
            
            // Create table
            String createTable = "CREATE TABLE IF NOT EXISTS employees (" +
                    "emp_id INT PRIMARY KEY, " +
                    "emp_name VARCHAR(50), " +
                    "department VARCHAR(30), " +
                    "salary DOUBLE)";
            Statement stmt = conn.createStatement();
            stmt.execute(createTable);
            stmt.close();
            System.out.println("✓ Table 'employees' ready");
            
            // 1. INSERT using PreparedStatement
            String insertSQL = "INSERT INTO employees (emp_id, emp_name, department, salary) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertSQL);
            
            // Insert multiple records efficiently
            Object[][] employees = {
                {101, "John Doe", "IT", 75000.0},
                {102, "Jane Smith", "HR", 65000.0},
                {103, "Mike Johnson", "Finance", 80000.0},
                {104, "Sarah Williams", "IT", 72000.0},
                {105, "Robert Brown", "Marketing", 68000.0}
            };
            
            for (Object[] emp : employees) {
                pstmt.setInt(1, (Integer) emp[0]);
                pstmt.setString(2, (String) emp[1]);
                pstmt.setString(3, (String) emp[2]);
                pstmt.setDouble(4, (Double) emp[3]);
                pstmt.executeUpdate();
            }
            System.out.println("✓ Inserted " + employees.length + " employees");
            
            pstmt.close();
            
            // 2. SELECT using PreparedStatement
            String selectSQL = "SELECT * FROM employees WHERE department = ?";
            pstmt = conn.prepareStatement(selectSQL);
            pstmt.setString(1, "IT");
            
            rs = pstmt.executeQuery();
            System.out.println("\n--- IT Department Employees ---");
            System.out.println("ID\tName\t\tDepartment\tSalary");
            System.out.println("-----------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%d\t%s\t%s\t\t%.2f%n",
                        rs.getInt("emp_id"),
                        rs.getString("emp_name"),
                        rs.getString("department"),
                        rs.getDouble("salary"));
            }
            
            rs.close();
            pstmt.close();
            
            // 3. UPDATE using PreparedStatement
            String updateSQL = "UPDATE employees SET salary = salary * ? WHERE department = ?";
            pstmt = conn.prepareStatement(updateSQL);
            pstmt.setDouble(1, 1.10); // 10% increment
            pstmt.setString(2, "IT");
            
            int updatedCount = pstmt.executeUpdate();
            System.out.println("\n✓ Updated salary for " + updatedCount + " IT employees (10% increment)");
            
            pstmt.close();
            
            // 4. SELECT with multiple conditions
            String complexSelectSQL = "SELECT * FROM employees WHERE salary > ? AND department = ?";
            pstmt = conn.prepareStatement(complexSelectSQL);
            pstmt.setDouble(1, 70000.0);
            pstmt.setString(2, "IT");
            
            rs = pstmt.executeQuery();
            System.out.println("\n--- IT Employees with Salary > 70000 ---");
            System.out.println("ID\tName\t\tSalary");
            System.out.println("--------------------------------");
            
            while (rs.next()) {
                System.out.printf("%d\t%s\t%.2f%n",
                        rs.getInt("emp_id"),
                        rs.getString("emp_name"),
                        rs.getDouble("salary"));
            }
            
            rs.close();
            pstmt.close();
            
            // 5. DELETE using PreparedStatement
            String deleteSQL = "DELETE FROM employees WHERE emp_id = ?";
            pstmt = conn.prepareStatement(deleteSQL);
            pstmt.setInt(1, 105);
            
            int deletedCount = pstmt.executeUpdate();
            System.out.println("\n✓ Deleted " + deletedCount + " employee(s)");
            
            // 6. Batch Processing (Advanced)
            pstmt.close();
            String batchInsertSQL = "INSERT INTO employees (emp_id, emp_name, department, salary) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(batchInsertSQL);
            
            pstmt.setInt(1, 106);
            pstmt.setString(2, "Alice Cooper");
            pstmt.setString(3, "Sales");
            pstmt.setDouble(4, 62000.0);
            pstmt.addBatch();
            
            pstmt.setInt(1, 107);
            pstmt.setString(2, "David Lee");
            pstmt.setString(3, "Operations");
            pstmt.setDouble(4, 58000.0);
            pstmt.addBatch();
            
            int[] batchResults = pstmt.executeBatch();
            System.out.println("✓ Batch inserted " + batchResults.length + " records");
            
            // SQL Injection Prevention Demo
            System.out.println("\n--- SQL Injection Prevention ---");
            String userInput = "IT' OR '1'='1"; // Malicious input
            
            String safeQuery = "SELECT * FROM employees WHERE department = ?";
            pstmt.close();
            pstmt = conn.prepareStatement(safeQuery);
            pstmt.setString(1, userInput); // Safe - treated as literal string
            
            rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) count++;
            
            System.out.println("Query with malicious input returned " + count + " rows (Safe)");
            System.out.println("PreparedStatement prevents SQL injection by parameter binding");
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                System.out.println("\n✓ Resources closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
