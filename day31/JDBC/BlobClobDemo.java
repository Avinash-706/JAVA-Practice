package JDBC;

import java.io.*;
import java.sql.*;

public class BlobClobDemo {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✓ Connected to database\n");
            
            Statement stmt = conn.createStatement();
            
            String createTable = "CREATE TABLE IF NOT EXISTS documents (" +
                    "doc_id INT PRIMARY KEY, " +
                    "doc_name VARCHAR(100), " +
                    "doc_content BLOB, " +
                    "doc_description CLOB)";
            stmt.execute(createTable);
            System.out.println("✓ Table 'documents' created");
            stmt.close();
            
            System.out.println("\n--- BLOB (Binary Large Object) Demo ---");
            
            String binaryData = "This is binary content that will be stored as BLOB";
            byte[] blobData = binaryData.getBytes();
            
            String insertSQL = "INSERT INTO documents (doc_id, doc_name, doc_content) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setInt(1, 1);
            pstmt.setString(2, "Sample Document");
            pstmt.setBytes(3, blobData);
            pstmt.executeUpdate();
            System.out.println("✓ BLOB data inserted");
            pstmt.close();
            
            String selectSQL = "SELECT doc_id, doc_name, doc_content FROM documents WHERE doc_id = 1";
            pstmt = conn.prepareStatement(selectSQL);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("doc_id");
                String name = rs.getString("doc_name");
                byte[] content = rs.getBytes("doc_content");
                String contentStr = new String(content);
                
                System.out.println("\nRetrieved BLOB data:");
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Content: " + contentStr);
            }
            rs.close();
            pstmt.close();
            
            System.out.println("\n--- CLOB (Character Large Object) Demo ---");
            
            String clobData = "This is a large text document stored as CLOB. " +
                    "CLOBs are useful for storing large amounts of text data like " +
                    "articles, descriptions, or documentation.";
            
            pstmt = conn.prepareStatement("UPDATE documents SET doc_description = ? WHERE doc_id = 1");
            pstmt.setString(1, clobData);
            pstmt.executeUpdate();
            System.out.println("✓ CLOB data inserted");
            pstmt.close();
            
            pstmt = conn.prepareStatement("SELECT doc_description FROM documents WHERE doc_id = 1");
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String description = rs.getString("doc_description");
                System.out.println("\nRetrieved CLOB data:");
                System.out.println(description);
            }
            rs.close();
            pstmt.close();
            
            System.out.println("\n--- Storing File as BLOB ---");
            
            String fileContent = "Sample file content for BLOB storage demonstration";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
            
            pstmt = conn.prepareStatement("INSERT INTO documents (doc_id, doc_name, doc_content) VALUES (?, ?, ?)");
            pstmt.setInt(1, 2);
            pstmt.setString(2, "File Document");
            pstmt.setBinaryStream(3, inputStream, fileContent.length());
            pstmt.executeUpdate();
            System.out.println("✓ File stored as BLOB using stream");
            pstmt.close();
            
            pstmt = conn.prepareStatement("SELECT doc_content FROM documents WHERE doc_id = 2");
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                InputStream retrievedStream = rs.getBinaryStream("doc_content");
                BufferedReader reader = new BufferedReader(new InputStreamReader(retrievedStream));
                String line = reader.readLine();
                
                System.out.println("\nRetrieved file content:");
                System.out.println(line);
                
                reader.close();
            }
            rs.close();
            pstmt.close();
            
            System.out.println("\n--- Use Cases ---");
            System.out.println("BLOB: Images, PDFs, audio files, video files, encrypted data");
            System.out.println("CLOB: Articles, documentation, XML data, JSON data, large text");
            
        } catch (SQLException | IOException e) {
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
