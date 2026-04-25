import java.util.*;
import java.util.function.*;

/**
 * Comprehensive coverage of Java 8 Predefined Functional Interfaces
 * From basic to advanced usage patterns
 */
public class FunctionalInterfacesAdvanced {
    
    public static void main(String[] args) {
        System.out.println("=== PREDEFINED FUNCTIONAL INTERFACES ===\n");
        
        demonstrateConsumer();
        demonstrateSupplier();
        demonstratePredicate();
        demonstrateFunction();
        demonstrateBiFunction();
        demonstrateUnaryOperator();
        demonstrateBinaryOperator();
        demonstrateAdvancedCombinations();
    }
    
    // 1. CONSUMER - Takes input, returns nothing
    static void demonstrateConsumer() {
        System.out.println("1. CONSUMER INTERFACE");
        System.out.println("Takes input, returns void\n");
        
        // Basic Consumer
        Consumer<String> printer = str -> System.out.println("Printing: " + str);
        printer.accept("Hello World");
        
        // Consumer with List
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        Consumer<String> upperCasePrinter = name -> System.out.println(name.toUpperCase());
        names.forEach(upperCasePrinter);
        
        // BiConsumer - takes two inputs
        BiConsumer<String, Integer> nameAgePrinter = (name, age) -> 
            System.out.println(name + " is " + age + " years old");
        nameAgePrinter.accept("John", 25);
        
        // Consumer chaining with andThen()
        Consumer<String> c1 = str -> System.out.print("First: " + str);
        Consumer<String> c2 = str -> System.out.println(" Second: " + str.length());
        Consumer<String> combined = c1.andThen(c2);
        combined.accept("Testing");
        
        System.out.println();
    }
    
    // 2. SUPPLIER - Takes no input, returns output
    static void demonstrateSupplier() {
        System.out.println("2. SUPPLIER INTERFACE");
        System.out.println("Takes no input, returns output\n");
        
        // Basic Supplier
        Supplier<String> messageSupplier = () -> "Hello from Supplier!";
        System.out.println(messageSupplier.get());
        
        // Random number supplier
        Supplier<Integer> randomSupplier = () -> (int)(Math.random() * 100);
        System.out.println("Random number: " + randomSupplier.get());
        
        // Date supplier
        Supplier<Date> dateSupplier = Date::new;
        System.out.println("Current date: " + dateSupplier.get());
        
        // Lazy evaluation with Supplier
        Supplier<String> expensiveOperation = () -> {
            System.out.println("Performing expensive operation...");
            return "Expensive result";
        };
        
        // Only executed when needed
        if (true) { // some condition
            System.out.println(expensiveOperation.get());
        }
        
        System.out.println();
    }
    
    // 3. PREDICATE - Takes input, returns boolean
    static void demonstratePredicate() {
        System.out.println("3. PREDICATE INTERFACE");
        System.out.println("Takes input, returns boolean\n");
        
        // Basic Predicate
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 5 even? " + isEven.test(5));
        
        // String Predicate
        Predicate<String> startsWithA = str -> str.startsWith("A");
        System.out.println("Does 'Apple' start with A? " + startsWithA.test("Apple"));
        
        // Predicate combinations
        Predicate<Integer> isPositive = num -> num > 0;
        Predicate<Integer> isPositiveEven = isEven.and(isPositive);
        System.out.println("Is 4 positive and even? " + isPositiveEven.test(4));
        System.out.println("Is -4 positive and even? " + isPositiveEven.test(-4));
        
        // OR combination
        Predicate<String> startsWithB = str -> str.startsWith("B");
        Predicate<String> startsWithAOrB = startsWithA.or(startsWithB);
        System.out.println("Does 'Cat' start with A or B? " + startsWithAOrB.test("Cat"));
        System.out.println("Does 'Ball' start with A or B? " + startsWithAOrB.test("Ball"));
        
        // NEGATE
        Predicate<Integer> isOdd = isEven.negate();
        System.out.println("Is 5 odd? " + isOdd.test(5));
        
        // BiPredicate
        BiPredicate<String, Integer> lengthChecker = (str, len) -> str.length() == len;
        System.out.println("Is 'Hello' length 5? " + lengthChecker.test("Hello", 5));
        
        System.out.println();
    }
    
    // 4. FUNCTION - Takes input, returns output (transformation)
    static void demonstrateFunction() {
        System.out.println("4. FUNCTION INTERFACE");
        System.out.println("Takes input, returns output (transformation)\n");
        
        // Basic Function
        Function<String, Integer> stringLength = str -> str.length();
        System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));
        
        // Square function
        Function<Integer, Integer> square = num -> num * num;
        System.out.println("Square of 5: " + square.apply(5));
        
        // String transformation
        Function<String, String> upperCase = String::toUpperCase;
        System.out.println("Uppercase 'hello': " + upperCase.apply("hello"));
        
        // Function composition - andThen
        Function<Integer, Integer> addTwo = num -> num + 2;
        Function<Integer, Integer> multiplyByThree = num -> num * 3;
        Function<Integer, Integer> addThenMultiply = addTwo.andThen(multiplyByThree);
        System.out.println("(5 + 2) * 3 = " + addThenMultiply.apply(5));
        
        // Function composition - compose
        Function<Integer, Integer> multiplyThenAdd = addTwo.compose(multiplyByThree);
        System.out.println("(5 * 3) + 2 = " + multiplyThenAdd.apply(5));
        
        // Identity function
        Function<String, String> identity = Function.identity();
        System.out.println("Identity of 'test': " + identity.apply("test"));
        
        System.out.println();
    }
    
    // 5. BIFUNCTION - Takes two inputs, returns output
    static void demonstrateBiFunction() {
        System.out.println("5. BIFUNCTION INTERFACE");
        System.out.println("Takes two inputs, returns output\n");
        
        // Basic BiFunction
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("5 + 3 = " + add.apply(5, 3));
        
        // String concatenation
        BiFunction<String, String, String> concat = (s1, s2) -> s1 + " " + s2;
        System.out.println("Concat result: " + concat.apply("Hello", "World"));
        
        // Complex BiFunction
        BiFunction<String, Integer, String> repeat = (str, times) -> {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < times; i++) {
                result.append(str);
            }
            return result.toString();
        };
        System.out.println("Repeat 'Hi' 3 times: " + repeat.apply("Hi", 3));
        
        // BiFunction with andThen
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        Function<Integer, String> toString = Object::toString;
        BiFunction<Integer, Integer, String> multiplyAndConvert = multiply.andThen(toString);
        System.out.println("4 * 5 as string: " + multiplyAndConvert.apply(4, 5));
        
        System.out.println();
    }
    
    // 6. UNARYOPERATOR - Special case of Function where input and output types are same
    static void demonstrateUnaryOperator() {
        System.out.println("6. UNARYOPERATOR INTERFACE");
        System.out.println("Special Function where input and output types are same\n");
        
        // Basic UnaryOperator
        UnaryOperator<Integer> square = num -> num * num;
        System.out.println("Square of 6: " + square.apply(6));
        
        // String UnaryOperator
        UnaryOperator<String> addPrefix = str -> "Mr. " + str;
        System.out.println("Add prefix: " + addPrefix.apply("Smith"));
        
        // Identity UnaryOperator
        UnaryOperator<String> identity = UnaryOperator.identity();
        System.out.println("Identity: " + identity.apply("unchanged"));
        
        System.out.println();
    }
    
    // 7. BINARYOPERATOR - Special case of BiFunction where both inputs and output are same type
    static void demonstrateBinaryOperator() {
        System.out.println("7. BINARYOPERATOR INTERFACE");
        System.out.println("Special BiFunction where all types are same\n");
        
        // Basic BinaryOperator
        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
        System.out.println("Max of 10 and 15: " + max.apply(10, 15));
        
        // String BinaryOperator
        BinaryOperator<String> longerString = (s1, s2) -> s1.length() > s2.length() ? s1 : s2;
        System.out.println("Longer string: " + longerString.apply("Hello", "Hi"));
        
        // Using BinaryOperator.maxBy and minBy
        BinaryOperator<String> maxByLength = BinaryOperator.maxBy(Comparator.comparing(String::length));
        System.out.println("Max by length: " + maxByLength.apply("Java", "Programming"));
        
        BinaryOperator<Integer> minValue = BinaryOperator.minBy(Integer::compareTo);
        System.out.println("Min value: " + minValue.apply(25, 15));
        
        System.out.println();
    }
    
    // 8. ADVANCED COMBINATIONS
    static void demonstrateAdvancedCombinations() {
        System.out.println("8. ADVANCED COMBINATIONS");
        System.out.println("Real-world usage patterns\n");
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "Engineer"),
            new Person("Bob", 30, "Doctor"),
            new Person("Charlie", 35, "Teacher"),
            new Person("Diana", 28, "Engineer")
        );
        
        // Complex filtering and transformation
        Predicate<Person> isEngineer = person -> "Engineer".equals(person.profession);
        Predicate<Person> isYoung = person -> person.age < 30;
        Function<Person, String> getNameAndAge = person -> person.name + " (" + person.age + ")";
        
        System.out.println("Young Engineers:");
        people.stream()
               .filter(isEngineer.and(isYoung))
               .map(getNameAndAge)
               .forEach(System.out::println);
        
        // Using Supplier for lazy initialization
        Supplier<List<String>> expensiveListSupplier = () -> {
            System.out.println("Creating expensive list...");
            return Arrays.asList("Item1", "Item2", "Item3");
        };
        
        // Only create when needed
        boolean needList = true;
        if (needList) {
            List<String> items = expensiveListSupplier.get();
            items.forEach(System.out::println);
        }
        
        System.out.println();
    }
    
    // Helper class for demonstrations
    static class Person {
        String name;
        int age;
        String profession;
        
        Person(String name, int age, String profession) {
            this.name = name;
            this.age = age;
            this.profession = profession;
        }
        
        @Override
        public String toString() {
            return name + " (" + age + ", " + profession + ")";
        }
    }
}