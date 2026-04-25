import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Advanced Stream API - Complete Coverage
 * Intermediate Operations, Terminal Operations, Collectors, and Advanced Patterns
 */
public class StreamAPIAdvanced {
    
    public static void main(String[] args) {
        System.out.println("=== STREAM API ADVANCED ===\n");
        
        streamBasics();
        intermediateOperations();
        terminalOperations();
        collectorsAdvanced();
        parallelStreams();
        streamPerformance();
        realWorldExamples();
    }
    
    // 1. STREAM BASICS
    static void streamBasics() {
        System.out.println("1. STREAM BASICS");
        System.out.println("Creating and understanding streams\n");
        
        // Different ways to create streams
        System.out.println("Creating streams:");
        
        // From collections
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream1 = numbers.stream();
        System.out.println("From collection: " + stream1.count());
        
        // From arrays
        String[] array = {"a", "b", "c"};
        Stream<String> stream2 = Arrays.stream(array);
        System.out.println("From array: " + stream2.count());
        
        // Using Stream.of()
        Stream<String> stream3 = Stream.of("x", "y", "z");
        System.out.println("Using Stream.of(): " + stream3.count());
        
        // Infinite streams
        Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 2);
        System.out.println("First 5 even numbers: ");
        infiniteStream.limit(5).forEach(n -> System.out.print(n + " "));
        
        // Generate stream
        System.out.println("\nRandom numbers: ");
        Stream.generate(Math::random).limit(3).forEach(n -> System.out.print(n + " "));
        
        // Range streams
        System.out.println("\nRange 1-5: ");
        IntStream.range(1, 6).forEach(n -> System.out.print(n + " "));
        
        System.out.println("\n");
    }
    
    // 2. INTERMEDIATE OPERATIONS
    static void intermediateOperations() {
        System.out.println("2. INTERMEDIATE OPERATIONS");
        System.out.println("Operations that return streams (lazy evaluation)\n");
        
        List<Employee> employees = getEmployees();
        
        // FILTER
        System.out.println("FILTER - Select elements based on condition:");
        employees.stream()
                 .filter(emp -> emp.salary > 60000)
                 .forEach(emp -> System.out.println("  " + emp.name + ": $" + emp.salary));
        
        // MAP
        System.out.println("\nMAP - Transform elements:");
        employees.stream()
                 .map(emp -> emp.name.toUpperCase())
                 .forEach(name -> System.out.println("  " + name));
        
        // FLATMAP
        System.out.println("\nFLATMAP - Flatten nested structures:");
        List<List<String>> nestedList = Arrays.asList(
            Arrays.asList("a", "b"),
            Arrays.asList("c", "d", "e"),
            Arrays.asList("f")
        );
        nestedList.stream()
                  .flatMap(List::stream)
                  .forEach(item -> System.out.print(item + " "));
        
        // DISTINCT
        System.out.println("\n\nDISTINCT - Remove duplicates:");
        Arrays.asList(1, 2, 2, 3, 3, 4, 5).stream()
              .distinct()
              .forEach(n -> System.out.print(n + " "));
        
        // SORTED
        System.out.println("\n\nSORTED - Sort elements:");
        employees.stream()
                 .sorted(Comparator.comparing(emp -> emp.salary))
                 .forEach(emp -> System.out.println("  " + emp.name + ": $" + emp.salary));
        
        // PEEK
        System.out.println("\nPEEK - Debug/side effects:");
        List<Integer> result = Arrays.asList(1, 2, 3, 4, 5).stream()
                                     .peek(n -> System.out.print("Processing " + n + " "))
                                     .map(n -> n * 2)
                                     .peek(n -> System.out.println("-> " + n))
                                     .collect(Collectors.toList());
        System.out.println("Final result: " + result);
        
        // LIMIT and SKIP
        System.out.println("\nLIMIT and SKIP:");
        System.out.println("First 3 employees:");
        employees.stream().limit(3).forEach(emp -> System.out.println("  " + emp.name));
        
        System.out.println("Skip first 2, take next 2:");
        employees.stream().skip(2).limit(2).forEach(emp -> System.out.println("  " + emp.name));
        
        // TAKEWHILE and DROPWHILE (Java 9+)
        System.out.println("\nTAKEWHILE - Take elements while condition is true:");
        Arrays.asList(1, 2, 3, 4, 5, 3, 2, 1).stream()
              .takeWhile(n -> n < 4)
              .forEach(n -> System.out.print(n + " "));
        
        System.out.println("\nDROPWHILE - Drop elements while condition is true:");
        Arrays.asList(1, 2, 3, 4, 5, 3, 2, 1).stream()
              .dropWhile(n -> n < 4)
              .forEach(n -> System.out.print(n + " "));
        
        System.out.println("\n");
    }
    
    // 3. TERMINAL OPERATIONS
    static void terminalOperations() {
        System.out.println("3. TERMINAL OPERATIONS");
        System.out.println("Operations that produce results (trigger execution)\n");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Employee> employees = getEmployees();
        
        // FOREACH
        System.out.println("FOREACH - Perform action on each element:");
        numbers.stream().limit(5).forEach(n -> System.out.print(n + " "));
        
        // COLLECT
        System.out.println("\n\nCOLLECT - Collect to collections:");
        List<Integer> evenNumbers = numbers.stream()
                                          .filter(n -> n % 2 == 0)
                                          .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
        
        // REDUCE
        System.out.println("\nREDUCE - Combine elements:");
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        System.out.println("Sum: " + sum.orElse(0));
        
        Integer product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);
        
        // FIND operations
        System.out.println("\nFIND operations:");
        Optional<Employee> firstHighPaid = employees.stream()
                                                   .filter(emp -> emp.salary > 70000)
                                                   .findFirst();
        System.out.println("First high paid: " + firstHighPaid.map(emp -> emp.name).orElse("None"));
        
        Optional<Employee> anyITEmployee = employees.stream()
                                                   .filter(emp -> "IT".equals(emp.department))
                                                   .findAny();
        System.out.println("Any IT employee: " + anyITEmployee.map(emp -> emp.name).orElse("None"));
        
        // MATCH operations
        System.out.println("\nMATCH operations:");
        boolean allHighPaid = employees.stream().allMatch(emp -> emp.salary > 50000);
        System.out.println("All employees earn > 50k: " + allHighPaid);
        
        boolean anyHighPaid = employees.stream().anyMatch(emp -> emp.salary > 80000);
        System.out.println("Any employee earns > 80k: " + anyHighPaid);
        
        boolean noneEarnMillion = employees.stream().noneMatch(emp -> emp.salary > 1000000);
        System.out.println("No employee earns > 1M: " + noneEarnMillion);
        
        // COUNT
        System.out.println("\nCOUNT:");
        long itEmployeeCount = employees.stream()
                                       .filter(emp -> "IT".equals(emp.department))
                                       .count();
        System.out.println("IT employees: " + itEmployeeCount);
        
        // MIN and MAX
        System.out.println("\nMIN and MAX:");
        Optional<Employee> highestPaid = employees.stream()
                                                 .max(Comparator.comparing(emp -> emp.salary));
        System.out.println("Highest paid: " + highestPaid.map(emp -> emp.name + " ($" + emp.salary + ")").orElse("None"));
        
        Optional<Employee> lowestPaid = employees.stream()
                                                .min(Comparator.comparing(emp -> emp.salary));
        System.out.println("Lowest paid: " + lowestPaid.map(emp -> emp.name + " ($" + emp.salary + ")").orElse("None"));
        
        System.out.println();
    }
    
    // 4. ADVANCED COLLECTORS
    static void collectorsAdvanced() {
        System.out.println("4. ADVANCED COLLECTORS");
        System.out.println("Powerful data collection and transformation\n");
        
        List<Employee> employees = getEmployees();
        
        // GROUPING BY
        System.out.println("GROUPING BY department:");
        Map<String, List<Employee>> byDepartment = employees.stream()
            .collect(Collectors.groupingBy(emp -> emp.department));
        byDepartment.forEach((dept, empList) -> {
            System.out.println("  " + dept + ": " + empList.size() + " employees");
            empList.forEach(emp -> System.out.println("    " + emp.name));
        });
        
        // GROUPING BY with downstream collector
        System.out.println("\nGROUPING BY with counting:");
        Map<String, Long> countByDepartment = employees.stream()
            .collect(Collectors.groupingBy(emp -> emp.department, Collectors.counting()));
        countByDepartment.forEach((dept, count) -> 
            System.out.println("  " + dept + ": " + count + " employees"));
        
        // GROUPING BY with average salary
        System.out.println("\nAverage salary by department:");
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(emp -> emp.department, 
                    Collectors.averagingDouble(emp -> emp.salary)));
        avgSalaryByDept.forEach((dept, avg) -> 
            System.out.println("  " + dept + ": $" + String.format("%.2f", avg)));
        
        // PARTITIONING BY
        System.out.println("\nPARTITIONING BY (high/low salary):");
        Map<Boolean, List<Employee>> partitioned = employees.stream()
            .collect(Collectors.partitioningBy(emp -> emp.salary > 65000));
        System.out.println("  High salary (>65k): " + partitioned.get(true).size());
        System.out.println("  Low salary (<=65k): " + partitioned.get(false).size());
        
        // JOINING
        System.out.println("\nJOINING names:");
        String allNames = employees.stream()
            .map(emp -> emp.name)
            .collect(Collectors.joining(", "));
        System.out.println("  All names: " + allNames);
        
        String formattedNames = employees.stream()
            .map(emp -> emp.name)
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("  Formatted: " + formattedNames);
        
        // SUMMARIZING
        System.out.println("\nSUMMARIZING statistics:");
        DoubleSummaryStatistics salaryStats = employees.stream()
            .collect(Collectors.summarizingDouble(emp -> emp.salary));
        System.out.println("  Count: " + salaryStats.getCount());
        System.out.println("  Sum: $" + salaryStats.getSum());
        System.out.println("  Average: $" + String.format("%.2f", salaryStats.getAverage()));
        System.out.println("  Min: $" + salaryStats.getMin());
        System.out.println("  Max: $" + salaryStats.getMax());
        
        // CUSTOM COLLECTOR
        System.out.println("\nCUSTOM COLLECTOR (concatenate names with salaries):");
        String customResult = employees.stream()
            .collect(Collector.of(
                StringBuilder::new,
                (sb, emp) -> sb.append(emp.name).append("($").append(emp.salary).append(") "),
                StringBuilder::append,
                StringBuilder::toString
            ));
        System.out.println("  " + customResult);
        
        System.out.println();
    }
    
    // 5. PARALLEL STREAMS
    static void parallelStreams() {
        System.out.println("5. PARALLEL STREAMS");
        System.out.println("Leveraging multiple cores for processing\n");
        
        List<Integer> largeList = IntStream.range(1, 1000000).boxed().collect(Collectors.toList());
        
        // Sequential vs Parallel performance
        System.out.println("Performance comparison (processing large dataset):");
        
        // Sequential
        long startTime = System.currentTimeMillis();
        long sequentialSum = largeList.stream()
                                     .mapToLong(n -> expensiveOperation(n))
                                     .sum();
        long sequentialTime = System.currentTimeMillis() - startTime;
        System.out.println("  Sequential time: " + sequentialTime + "ms");
        
        // Parallel
        startTime = System.currentTimeMillis();
        long parallelSum = largeList.parallelStream()
                                   .mapToLong(n -> expensiveOperation(n))
                                   .sum();
        long parallelTime = System.currentTimeMillis() - startTime;
        System.out.println("  Parallel time: " + parallelTime + "ms");
        System.out.println("  Results match: " + (sequentialSum == parallelSum));
        
        // When to use parallel streams
        System.out.println("\nWhen to use parallel streams:");
        System.out.println("  ✓ Large datasets (>10,000 elements)");
        System.out.println("  ✓ CPU-intensive operations");
        System.out.println("  ✓ Independent operations (no shared state)");
        System.out.println("  ✗ I/O operations");
        System.out.println("  ✗ Small datasets");
        System.out.println("  ✗ Operations with side effects");
        
        // Parallel stream considerations
        List<Employee> employees = getEmployees();
        System.out.println("\nParallel grouping:");
        Map<String, List<Employee>> parallelGrouping = employees.parallelStream()
            .collect(Collectors.groupingBy(emp -> emp.department));
        parallelGrouping.forEach((dept, empList) -> 
            System.out.println("  " + dept + ": " + empList.size()));
        
        System.out.println();
    }
    
    // 6. STREAM PERFORMANCE
    static void streamPerformance() {
        System.out.println("6. STREAM PERFORMANCE");
        System.out.println("Optimization tips and best practices\n");
        
        List<Integer> numbers = IntStream.range(1, 100000).boxed().collect(Collectors.toList());
        
        // Lazy evaluation demonstration
        System.out.println("Lazy evaluation - operations are not executed until terminal operation:");
        Stream<Integer> lazyStream = numbers.stream()
            .peek(n -> System.out.print("peek1 "))
            .filter(n -> n % 2 == 0)
            .peek(n -> System.out.print("peek2 "))
            .map(n -> n * 2);
        
        System.out.println("Stream created but no output yet...");
        System.out.println("Now calling terminal operation:");
        lazyStream.limit(3).forEach(n -> System.out.print(n + " "));
        
        // Short-circuiting operations
        System.out.println("\n\nShort-circuiting operations (limit, findFirst, anyMatch):");
        long startTime = System.currentTimeMillis();
        Optional<Integer> found = numbers.stream()
                                        .filter(n -> n > 50000)
                                        .findFirst();
        long time1 = System.currentTimeMillis() - startTime;
        System.out.println("  findFirst() time: " + time1 + "ms, found: " + found.orElse(-1));
        
        // Order matters for performance
        System.out.println("\nOrder matters for performance:");
        
        // Less efficient: map then filter
        startTime = System.currentTimeMillis();
        long count1 = numbers.stream()
                            .map(n -> n * 2)  // Applied to all elements
                            .filter(n -> n > 100000)
                            .count();
        long time2 = System.currentTimeMillis() - startTime;
        
        // More efficient: filter then map
        startTime = System.currentTimeMillis();
        long count2 = numbers.stream()
                            .filter(n -> n > 50000)  // Reduces elements first
                            .map(n -> n * 2)
                            .count();
        long time3 = System.currentTimeMillis() - startTime;
        
        System.out.println("  Map then filter: " + time2 + "ms, count: " + count1);
        System.out.println("  Filter then map: " + time3 + "ms, count: " + count2);
        
        // Avoid boxing/unboxing with primitive streams
        System.out.println("\nPrimitive streams avoid boxing overhead:");
        startTime = System.currentTimeMillis();
        long sum1 = numbers.stream().mapToInt(Integer::intValue).sum();
        long time4 = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        int sum2 = IntStream.range(1, 100000).sum();
        long time5 = System.currentTimeMillis() - startTime;
        
        System.out.println("  Boxed stream sum: " + time4 + "ms");
        System.out.println("  Primitive stream sum: " + time5 + "ms");
        
        System.out.println();
    }
    
    // 7. REAL WORLD EXAMPLES
    static void realWorldExamples() {
        System.out.println("7. REAL WORLD EXAMPLES");
        System.out.println("Practical applications of Stream API\n");
        
        List<Employee> employees = getEmployees();
        List<Order> orders = getOrders();
        
        // Example 1: Employee analytics
        System.out.println("Example 1: Employee Analytics");
        
        // Top 3 highest paid employees
        System.out.println("Top 3 highest paid employees:");
        employees.stream()
                 .sorted(Comparator.comparing((Employee emp) -> emp.salary).reversed())
                 .limit(3)
                 .forEach(emp -> System.out.println("  " + emp.name + ": $" + emp.salary));
        
        // Department with highest average salary
        Map<String, Double> avgSalaries = employees.stream()
            .collect(Collectors.groupingBy(emp -> emp.department, 
                    Collectors.averagingDouble(emp -> emp.salary)));
        
        Optional<Map.Entry<String, Double>> topDept = avgSalaries.entrySet().stream()
            .max(Map.Entry.comparingByValue());
        
        System.out.println("Department with highest avg salary: " + 
                          topDept.map(entry -> entry.getKey() + " ($" + 
                          String.format("%.2f", entry.getValue()) + ")").orElse("None"));
        
        // Example 2: Order processing
        System.out.println("\nExample 2: Order Processing");
        
        // Total revenue
        double totalRevenue = orders.stream()
                                   .mapToDouble(order -> order.amount)
                                   .sum();
        System.out.println("Total revenue: $" + String.format("%.2f", totalRevenue));
        
        // Orders by status
        Map<String, Long> ordersByStatus = orders.stream()
            .collect(Collectors.groupingBy(order -> order.status, Collectors.counting()));
        System.out.println("Orders by status:");
        ordersByStatus.forEach((status, count) -> 
            System.out.println("  " + status + ": " + count));
        
        // High value customers (orders > $500)
        Set<String> highValueCustomers = orders.stream()
            .filter(order -> order.amount > 500)
            .map(order -> order.customerName)
            .collect(Collectors.toSet());
        System.out.println("High value customers: " + highValueCustomers);
        
        // Example 3: Data transformation pipeline
        System.out.println("\nExample 3: Data Transformation Pipeline");
        
        List<String> processedData = orders.stream()
            .filter(order -> "COMPLETED".equals(order.status))
            .filter(order -> order.amount > 100)
            .sorted(Comparator.comparing((Order order) -> order.amount).reversed())
            .map(order -> order.customerName + " - $" + order.amount)
            .distinct()
            .limit(5)
            .collect(Collectors.toList());
        
        System.out.println("Top 5 completed high-value orders:");
        processedData.forEach(data -> System.out.println("  " + data));
        
        System.out.println();
    }
    
    // Helper methods and classes
    static List<Employee> getEmployees() {
        return Arrays.asList(
            new Employee("Alice", 75000, "IT"),
            new Employee("Bob", 65000, "HR"),
            new Employee("Charlie", 80000, "IT"),
            new Employee("Diana", 60000, "Finance"),
            new Employee("Eve", 70000, "IT"),
            new Employee("Frank", 55000, "HR"),
            new Employee("Grace", 85000, "Finance")
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
            new Order("Eve", 180.00, "PENDING")
        );
    }
    
    static long expensiveOperation(int n) {
        // Simulate CPU-intensive work
        long result = 0;
        for (int i = 0; i < 1000; i++) {
            result += n * i;
        }
        return result;
    }
    
    static class Employee {
        String name;
        double salary;
        String department;
        
        Employee(String name, double salary, String department) {
            this.name = name;
            this.salary = salary;
            this.department = department;
        }
        
        @Override
        public String toString() {
            return name + " ($" + salary + ", " + department + ")";
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