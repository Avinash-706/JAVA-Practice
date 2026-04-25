import java.util.*;
import java.util.stream.*;

/**
 * Stream Operations Mastery - Comprehensive Examples
 * Covering all intermediate and terminal operations with real-world scenarios
 */
public class StreamOperationsMastery {
    
    public static void main(String[] args) {
        System.out.println("=== STREAM OPERATIONS MASTERY ===\n");
        
        intermediateOperationsDeep();
        terminalOperationsDeep();
        streamOrderMatters();
        practicalExamples();
    }
    
    // 1. INTERMEDIATE OPERATIONS DEEP DIVE
    static void intermediateOperationsDeep() {
        System.out.println("1. INTERMEDIATE OPERATIONS DEEP DIVE\n");
        
        List<Employee> employees = getEmployees();
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10);
        
        // FILTER - Multiple conditions
        System.out.println("FILTER with multiple conditions:");
        employees.stream()
                 .filter(emp -> emp.department.equals("IT"))
                 .filter(emp -> emp.salary > 70000)
                 .filter(emp -> emp.age < 35)
                 .forEach(emp -> System.out.println("  " + emp));
        
        // MAP - Complex transformations
        System.out.println("\nMAP transformations:");
        employees.stream()
                 .map(emp -> emp.name + " earns $" + emp.salary + " in " + emp.department)
                 .forEach(System.out::println);
        
        // FLATMAP - Flattening nested structures
        System.out.println("\nFLATMAP examples:");
        List<List<String>> departments = Arrays.asList(
            Arrays.asList("IT", "Software", "Hardware"),
            Arrays.asList("HR", "Recruitment", "Training"),
            Arrays.asList("Finance", "Accounting", "Audit")
        );
        
        System.out.println("All sub-departments:");
        departments.stream()
                   .flatMap(List::stream)
                   .forEach(dept -> System.out.println("  " + dept));
    }
    
    // Continue with more methods...
}
        
        // DISTINCT - Remove duplicates
        System.out.println("\nDISTINCT operations:");
        System.out.println("Original numbers: " + numbers);
        List<Integer> distinctNumbers = numbers.stream()
                                              .distinct()
                                              .collect(Collectors.toList());
        System.out.println("Distinct numbers: " + distinctNumbers);
        
        // SORTED - Various sorting approaches
        System.out.println("\nSORTED operations:");
        
        // Natural sorting
        System.out.println("Natural sort:");
        numbers.stream().distinct().sorted().forEach(n -> System.out.print(n + " "));
        
        // Reverse sorting
        System.out.println("\nReverse sort:");
        numbers.stream().distinct().sorted(Comparator.reverseOrder())
               .forEach(n -> System.out.print(n + " "));
        
        // Custom sorting for objects
        System.out.println("\nEmployees by salary (ascending):");
        employees.stream()
                 .sorted(Comparator.comparing(emp -> emp.salary))
                 .forEach(emp -> System.out.println("  " + emp.name + ": $" + emp.salary));
        
        // PEEK - Debugging and side effects
        System.out.println("\nPEEK for debugging:");
        List<Integer> processed = numbers.stream()
                                        .peek(n -> System.out.print("Original: " + n + " "))
                                        .filter(n -> n % 2 == 0)
                                        .peek(n -> System.out.print("After filter: " + n + " "))
                                        .map(n -> n * 2)
                                        .peek(n -> System.out.println("After map: " + n))
                                        .collect(Collectors.toList());
        
        // LIMIT and SKIP
        System.out.println("\nLIMIT and SKIP:");
        System.out.println("First 3 employees:");
        employees.stream().limit(3)
                 .forEach(emp -> System.out.println("  " + emp.name));
        
        System.out.println("Skip first 2, take next 3:");
        employees.stream().skip(2).limit(3)
                 .forEach(emp -> System.out.println("  " + emp.name));
        
        System.out.println();
    }
    
    // 2. TERMINAL OPERATIONS DEEP DIVE
    static void terminalOperationsDeep() {
        System.out.println("2. TERMINAL OPERATIONS DEEP DIVE\n");
        
        List<Employee> employees = getEmployees();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // COLLECT - Various collectors
        System.out.println("COLLECT operations:");
        
        // To different collection types
        Set<String> departments = employees.stream()
                                          .map(emp -> emp.department)
                                          .collect(Collectors.toSet());
        System.out.println("Unique departments: " + departments);
        
        // Grouping by department
        Map<String, List<Employee>> byDepartment = employees.stream()
            .collect(Collectors.groupingBy(emp -> emp.department));
        System.out.println("Employees by department:");
        byDepartment.forEach((dept, empList) -> 
            System.out.println("  " + dept + ": " + empList.size() + " employees"));
        
        // REDUCE - Combining elements
        System.out.println("\nREDUCE operations:");
        
        // Sum with reduce
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        System.out.println("Sum using reduce: " + sum.orElse(0));
        
        // Product with reduce
        Optional<Integer> product = numbers.stream().reduce((a, b) -> a * b);
        System.out.println("Product: " + product.orElse(0));
        
        // Find operations
        System.out.println("\nFIND operations:");
        
        Optional<Employee> firstITEmployee = employees.stream()
                                                     .filter(emp -> "IT".equals(emp.department))
                                                     .findFirst();
        System.out.println("First IT employee: " + 
                          firstITEmployee.map(emp -> emp.name).orElse("None"));
        
        // MATCH operations
        System.out.println("\nMATCH operations:");
        boolean allAdults = employees.stream().allMatch(emp -> emp.age >= 18);
        System.out.println("All employees are adults: " + allAdults);
        
        boolean anyHighEarner = employees.stream().anyMatch(emp -> emp.salary > 80000);
        System.out.println("Any employee earns > 80k: " + anyHighEarner);
        
        boolean noneEarnMillion = employees.stream().noneMatch(emp -> emp.salary > 1000000);
        System.out.println("No employee earns > 1M: " + noneEarnMillion);
        
        System.out.println();
    }
    
    // 3. STREAM ORDER MATTERS
    static void streamOrderMatters() {
        System.out.println("3. STREAM ORDER MATTERS");
        System.out.println("Demonstrating how operation order affects results\n");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Case 1: limit() then skip()
        System.out.println("Case 1: limit(5) then skip(2)");
        List<Integer> result1 = numbers.stream()
                                      .limit(5)      // [1,2,3,4,5]
                                      .skip(2)       // [3,4,5]
                                      .collect(Collectors.toList());
        System.out.println("Result: " + result1);
        
        // Case 2: skip() then limit()
        System.out.println("\nCase 2: skip(2) then limit(5)");
        List<Integer> result2 = numbers.stream()
                                      .skip(2)       // [3,4,5,6,7,8,9,10]
                                      .limit(5)      // [3,4,5,6,7]
                                      .collect(Collectors.toList());
        System.out.println("Result: " + result2);
        
        // Performance implications
        System.out.println("\nPerformance implications:");
        
        // Less efficient: map then filter
        System.out.println("Less efficient - map then filter:");
        long startTime = System.nanoTime();
        long count1 = numbers.stream()
                            .map(n -> expensiveOperation(n))  // Applied to all
                            .filter(n -> n > 50)
                            .count();
        long time1 = System.nanoTime() - startTime;
        
        // More efficient: filter then map
        System.out.println("More efficient - filter then map:");
        startTime = System.nanoTime();
        long count2 = numbers.stream()
                            .filter(n -> n > 5)               // Reduces elements first
                            .map(n -> expensiveOperation(n))
                            .filter(n -> n > 50)
                            .count();
        long time2 = System.nanoTime() - startTime;
        
        System.out.println("Map-then-filter time: " + time1 + "ns, count: " + count1);
        System.out.println("Filter-then-map time: " + time2 + "ns, count: " + count2);
        
        System.out.println();
    }
    
    // 4. PRACTICAL EXAMPLES
    static void practicalExamples() {
        System.out.println("4. PRACTICAL EXAMPLES");
        System.out.println("Real-world stream usage patterns\n");
        
        List<Employee> employees = getEmployees();
        List<Order> orders = getOrders();
        
        // Example 1: Employee Analytics Dashboard
        System.out.println("Example 1: Employee Analytics Dashboard");
        
        // Average salary by department
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(emp -> emp.department,
                    Collectors.averagingDouble(emp -> emp.salary)));
        
        System.out.println("Average salary by department:");
        avgSalaryByDept.entrySet().stream()
                      .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                      .forEach(entry -> System.out.println("  " + entry.getKey() + 
                              ": $" + String.format("%.2f", entry.getValue())));
        
        // Top performers (top 20% by salary)
        double salaryThreshold = employees.stream()
                                         .mapToDouble(emp -> emp.salary)
                                         .sorted()
                                         .skip((long)(employees.size() * 0.8))
                                         .findFirst()
                                         .orElse(0);
        
        System.out.println("\nTop 20% performers (salary > $" + salaryThreshold + "):");
        employees.stream()
                 .filter(emp -> emp.salary > salaryThreshold)
                 .sorted(Comparator.comparing((Employee emp) -> emp.salary).reversed())
                 .forEach(emp -> System.out.println("  " + emp.name + ": $" + emp.salary));
        
        // Example 2: Order Processing Pipeline
        System.out.println("\nExample 2: Order Processing Pipeline");
        
        // Revenue by customer
        Map<String, Double> revenueByCustomer = orders.stream()
            .filter(order -> "COMPLETED".equals(order.status))
            .collect(Collectors.groupingBy(order -> order.customerName,
                    Collectors.summingDouble(order -> order.amount)));
        
        System.out.println("Revenue by customer:");
        revenueByCustomer.entrySet().stream()
                        .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                        .forEach(entry -> System.out.println("  " + entry.getKey() + 
                                ": $" + String.format("%.2f", entry.getValue())));
        
        // High-value order analysis
        double avgOrderValue = orders.stream()
                                    .filter(order -> "COMPLETED".equals(order.status))
                                    .mapToDouble(order -> order.amount)
                                    .average()
                                    .orElse(0);
        
        System.out.println("\nHigh-value orders (above average $" + 
                          String.format("%.2f", avgOrderValue) + "):");
        orders.stream()
              .filter(order -> "COMPLETED".equals(order.status))
              .filter(order -> order.amount > avgOrderValue)
              .sorted(Comparator.comparing((Order order) -> order.amount).reversed())
              .forEach(order -> System.out.println("  " + order.customerName + 
                      ": $" + order.amount));
        
        // Example 3: Data Validation Pipeline
        System.out.println("\nExample 3: Data Validation Pipeline");
        
        List<String> emails = Arrays.asList(
            "valid@email.com", "invalid-email", "another@valid.com", 
            "", "test@domain.co.uk", "bad@", "@bad.com"
        );
        
        List<String> validEmails = emails.stream()
            .filter(email -> email != null && !email.trim().isEmpty())
            .filter(email -> email.contains("@"))
            .filter(email -> email.indexOf("@") > 0)
            .filter(email -> email.indexOf("@") < email.length() - 1)
            .filter(email -> email.contains("."))
            .filter(email -> email.lastIndexOf(".") > email.indexOf("@"))
            .collect(Collectors.toList());
        
        System.out.println("Valid emails: " + validEmails);
        
        System.out.println();
    }
    
    // Helper methods and classes
    static List<Employee> getEmployees() {
        return Arrays.asList(
            new Employee("Alice", 75000, "IT", 28),
            new Employee("Bob", 65000, "HR", 32),
            new Employee("Charlie", 85000, "IT", 30),
            new Employee("Diana", 70000, "Finance", 29),
            new Employee("Eve", 80000, "IT", 26),
            new Employee("Frank", 60000, "HR", 35),
            new Employee("Grace", 90000, "Finance", 31)
        );
    }
    
    static List<Order> getOrders() {
        return Arrays.asList(
            new Order("Alice", 250.50, "COMPLETED"),
            new Order("Bob", 150.75, "PENDING"),
            new Order("Alice", 600.00, "COMPLETED"),
            new Order("Charlie", 75.25, "CANCELLED"),
            new Order("Diana", 450.00, "COMPLETED"),
            new Order("Bob", 320.50, "COMPLETED"),
            new Order("Eve", 180.00, "PENDING"),
            new Order("Alice", 125.00, "COMPLETED")
        );
    }
    
    static int expensiveOperation(int n) {
        // Simulate expensive computation
        int result = n;
        for (int i = 0; i < 1000; i++) {
            result = result * 2 / 2 + 1 - 1;
        }
        return result * n;
    }
    
    static class Employee {
        String name;
        double salary;
        String department;
        int age;
        
        Employee(String name, double salary, String department, int age) {
            this.name = name;
            this.salary = salary;
            this.department = department;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return name + " ($" + salary + ", " + department + ", " + age + ")";
        }
    }
    
    static class Order {
        String customerName;
        double amount;
        String status;
        
        Order(String customerName, double amount, String status) {
            this.customerName = customerName;
            this.amount = amount;
            this.status = status;
        }
        
        @Override
        public String toString() {
            return customerName + ": $" + amount + " (" + status + ")";
        }
    }
}