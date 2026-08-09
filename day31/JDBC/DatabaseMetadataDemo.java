package JDBC;

import java.sql.*;

// DatabaseMetaData and ResultSetMetaData: Retrieving database and result information
public class DatabaseMetadataDemo {
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
            
            // ========== 1. DatabaseMetaData ==========
            System.out.println("=== DATABASE METADATA ===\n");
            
            DatabaseMetaData dbmd = conn.getMetaData();
            
            // Database information
            System.out.println("--- Database Information ---");
            System.out.println("Database Product Name: " + dbmd.getDatabaseProductName());
            System.out.println("Database Product Version: " + dbmd.getDatabaseProductVersion());
            System.out.println("Database Major Version: " + dbmd.getDatabaseMajorVersion());
            System.out.println("Database Minor Version: " + dbmd.getDatabaseMinorVersion());
            
            // Driver information
            System.out.println("\n--- Driver Information ---");
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverVersion());
            System.out.println("Driver Major Version: " + dbmd.getDriverMajorVersion());
            System.out.println("Driver Minor Version: " + dbmd.getDriverMinorVersion());
            
            // Connection information
            System.out.println("\n--- Connection Information ---");
            System.out.println("URL: " + dbmd.getURL());
            System.out.println("User Name: " + dbmd.getUserName());
            System.out.println("Is Read Only: " + dbmd.isReadOnly());
            
            // Features support
            System.out.println("\n--- Feature Support ---");
            System.out.println("Supports Transactions: " + dbmd.supportsTransactions());
            System.out.println("Supports Batch Updates: " + dbmd.supportsBatchUpdates());
            System.out.println("Supports Savepoints: " + dbmd.supportsSavepoints());
            System.out.println("Supports Stored Procedures: " + dbmd.supportsStoredProcedures());
            System.out.println("Supports Multiple ResultSets: " + dbmd.supportsMultipleResultSets());
            
            // SQL limits
            System.out.println("\n--- SQL Limits ---");
            System.out.println("Max Connections: " + dbmd.getMaxConnections());
            System.out.println("Max Columns in Table: " + dbmd.getMaxColumnsInTable());
            System.out.println("Max Table Name Length: " + dbmd.getMaxTableNameLength());
            System.out.println("Max Column Name Length: " + dbmd.getMaxColumnNameLength());
            
            // Create sample table for metadata demos
            stmt = conn.createStatement();
            
            stmt.execute("DROP TABLE IF EXISTS metadata_demo");
            stmt.execute("CREATE TABLE metadata_demo (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "email VARCHAR(100) UNIQUE, " +
                    "age INT CHECK (age >= 18), " +
                    "salary DECIMAL(10,2), " +
                    "hire_date DATE, " +
                    "is_active BOOLEAN DEFAULT TRUE)");
            
            stmt.execute("INSERT INTO metadata_demo (name, email, age, salary, hire_date) VALUES " +
                    "('John Doe', 'john@email.com', 30, 75000.50, '2020-01-15'), " +
                    "('Jane Smith', 'jane@email.com', 28, 82000.00, '2019-06-20'), " +
                    "('Bob Wilson', 'bob@email.com', 35, 68000.75, '2021-03-10')");
            
            // ========== 2. Table Metadata ==========
            System.out.println("\n=== TABLE METADATA ===\n");
            
            // Get all tables
            System.out.println("--- Tables in Database ---");
            rs = dbmd.getTables(null, null, "%", new String[]{"TABLE"});
            
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                String tableType = rs.getString("TABLE_TYPE");
                System.out.println("Table: " + tableName + " (" + tableType + ")");
            }
            rs.close();
            
            // Get columns of specific table
            System.out.println("\n--- Columns in metadata_demo Table ---");
            rs = dbmd.getColumns(null, null, "metadata_demo", "%");
            
            System.out.println("Column Name\tData Type\tSize\tNullable");
            System.out.println("------------------------------------------------");
            
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String dataType = rs.getString("TYPE_NAME");
                int columnSize = rs.getInt("COLUMN_SIZE");
                String isNullable = rs.getString("IS_NULLABLE");
                
                System.out.printf("%-15s\t%-10s\t%d\t%s%n", 
                        columnName, dataType, columnSize, isNullable);
            }
            rs.close();
            
            // ========== 3. Primary Keys ==========
            System.out.println("\n--- Primary Keys ---");
            rs = dbmd.getPrimaryKeys(null, null, "metadata_demo");
            
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String pkName = rs.getString("PK_NAME");
                int keySeq = rs.getInt("KEY_SEQ");
                System.out.println("Column: " + columnName + ", PK Name: " + pkName + ", Sequence: " + keySeq);
            }
            rs.close();
            
            // ========== 4. Indexes ==========
            System.out.println("\n--- Indexes ---");
            rs = dbmd.getIndexInfo(null, null, "metadata_demo", false, false);
            
            while (rs.next()) {
                String indexName = rs.getString("INDEX_NAME");
                String columnName = rs.getString("COLUMN_NAME");
                boolean nonUnique = rs.getBoolean("NON_UNIQUE");
                
                if (indexName != null) {
                    System.out.println("Index: " + indexName + 
                            ", Column: " + columnName + 
                            ", Unique: " + !nonUnique);
                }
            }
            rs.close();
            
            // ========== 5. ResultSetMetaData ==========
            System.out.println("\n=== RESULTSET METADATA ===\n");
            
            rs = stmt.executeQuery("SELECT * FROM metadata_demo LIMIT 1");
            ResultSetMetaData rsmd = rs.getMetaData();
            
            int columnCount = rsmd.getColumnCount();
            System.out.println("Column Count: " + columnCount);
            
            System.out.println("\n--- Column Details ---");
            System.out.println("No\tName\t\tType\t\tSize\tPrecision\tScale\tNullable\tAutoIncrement");
            System.out.println("---------------------------------------------------------------------------------");
            
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);
                String columnType = rsmd.getColumnTypeName(i);
                int columnSize = rsmd.getColumnDisplaySize(i);
                int precision = rsmd.getPrecision(i);
                int scale = rsmd.getScale(i);
                int nullable = rsmd.isNullable(i);
                boolean autoIncrement = rsmd.isAutoIncrement(i);
                
                String nullableStr = nullable == ResultSetMetaData.columnNullable ? "YES" : 
                                    nullable == ResultSetMetaData.columnNoNulls ? "NO" : "UNKNOWN";
                
                System.out.printf("%d\t%-15s\t%-10s\t%d\t%d\t\t%d\t%s\t\t%s%n",
                        i, columnName, columnType, columnSize, precision, scale, 
                        nullableStr, autoIncrement);
            }
            
            // Additional column properties
            System.out.println("\n--- Additional Column Properties ---");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("\nColumn " + i + " (" + rsmd.getColumnName(i) + "):");
                System.out.println("  Table Name: " + rsmd.getTableName(i));
                System.out.println("  Catalog Name: " + rsmd.getCatalogName(i));
                System.out.println("  Schema Name: " + rsmd.getSchemaName(i));
                System.out.println("  Column Label: " + rsmd.getColumnLabel(i));
                System.out.println("  Column Class: " + rsmd.getColumnClassName(i));
                System.out.println("  Is Searchable: " + rsmd.isSearchable(i));
                System.out.println("  Is Writable: " + rsmd.isWritable(i));
                System.out.println("  Is Read Only: " + rsmd.isReadOnly(i));
                System.out.println("  Is Currency: " + rsmd.isCurrency(i));
                System.out.println("  Is Signed: " + rsmd.isSigned(i));
            }
            
            rs.close();
            
            // ========== 6. Data Types Supported ==========
            System.out.println("\n=== SUPPORTED DATA TYPES ===\n");
            
            rs = dbmd.getTypeInfo();
            System.out.println("Type Name\t\tData Type\tPrecision");
            System.out.println("------------------------------------------------");
            
            int count = 0;
            while (rs.next() && count < 15) { // Limit to first 15 types
                String typeName = rs.getString("TYPE_NAME");
                int dataType = rs.getInt("DATA_TYPE");
                int precision = rs.getInt("PRECISION");
                
                System.out.printf("%-20s\t%d\t\t%d%n", typeName, dataType, precision);
                count++;
            }
            System.out.println("... (showing first 15 types)");
            rs.close();
            
            // ========== 7. Stored Procedures ==========
            System.out.println("\n=== STORED PROCEDURES ===\n");
            
            rs = dbmd.getProcedures(null, null, "%");
            
            System.out.println("Procedures in database:");
            boolean foundProcedures = false;
            while (rs.next()) {
                String procName = rs.getString("PROCEDURE_NAME");
                String procType = rs.getString("PROCEDURE_TYPE");
                System.out.println("  " + procName + " (Type: " + procType + ")");
                foundProcedures = true;
            }
            
            if (!foundProcedures) {
                System.out.println("  (No stored procedures found)");
            }
            
            rs.close();
            
            // ========== 8. Practical Use Case ==========
            System.out.println("\n=== PRACTICAL USE: Dynamic Query Generator ===\n");
            
            rs = stmt.executeQuery("SELECT * FROM metadata_demo");
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            
            // Generate dynamic table display
            System.out.print("| ");
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsmd.getColumnName(i) + " | ");
            }
            System.out.println();
            
            System.out.print("| ");
            for (int i = 1; i <= columnCount; i++) {
                System.out.print("---------- | ");
            }
            System.out.println();
            
            while (rs.next()) {
                System.out.print("| ");
                for (int i = 1; i <= columnCount; i++) {
                    Object value = rs.getObject(i);
                    System.out.print((value != null ? value.toString() : "NULL") + " | ");
                }
                System.out.println();
            }
            
            // ========== 9. Schema Information ==========
            System.out.println("\n=== SCHEMA INFORMATION ===\n");
            
            rs = dbmd.getSchemas();
            System.out.println("Available schemas:");
            while (rs.next()) {
                String schemaName = rs.getString("TABLE_SCHEM");
                System.out.println("  " + schemaName);
            }
            rs.close();
            
            // ========== 10. Catalogs ==========
            System.out.println("\n--- Catalogs ---");
            rs = dbmd.getCatalogs();
            System.out.println("Available catalogs:");
            while (rs.next()) {
                String catalogName = rs.getString("TABLE_CAT");
                System.out.println("  " + catalogName);
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
