package JDBC;

import javax.sql.rowset.*;
import java.sql.*;

public class RowSetDemo {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password";
        
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✓ Connected to database\n");
            
            stmt = conn.createStatement();
            
            stmt.execute("CREATE TABLE IF NOT EXISTS employees_rs (" +
                    "emp_id INT PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "salary DOUBLE)");
            
            stmt.execute("DELETE FROM employees_rs");
            stmt.execute("INSERT INTO employees_rs VALUES (1, 'Alice', 75000)");
            stmt.execute("INSERT INTO employees_rs VALUES (2, 'Bob', 65000)");
            stmt.execute("INSERT INTO employees_rs VALUES (3, 'Charlie', 80000)");
            System.out.println("✓ Sample data created\n");
            
            stmt.close();
            conn.close();
            
            System.out.println("--- JdbcRowSet (Connected) ---");
            
            JdbcRowSet jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet();
            jdbcRowSet.setUrl(url);
            jdbcRowSet.setUsername(user);
            jdbcRowSet.setPassword(password);
            jdbcRowSet.setCommand("SELECT * FROM employees_rs");
            jdbcRowSet.execute();
            
            System.out.println("ID\tName\t\tSalary");
            System.out.println("------------------------------");
            while (jdbcRowSet.next()) {
                System.out.printf("%d\t%s\t\t%.2f%n",
                        jdbcRowSet.getInt("emp_id"),
                        jdbcRowSet.getString("name"),
                        jdbcRowSet.getDouble("salary"));
            }
            
            jdbcRowSet.absolute(2);
            jdbcRowSet.updateDouble("salary", 70000);
            jdbcRowSet.updateRow();
            System.out.println("\n✓ Updated Bob's salary using JdbcRowSet");
            
            jdbcRowSet.close();
            
            System.out.println("\n--- CachedRowSet (Disconnected) ---");
            
            CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            cachedRowSet.setUrl(url);
            cachedRowSet.setUsername(user);
            cachedRowSet.setPassword(password);
            cachedRowSet.setCommand("SELECT * FROM employees_rs");
            cachedRowSet.execute();
            
            System.out.println("After disconnecting from database:");
            System.out.println("ID\tName\t\tSalary");
            System.out.println("------------------------------");
            
            while (cachedRowSet.next()) {
                System.out.printf("%d\t%s\t\t%.2f%n",
                        cachedRowSet.getInt("emp_id"),
                        cachedRowSet.getString("name"),
                        cachedRowSet.getDouble("salary"));
            }
            
            cachedRowSet.beforeFirst();
            cachedRowSet.next();
            cachedRowSet.updateDouble("salary", 78000);
            cachedRowSet.updateRow();
            
            conn = DriverManager.getConnection(url, user, password);
            cachedRowSet.acceptChanges(conn);
            System.out.println("\n✓ Changes synchronized back to database");
            conn.close();
            
            cachedRowSet.close();
            
            System.out.println("\n--- WebRowSet (XML Format) ---");
            
            WebRowSet webRowSet = RowSetProvider.newFactory().createWebRowSet();
            webRowSet.setUrl(url);
            webRowSet.setUsername(user);
            webRowSet.setPassword(password);
            webRowSet.setCommand("SELECT * FROM employees_rs");
            webRowSet.execute();
            
            System.out.println("Writing RowSet to XML format:");
            java.io.StringWriter writer = new java.io.StringWriter();
            webRowSet.writeXml(writer);
            String xml = writer.toString();
            System.out.println(xml.substring(0, Math.min(500, xml.length())) + "...");
            
            webRowSet.close();
            
            System.out.println("\n--- FilteredRowSet ---");
            
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees_rs");
            
            FilteredRowSet filteredRowSet = RowSetProvider.newFactory().createFilteredRowSet();
            filteredRowSet.populate(rs);
            
            filteredRowSet.setFilter(new Predicate() {
                public boolean evaluate(javax.sql.RowSet rs) {
                    try {
                        return rs.getDouble("salary") > 70000;
                    } catch (SQLException e) {
                        return false;
                    }
                }
                public boolean evaluate(Object value, int column) { return true; }
                public boolean evaluate(Object value, String columnName) { return true; }
            });
            
            System.out.println("Employees with salary > 70000:");
            System.out.println("ID\tName\t\tSalary");
            System.out.println("------------------------------");
            
            filteredRowSet.beforeFirst();
            while (filteredRowSet.next()) {
                System.out.printf("%d\t%s\t\t%.2f%n",
                        filteredRowSet.getInt("emp_id"),
                        filteredRowSet.getString("name"),
                        filteredRowSet.getDouble("salary"));
            }
            
            filteredRowSet.close();
            rs.close();
            stmt.close();
            conn.close();
            
            System.out.println("\n--- RowSet Benefits ---");
            System.out.println("• JdbcRowSet: Scrollable & updatable wrapper for ResultSet");
            System.out.println("• CachedRowSet: Works disconnected from database");
            System.out.println("• WebRowSet: Serializes data to/from XML");
            System.out.println("• FilteredRowSet: Provides filtering capabilities");
            System.out.println("• JoinRowSet: Joins multiple RowSets without SQL");
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("\n✓ Resources closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
