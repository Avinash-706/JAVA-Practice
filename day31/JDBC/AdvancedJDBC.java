package JDBC;

import java.sql.*;
import java.util.*;

public class AdvancedJDBC {
    
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    public static void main(String[] args) {
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✓ Connected to database\n");
            
            setupDatabase(conn);
            
            demonstrateGeneratedKeys(conn);
            demonstrateMultipleResultSets(conn);
            demonstrateScrollableCursor(conn);
            demonstrateParameterMetadata(conn);
            demonstrateBlobStreaming(conn);
            demonstrateWarnings(conn);
            demonstrateArrayType(conn);
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void setupDatabase(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        stmt.execute("CREATE TABLE IF NOT EXISTS advanced_demo (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "age INT, " +
                "salary DOUBLE, " +
                "hire_date DATE)");
        
        stmt.execute("DELETE FROM advanced_demo");
        stmt.close();
    }
    
    private static void demonstrateGeneratedKeys(Connection conn) throws SQLException {
        System.out.println("--- 1. Retrieve Generated Keys ---\n");
        
        String sql = "INSERT INTO advanced_demo (name, age, salary, hire_date) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        pstmt.setString(1, "John Doe");
        pstmt.setInt(2, 30);
        pstmt.setDouble(3, 75000);
        pstmt.setDate(4, java.sql.Date.valueOf("2023-01-15"));
        pstmt.executeUpdate();
        
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            int generatedId = rs.getInt(1);
            System.out.println("✓ Inserted record with ID: " + generatedId);
        }
        
        rs.close();
        pstmt.close();
        System.out.println();
    }
    
    private static void demonstrateMultipleResultSets(Connection conn) throws SQLException {
        System.out.println("--- 2. Multiple ResultSets ---\n");
        
        Statement stmt = conn.createStatement();
        
        stmt.execute("INSERT INTO advanced_demo (name, age, salary, hire_date) VALUES " +
                "('Alice', 28, 82000, '2022-05-10'), " +
                "('Bob', 35, 95000, '2021-03-20')");
        
        String multiQuery = "SELECT name, age FROM advanced_demo WHERE age < 32; " +
                           "SELECT name, salary FROM advanced_demo WHERE salary > 80000";
        
        boolean hasResults = stmt.execute(multiQuery);
        int resultSetCount = 0;
        
        while (hasResults || stmt.getUpdateCount() != -1) {
            if (hasResults) {
                resultSetCount++;
                ResultSet rs = stmt.getResultSet();
                System.out.println("ResultSet " + resultSetCount + ":");
                
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rsmd.getColumnName(i) + ": " + rs.getString(i) + " ");
                    }
                    System.out.println();
                }
                rs.close();
            }
            
            hasResults = stmt.getMoreResults();
        }
        
        stmt.close();
        System.out.println();
    }
    
    private static void demonstrateScrollableCursor(Connection conn) throws SQLException {
        System.out.println("--- 3. Scrollable Cursor Navigation ---\n");
        
        Statement stmt = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        
        ResultSet rs = stmt.executeQuery("SELECT id, name, age FROM advanced_demo");
        
        rs.last();
        int rowCount = rs.getRow();
        System.out.println("Total rows: " + rowCount);
        
        rs.absolute(2);
        System.out.println("Row 2: " + rs.getString("name"));
        
        rs.relative(-1);
        System.out.println("Previous row: " + rs.getString("name"));
        
        rs.first();
        System.out.println("First row: " + rs.getString("name"));
        
        rs.close();
        stmt.close();
        System.out.println();
    }
    
    private static void demonstrateParameterMetadata(Connection conn) throws SQLException {
        System.out.println("--- 4. Parameter Metadata ---\n");
        
        String sql = "SELECT * FROM advanced_demo WHERE age > ? AND salary < ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        ParameterMetaData pmd = pstmt.getParameterMetaData();
        
        System.out.println("Parameter Count: " + pmd.getParameterCount());
        
        for (int i = 1; i <= pmd.getParameterCount(); i++) {
            System.out.println("Parameter " + i + ":");
            System.out.println("  Type: " + pmd.getParameterTypeName(i));
            System.out.println("  Mode: " + getParameterMode(pmd.getParameterMode(i)));
            System.out.println("  Nullable: " + (pmd.isNullable(i) == ParameterMetaData.parameterNullable));
        }
        
        pstmt.close();
        System.out.println();
    }
    
    private static String getParameterMode(int mode) {
        switch (mode) {
            case ParameterMetaData.parameterModeIn: return "IN";
            case ParameterMetaData.parameterModeOut: return "OUT";
            case ParameterMetaData.parameterModeInOut: return "INOUT";
            default: return "UNKNOWN";
        }
    }
    
    private static void demonstrateBlobStreaming(Connection conn) throws SQLException {
        System.out.println("--- 5. BLOB Streaming ---\n");
        
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS blob_demo (id INT PRIMARY KEY, data BLOB)");
        stmt.execute("DELETE FROM blob_demo");
        stmt.close();
        
        byte[] data = "Large binary data for streaming demo".getBytes();
        
        String sql = "INSERT INTO blob_demo (id, data) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, 1);
        pstmt.setBlob(2, new java.io.ByteArrayInputStream(data));
        pstmt.executeUpdate();
        System.out.println("✓ BLOB data inserted using stream");
        
        pstmt.close();
        
        pstmt = conn.prepareStatement("SELECT data FROM blob_demo WHERE id = 1");
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            Blob blob = rs.getBlob("data");
            byte[] retrievedData = blob.getBytes(1, (int) blob.length());
            System.out.println("Retrieved: " + new String(retrievedData));
        }
        
        rs.close();
        pstmt.close();
        System.out.println();
    }
    
    private static void demonstrateWarnings(Connection conn) throws SQLException {
        System.out.println("--- 6. SQL Warnings ---\n");
        
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS warning_demo (id INT, value TINYINT)");
        stmt.execute("DELETE FROM warning_demo");
        
        stmt.executeUpdate("INSERT INTO warning_demo VALUES (1, 200)");
        
        SQLWarning warning = stmt.getWarnings();
        if (warning != null) {
            System.out.println("Warning detected:");
            while (warning != null) {
                System.out.println("  Message: " + warning.getMessage());
                System.out.println("  SQL State: " + warning.getSQLState());
                System.out.println("  Error Code: " + warning.getErrorCode());
                warning = warning.getNextWarning();
            }
        } else {
            System.out.println("No warnings (value truncated silently)");
        }
        
        stmt.clearWarnings();
        stmt.close();
        System.out.println();
    }
    
    private static void demonstrateArrayType(Connection conn) throws SQLException {
        System.out.println("--- 7. Working with NULL Values ---\n");
        
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO advanced_demo (name, age, salary) VALUES ('Test User', NULL, NULL)");
        stmt.close();
        
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM advanced_demo WHERE name = 'Test User'");
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            int age = rs.getInt("age");
            boolean ageWasNull = rs.wasNull();
            
            double salary = rs.getDouble("salary");
            boolean salaryWasNull = rs.wasNull();
            
            System.out.println("Age: " + (ageWasNull ? "NULL" : age));
            System.out.println("Salary: " + (salaryWasNull ? "NULL" : salary));
        }
        
        rs.close();
        pstmt.close();
        
        pstmt = conn.prepareStatement("UPDATE advanced_demo SET age = ?, salary = ? WHERE name = 'Test User'");
        pstmt.setNull(1, Types.INTEGER);
        pstmt.setNull(2, Types.DOUBLE);
        pstmt.executeUpdate();
        System.out.println("✓ NULL values set explicitly");
        
        pstmt.close();
        System.out.println();
    }
    
    static {
        System.out.println("\n=== ADVANCED JDBC TECHNIQUES ===\n");
        System.out.println("8. Custom Type Mapping");
        System.out.println("   • Map SQL types to Java classes");
        System.out.println("   • Use SQLData interface for custom objects");
        
        System.out.println("\n9. Statement Pooling");
        System.out.println("   • Cache PreparedStatement objects");
        System.out.println("   • Improves performance for repeated queries");
        
        System.out.println("\n10. Large Result Set Handling");
        System.out.println("   • Use setFetchSize() for memory management");
        System.out.println("   • Stream processing for huge datasets");
        
        System.out.println("\n11. Asynchronous JDBC (Java 9+)");
        System.out.println("   • Non-blocking database operations");
        System.out.println("   • CompletableFuture integration");
        
        System.out.println("\n12. Multi-threaded Access");
        System.out.println("   • One connection per thread");
        System.out.println("   • Use connection pooling");
        System.out.println("   • Synchronize shared resources");
        
        System.out.println("\n=====================================\n");
    }
}
