# Advanced Functional Programming in Java 8+

## 1. Functional Programming Fundamentals

### What is Functional Programming?

Functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids changing state and mutable data. Key principles:

* **Immutability** – Data doesn't change after creation
* **Pure Functions** – Functions with no side effects
* **Higher-Order Functions** – Functions that take or return other functions
* **Function Composition** – Building complex operations from simple functions

### Why Java 8+ Embraces Functional Programming

Java 8+ introduced functional programming to make Java:

* **Shorter** – Less boilerplate code with lambdas
* **Cleaner** – More readable and expressive code
* **Faster** – Better performance with streams and parallelism
* **Modern** – Competitive with functional languages
* **Safer** – Reduced null pointer exceptions with Optional

---

## 2. Functional Interfaces Deep Dive

### Core Functional Interfaces

#### Consumer<T>
Takes input, returns nothing (void)
```java
Consumer<String> printer = System.out::println;
BiConsumer<String, Integer> nameAge = (name, age) -> 
    System.out.println(name + " is " + age);
```

#### Supplier<T>
Takes no input, returns output
```java
Supplier<String> messageSupplier = () -> "Hello World";
Supplier<Date> dateSupplier = Date::new;
```

#### Predicate<T>
Takes input, returns boolean
```java
Predicate<Integer> isEven = n -> n % 2 == 0;
Predicate<String> startsWithA = s -> s.startsWith("A");
// Combining: isEven.and(isPositive), isEven.or(isOdd), isEven.negate()
```

#### Function<T, R>
Takes input T, returns output R
```java
Function<String, Integer> length = String::length;
Function<Integer, String> toString = Object::toString;
// Composition: f1.andThen(f2), f1.compose(f2)
```

#### UnaryOperator<T>
Special Function where input and output types are same
```java
UnaryOperator<String> toUpper = String::toUpperCase;
UnaryOperator<Integer> square = n -> n * n;
```

#### BinaryOperator<T>
Special BiFunction where all types are same
```java
BinaryOperator<Integer> max = Integer::max;
BinaryOperator<String> concat = String::concat;
```

---

## 3. Lambda Expressions Advanced

### Syntax Variations
```java
// No parameters
Runnable r = () -> System.out.println("Hello");

// Single parameter (parentheses optional)
Consumer<String> c1 = str -> System.out.println(str);
Consumer<String> c2 = (str) -> System.out.println(str);

// Multiple parameters
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

// Block syntax
Function<String, String> process = str -> {
    String result = str.trim().toLowerCase();
    return "Processed: " + result;
};
```

### Variable Capture
```java
int multiplier = 10; // Must be effectively final
Function<Integer, Integer> multiply = n -> n * multiplier;
```

### Limitations
* Cannot use with abstract classes
* Cannot use with regular classes  
* Cannot use with interfaces having multiple abstract methods
* Cannot modify captured variables
* Cannot throw checked exceptions without handling

---

## 4. Method References Complete

### Four Types of Method References

#### 1. Static Method Reference
```java
// ClassName::staticMethodName
Function<String, Integer> parser = Integer::parseInt;
BinaryOperator<Integer> adder = Integer::sum;
```

#### 2. Instance Method Reference (Bound)
```java
// objectInstance::instanceMethodName
String prefix = "Hello, ";
Function<String, String> greeter = prefix::concat;
```

#### 3. Instance Method of Arbitrary Object (Unbound)
```java
// ClassName::instanceMethodName
Function<String, Integer> length = String::length;
Comparator<String> comparator = String::compareToIgnoreCase;
```

#### 4. Constructor Reference
```java
// ClassName::new
Supplier<ArrayList<String>> listSupplier = ArrayList::new;
Function<String, StringBuilder> sbCreator = StringBuilder::new;
```

---

## 5. Stream API Advanced

### Stream Creation
```java
// From collections
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream1 = list.stream();

// From arrays
Stream<String> stream2 = Arrays.stream(new String[]{"x", "y", "z"});

// Using Stream.of()
Stream<String> stream3 = Stream.of("1", "2", "3");

// Infinite streams
Stream<Integer> infinite = Stream.iterate(0, n -> n + 2);
Stream<Double> random = Stream.generate(Math::random);

// Range streams
IntStream range = IntStream.range(1, 10);
```

### Intermediate Operations (Lazy)
```java
// filter, map, flatMap, distinct, sorted, peek, limit, skip
// takeWhile, dropWhile (Java 9+)

numbers.stream()
    .filter(n -> n > 0)           // Keep positive numbers
    .map(n -> n * 2)              // Double each number
    .distinct()                   // Remove duplicates
    .sorted()                     // Sort ascending
    .limit(10)                    // Take first 10
    .peek(System.out::println)    // Debug/side effect
    .collect(Collectors.toList());
```

### Terminal Operations (Eager)
```java
// forEach, collect, reduce, count, min, max
// findFirst, findAny, allMatch, anyMatch, noneMatch

Optional<Integer> max = numbers.stream().max(Integer::compareTo);
long count = numbers.stream().filter(n -> n > 0).count();
Optional<Integer> first = numbers.stream().findFirst();
boolean allPositive = numbers.stream().allMatch(n -> n > 0);
```

### Advanced Collectors
```java
// Grouping
Map<String, List<Person>> byDept = people.stream()
    .collect(Collectors.groupingBy(Person::getDepartment));

// Partitioning
Map<Boolean, List<Person>> partitioned = people.stream()
    .collect(Collectors.partitioningBy(p -> p.getAge() > 30));

// Joining
String names = people.stream()
    .map(Person::getName)
    .collect(Collectors.joining(", ", "[", "]"));

// Statistics
DoubleSummaryStatistics stats = people.stream()
    .collect(Collectors.summarizingDouble(Person::getSalary));
```

---

## 6. Optional Class Complete

### Creating Optionals
```java
Optional<String> empty = Optional.empty();
Optional<String> nonNull = Optional.of("value");        // Throws if null
Optional<String> nullable = Optional.ofNullable(value); // Safe for null
```

### Checking and Retrieving Values
```java
// Checking
if (optional.isPresent()) { /* ... */ }
if (optional.isEmpty()) { /* ... */ }    // Java 11+

// Safe retrieval
String value = optional.orElse("default");
String value = optional.orElseGet(() -> computeDefault());
String value = optional.orElseThrow(() -> new IllegalStateException());

// Conditional execution
optional.ifPresent(System.out::println);
optional.ifPresentOrElse(                // Java 9+
    System.out::println,
    () -> System.out.println("Empty")
);
```

### Transforming and Filtering
```java
// Transform
Optional<Integer> length = optional.map(String::length);
Optional<String> email = person.flatMap(Person::getEmail);

// Filter
Optional<String> longString = optional.filter(s -> s.length() > 5);

// Chaining
Optional<String> result = optional
    .filter(s -> !s.isEmpty())
    .map(String::trim)
    .map(String::toUpperCase);
```

---

## 7. Advanced Functional Patterns

### Higher-Order Functions
```java
// Function that returns a function
Function<String, Function<String, String>> createGreeter = 
    greeting -> name -> greeting + ", " + name + "!";

Function<String, String> englishGreeter = createGreeter.apply("Hello");
```

### Currying
```java
// Breaking multi-parameter functions into single-parameter functions
Function<Integer, Function<Integer, Function<Integer, Integer>>> curriedAdd = 
    a -> b -> c -> a + b + c;

Function<Integer, Integer> addFiveAndThree = curriedAdd.apply(5).apply(3);
```

### Function Composition
```java
Function<String, String> trim = String::trim;
Function<String, String> upper = String::toUpperCase;
Function<String, String> addPrefix = s -> "Processed: " + s;

Function<String, String> pipeline = trim.andThen(upper).andThen(addPrefix);
```

### Memoization
```java
public static <T> Function<T, T> memoize(Function<T, T> function) {
    Map<T, T> cache = new ConcurrentHashMap<>();
    return input -> cache.computeIfAbsent(input, function);
}
```

---

## 8. Parallel Streams

### When to Use Parallel Streams
* Large datasets (>10,000 elements)
* CPU-intensive operations
* Independent operations (no shared state)

### Performance Considerations
```java
// Sequential
long sum1 = numbers.stream()
    .mapToLong(this::expensiveOperation)
    .sum();

// Parallel
long sum2 = numbers.parallelStream()
    .mapToLong(this::expensiveOperation)
    .sum();
```

---

## 9. Best Practices

### Do's
* Use Optional as return type for methods that might not return a value
* Prefer method references over lambdas when possible
* Use primitive streams (IntStream, LongStream, DoubleStream) to avoid boxing
* Chain operations efficiently (filter before map)
* Use parallel streams for CPU-intensive operations on large datasets

### Don'ts
* Don't use Optional for fields or method parameters
* Don't call get() without checking isPresent()
* Don't use Optional just to call isPresent()
* Don't use streams for simple iterations
* Don't modify external state in stream operations

---

## 10. Real-World Applications

### Data Processing Pipeline
```java
List<Employee> highPerformers = employees.stream()
    .filter(emp -> emp.getPerformanceRating() > 4.0)
    .filter(emp -> emp.getDepartment().equals("Engineering"))
    .sorted(Comparator.comparing(Employee::getSalary).reversed())
    .limit(10)
    .collect(Collectors.toList());
```

### Error Handling with Optional
```java
public Optional<User> findUser(String email) {
    return userRepository.findByEmail(email)
        .filter(user -> user.isActive())
        .map(this::enrichUserData);
}
```

### Functional Validation
```java
public ValidationResult validateUser(User user) {
    return Stream.of(
        validateName(user.getName()),
        validateEmail(user.getEmail()),
        validateAge(user.getAge())
    )
    .filter(result -> !result.isValid())
    .findFirst()
    .orElse(ValidationResult.success());
}
```



There is a BIG logical difference between:

limit() → skip()


and

skip() → limit()


Order matters in streams ⚠

✅ CASE 1 → limit() FIRST THEN skip()
List<Integer> numbers = List.of(10, 20, 30, 40, 50);

numbers.stream()
       .limit(3)
       .skip(1)
       .forEach(System.out::println);

Step-by-step:

Original:

10, 20, 30, 40, 50


After limit(3):

10, 20, 30


After skip(1):

20, 30

✅ Output:
20
30

✅ CASE 2 → skip() FIRST THEN limit()
numbers.stream()
       .skip(1)
       .limit(3)
       .forEach(System.out::println);

Step-by-step:

Original:

10, 20, 30, 40, 50


After skip(1):

20, 30, 40, 50


After limit(3):

20, 30, 40

✅ Output:
20
30
40

🔥 SEE THE DIFFERENCE
Order	Result
limit(3).skip(1)	20, 30
skip(1).limit(3)	20, 30, 40

Completely different results 🔥

🧠 In Your Second Highest Example
Version A
.sorted(desc)
.limit(2)
.skip(1)
.findFirst()


Logic:
👉 Take top 2
👉 Skip highest
👉 Get second highest

✔ Efficient when you only care about top 2

Version B
.sorted(desc)
.skip(1)
.limit(1)
.findFirst()


Logic:
👉 Skip highest
👉 Take 1 element
👉 Get second highest

✔ Cleaner & more readable

🚀 Which One Is Better?

For second highest:

.skip(1).findFirst()


Is simplest and cleanest.

🧠 Important Concept

Streams execute operations in the order you write them.

They are:
✔ Lazy
✔ Pipeline based
✔ Order-sensitive