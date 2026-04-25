import java.util.*;
import java.util.function.*;

/**
 * Complete Optional Class Guide
 * Handling null values elegantly and avoiding NullPointerException
 */
public class OptionalClassComplete {
    
    public static void main(String[] args) {
        System.out.println("=== OPTIONAL CLASS COMPLETE GUIDE ===\n");
        
        optionalBasics();
        creatingOptionals();
        checkingValues();
        retrievingValues();
        transformingValues();
        filteringValues();
        combiningOptionals();
        realWorldExamples();
        bestPractices();
    }
    
    // 1. OPTIONAL BASICS
    static void optionalBasics() {
        System.out.println("1. OPTIONAL BASICS");
        System.out.println("Why Optional was introduced and basic concepts\n");
        
        // The problem with null
        System.out.println("The problem with null:");
        String nullString = null;
        try {
            int length = nullString.length(); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("  NullPointerException caught: " + e.getMessage());
        }
        
        // Optional solution
        System.out.println("\nOptional solution:");
        Optional<String> optionalString = Optional.ofNullable(nullString);
        if (optionalString.isPresent()) {
            System.out.println("  Length: " + optionalString.get().length());
        } else {
            System.out.println("  String is not present");
        }
        
        // Better approach with Optional
        optionalString.ifPresent(str -> System.out.println("  Length: " + str.length()));
        optionalString.ifPresentOrElse(
            str -> System.out.println("  Value: " + str),
            () -> System.out.println("  No value present")
        );
        
        System.out.println();
    }
    
    // 2. CREATING OPTIONALS
    static void creatingOptionals() {
        System.out.println("2. CREATING OPTIONALS");
        System.out.println("Different ways to create Optional instances\n");
        
        // Optional.empty()
        Optional<String> empty = Optional.empty();
        System.out.println("Empty Optional: " + empty);
        
        // Optional.of() - for non-null values
        Optional<String> nonNull = Optional.of("Hello World");
        System.out.println("Non-null Optional: " + nonNull);
        
        // Optional.of() with null throws exception
        try {
            Optional<String> nullOptional = Optional.of(null);
        } catch (NullPointerException e) {
            System.out.println("Optional.of(null) throws: " + e.getClass().getSimpleName());
        }
        
        // Optional.ofNullable() - safe for null values
        Optional<String> nullable1 = Optional.ofNullable("Valid Value");
        Optional<String> nullable2 = Optional.ofNullable(null);
        System.out.println("ofNullable with value: " + nullable1);
        System.out.println("ofNullable with null: " + nullable2);
        
        // Creating Optional from method returns
        Optional<String> fromMethod = getOptionalString(true);
        System.out.println("From method (true): " + fromMethod);
        
        Optional<String> fromMethodNull = getOptionalString(false);
        System.out.println("From method (false): " + fromMethodNull);
        
        System.out.println();
    }
    
    // 3. CHECKING VALUES
    static void checkingValues() {
        System.out.println("3. CHECKING VALUES");
        System.out.println("Methods to check if Optional contains a value\n");
        
        Optional<String> present = Optional.of("Present Value");
        Optional<String> absent = Optional.empty();
        
        // isPresent()
        System.out.println("isPresent() checks:");
        System.out.println("  Present Optional: " + present.isPresent());
        System.out.println("  Absent Optional: " + absent.isPresent());
        
        // isEmpty() (Java 11+)
        System.out.println("\nisEmpty() checks:");
        System.out.println("  Present Optional: " + present.isEmpty());
        System.out.println("  Absent Optional: " + absent.isEmpty());
        
        // Conditional execution
        System.out.println("\nConditional execution:");
        present.ifPresent(value -> System.out.println("  Found value: " + value));
        absent.ifPresent(value -> System.out.println("  This won't print"));
        
        // ifPresentOrElse() (Java 9+)
        System.out.println("\nifPresentOrElse():");
        present.ifPresentOrElse(
            value -> System.out.println("  Present: " + value),
            () -> System.out.println("  Not present")
        );
        
        absent.ifPresentOrElse(
            value -> System.out.println("  Present: " + value),
            () -> System.out.println("  Not present")
        );
        
        System.out.println();
    }
    
    // 4. RETRIEVING VALUES
    static void retrievingValues() {
        System.out.println("4. RETRIEVING VALUES");
        System.out.println("Safe ways to extract values from Optional\n");
        
        Optional<String> present = Optional.of("Hello");
        Optional<String> absent = Optional.empty();
        
        // get() - dangerous, can throw exception
        System.out.println("get() method (use with caution):");
        System.out.println("  Present value: " + present.get());
        try {
            System.out.println("  Absent value: " + absent.get());
        } catch (NoSuchElementException e) {
            System.out.println("  Exception: " + e.getClass().getSimpleName());
        }
        
        // orElse() - provide default value
        System.out.println("\norElse() - provide default:");
        System.out.println("  Present: " + present.orElse("Default"));
        System.out.println("  Absent: " + absent.orElse("Default"));
        
        // orElseGet() - lazy default computation
        System.out.println("\norElseGet() - lazy default:");
        System.out.println("  Present: " + present.orElseGet(() -> {
            System.out.println("    This won't execute");
            return "Computed Default";
        }));
        System.out.println("  Absent: " + absent.orElseGet(() -> {
            System.out.println("    Computing default...");
            return "Computed Default";
        }));
        
        // orElseThrow() - throw custom exception
        System.out.println("\norElseThrow() - custom exception:");
        System.out.println("  Present: " + present.orElseThrow(() -> 
            new IllegalStateException("Value not found")));
        
        try {
            System.out.println("  Absent: " + absent.orElseThrow(() -> 
                new IllegalStateException("Value not found")));
        } catch (IllegalStateException e) {
            System.out.println("  Exception: " + e.getMessage());
        }
        
        // orElseThrow() with no arguments (Java 10+)
        try {
            absent.orElseThrow(); // Throws NoSuchElementException
        } catch (NoSuchElementException e) {
            System.out.println("  orElseThrow() with no args: " + e.getClass().getSimpleName());
        }
        
        System.out.println();
    }
    
    // 5. TRANSFORMING VALUES
    static void transformingValues() {
        System.out.println("5. TRANSFORMING VALUES");
        System.out.println("Transform Optional values with map and flatMap\n");
        
        Optional<String> name = Optional.of("john doe");
        Optional<String> empty = Optional.empty();
        
        // map() - transform the value if present
        System.out.println("map() transformation:");
        Optional<String> upperName = name.map(String::toUpperCase);
        System.out.println("  Original: " + name.orElse("N/A"));
        System.out.println("  Uppercase: " + upperName.orElse("N/A"));
        
        Optional<String> emptyUpper = empty.map(String::toUpperCase);
        System.out.println("  Empty mapped: " + emptyUpper.orElse("N/A"));
        
        // Chaining map operations
        System.out.println("\nChaining map operations:");
        Optional<Integer> nameLength = name
            .map(String::trim)
            .map(String::toUpperCase)
            .map(String::length);
        System.out.println("  Name length: " + nameLength.orElse(0));
        
        // map with complex transformations
        System.out.println("\nComplex transformations:");
        Optional<Person> person = Optional.of(new Person("Alice", 25));
        Optional<String> personInfo = person
            .map(p -> p.name + " is " + p.age + " years old");
        System.out.println("  Person info: " + personInfo.orElse("No person"));
        
        // flatMap() - for nested Optionals
        System.out.println("\nflatMap() for nested Optionals:");
        Optional<Person> personWithEmail = Optional.of(new Person("Bob", 30, "bob@email.com"));
        Optional<Person> personWithoutEmail = Optional.of(new Person("Charlie", 35));
        
        Optional<String> email1 = personWithEmail.flatMap(Person::getEmail);
        Optional<String> email2 = personWithoutEmail.flatMap(Person::getEmail);
        
        System.out.println("  Bob's email: " + email1.orElse("No email"));
        System.out.println("  Charlie's email: " + email2.orElse("No email"));
        
        // Difference between map and flatMap
        System.out.println("\nDifference between map and flatMap:");
        
        // Using map with method that returns Optional
        Optional<Optional<String>> nestedOptional = personWithEmail.map(Person::getEmail);
        System.out.println("  map() result: " + nestedOptional); // Optional[Optional[bob@email.com]]
        
        // Using flatMap with method that returns Optional
        Optional<String> flatOptional = personWithEmail.flatMap(Person::getEmail);
        System.out.println("  flatMap() result: " + flatOptional); // Optional[bob@email.com]
        
        System.out.println();
    }
    
    // 6. FILTERING VALUES
    static void filteringValues() {
        System.out.println("6. FILTERING VALUES");
        System.out.println("Filter Optional values based on conditions\n");
        
        Optional<Integer> number = Optional.of(42);
        Optional<Integer> empty = Optional.empty();
        
        // Basic filtering
        System.out.println("Basic filtering:");
        Optional<Integer> evenNumber = number.filter(n -> n % 2 == 0);
        Optional<Integer> oddNumber = number.filter(n -> n % 2 != 0);
        
        System.out.println("  Original: " + number.orElse(0));
        System.out.println("  Even filter: " + evenNumber.orElse(-1));
        System.out.println("  Odd filter: " + oddNumber.orElse(-1));
        
        // Filtering empty Optional
        Optional<Integer> emptyFiltered = empty.filter(n -> n > 0);
        System.out.println("  Empty filtered: " + emptyFiltered.orElse(-1));
        
        // Complex filtering with objects
        System.out.println("\nComplex filtering:");
        Optional<Person> person = Optional.of(new Person("Alice", 25));
        
        Optional<Person> adult = person.filter(p -> p.age >= 18);
        Optional<Person> senior = person.filter(p -> p.age >= 65);
        
        System.out.println("  Is adult: " + adult.isPresent());
        System.out.println("  Is senior: " + senior.isPresent());
        
        // Chaining filter with map
        System.out.println("\nChaining filter with map:");
        Optional<String> adultName = person
            .filter(p -> p.age >= 18)
            .map(p -> p.name.toUpperCase());
        
        System.out.println("  Adult name: " + adultName.orElse("Not an adult"));
        
        // Multiple conditions
        System.out.println("\nMultiple filter conditions:");
        Optional<Person> validPerson = person
            .filter(p -> p.name != null)
            .filter(p -> p.name.length() > 2)
            .filter(p -> p.age > 0);
        
        System.out.println("  Valid person: " + validPerson.isPresent());
        
        System.out.println();
    }
    
    // 7. COMBINING OPTIONALS
    static void combiningOptionals() {
        System.out.println("7. COMBINING OPTIONALS");
        System.out.println("Working with multiple Optional values\n");
        
        Optional<String> firstName = Optional.of("John");
        Optional<String> lastName = Optional.of("Doe");
        Optional<String> middleName = Optional.empty();
        
        // Combining with flatMap
        System.out.println("Combining with flatMap:");
        Optional<String> fullName = firstName.flatMap(first ->
            lastName.map(last -> first + " " + last)
        );
        System.out.println("  Full name: " + fullName.orElse("Incomplete name"));
        
        // Three-way combination
        System.out.println("\nThree-way combination:");
        Optional<String> fullNameWithMiddle = firstName.flatMap(first ->
            middleName.flatMap(middle ->
                lastName.map(last -> first + " " + middle + " " + last)
            )
        );
        System.out.println("  With middle name: " + fullNameWithMiddle.orElse("No middle name"));
        
        // Alternative approach with helper method
        Optional<String> combinedName = combineNames(firstName, middleName, lastName);
        System.out.println("  Combined (helper): " + combinedName.orElse("Cannot combine"));
        
        // or() method (Java 9+) - provide alternative Optional
        System.out.println("\nor() method - alternative Optional:");
        Optional<String> primary = Optional.empty();
        Optional<String> secondary = Optional.of("Secondary Value");
        Optional<String> tertiary = Optional.of("Tertiary Value");
        
        Optional<String> result = primary
            .or(() -> secondary)
            .or(() -> tertiary);
        
        System.out.println("  Result: " + result.orElse("No value"));
        
        // Combining multiple Optionals into a List
        System.out.println("\nCombining into List:");
        List<String> names = Arrays.asList(firstName, lastName, middleName).stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(java.util.stream.Collectors.toList());
        
        System.out.println("  Available names: " + names);
        
        System.out.println();
    }
    
    // 8. REAL WORLD EXAMPLES
    static void realWorldExamples() {
        System.out.println("8. REAL WORLD EXAMPLES");
        System.out.println("Practical applications of Optional\n");
        
        // Example 1: User repository
        System.out.println("Example 1: User Repository");
        UserRepository repo = new UserRepository();
        
        Optional<User> user = repo.findById(1);
        user.ifPresentOrElse(
            u -> System.out.println("  Found user: " + u.name),
            () -> System.out.println("  User not found")
        );
        
        // Safe navigation
        String userEmail = repo.findById(1)
            .flatMap(User::getEmail)
            .orElse("no-email@example.com");
        System.out.println("  User email: " + userEmail);
        
        // Example 2: Configuration values
        System.out.println("\nExample 2: Configuration");
        ConfigService config = new ConfigService();
        
        int timeout = config.getTimeout()
            .filter(t -> t > 0)
            .orElse(30);
        System.out.println("  Timeout: " + timeout + " seconds");
        
        String dbUrl = config.getDatabaseUrl()
            .orElseThrow(() -> new IllegalStateException("Database URL not configured"));
        System.out.println("  Database URL: " + dbUrl);
        
        // Example 3: Processing pipeline
        System.out.println("\nExample 3: Processing Pipeline");
        Optional<String> input = Optional.of("  Hello World  ");
        
        Optional<String> processed = input
            .filter(s -> !s.trim().isEmpty())
            .map(String::trim)
            .map(String::toLowerCase)
            .filter(s -> s.length() > 5);
        
        processed.ifPresentOrElse(
            result -> System.out.println("  Processed: '" + result + "'"),
            () -> System.out.println("  Processing failed")
        );
        
        // Example 4: Nested object navigation
        System.out.println("\nExample 4: Nested Object Navigation");
        Optional<Company> company = Optional.of(new Company("TechCorp"));
        
        String ceoEmail = company
            .flatMap(Company::getCeo)
            .flatMap(Person::getEmail)
            .orElse("ceo@company.com");
        
        System.out.println("  CEO email: " + ceoEmail);
        
        System.out.println();
    }
    
    // 9. BEST PRACTICES
    static void bestPractices() {
        System.out.println("9. BEST PRACTICES");
        System.out.println("Do's and Don'ts when using Optional\n");
        
        System.out.println("✓ DO:");
        System.out.println("  - Use Optional as return type for methods that might not return a value");
        System.out.println("  - Use orElse() for simple default values");
        System.out.println("  - Use orElseGet() for expensive default computations");
        System.out.println("  - Use ifPresent() instead of isPresent() + get()");
        System.out.println("  - Chain operations with map() and flatMap()");
        System.out.println("  - Use filter() to add conditions");
        
        System.out.println("\n✗ DON'T:");
        System.out.println("  - Use Optional for fields or method parameters");
        System.out.println("  - Call get() without checking isPresent()");
        System.out.println("  - Use Optional.of() with potentially null values");
        System.out.println("  - Create Optional just to call isPresent()");
        System.out.println("  - Use Optional in collections (use empty collections instead)");
        
        // Good examples
        System.out.println("\nGood examples:");
        
        // Good: Method return type
        Optional<String> result = findUserName(123);
        result.ifPresent(name -> System.out.println("  User: " + name));
        
        // Good: Chaining operations
        Optional.of("test@email.com")
                .filter(email -> email.contains("@"))
                .map(String::toLowerCase)
                .ifPresent(email -> System.out.println("  Valid email: " + email));
        
        // Bad examples (commented out)
        System.out.println("\nBad examples (avoid these patterns):");
        System.out.println("  // Optional<String> field; // DON'T use as field");
        System.out.println("  // public void method(Optional<String> param) // DON'T use as parameter");
        System.out.println("  // if (optional.isPresent()) { optional.get(); } // Use ifPresent() instead");
        
        System.out.println();
    }
    
    // Helper methods and classes
    static Optional<String> getOptionalString(boolean returnValue) {
        return returnValue ? Optional.of("Valid String") : Optional.empty();
    }
    
    static Optional<String> combineNames(Optional<String> first, Optional<String> middle, Optional<String> last) {
        if (first.isPresent() && last.isPresent()) {
            String result = first.get();
            if (middle.isPresent()) {
                result += " " + middle.get();
            }
            result += " " + last.get();
            return Optional.of(result);
        }
        return Optional.empty();
    }
    
    static Optional<String> findUserName(int id) {
        // Simulate database lookup
        return id > 0 ? Optional.of("User" + id) : Optional.empty();
    }
    
    static class Person {
        String name;
        int age;
        String email;
        
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        Person(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }
        
        Optional<String> getEmail() {
            return Optional.ofNullable(email);
        }
        
        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
    
    static class User {
        String name;
        String email;
        
        User(String name, String email) {
            this.name = name;
            this.email = email;
        }
        
        Optional<String> getEmail() {
            return Optional.ofNullable(email);
        }
    }
    
    static class UserRepository {
        Map<Integer, User> users = Map.of(
            1, new User("Alice", "alice@email.com"),
            2, new User("Bob", null)
        );
        
        Optional<User> findById(int id) {
            return Optional.ofNullable(users.get(id));
        }
    }
    
    static class ConfigService {
        Optional<Integer> getTimeout() {
            return Optional.of(60);
        }
        
        Optional<String> getDatabaseUrl() {
            return Optional.of("jdbc:mysql://localhost:3306/mydb");
        }
    }
    
    static class Company {
        String name;
        Person ceo;
        
        Company(String name) {
            this.name = name;
            // CEO is optional
        }
        
        Optional<Person> getCeo() {
            return Optional.ofNullable(ceo);
        }
    }
}