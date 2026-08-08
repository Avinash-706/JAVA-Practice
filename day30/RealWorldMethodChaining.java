// =====================================================================
//          REAL-WORLD METHOD CHAINING EXAMPLES
// =====================================================================
// Demonstrates method chaining in Java standard library and frameworks

import java.util.*;
import java.util.stream.*;

public class RealWorldMethodChaining {
    
    public static void main(String[] args) {
        
        System.out.println("=== 1. STRINGBUILDER - CLASSIC METHOD CHAINING ===\n");
        
        // StringBuilder uses method chaining extensively
        String result = new StringBuilder()
            .append("Hello")
            .append(" ")
            .append("World")
            .append("!")
            .insert(6, "Beautiful ")
            .reverse()
            .toString();
        
        System.out.println("Result: " + result);
        
        // Building complex strings
        String html = new StringBuilder()
            .append("<html>")
            .append("<head><title>Page</title></head>")
            .append("<body>")
            .append("<h1>Welcome</h1>")
            .append("<p>Content here</p>")
            .append("</body>")
            .append("</html>")
            .toString();
        
        System.out.println("\nHTML:\n" + html);
        
        System.out.println("\n=== 2. STREAM API - FUNCTIONAL METHOD CHAINING ===\n");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Chaining stream operations
        int sum = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * 2)
            .reduce(0, Integer::sum);
        
        System.out.println("Sum of doubled even numbers: " + sum);
        
        // Complex stream pipeline
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        
        String result2 = names.stream()
            .filter(name -> name.length() > 3)
            .map(String::toUpperCase)
            .sorted()
            .collect(Collectors.joining(", "));
        
        System.out.println("Filtered names: " + result2);
        
        // Grouping with streams
        Map<Integer, List<String>> groupedByLength = names.stream()
            .collect(Collectors.groupingBy(String::length));
        
        System.out.println("Grouped by length: " + groupedByLength);
        
        System.out.println("\n=== 3. OPTIONAL - CHAINING WITH NULLS ===\n");
        
        // Optional chaining
        String name = Optional.ofNullable(getName())
            .map(String::toUpperCase)
            .orElse("UNKNOWN");
        
        System.out.println("Name: " + name);
        
        // Complex optional chaining
        Optional.of("12345")
            .filter(s -> s.length() > 3)
            .map(Integer::parseInt)
            .ifPresent(num -> System.out.println("Number: " + num));
        
        System.out.println("\n=== 4. COMPARATOR - CHAINING COMPARISONS ===\n");
        
        class Person {
            String name;
            int age;
            String city;
            
            Person(String name, int age, String city) {
                this.name = name;
                this.age = age;
                this.city = city;
            }
            
            @Override
            public String toString() {
                return name + " (" + age + ", " + city + ")";
            }
        }
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, "Mumbai"),
            new Person("Bob", 25, "Delhi"),
            new Person("Charlie", 30, "Mumbai"),
            new Person("David", 25, "Mumbai")
        );
        
        // Chaining comparators
        people.sort(Comparator
            .comparing((Person p) -> p.age)
            .thenComparing(p -> p.city)
            .thenComparing(p -> p.name));
        
        System.out.println("Sorted people:");
        people.forEach(System.out::println);
        
        System.out.println("\n=== 5. COLLECTIONS - UTILITY METHOD CHAINING ===\n");
        
        // Creating and modifying collections
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "A", "B", "C");
        
        // Stream chaining on collections
        list.stream()
            .peek(s -> System.out.print(s + " "))
            .map(String::toLowerCase)
            .forEach(s -> {});
        
        System.out.println();
        
        System.out.println("\n=== 6. DATE/TIME API - IMMUTABLE CHAINING ===\n");
        
        // LocalDate chaining (immutable)
        java.time.LocalDate today = java.time.LocalDate.now();
        System.out.println("Today: " + today);
        
        java.time.LocalDate futureDate = today
            .plusDays(10)
            .plusMonths(2)
            .plusYears(1);
        
        System.out.println("Future date: " + futureDate);
        
        // LocalDateTime chaining
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.LocalDateTime modified = now
            .withHour(10)
            .withMinute(30)
            .withSecond(0);
        
        System.out.println("Modified time: " + modified);
        
        System.out.println("\n=== 7. BIGDECIMAL - FINANCIAL CALCULATIONS ===\n");
        
        java.math.BigDecimal price = new java.math.BigDecimal("100.00");
        
        java.math.BigDecimal finalPrice = price
            .multiply(new java.math.BigDecimal("1.18"))  // Add 18% tax
            .subtract(new java.math.BigDecimal("10.00")) // Discount
            .setScale(2, java.math.RoundingMode.HALF_UP);
        
        System.out.println("Original price: " + price);
        System.out.println("Final price: " + finalPrice);
        
        System.out.println("\n=== 8. CUSTOM LOGGER (FLUENT API) ===\n");
        
        // Simulating fluent logger
        class Logger {
            private String level;
            private String message;
            private String timestamp;
            
            public Logger level(String level) {
                this.level = level;
                return this;
            }
            
            public Logger message(String message) {
                this.message = message;
                return this;
            }
            
            public Logger timestamp(String timestamp) {
                this.timestamp = timestamp;
                return this;
            }
            
            public void log() {
                System.out.println("[" + timestamp + "] " + level + ": " + message);
            }
        }
        
        new Logger()
            .level("INFO")
            .timestamp("2024-01-01 10:30:00")
            .message("Application started")
            .log();
        
        new Logger()
            .level("ERROR")
            .timestamp("2024-01-01 10:31:00")
            .message("Connection failed")
            .log();
        
        System.out.println("\n=== REAL-WORLD FRAMEWORKS USING METHOD CHAINING ===");
        System.out.println("1. JUnit 5: Assertions.assertThat().isEqualTo()");
        System.out.println("2. Mockito: when().thenReturn()");
        System.out.println("3. Spring: @Bean().scope().lazy()");
        System.out.println("4. Hibernate: session.createQuery().setParameter()");
        System.out.println("5. JDBC: connection.prepareStatement().setString()");
        System.out.println("6. Lombok: @Builder annotation");
        System.out.println("7. Apache Commons: StringUtils.trim().toLowerCase()");
        
        System.out.println("\n=== BENEFITS IN REAL WORLD ===");
        System.out.println("1. More readable code (reads like English)");
        System.out.println("2. Less boilerplate");
        System.out.println("3. Easier to maintain");
        System.out.println("4. Better IDE support");
        System.out.println("5. Encourages immutability");
    }
    
    static String getName() {
        return "john";  // Could be null in real scenario
    }
}
