package JDBC;

import java.sql.*;

// ResultSet Types: TYPE_FORWARD_ONLY, TYPE_SCROLL_INSENSITIVE, TYPE_SCROLL_SENSITIVE
// ResultSet Concurrency: CONCUR_READ_ONLY, CONCUR_UPDATABLE
public class ResultSetTypes {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "password";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("✓ Connected to database\n");
            
            // Create and populate sample table
            Statement setupStmt = conn.createStatement();
            setupStmt.execute("CREATE TABLE IF NOT EXISTS books (" +
                    "book_id INT PRIMARY KEY, " +
                    "title VARCHAR(100), " +
                    "author VARCHAR(50), " +
                    "price DOUBLE)");
            
            setupStmt.execute("DELETE FROM books");
            setupStmt.execute("INSERT INTO books VALUES (1, 'Java Programming', 'John Doe', 45.99)");
            setupStmt.execute("INSERT INTO books VALUES (2, 'Database Systems', 'Jane Smith', 55.50)");
            setupStmt.execute("INSERT INTO books VALUES (3, 'Web Development', 'Mike Johnson', 39.99)");
            setupStmt.execute("INSERT INTO books VALUES (4, 'Data Structures', 'Sarah Williams', 42.75)");
            setupStmt.execute("INSERT INTO books VALUES (5, 'Machine Learning', 'Robert Brown', 65.00)");
            setupStmt.close();
            System.out.println("✓ Sample books table created\n");
            
            // ========== 1. TYPE_FORWARD_ONLY (Default) ==========
            System.out.println("--- 1. TYPE_FORWARD_ONLY ResultSet ---");
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM books");
            
            System.out.println("Forward iteration only:");
            while (rs.next()) {
                System.out.println(rs.getInt("book_id") + ". " + rs.getString("title"));
            }
            
            // Try to move backward (will fail)
            try {
                rs.previous();
            } catch (SQLException e) {
                System.out.println("✗ Cannot move backward: " + e.getMessage());
            }
            
            rs.close();
            stmt.close();
            
            // ========== 2. TYPE_SCROLL_INSENSITIVE ==========
            System.out.println("\n--- 2. TYPE_SCROLL_INSENSITIVE ResultSet ---");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM books");
            
            // Move to last row
            rs.last();
            System.out.println("Last book: " + rs.getString("title"));
            
            // Move to first row
            rs.first();
            System.out.println("First book: " + rs.getString("title"));
            
            // Move to specific row
            rs.absolute(3);
            System.out.println("Book at position 3: " + rs.getString("title"));
            
            // Move backward
            rs.previous();
            System.out.println("Previous book: " + rs.getString("title"));
            
            // Check position
            System.out.println("Current row number: " + rs.getRow());
            System.out.println("Is first row? " + rs.isFirst());
            System.out.println("Is last row? " + rs.isLast());
            
            // Navigate relative to current position
            rs.relative(2); // Move forward 2 positions
            System.out.println("After relative(2): " + rs.getString("title"));
            
            rs.close();
            stmt.close();
            
            // ========== 3. TYPE_SCROLL_SENSITIVE ==========
            System.out.println("\n--- 3. TYPE_SCROLL_SENSITIVE ResultSet ---");
            System.out.println("(Note: Not all JDBC drivers support this fully)");
            
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM books WHERE book_id <= 3");
            
            rs.first();
            System.out.println("First book before update: " + rs.getString("title") + " - $" + rs.getDouble("price"));
            
            // Update from another statement
            Statement updateStmt = conn.createStatement();
            updateStmt.executeUpdate("UPDATE books SET price = 49.99 WHERE book_id = 1");
            updateStmt.close();
            System.out.println("✓ Price updated in database");
            
            // Refresh to see changes (driver-dependent)
            rs.refreshRow();
            System.out.println("After refresh: " + rs.getString("title") + " - $" + rs.getDouble("price"));
            
            rs.close();
            stmt.close();
            
            // ========== 4. CONCUR_UPDATABLE ==========
            System.out.println("\n--- 4. CONCUR_UPDATABLE ResultSet ---");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT * FROM books WHERE book_id = 2");
            
            if (rs.next()) {
                System.out.println("Before update: " + rs.getString("title") + " - $" + rs.getDouble("price"));
                
                // Update row directly from ResultSet
                rs.updateDouble("price", 59.99);
                rs.updateRow();
                System.out.println("✓ Row updated via ResultSet");
                
                // Verify update
                rs.beforeFirst();
                rs.next();
                System.out.println("After update: " + rs.getString("title") + " - $" + rs.getDouble("price"));
            }
            
            rs.close();
            
            // ========== 5. Insert Row via ResultSet ==========
            System.out.println("\n--- 5. Insert Row via Updatable ResultSet ---");
            rs = stmt.executeQuery("SELECT * FROM books");
            
            // Move to insert row
            rs.moveToInsertRow();
            rs.updateInt("book_id", 6);
            rs.updateString("title", "Cloud Computing");
            rs.updateString("author", "Emily Davis");
            rs.updateDouble("price", 52.50);
            rs.insertRow();
            System.out.println("✓ New book inserted via ResultSet");
            
            rs.moveToCurrentRow();
            rs.close();
            
            // ========== 6. Delete Row via ResultSet ==========
            System.out.println("\n--- 6. Delete Row via Updatable ResultSet ---");
            rs = stmt.executeQuery("SELECT * FROM books WHERE book_id = 6");
            
            if (rs.next()) {
                System.out.println("Deleting: " + rs.getString("title"));
                rs.deleteRow();
                System.out.println("✓ Row deleted via ResultSet");
            }
            
            rs.close();
            stmt.close();
            
            // ========== 7. ResultSet Metadata ==========
            System.out.println("\n--- 7. ResultSet Metadata ---");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM books LIMIT 1");
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            System.out.println("Column Count: " + columnCount);
            System.out.println("\nColumn Details:");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Column " + i + ":");
                System.out.println("  Name: " + rsmd.getColumnName(i));
                System.out.println("  Type: " + rsmd.getColumnTypeName(i));
                System.out.println("  Size: " + rsmd.getColumnDisplaySize(i));
                System.out.println("  Nullable: " + (rsmd.isNullable(i) == ResultSetMetaData.columnNullable));
            }
            
            rs.close();
            stmt.close();
            
            // ========== 8. Display Final Table ==========
            System.out.println("\n--- Final Books Table ---");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM books ORDER BY book_id");
            
            System.out.println("ID\tTitle\t\t\t\tAuthor\t\t\tPrice");
            System.out.println("---------------------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%d\t%-25s\t%-20s\t$%.2f%n",
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("\n✓ Resources closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
