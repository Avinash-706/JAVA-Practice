# Day 31: JDBC - Java Database Connectivity

Complete guide from basic to advanced JDBC concepts with practical implementations.

## JDBC Overview

JDBC is a Java API for connecting and executing queries with databases
- Part of Java SE (Standard Edition)
- Present in java.sql package since JDK 1.1
- Provides database-independent connectivity
- Supports CRUD operations (Create, Read, Update, Delete)
- Uses Driver interface for different databases
- Acts as bridge between Java application and database

## JDBC Architecture

**Two-tier**: Java Application → JDBC Driver → Database
**Three-tier**: Java Application → Application Server → Database

Core Components:
- **DriverManager**: Manages database drivers
- **Connection**: Database connection
- **Statement**: Executes SQL queries
- **PreparedStatement**: Parameterized queries (recommended)
- **CallableStatement**: Stored procedures
- **ResultSet**: Query results

## JDBC Drivers

1. **Type 1**: JDBC-ODBC Bridge (deprecated)
2. **Type 2**: Native-API Driver (platform dependent)
3. **Type 3**: Network Protocol (middleware)
4. **Type 4**: Thin Driver (pure Java) ⭐ Most common

## Connection Steps

1. Load/Register Driver (automatic in JDBC 4.0+)
2. Create Connection
3. Create Statement/PreparedStatement
4. Execute Query
5. Process ResultSet
6. Close Resources

## Statement Types

### Statement
- Static SQL queries
- Vulnerable to SQL injection
- Use only for fixed queries

### PreparedStatement ⭐
- Parameterized queries
- SQL injection safe
- Precompiled (better performance)
- Reusable
- Recommended for most operations

### CallableStatement
- Execute stored procedures
- Supports IN, OUT, INOUT parameters
- Database-specific logic

## ResultSet Types

### Type
- **TYPE_FORWARD_ONLY**: Default, forward only
- **TYPE_SCROLL_INSENSITIVE**: Scrollable, static snapshot
- **TYPE_SCROLL_SENSITIVE**: Scrollable, reflects changes

### Concurrency
- **CONCUR_READ_ONLY**: Default, read-only
- **CONCUR_UPDATABLE**: Allows updates

## Transaction Management

### ACID Properties
- **Atomicity**: All or nothing
- **Consistency**: Valid state transitions
- **Isolation**: Concurrent transaction handling
- **Durability**: Permanent changes

### Isolation Levels
1. **READ_UNCOMMITTED**: Lowest isolation
2. **READ_COMMITTED**: Prevents dirty reads
3. **REPEATABLE_READ**: Prevents non-repeatable reads
4. **SERIALIZABLE**: Highest isolation

### Operations
- `setAutoCommit(false)`: Start transaction
- `commit()`: Save changes
- `rollback()`: Undo changes
- `setSavepoint()`: Create checkpoint

## Advanced Features

### Connection Pooling
- Reuse connections
- Better performance
- Resource management
- Libraries: HikariCP, Apache DBCP

### BLOB/CLOB
- **BLOB**: Binary Large Objects (images, files)
- **CLOB**: Character Large Objects (text)
- Stream-based operations

### RowSet
- **JdbcRowSet**: Connected, scrollable
- **CachedRowSet**: Disconnected, serializable
- **WebRowSet**: XML format
- **FilteredRowSet**: Filtering capability

### Metadata
- **DatabaseMetaData**: Database info
- **ResultSetMetaData**: Column info
- **ParameterMetaData**: Parameter info

## Best Practices

1. **Use try-with-resources**: Automatic cleanup
2. **Prefer PreparedStatement**: Safe and fast
3. **Batch processing**: Bulk operations
4. **Connection pooling**: Production apps
5. **Transaction management**: Data consistency
6. **Close resources**: Prevent leaks
7. **Handle exceptions**: Specific catch blocks
8. **Avoid SELECT ***: Specify columns
9. **Use DAO pattern**: Clean architecture
10. **Monitor performance**: Log slow queries

## Common Pitfalls

❌ Not closing resources
❌ SQL injection vulnerabilities
❌ Creating too many connections
❌ Not using transactions
❌ Selecting all columns
❌ Ignoring batch processing
❌ Poor exception handling
❌ Not using connection pooling

## Performance Tips

- PreparedStatement reuse
- Batch updates for bulk operations
- Connection pooling
- Proper indexing
- Set fetch size for large results
- Statement caching
- Avoid N+1 query problem

## File Organization

### Basic Level
1. **JDBCBasics.java** - Core concepts
2. **PreparedStatementDemo.java** - Safe queries
3. **Storage.java** - Simple operations

### Intermediate Level
4. **CallableStatementDemo.java** - Stored procedures
5. **ResultSetTypes.java** - ResultSet manipulation
6. **DatabaseMetadataDemo.java** - Metadata exploration
7. **TransactionManagement.java** - ACID properties

### Advanced Level
8. **ConnectionPooling.java** - Pool implementation
9. **BlobClobDemo.java** - Large objects
10. **RowSetDemo.java** - Disconnected rowsets
11. **DAOPattern.java** - Design pattern
12. **AdvancedJDBC.java** - Advanced techniques
13. **JDBCBestPractices.java** - Production patterns

## Quick Reference

```java
// Connection
Connection conn = DriverManager.getConnection(url, user, pass);

// Query
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
pstmt.setInt(1, userId);
ResultSet rs = pstmt.executeQuery();

// Update
PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET name = ? WHERE id = ?");
pstmt.setString(1, name);
pstmt.setInt(2, id);
int rows = pstmt.executeUpdate();

// Transaction
conn.setAutoCommit(false);
// operations
conn.commit();
// or conn.rollback();
```

For detailed examples, check the README.md file in the JDBC folder.
