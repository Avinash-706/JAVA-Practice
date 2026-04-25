import java.util.*;
import java.util.function.*;

/**
 * Comprehensive Lambda Expressions - From Basic to Advanced
 * Covers syntax, types, limitations, and advanced patterns
 */
public class LambdaExpressionsDeep {
    
    public static void main(String[] args) {
        System.out.println("=== LAMBDA EXPRESSIONS DEEP DIVE ===\n");
        
        basicLambdaSyntax();
        lambdaWithDifferentParameters();
        lambdaWithMethodReferences();
        lambdaLimitations();
        advancedLambdaPatterns();
        lambdaWithCollections();
        customFunctionalInterfaces();
    }
    
    // 1. BASIC LAMBDA SYNTAX
    static void basicLambdaSyntax() {
        System.out.println("1. BASIC LAMBDA SYNTAX");
        System.out.println("Different ways to write lambda expressions\n");
        
        // No parameters
        Runnable r1 = () -> System.out.println("No parameters");
        r1.run();
        
        // Single parameter (parentheses optional)
        Consumer<String> c1 = str -> System.out.println("Single param: " + str);
        Consumer<String> c2 = (str) -> System.out.println("Single param with parentheses: " + str);
        c1.accept("Hello");
        c2.accept("World");
        
        // Multiple parameters
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("Multiple params: " + add.apply(5, 3));
        
        // Single expression vs block
        Function<Integer, Integer> square1 = x -> x * x; // Single expression
        Function<Integer, Integer> square2 = x -> { // Block
            int result = x * x;
            return result;
        };
        System.out.println("Single expression: " + square1.apply(4));
        System.out.println("Block expression: " + square2.apply(4));
        
        System.out.println();
    }
    
    // 2. LAMBDA WITH DIFFERENT PARAMETER TYPES
    static void lambdaWithDifferentParameters() {
        System.out.println("2. LAMBDA WITH DIFFERENT PARAMETER TYPES\n");
        
        // Explicit type declaration
        BiFunction<String, Integer, String> repeat1 = (String str, Integer times) -> {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < times; i++) {
                result.append(str);
            }
            return result.toString();
        };
        
        // Type inference (preferred)
        BiFunction<String, Integer, String> repeat2 = (str, times) -> {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < times; i++) {
                result.append(str);
            }
            return result.toString();
        };
        
        System.out.println("Explicit types: " + repeat1.apply("Hi", 3));
        System.out.println("Type inference: " + repeat2.apply("Bye", 2));
        
        // Complex parameter types
        BiConsumer<List<String>, String> listAdder = (list, item) -> list.add(item);
        List<String> names = new ArrayList<>();
        listAdder.accept(names, "Alice");
        listAdder.accept(names, "Bob");
        System.out.println("List after adding: " + names);
        
        System.out.println();
    }
    
    // 3. LAMBDA WITH METHOD REFERENCES
    static void lambdaWithMethodReferences() {
        System.out.println("3. LAMBDA WITH METHOD REFERENCES");
        System.out.println("Converting lambdas to method references\n");
        
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        
        // Lambda vs Method Reference - Static method
        System.out.println("Static method reference:");
        words.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        words.stream().map(String::toUpperCase).forEach(System.out::println);
        
        // Instance method reference
        System.out.println("\nInstance method reference:");
        String prefix = "Fruit: ";
        Function<String, String> addPrefix1 = str -> prefix.concat(str);
        Function<String, String> addPrefix2 = prefix::concat;
        System.out.println(addPrefix1.apply("Apple"));
        System.out.println(addPrefix2.apply("Banana"));
        
        // Constructor reference
        System.out.println("\nConstructor reference:");
        Supplier<List<String>> listSupplier1 = () -> new ArrayList<>();
        Supplier<List<String>> listSupplier2 = ArrayList::new;
        List<String> list1 = listSupplier1.get();
        List<String> list2 = listSupplier2.get();
        System.out.println("Lists created: " + (list1 != null) + ", " + (list2 != null));
        
        System.out.println();
    }
    
    // 4. LAMBDA LIMITATIONS
    static void lambdaLimitations() {
        System.out.println("4. LAMBDA LIMITATIONS");
        System.out.println("What lambdas cannot do\n");
        
        // 1. Cannot use with abstract classes
        System.out.println("1. Cannot use with abstract classes");
        System.out.println("   Abstract classes may have multiple abstract methods");
        
        // 2. Cannot use with regular classes
        System.out.println("2. Cannot use with regular classes");
        System.out.println("   Lambdas only work with functional interfaces");
        
        // 3. Cannot use with interfaces having multiple abstract methods
        System.out.println("3. Cannot use with interfaces having multiple abstract methods");
        System.out.println("   Compiler cannot determine target method");
        
        // 4. Variable capture limitations
        System.out.println("4. Variable capture limitations:");
        int localVar = 10;
        // localVar = 20; // This would cause compilation error in lambda below
        
        Consumer<Integer> lambda = x -> {
            System.out.println("   Local variable in lambda: " + localVar);
            // localVar++; // Cannot modify captured variable
        };
        lambda.accept(5);
        
        // 5. Cannot throw checked exceptions (without handling)
        System.out.println("5. Cannot throw checked exceptions without handling");
        
        System.out.println();
    }
    
    // 5. ADVANCED LAMBDA PATTERNS
    static void advancedLambdaPatterns() {
        System.out.println("5. ADVANCED LAMBDA PATTERNS\n");
        
        // Currying - returning functions from functions
        System.out.println("Currying pattern:");
        Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;
        Function<Integer, Integer> addFive = curriedAdd.apply(5);
        System.out.println("Curried add: " + addFive.apply(3));
        
        // Higher-order functions
        System.out.println("\nHigher-order functions:");
        Function<Function<Integer, Integer>, Function<Integer, Integer>> twice = 
            f -> x -> f.apply(f.apply(x));
        
        Function<Integer, Integer> increment = x -> x + 1;
        Function<Integer, Integer> incrementTwice = twice.apply(increment);
        System.out.println("Increment twice: " + incrementTwice.apply(5));
        
        // Memoization pattern
        System.out.println("\nMemoization pattern:");
        Map<Integer, Integer> cache = new HashMap<>();
        Function<Integer, Integer> fibonacci = new Function<Integer, Integer>() {
            public Integer apply(Integer n) {
                if (cache.containsKey(n)) {
                    System.out.println("Cache hit for " + n);
                    return cache.get(n);
                }
                
                int result;
                if (n <= 1) {
                    result = n;
                } else {
                    result = this.apply(n - 1) + this.apply(n - 2);
                }
                cache.put(n, result);
                return result;
            }
        };
        
        System.out.println("Fibonacci(10): " + fibonacci.apply(10));
        System.out.println("Fibonacci(10) again: " + fibonacci.apply(10));
        
        System.out.println();
    }
    
    // 6. LAMBDA WITH COLLECTIONS
    static void lambdaWithCollections() {
        System.out.println("6. LAMBDA WITH COLLECTIONS");
        System.out.println("Advanced collection operations\n");
        
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 50000, "IT"),
            new Employee("Bob", 60000, "HR"),
            new Employee("Charlie", 70000, "IT"),
            new Employee("Diana", 55000, "Finance"),
            new Employee("Eve", 65000, "IT")
        );
        
        // Complex filtering and sorting
        System.out.println("IT employees with salary > 55000, sorted by salary:");
        employees.stream()
                 .filter(emp -> "IT".equals(emp.department))
                 .filter(emp -> emp.salary > 55000)
                 .sorted((e1, e2) -> Double.compare(e1.salary, e2.salary))
                 .forEach(emp -> System.out.println("  " + emp));
        
        // Grouping with lambdas
        System.out.println("\nEmployees by department:");
        Map<String, List<Employee>> byDept = new HashMap<>();
        employees.forEach(emp -> {
            byDept.computeIfAbsent(emp.department, k -> new ArrayList<>()).add(emp);
        });
        byDept.forEach((dept, empList) -> {
            System.out.println("  " + dept + ": " + empList.size() + " employees");
        });
        
        // Custom collectors with lambdas
        System.out.println("\nTotal salary by department:");
        Map<String, Double> salaryByDept = new HashMap<>();
        employees.forEach(emp -> {
            salaryByDept.merge(emp.department, emp.salary, Double::sum);
        });
        salaryByDept.forEach((dept, total) -> {
            System.out.println("  " + dept + ": $" + total);
        });
        
        System.out.println();
    }
    
    // 7. CUSTOM FUNCTIONAL INTERFACES
    static void customFunctionalInterfaces() {
        System.out.println("7. CUSTOM FUNCTIONAL INTERFACES");
        System.out.println("Creating and using custom functional interfaces\n");
        
        // Simple custom interface
        Calculator calc = (a, b, operation) -> {
            switch (operation) {
                case "add": return a + b;
                case "subtract": return a - b;
                case "multiply": return a * b;
                case "divide": return b != 0 ? a / b : 0;
                default: return 0;
            }
        };
        
        System.out.println("Calculator results:");
        System.out.println("  10 + 5 = " + calc.calculate(10, 5, "add"));
        System.out.println("  10 - 5 = " + calc.calculate(10, 5, "subtract"));
        System.out.println("  10 * 5 = " + calc.calculate(10, 5, "multiply"));
        System.out.println("  10 / 5 = " + calc.calculate(10, 5, "divide"));
        
        // Generic custom interface
        Validator<String> emailValidator = email -> 
            email != null && email.contains("@") && email.contains(".");
        
        Validator<Integer> positiveValidator = num -> num != null && num > 0;
        
        System.out.println("\nValidation results:");
        System.out.println("  'test@email.com' is valid email: " + 
                          emailValidator.isValid("test@email.com"));
        System.out.println("  'invalid-email' is valid email: " + 
                          emailValidator.isValid("invalid-email"));
        System.out.println("  '5' is positive: " + positiveValidator.isValid(5));
        System.out.println("  '-3' is positive: " + positiveValidator.isValid(-3));
        
        // Chaining custom interfaces
        Processor<String> upperCase = String::toUpperCase;
        Processor<String> addExclamation = str -> str + "!";
        Processor<String> combined = str -> addExclamation.process(upperCase.process(str));
        
        System.out.println("\nChained processing:");
        System.out.println("  'hello world' -> '" + combined.process("hello world") + "'");
        
        System.out.println();
    }
    
    // Helper classes and interfaces
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
            return name + " ($" + salary + ")";
        }
    }
    
    @FunctionalInterface
    interface Calculator {
        double calculate(double a, double b, String operation);
    }
    
    @FunctionalInterface
    interface Validator<T> {
        boolean isValid(T value);
    }
    
    @FunctionalInterface
    interface Processor<T> {
        T process(T input);
    }
}