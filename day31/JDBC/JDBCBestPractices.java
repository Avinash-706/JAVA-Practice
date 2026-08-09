package JDBC;

import java.sql.*;

public class JDBCBestPractices {
    
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void main(String[] args) {
        
        System.out.println("=== JDBC BEST PRACTICES ===\n");
        
        demonstrateResourceManagement();
        demonstrateExceptionHandling();
        demonstratePreparedStatementReuse();
        demonstrateBatchProcessing();
        demonstrateTransactionManagement();
    }
    
    private static void demonstrateResourceManagement() {
        System.out.println("--- 1. Proper Resource Management (Try-with-Resources) ---\n");
        
        String sql = "SELECT COUNT(*) FROM information_schema.tables";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                System.out.println("✓ Query executed successfully");
                System.out.println("Table count: " + rs.getInt(1));
            }
            
            System.out.println("✓ Resources automatically closed");
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("\nBenefit: Automatic resource cleanup, prevents memory leaks\n");
    }
    
    private static void demonstrateExceptionHandling() {
        System.out.println("--- 2. Proper Exception Handling ---\n");
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM non_existent_table")) {
            
            pstmt.executeQuery();
            
        } catch (SQLSyntaxErrorException e) {
            System.out.println("✓ Caught syntax error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error Code: " + e.getErrorCode());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Message: " + e.getMessage());
        }
        
        System.out.println("\nBenefit: Specific exception handling for better error recovery\n");
    }
    
    private static void demonstratePreparedStatementReuse() {
        System.out.println("--- 3. PreparedStatement Reuse ---\n");
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE IF NOT EXISTS temp_test (id INT, name VARCHAR(50))");
            stmt.execute("DELETE FROM temp_test");
            
            String sql = "INSERT INTO temp_test VALUES (?, ?)";
            
            long startTime = System.currentTimeMillis();
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (int i = 1; i <= 100; i++) {
                    pstmt.setInt(1, i);
                    pstmt.setString(2, "Name" + i);
                    pstmt.executeUpdate();
                }
            }
            
            long endTime = System.currentTimeMillis();
            
            System.out.println("✓ Inserted 100 records");
            System.out.println("Time taken: " + (endTime - startTime) + "ms");
            System.out.println("\nBenefit: Precompiled SQL, better performance, SQL injection safe\n");
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateBatchProcessing() {
        System.out.println("--- 4. Batch Processing for Bulk Operations ---\n");
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE IF NOT EXISTS batch_test (id INT, value VARCHAR(50))");
            stmt.execute("DELETE FROM batch_test");
            
            String sql = "INSERT INTO batch_test VALUES (?, ?)";
            
            long startTime = System.currentTimeMillis();
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (int i = 1; i <= 1000; i++) {
                    pstmt.setInt(1, i);
                    pstmt.setString(2, "Value" + i);
                    pstmt.addBatch();
                    
                    if (i % 100 == 0) {
                        pstmt.executeBatch();
                        pstmt.clearBatch();
                    }
                }
                pstmt.executeBatch();
            }
            
            long endTime = System.currentTimeMillis();
            
            System.out.println("✓ Inserted 1000 records using batch");
            System.out.println("Time taken: " + (endTime - startTime) + "ms");
            System.out.println("\nBenefit: Reduced network calls, much faster for bulk operations\n");
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateTransactionManagement() {
        System.out.println("--- 5. Transaction Management ---\n");
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE IF NOT EXISTS trans_test (id INT, balance DOUBLE)");
            stmt.execute("DELETE FROM trans_test");
            stmt.execute("INSERT INTO trans_test VALUES (1, 1000)");
            stmt.execute("INSERT INTO trans_test VALUES (2, 500)");
            
            conn.setAutoCommit(false);
            
            try (PreparedStatement pstmt = conn.prepareStatement("UPDATE trans_test SET balance = balance + ? WHERE id = ?")) {
                
                pstmt.setDouble(1, -200);
                pstmt.setInt(2, 1);
                pstmt.executeUpdate();
                
                pstmt.setDouble(1, 200);
                pstmt.setInt(2, 2);
                pstmt.executeUpdate();
                
                conn.commit();
                System.out.println("✓ Transaction committed successfully");
                
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("✗ Transaction rolled back");
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
            
            System.out.println("\nBenefit: Data consistency, ACID properties maintained\n");
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    static {
        System.out.println("\n=== ADDITIONAL BEST PRACTICES ===\n");
        System.out.println("6. Connection Pooling");
        System.out.println("   • Use connection pools (HikariCP, Apache DBCP)");
        System.out.println("   • Reuse connections instead of creating new ones");
        
        System.out.println("\n7. Avoid SELECT *");
        System.out.println("   • Specify columns explicitly");
        System.out.println("   • Reduces network overhead and memory usage");
        
        System.out.println("\n8. Use Appropriate Fetch Size");
        System.out.println("   • stmt.setFetchSize(100) for large result sets");
        System.out.println("   • Reduces memory consumption");
        
        System.out.println("\n9. Close Resources in Reverse Order");
        System.out.println("   • ResultSet → Statement → Connection");
        System.out.println("   • Or use try-with-resources");
        
        System.out.println("\n10. Avoid Business Logic in Database");
        System.out.println("   • Keep SQL simple");
        System.out.println("   • Use stored procedures sparingly");
        
        System.out.println("\n11. Use Appropriate Isolation Levels");
        System.out.println("   • Choose based on consistency vs performance needs");
        
        System.out.println("\n12. Enable Statement Caching");
        System.out.println("   • Improves performance for repeated queries");
        
        System.out.println("\n13. Monitor and Log SQL Queries");
        System.out.println("   • Track slow queries");
        System.out.println("   • Identify performance bottlenecks");
        
        System.out.println("\n14. Use DAO Pattern");
        System.out.println("   • Separate data access from business logic");
        System.out.println("   • Improves maintainability and testability");
        
        System.out.println("\n15. Handle NULL Values Properly");
        System.out.println("   • Use rs.wasNull() to check for NULL");
        System.out.println("   • Set NULL using setNull() method");
        
        System.out.println("\n=====================================\n");
    }
}
