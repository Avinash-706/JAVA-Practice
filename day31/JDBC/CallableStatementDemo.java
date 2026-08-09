package JDBC;

import java.sql.*;

// CallableStatement: Execute stored procedures and functions
public class CallableStatementDemo {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "password";
        
        Connection conn = null;
        Statement stmt = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("✓ Connected to database");
            
            stmt = conn.createStatement();
            
            // Create table for demo
            String createTable = "CREATE TABLE IF NOT EXISTS products (" +
                    "product_id INT PRIMARY KEY, " +
                    "product_name VARCHAR(50), " +
                    "price DOUBLE, " +
                    "stock INT)";
            stmt.execute(createTable);
            
            // Insert sample data
            stmt.execute("DELETE FROM products"); // Clear existing data
            stmt.execute("INSERT INTO products VALUES (1, 'Laptop', 1200.0, 50)");
            stmt.execute("INSERT INTO products VALUES (2, 'Mouse', 25.0, 200)");
            stmt.execute("INSERT INTO products VALUES (3, 'Keyboard', 75.0, 150)");
            stmt.execute("INSERT INTO products VALUES (4, 'Monitor', 300.0, 80)");
            System.out.println("✓ Sample data inserted");
            
            // Drop procedures if they exist
            try {
                stmt.execute("DROP PROCEDURE IF EXISTS GetProductCount");
                stmt.execute("DROP PROCEDURE IF EXISTS GetProductInfo");
                stmt.execute("DROP PROCEDURE IF EXISTS UpdateProductPrice");
                stmt.execute("DROP PROCEDURE IF EXISTS GetProductsByPriceRange");
            } catch (SQLException e) {
                // Ignore if procedures don't exist
            }
            
            // Create Stored Procedure 1: OUT parameter
            String procedure1 = 
                "CREATE PROCEDURE GetProductCount(OUT total INT) " +
                "BEGIN " +
                "   SELECT COUNT(*) INTO total FROM products; " +
                "END";
            stmt.execute(procedure1);
            System.out.println("✓ Procedure 'GetProductCount' created");
            
            // Create Stored Procedure 2: IN and OUT parameters
            String procedure2 = 
                "CREATE PROCEDURE GetProductInfo(IN prod_id INT, OUT prod_name VARCHAR(50), OUT prod_price DOUBLE) " +
                "BEGIN " +
                "   SELECT product_name, price INTO prod_name, prod_price " +
                "   FROM products WHERE product_id = prod_id; " +
                "END";
            stmt.execute(procedure2);
            System.out.println("✓ Procedure 'GetProductInfo' created");
            
            // Create Stored Procedure 3: INOUT parameter
            String procedure3 = 
                "CREATE PROCEDURE UpdateProductPrice(IN prod_id INT, INOUT new_price DOUBLE) " +
                "BEGIN " +
                "   DECLARE old_price DOUBLE; " +
                "   SELECT price INTO old_price FROM products WHERE product_id = prod_id; " +
                "   UPDATE products SET price = new_price WHERE product_id = prod_id; " +
                "   SET new_price = old_price; " +
                "END";
            stmt.execute(procedure3);
            System.out.println("✓ Procedure 'UpdateProductPrice' created");
            
            // Create Stored Procedure 4: Returns ResultSet
            String procedure4 = 
                "CREATE PROCEDURE GetProductsByPriceRange(IN min_price DOUBLE, IN max_price DOUBLE) " +
                "BEGIN " +
                "   SELECT * FROM products WHERE price BETWEEN min_price AND max_price; " +
                "END";
            stmt.execute(procedure4);
            System.out.println("✓ Procedure 'GetProductsByPriceRange' created");
            
            stmt.close();
            
            // ============ Execute Stored Procedures ============
            
            // 1. Call procedure with OUT parameter
            System.out.println("\n--- Calling GetProductCount ---");
            cstmt = conn.prepareCall("{CALL GetProductCount(?)}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.execute();
            
            int totalProducts = cstmt.getInt(1);
            System.out.println("Total products in database: " + totalProducts);
            cstmt.close();
            
            // 2. Call procedure with IN and OUT parameters
            System.out.println("\n--- Calling GetProductInfo ---");
            cstmt = conn.prepareCall("{CALL GetProductInfo(?, ?, ?)}");
            cstmt.setInt(1, 1); // IN parameter
            cstmt.registerOutParameter(2, Types.VARCHAR); // OUT parameter
            cstmt.registerOutParameter(3, Types.DOUBLE);  // OUT parameter
            cstmt.execute();
            
            String productName = cstmt.getString(2);
            double productPrice = cstmt.getDouble(3);
            System.out.println("Product ID 1: " + productName + ", Price: $" + productPrice);
            cstmt.close();
            
            // 3. Call procedure with INOUT parameter
            System.out.println("\n--- Calling UpdateProductPrice ---");
            cstmt = conn.prepareCall("{CALL UpdateProductPrice(?, ?)}");
            cstmt.setInt(1, 2); // IN parameter (product_id)
            cstmt.setDouble(2, 30.0); // INOUT parameter (new price)
            cstmt.registerOutParameter(2, Types.DOUBLE);
            cstmt.execute();
            
            double oldPrice = cstmt.getDouble(2);
            System.out.println("Product ID 2: Old price = $" + oldPrice + ", Updated to $30.0");
            cstmt.close();
            
            // 4. Call procedure that returns ResultSet
            System.out.println("\n--- Calling GetProductsByPriceRange ---");
            cstmt = conn.prepareCall("{CALL GetProductsByPriceRange(?, ?)}");
            cstmt.setDouble(1, 50.0);  // min_price
            cstmt.setDouble(2, 500.0); // max_price
            
            rs = cstmt.executeQuery();
            System.out.println("Products in price range $50 - $500:");
            System.out.println("ID\tName\t\tPrice\tStock");
            System.out.println("----------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%d\t%s\t$%.2f\t%d%n",
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getDouble("price"),
                        rs.getInt("stock"));
            }
            
            // Stored Function Example (MySQL)
            System.out.println("\n--- Creating and Calling Stored Function ---");
            stmt = conn.createStatement();
            
            try {
                stmt.execute("DROP FUNCTION IF EXISTS CalculateDiscount");
            } catch (SQLException e) {
                // Ignore
            }
            
            String function = 
                "CREATE FUNCTION CalculateDiscount(original_price DOUBLE, discount_percent DOUBLE) " +
                "RETURNS DOUBLE " +
                "DETERMINISTIC " +
                "BEGIN " +
                "   RETURN original_price - (original_price * discount_percent / 100); " +
                "END";
            stmt.execute(function);
            System.out.println("✓ Function 'CalculateDiscount' created");
            
            // Call function
            cstmt.close();
            cstmt = conn.prepareCall("{? = CALL CalculateDiscount(?, ?)}");
            cstmt.registerOutParameter(1, Types.DOUBLE);
            cstmt.setDouble(2, 1200.0); // original price
            cstmt.setDouble(3, 10.0);   // 10% discount
            cstmt.execute();
            
            double discountedPrice = cstmt.getDouble(1);
            System.out.println("Original Price: $1200, After 10% discount: $" + discountedPrice);
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (cstmt != null) cstmt.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("\n✓ Resources closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
