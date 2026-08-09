package JDBC;

import java.sql.*;
import java.util.*;

public class ConnectionPooling {
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 5;
    private static int MAX_POOL_SIZE = 10;
    
    public ConnectionPooling(String url, String user, String password) throws SQLException {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
        
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            connectionPool.add(createConnection());
        }
    }
    
    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
    public synchronized Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(createConnection());
            } else {
                throw new SQLException("Maximum pool size reached, no available connections!");
            }
        }
        
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }
    
    public synchronized boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }
    
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
    
    public void shutdown() throws SQLException {
        for (Connection c : usedConnections) {
            c.close();
        }
        usedConnections.clear();
        
        for (Connection c : connectionPool) {
            c.close();
        }
        connectionPool.clear();
    }
    
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password";
        
        ConnectionPooling pool = null;
        
        try {
            pool = new ConnectionPooling(url, user, password);
            System.out.println("✓ Connection pool created with " + INITIAL_POOL_SIZE + " connections");
            System.out.println("Pool size: " + pool.getSize());
            
            Connection conn1 = pool.getConnection();
            System.out.println("\n✓ Connection 1 acquired");
            System.out.println("Available connections: " + pool.connectionPool.size());
            
            Connection conn2 = pool.getConnection();
            System.out.println("✓ Connection 2 acquired");
            System.out.println("Available connections: " + pool.connectionPool.size());
            
            Statement stmt = conn1.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS pool_test (id INT, data VARCHAR(50))");
            stmt.execute("INSERT INTO pool_test VALUES (1, 'Pool Test')");
            System.out.println("\n✓ Executed queries using pooled connection");
            stmt.close();
            
            pool.releaseConnection(conn1);
            System.out.println("\n✓ Connection 1 released back to pool");
            System.out.println("Available connections: " + pool.connectionPool.size());
            
            Connection conn3 = pool.getConnection();
            System.out.println("✓ Connection 3 acquired (reused from pool)");
            
            pool.releaseConnection(conn2);
            pool.releaseConnection(conn3);
            System.out.println("✓ All connections released");
            System.out.println("Available connections: " + pool.connectionPool.size());
            
            System.out.println("\n--- Benefits of Connection Pooling ---");
            System.out.println("• Reuses connections instead of creating new ones");
            System.out.println("• Reduces connection overhead");
            System.out.println("• Improves application performance");
            System.out.println("• Manages connection lifecycle automatically");
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pool != null) {
                    pool.shutdown();
                    System.out.println("\n✓ Connection pool shut down");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
