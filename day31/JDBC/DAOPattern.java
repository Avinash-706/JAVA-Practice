package JDBC;

import java.sql.*;
import java.util.*;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    
    @Override
    public String toString() {
        return String.format("Employee[id=%d, name=%s, dept=%s, salary=%.2f]", 
                id, name, department, salary);
    }
}

interface EmployeeDAO {
    void create(Employee employee) throws SQLException;
    Employee read(int id) throws SQLException;
    List<Employee> readAll() throws SQLException;
    void update(Employee employee) throws SQLException;
    void delete(int id) throws SQLException;
    List<Employee> findByDepartment(String department) throws SQLException;
}

class EmployeeDAOImpl implements EmployeeDAO {
    private Connection connection;
    
    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void create(Employee employee) throws SQLException {
        String sql = "INSERT INTO emp_dao (emp_id, name, department, salary) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, employee.getId());
        pstmt.setString(2, employee.getName());
        pstmt.setString(3, employee.getDepartment());
        pstmt.setDouble(4, employee.getSalary());
        pstmt.executeUpdate();
        pstmt.close();
    }
    
    @Override
    public Employee read(int id) throws SQLException {
        String sql = "SELECT * FROM emp_dao WHERE emp_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        
        Employee employee = null;
        if (rs.next()) {
            employee = new Employee(
                rs.getInt("emp_id"),
                rs.getString("name"),
                rs.getString("department"),
                rs.getDouble("salary")
            );
        }
        
        rs.close();
        pstmt.close();
        return employee;
    }
    
    @Override
    public List<Employee> readAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM emp_dao";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            employees.add(new Employee(
                rs.getInt("emp_id"),
                rs.getString("name"),
                rs.getString("department"),
                rs.getDouble("salary")
            ));
        }
        
        rs.close();
        stmt.close();
        return employees;
    }
    
    @Override
    public void update(Employee employee) throws SQLException {
        String sql = "UPDATE emp_dao SET name = ?, department = ?, salary = ? WHERE emp_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, employee.getName());
        pstmt.setString(2, employee.getDepartment());
        pstmt.setDouble(3, employee.getSalary());
        pstmt.setInt(4, employee.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }
    
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM emp_dao WHERE emp_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }
    
    @Override
    public List<Employee> findByDepartment(String department) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM emp_dao WHERE department = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, department);
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            employees.add(new Employee(
                rs.getInt("emp_id"),
                rs.getString("name"),
                rs.getString("department"),
                rs.getDouble("salary")
            ));
        }
        
        rs.close();
        pstmt.close();
        return employees;
    }
}

public class DAOPattern {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password";
        
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✓ Connected to database\n");
            
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS emp_dao (" +
                    "emp_id INT PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "department VARCHAR(30), " +
                    "salary DOUBLE)");
            stmt.execute("DELETE FROM emp_dao");
            stmt.close();
            
            EmployeeDAO dao = new EmployeeDAOImpl(conn);
            
            System.out.println("--- CREATE Operation ---");
            dao.create(new Employee(101, "Alice Johnson", "IT", 75000));
            dao.create(new Employee(102, "Bob Smith", "HR", 65000));
            dao.create(new Employee(103, "Charlie Brown", "IT", 80000));
            dao.create(new Employee(104, "Diana Prince", "Finance", 90000));
            System.out.println("✓ 4 employees created\n");
            
            System.out.println("--- READ Operation ---");
            Employee emp = dao.read(101);
            System.out.println("Employee 101: " + emp);
            System.out.println();
            
            System.out.println("--- READ ALL Operation ---");
            List<Employee> allEmployees = dao.readAll();
            System.out.println("All Employees:");
            for (Employee e : allEmployees) {
                System.out.println("  " + e);
            }
            System.out.println();
            
            System.out.println("--- UPDATE Operation ---");
            emp.setSalary(82000);
            dao.update(emp);
            System.out.println("✓ Updated salary for employee 101");
            Employee updatedEmp = dao.read(101);
            System.out.println("Updated: " + updatedEmp);
            System.out.println();
            
            System.out.println("--- FIND BY DEPARTMENT ---");
            List<Employee> itEmployees = dao.findByDepartment("IT");
            System.out.println("IT Department Employees:");
            for (Employee e : itEmployees) {
                System.out.println("  " + e);
            }
            System.out.println();
            
            System.out.println("--- DELETE Operation ---");
            dao.delete(102);
            System.out.println("✓ Deleted employee 102");
            System.out.println("Remaining employees: " + dao.readAll().size());
            System.out.println();
            
            System.out.println("--- DAO Pattern Benefits ---");
            System.out.println("• Separates data access logic from business logic");
            System.out.println("• Makes code more maintainable and testable");
            System.out.println("• Easy to switch database implementations");
            System.out.println("• Follows Single Responsibility Principle");
            System.out.println("• Provides clean abstraction over database operations");
            
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
                System.out.println("\n✓ Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
