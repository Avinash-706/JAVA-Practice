package JDBC;

import java.sql.*;

// Transaction Management: ACID properties, Commit, Rollback, Savepoint
public class TransactionManagement {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "password";
        
        Connection conn = null;
        Statement stmt = null;
        Savepoint savepoint1 = null;
        Savepoint savepoint2 = null;
        
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("✓ Connected to database");
            
            stmt = conn.createStatement();
            
            // Create accounts table
            String createTable = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "account_id INT PRIMARY KEY, " +
                    "account_holder VARCHAR(50), " +
                    "balance DOUBLE)";
            stmt.execute(createTable);
            
            // Insert sample accounts
            stmt.execute("DELETE FROM accounts");
            stmt.execute("INSERT INTO accounts VALUES (1, 'Alice', 5000.0)");
            stmt.execute("INSERT INTO accounts VALUES (2, 'Bob', 3000.0)");
            stmt.execute("INSERT INTO accounts VALUES (3, 'Charlie', 2000.0)");
            System.out.println("✓ Sample accounts created");
            
            // Display initial balances
            displayAccounts(stmt);
            
            // =========== Transaction Example 1: Money Transfer ===========
            System.out.println("\n--- Transaction Example 1: Money Transfer (Success) ---");
            
            // Disable auto-commit (Start transaction)
            conn.setAutoCommit(false);
            System.out.println("✓ Auto-commit disabled (Transaction started)");
            
            // Transfer $500 from Alice to Bob
            stmt.executeUpdate("UPDATE accounts SET balance = balance - 500 WHERE account_id = 1");
            System.out.println("Step 1: Deducted $500 from Alice");
            
            stmt.executeUpdate("UPDATE accounts SET balance = balance + 500 WHERE account_id = 2");
            System.out.println("Step 2: Added $500 to Bob");
            
            // Commit transaction
            conn.commit();
            System.out.println("✓ Transaction committed successfully");
            
            displayAccounts(stmt);
            
            // =========== Transaction Example 2: Rollback on Error ===========
            System.out.println("\n--- Transaction Example 2: Rollback on Error ---");
            
            try {
                // Start new transaction
                stmt.executeUpdate("UPDATE accounts SET balance = balance - 1000 WHERE account_id = 2");
                System.out.println("Step 1: Deducted $1000 from Bob");
                
                // Simulate error - trying to add to non-existent account
                stmt.executeUpdate("UPDATE accounts SET balance = balance + 1000 WHERE account_id = 999");
                System.out.println("Step 2: Attempted to add $1000 to account 999");
                
                conn.commit();
                
            } catch (SQLException e) {
                // Error occurred, rollback transaction
                conn.rollback();
                System.out.println("✗ Error occurred: " + e.getMessage());
                System.out.println("✓ Transaction rolled back - Bob's balance restored");
            }
            
            displayAccounts(stmt);
            
            // =========== Transaction Example 3: Savepoints ===========
            System.out.println("\n--- Transaction Example 3: Using Savepoints ---");
            
            // Start transaction
            System.out.println("Starting complex transaction with savepoints...");
            
            // Operation 1: Deduct from Alice
            stmt.executeUpdate("UPDATE accounts SET balance = balance - 300 WHERE account_id = 1");
            System.out.println("Operation 1: Deducted $300 from Alice");
            savepoint1 = conn.setSavepoint("Savepoint1");
            System.out.println("✓ Savepoint1 created");
            
            // Operation 2: Add to Bob
            stmt.executeUpdate("UPDATE accounts SET balance = balance + 300 WHERE account_id = 2");
            System.out.println("Operation 2: Added $300 to Bob");
            savepoint2 = conn.setSavepoint("Savepoint2");
            System.out.println("✓ Savepoint2 created");
            
            // Operation 3: Deduct from Charlie (will fail)
            try {
                stmt.executeUpdate("UPDATE accounts SET balance = balance - 5000 WHERE account_id = 3");
                System.out.println("Operation 3: Deducted $5000 from Charlie");
                
                // Check if balance is negative
                ResultSet rs = stmt.executeQuery("SELECT balance FROM accounts WHERE account_id = 3");
                rs.next();
                double balance = rs.getDouble(1);
                rs.close();
                
                if (balance < 0) {
                    throw new SQLException("Insufficient funds - negative balance!");
                }
                
            } catch (SQLException e) {
                // Rollback to Savepoint2
                conn.rollback(savepoint2);
                System.out.println("✗ Operation 3 failed: " + e.getMessage());
                System.out.println("✓ Rolled back to Savepoint2 (Charlie's balance unchanged)");
            }
            
            // Commit remaining operations
            conn.commit();
            System.out.println("✓ Transaction committed (Operations 1 & 2 successful)");
            
            displayAccounts(stmt);
            
            // =========== Transaction Isolation Levels ===========
            System.out.println("\n--- Transaction Isolation Levels ---");
            
            int isolationLevel = conn.getTransactionIsolation();
            System.out.println("Current Isolation Level: " + getIsolationLevelName(isolationLevel));
            
            System.out.println("\nAvailable Isolation Levels:");
            System.out.println("1. TRANSACTION_READ_UNCOMMITTED - " + Connection.TRANSACTION_READ_UNCOMMITTED);
            System.out.println("2. TRANSACTION_READ_COMMITTED   - " + Connection.TRANSACTION_READ_COMMITTED);
            System.out.println("3. TRANSACTION_REPEATABLE_READ  - " + Connection.TRANSACTION_REPEATABLE_READ);
            System.out.println("4. TRANSACTION_SERIALIZABLE     - " + Connection.TRANSACTION_SERIALIZABLE);
            
            // Set isolation level
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            System.out.println("\n✓ Isolation level set to SERIALIZABLE");
            
            // =========== Batch Transaction ===========
            System.out.println("\n--- Batch Transaction Example ---");
            
            conn.setAutoCommit(false);
            
            stmt.addBatch("UPDATE accounts SET balance = balance + 100 WHERE account_id = 1");
            stmt.addBatch("UPDATE accounts SET balance = balance + 100 WHERE account_id = 2");
            stmt.addBatch("UPDATE accounts SET balance = balance + 100 WHERE account_id = 3");
            
            int[] results = stmt.executeBatch();
            System.out.println("✓ Batch executed: " + results.length + " operations");
            
            conn.commit();
            System.out.println("✓ Batch transaction committed (Added $100 to all accounts)");
            
            displayAccounts(stmt);
            
            // Restore auto-commit
            conn.setAutoCommit(true);
            System.out.println("\n✓ Auto-commit restored");
            
        } catch (SQLException e) {
            System.err.println("Transaction Error: " + e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("✓ Transaction rolled back");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true); // Restore default
                    conn.close();
                }
                System.out.println("\n✓ Resources closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Helper method to display all accounts
    private static void displayAccounts(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM accounts ORDER BY account_id");
        System.out.println("\n--- Current Account Balances ---");
        System.out.println("ID\tHolder\t\tBalance");
        System.out.println("--------------------------------");
        
        while (rs.next()) {
            System.out.printf("%d\t%s\t\t$%.2f%n",
                    rs.getInt("account_id"),
                    rs.getString("account_holder"),
                    rs.getDouble("balance"));
        }
        rs.close();
    }
    
    // Helper method to get isolation level name
    private static String getIsolationLevelName(int level) {
        switch (level) {
            case Connection.TRANSACTION_READ_UNCOMMITTED:
                return "READ_UNCOMMITTED";
            case Connection.TRANSACTION_READ_COMMITTED:
                return "READ_COMMITTED";
            case Connection.TRANSACTION_REPEATABLE_READ:
                return "REPEATABLE_READ";
            case Connection.TRANSACTION_SERIALIZABLE:
                return "SERIALIZABLE";
            default:
                return "UNKNOWN";
        }
    }
}
