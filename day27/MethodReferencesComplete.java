import java.util.*;
import java.util.function.*;

/**
 * Complete Method References Guide
 * All 4 types with advanced usage patterns
 */
public class MethodReferencesComplete {
    
    public static void main(String[] args) {
        System.out.println("=== METHOD REFERENCES COMPLETE GUIDE ===\n");
        
        staticMethodReferences();
        instanceMethodReferences();
        instanceMethodOfArbitraryObject();
        constructorReferences();
        advancedMethodReferencePatterns();
        methodReferenceVsLambda();
    }
    
    // 1. STATIC METHOD REFERENCES
    static void staticMethodReferences() {
        System.out.println("1. STATIC METHOD REFERENCES");
        System.out.println("ClassName::staticMethodName\n");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // Integer::sum
        System.out.println("Using Integer::sum");
        BinaryOperator<Integer> adder = Integer::sum;
        System.out.println("Sum: " + adder.apply(10, 20));
        
        // Math::max
        System.out.println("\nUsing Math::max");
        BinaryOperator<Integer> maxFinder = Math::max;
        System.out.println("Max: " + maxFinder.apply(15, 25));
        
        // String::valueOf
        System.out.println("\nUsing String::valueOf");
        Function<Integer, String> converter = String::valueOf;
        numbers.stream().map(converter).forEach(s -> System.out.print(s + " "));
        
        // Custom static method
        System.out.println("\n\nUsing custom static method:");
        Function<String, String> reverser = MethodReferencesComplete::reverseString;
        System.out.println("Reversed 'hello': " + reverser.apply("hello"));
        
        // Collections::sort
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        System.out.println("Before sort: " + names);
        Consumer<List<String>> sorter = Collections::sort;
        sorter.accept(names);
        System.out.println("After sort: " + names);
        
        System.out.println();
    }
    
    // 2. INSTANCE METHOD REFERENCES (Bound)
    static void instanceMethodReferences() {
        System.out.println("2. INSTANCE METHOD REFERENCES (Bound)");
        System.out.println("objectInstance::instanceMethodName\n");
        
        String prefix = "Hello, ";
        
        // String instance method
        Function<String, String> greeter = prefix::concat;
        System.out.println("Greeting: " + greeter.apply("World"));
        
        // List instance method
        List<String> list = new ArrayList<>();
        Consumer<String> adder = list::add;
        adder.accept("Item1");
        adder.accept("Item2");
        System.out.println("List contents: " + list);
        
        // PrintStream instance method
        Consumer<String> printer = System.out::println;
        Arrays.asList("Line1", "Line2", "Line3").forEach(printer);
        
        // Custom object instance method
        Calculator calc = new Calculator();
        BinaryOperator<Double> multiplier = calc::multiply;
        System.out.println("Multiplication: " + multiplier.apply(4.5, 2.0));
        
        // StringBuilder instance method
        StringBuilder sb = new StringBuilder("Start");
        Function<String, StringBuilder> appender = sb::append;
        appender.apply(" Middle").append(" End");
        System.out.println("StringBuilder result: " + sb.toString());
        
        System.out.println();
    }
    
    // 3. INSTANCE METHOD OF ARBITRARY OBJECT (Unbound)
    static void instanceMethodOfArbitraryObject() {
        System.out.println("3. INSTANCE METHOD OF ARBITRARY OBJECT (Unbound)");
        System.out.println("ClassName::instanceMethodName\n");
        
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        
        // String::length
        System.out.println("String lengths:");
        words.stream().map(String::length).forEach(len -> System.out.print(len + " "));
        
        // String::toUpperCase
        System.out.println("\n\nUppercase words:");
        words.stream().map(String::toUpperCase).forEach(System.out::println);
        
        // String::startsWith (with additional parameter)
        System.out.println("Words starting with 'a':");
        Predicate<String> startsWithA = str -> str.startsWith("a");
        // Note: String::startsWith needs additional parameter, so lambda is clearer here
        words.stream().filter(startsWithA).forEach(System.out::println);
        
        // Comparator using method reference
        System.out.println("\nSorted by length:");
        words.stream()
             .sorted(Comparator.comparing(String::length))
             .forEach(System.out::println);
        
        // Custom class method reference
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Charlie", 20)
        );
        
        System.out.println("\nPeople sorted by age:");
        people.stream()
              .sorted(Comparator.comparing(Person::getAge))
              .forEach(System.out::println);
        
        System.out.println("\nPeople names:");
        people.stream()
              .map(Person::getName)
              .forEach(System.out::println);
        
        System.out.println();
    }
    
    // 4. CONSTRUCTOR REFERENCES
    static void constructorReferences() {
        System.out.println("4. CONSTRUCTOR REFERENCES");
        System.out.println("ClassName::new\n");
        
        // Simple constructor reference
        Supplier<ArrayList<String>> listSupplier = ArrayList::new;
        List<String> newList = listSupplier.get();
        System.out.println("Created new list: " + (newList != null));
        
        // Constructor with parameters
        Function<String, StringBuilder> sbCreator = StringBuilder::new;
        StringBuilder sb = sbCreator.apply("Initial content");
        System.out.println("StringBuilder: " + sb.toString());
        
        // BiFunction constructor
        BiFunction<String, Integer, Person> personCreator = Person::new;
        Person person = personCreator.apply("John", 35);
        System.out.println("Created person: " + person);
        
        // Array constructor reference
        Function<Integer, String[]> arrayCreator = String[]::new;
        String[] stringArray = arrayCreator.apply(5);
        System.out.println("Created array of length: " + stringArray.length);
        
        // Using constructor reference in streams
        System.out.println("\nCreating Person objects from data:");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<Integer> ages = Arrays.asList(25, 30, 35);
        
        // Simulate creating Person objects (in real scenario, you'd zip the streams)
        names.stream()
             .map(name -> new Person(name, 25)) // Using constructor
             .forEach(System.out::println);
        
        // Generic constructor reference
        Supplier<HashSet<String>> setSupplier = HashSet::new;
        Set<String> newSet = setSupplier.get();
        newSet.add("Element1");
        System.out.println("Created set with element: " + newSet);
        
        System.out.println();
    }
    
    // 5. ADVANCED METHOD REFERENCE PATTERNS
    static void advancedMethodReferencePatterns() {
        System.out.println("5. ADVANCED METHOD REFERENCE PATTERNS\n");
        
        // Chaining method references
        List<String> words = Arrays.asList("  hello  ", "  world  ", "  java  ");
        
        System.out.println("Original words: " + words);
        System.out.println("Processed words:");
        words.stream()
             .map(String::trim)           // Remove whitespace
             .map(String::toUpperCase)    // Convert to uppercase
             .map(s -> "*** " + s + " ***") // Add decoration (lambda needed here)
             .forEach(System.out::println);
        
        // Method reference with Optional
        System.out.println("\nOptional with method references:");
        Optional<String> optional = Optional.of("test string");
        optional.map(String::toUpperCase)
                .map(String::length)
                .ifPresent(System.out::println);
        
        // Method reference in collectors
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 30),
            new Person("Alice", 35)
        );
        
        System.out.println("\nGrouping by name using method reference:");
        Map<String, List<Person>> groupedByName = people.stream()
            .collect(Collectors.groupingBy(Person::getName));
        groupedByName.forEach((name, personList) -> 
            System.out.println(name + ": " + personList.size() + " people"));
        
        // Method reference with parallel streams
        System.out.println("\nParallel processing with method references:");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.parallelStream()
               .map(MethodReferencesComplete::expensiveOperation)
               .forEach(result -> System.out.print(result + " "));
        
        System.out.println();
    }
    
    // 6. METHOD REFERENCE VS LAMBDA COMPARISON
    static void methodReferenceVsLambda() {
        System.out.println("\n6. METHOD REFERENCE VS LAMBDA COMPARISON");
        System.out.println("When to use which approach\n");
        
        List<String> items = Arrays.asList("apple", "banana", "cherry");
        
        // Case 1: Simple method call - Method Reference is cleaner
        System.out.println("Case 1: Simple method call");
        System.out.println("Lambda:           items.forEach(item -> System.out.println(item))");
        System.out.println("Method Reference: items.forEach(System.out::println)");
        items.forEach(System.out::println);
        
        // Case 2: Additional logic - Lambda is better
        System.out.println("\nCase 2: Additional logic needed");
        System.out.println("Lambda is better when you need additional logic:");
        items.forEach(item -> {
            if (item.length() > 5) {
                System.out.println("Long: " + item.toUpperCase());
            } else {
                System.out.println("Short: " + item);
            }
        });
        
        // Case 3: Method with parameters - depends on complexity
        System.out.println("\nCase 3: Method with parameters");
        Predicate<String> startsWithA_Lambda = str -> str.startsWith("a");
        // Method reference would need additional setup for parameters
        
        // Case 4: Constructor calls - Method Reference is cleaner
        System.out.println("\nCase 4: Constructor calls");
        System.out.println("Lambda:           () -> new ArrayList<>()");
        System.out.println("Method Reference: ArrayList::new");
        
        // Performance comparison (minimal difference in most cases)
        System.out.println("\nPerformance: Both compile to similar bytecode");
        System.out.println("Choose based on readability and maintainability");
        
        System.out.println();
    }
    
    // Helper methods and classes
    static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    static Integer expensiveOperation(Integer num) {
        // Simulate some processing
        try {
            Thread.sleep(10); // Small delay to simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return num * num;
    }
    
    static class Calculator {
        public double multiply(double a, double b) {
            return a * b;
        }
        
        public double add(double a, double b) {
            return a + b;
        }
    }
    
    static class Person {
        private String name;
        private int age;
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() {
            return name;
        }
        
        public int getAge() {
            return age;
        }
        
        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}