import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Advanced Functional Programming Patterns in Java
 * Higher-order functions, Currying, Composition, Monads, and more
 */
public class FunctionalProgrammingPatterns {
    
    public static void main(String[] args) {
        System.out.println("=== ADVANCED FUNCTIONAL PROGRAMMING PATTERNS ===\n");
        
        higherOrderFunctions();
        curryingAndPartialApplication();
        functionComposition();
        monadicPatterns();
        immutabilityPatterns();
        lazyEvaluation();
        memoization();
        functionalErrorHandling();
    }
    
    // 1. HIGHER-ORDER FUNCTIONS
    static void higherOrderFunctions() {
        System.out.println("1. HIGHER-ORDER FUNCTIONS");
        System.out.println("Functions that take or return other functions\n");
        
        // Function that takes a function as parameter
        Function<List<Integer>, Integer> processNumbers = createProcessor(
            numbers -> numbers.stream().mapToInt(Integer::intValue).sum()
        );
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Sum using higher-order function: " + processNumbers.apply(numbers));
        
        // Function that returns a function
        Function<String, Function<String, String>> createGreeter = greeting -> name -> greeting + ", " + name + "!";
        
        Function<String, String> englishGreeter = createGreeter.apply("Hello");
        Function<String, String> spanishGreeter = createGreeter.apply("Hola");
        
        System.out.println("English: " + englishGreeter.apply("Alice"));
        System.out.println("Spanish: " + spanishGreeter.apply("Bob"));
        
        // Function factory
        List<Function<Integer, Integer>> operations = createMathOperations();
        System.out.println("Math operations on 5:");
        operations.forEach(op -> System.out.println("  Result: " + op.apply(5)));
        
        // Strategy pattern with functions
        Calculator calc = new Calculator();
        System.out.println("\nCalculator with function strategies:");
        System.out.println("  Add: " + calc.calculate(10, 5, (a, b) -> a + b));
        System.out.println("  Multiply: " + calc.calculate(10, 5, (a, b) -> a * b));
        System.out.println("  Power: " + calc.calculate(2, 3, (a, b) -> (int) Math.pow(a, b)));
        
        System.out.println();
    }
    
    // 2. CURRYING AND PARTIAL APPLICATION
    static void curryingAndPartialApplication() {
        System.out.println("2. CURRYING AND PARTIAL APPLICATION");
        System.out.println("Breaking down multi-parameter functions\n");
        
        // Basic currying
        Function<Integer, Function<Integer, Function<Integer, Integer>>> curriedAdd = 
            a -> b -> c -> a + b + c;
        
        System.out.println("Curried addition:");
        Function<Integer, Function<Integer, Integer>> addFive = curriedAdd.apply(5);
        Function<Integer, Integer> addFiveAndThree = addFive.apply(3);
        System.out.println("  5 + 3 + 2 = " + addFiveAndThree.apply(2));
        
        // Partial application
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        Function<Integer, Integer> multiplyByTen = partialApply(multiply, 10);
        
        System.out.println("\nPartial application:");
        System.out.println("  7 * 10 = " + multiplyByTen.apply(7));
        
        // Curried validation
        Function<Integer, Function<Integer, Function<String, ValidationResult>>> validateRange = 
            min -> max -> value -> {
                try {
                    int num = Integer.parseInt(value);
                    if (num >= min && num <= max) {
                        return new ValidationResult(true, "Valid");
                    } else {
                        return new ValidationResult(false, "Out of range");
                    }
                } catch (NumberFormatException e) {
                    return new ValidationResult(false, "Not a number");
                }
            };
        
        Function<String, ValidationResult> ageValidator = validateRange.apply(0).apply(120);
        Function<String, ValidationResult> percentValidator = validateRange.apply(0).apply(100);
        
        System.out.println("\nCurried validation:");
        System.out.println("  Age '25': " + ageValidator.apply("25"));
        System.out.println("  Age '150': " + ageValidator.apply("150"));
        System.out.println("  Percent '85': " + percentValidator.apply("85"));
        
        System.out.println();
    }
    
    // 3. FUNCTION COMPOSITION
    static void functionComposition() {
        System.out.println("3. FUNCTION COMPOSITION");
        System.out.println("Combining simple functions to create complex ones\n");
        
        // Basic composition
        Function<String, String> trim = String::trim;
        Function<String, String> toLowerCase = String::toLowerCase;
        Function<String, String> addPrefix = str -> "processed: " + str;
        
        // Using andThen
        Function<String, String> pipeline1 = trim.andThen(toLowerCase).andThen(addPrefix);
        System.out.println("Pipeline 1: '" + pipeline1.apply("  HELLO WORLD  ") + "'");
        
        // Using compose
        Function<String, String> pipeline2 = addPrefix.compose(toLowerCase).compose(trim);
        System.out.println("Pipeline 2: '" + pipeline2.apply("  HELLO WORLD  ") + "'");
        
        // Complex composition
        Function<List<String>, List<String>> processWords = createWordProcessor();
        List<String> words = Arrays.asList("  apple  ", "  BANANA  ", "  Cherry  ");
        System.out.println("Word processing: " + processWords.apply(words));
        
        // Mathematical composition
        Function<Double, Double> square = x -> x * x;
        Function<Double, Double> addOne = x -> x + 1;
        Function<Double, Double> sqrt = Math::sqrt;
        
        Function<Double, Double> complexFunction = square.andThen(addOne).andThen(sqrt);
        System.out.println("Complex math (sqrt((x^2) + 1)) for x=3: " + complexFunction.apply(3.0));
        
        // Composition with validation
        Function<String, Optional<Integer>> parseInteger = str -> {
            try {
                return Optional.of(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        };
        
        Function<Integer, Optional<Integer>> validatePositive = num -> 
            num > 0 ? Optional.of(num) : Optional.empty();
        
        Function<Integer, String> format = num -> "Valid number: " + num;
        
        Function<String, String> safeProcessor = str -> 
            parseInteger.apply(str)
                       .flatMap(validatePositive)
                       .map(format)
                       .orElse("Invalid input");
        
        System.out.println("\nSafe processing:");
        System.out.println("  '42': " + safeProcessor.apply("42"));
        System.out.println("  '-5': " + safeProcessor.apply("-5"));
        System.out.println("  'abc': " + safeProcessor.apply("abc"));
        
        System.out.println();
    }
    
    // 4. MONADIC PATTERNS
    static void monadicPatterns() {
        System.out.println("4. MONADIC PATTERNS");
        System.out.println("Functional patterns for handling context and chaining operations\n");
        
        // Optional as a monad
        System.out.println("Optional monad:");
        Optional<String> user = Optional.of("john.doe@email.com");
        
        Optional<String> result = user
            .filter(email -> email.contains("@"))
            .map(email -> email.split("@")[0])
            .map(String::toUpperCase)
            .filter(name -> name.length() > 3);
        
        System.out.println("  Processed user: " + result.orElse("Invalid"));
        
        // Stream as a monad
        System.out.println("\nStream monad:");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        List<String> processed = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .map(n -> "Square: " + n)
            .collect(Collectors.toList());
        
        System.out.println("  Processed numbers: " + processed);
        
        // Custom Maybe monad
        System.out.println("\nCustom Maybe monad:");
        Maybe<String> maybe1 = Maybe.of("Hello");
        Maybe<String> maybe2 = Maybe.empty();
        
        Maybe<String> result1 = maybe1
            .map(String::toUpperCase)
            .flatMap(s -> Maybe.of(s + " World"))
            .filter(s -> s.length() > 5);
        
        Maybe<String> result2 = maybe2
            .map(String::toUpperCase)
            .flatMap(s -> Maybe.of(s + " World"));
        
        System.out.println("  Maybe 1: " + result1.getValue().orElse("Empty"));
        System.out.println("  Maybe 2: " + result2.getValue().orElse("Empty"));
        
        // Either monad for error handling
        System.out.println("\nEither monad:");
        Either<String, Integer> success = Either.right(42);
        Either<String, Integer> failure = Either.left("Error occurred");
        
        Either<String, String> processed1 = success
            .map(n -> n * 2)
            .map(n -> "Result: " + n);
        
        Either<String, String> processed2 = failure
            .map(n -> n * 2)
            .map(n -> "Result: " + n);
        
        System.out.println("  Success: " + processed1.fold(err -> "Error: " + err, val -> val));
        System.out.println("  Failure: " + processed2.fold(err -> "Error: " + err, val -> val));
        
        System.out.println();
    }
    
    // 5. IMMUTABILITY PATTERNS
    static void immutabilityPatterns() {
        System.out.println("5. IMMUTABILITY PATTERNS");
        System.out.println("Working with immutable data structures\n");
        
        // Immutable list operations
        List<Integer> original = Arrays.asList(1, 2, 3, 4, 5);
        
        List<Integer> doubled = original.stream()
            .map(n -> n * 2)
            .collect(Collectors.toList());
        
        List<Integer> filtered = original.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        
        System.out.println("Original: " + original);
        System.out.println("Doubled: " + doubled);
        System.out.println("Filtered: " + filtered);
        
        // Immutable object updates
        ImmutablePerson person = new ImmutablePerson("Alice", 25, "alice@email.com");
        ImmutablePerson updatedPerson = person.withAge(26).withEmail("alice.new@email.com");
        
        System.out.println("\nImmutable updates:");
        System.out.println("  Original: " + person);
        System.out.println("  Updated: " + updatedPerson);
        
        // Functional data transformation
        List<ImmutablePerson> people = Arrays.asList(
            new ImmutablePerson("Alice", 25, "alice@email.com"),
            new ImmutablePerson("Bob", 30, "bob@email.com"),
            new ImmutablePerson("Charlie", 35, "charlie@email.com")
        );
        
        List<ImmutablePerson> updatedPeople = people.stream()
            .map(p -> p.withAge(p.age + 1))
            .collect(Collectors.toList());
        
        System.out.println("\nAge increment (functional):");
        updatedPeople.forEach(p -> System.out.println("  " + p));
        
        System.out.println();
    }
    
    // 6. LAZY EVALUATION
    static void lazyEvaluation() {
        System.out.println("6. LAZY EVALUATION");
        System.out.println("Deferring computation until needed\n");
        
        // Supplier for lazy evaluation
        Supplier<String> expensiveComputation = () -> {
            System.out.println("  Performing expensive computation...");
            try {
                Thread.sleep(100); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Expensive Result";
        };
        
        System.out.println("Lazy supplier created (no computation yet)");
        
        // Computation happens only when needed
        boolean needResult = true;
        if (needResult) {
            System.out.println("Result: " + expensiveComputation.get());
        }
        
        // Stream lazy evaluation
        System.out.println("\nStream lazy evaluation:");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        Stream<Integer> lazyStream = numbers.stream()
            .peek(n -> System.out.print("Processing " + n + " "))
            .filter(n -> n % 2 == 0)
            .peek(n -> System.out.print("-> " + n + " "))
            .map(n -> n * n);
        
        System.out.println("Stream created but not executed yet");
        System.out.println("Now executing with limit(2):");
        lazyStream.limit(2).forEach(n -> System.out.print("Final: " + n + " "));
        
        // Lazy initialization pattern
        System.out.println("\n\nLazy initialization:");
        LazyValue<List<String>> lazyList = new LazyValue<>(() -> {
            System.out.println("  Creating expensive list...");
            return Arrays.asList("Item1", "Item2", "Item3");
        });
        
        System.out.println("LazyValue created");
        System.out.println("First access: " + lazyList.get().size());
        System.out.println("Second access: " + lazyList.get().size()); // No recomputation
        
        System.out.println();
    }
    
    // 7. MEMOIZATION
    static void memoization() {
        System.out.println("7. MEMOIZATION");
        System.out.println("Caching function results for performance\n");
        
        // Simple memoization
        Function<Integer, Integer> fibonacci = memoize(new Function<Integer, Integer>() {
            public Integer apply(Integer n) {
                System.out.println("  Computing fibonacci(" + n + ")");
                if (n <= 1) return n;
                return this.apply(n - 1) + this.apply(n - 2);
            }
        });
        
        System.out.println("Memoized Fibonacci:");
        System.out.println("fib(10) = " + fibonacci.apply(10));
        System.out.println("fib(10) again = " + fibonacci.apply(10)); // Cached
        System.out.println("fib(11) = " + fibonacci.apply(11)); // Reuses cached values
        
        // Memoized expensive operation
        Function<String, String> expensiveTransform = memoize(str -> {
            System.out.println("  Expensive transform for: " + str);
            try {
                Thread.sleep(100); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return str.toUpperCase().replace(" ", "_");
        });
        
        System.out.println("\nMemoized transformation:");
        System.out.println("Transform 'hello world': " + expensiveTransform.apply("hello world"));
        System.out.println("Transform 'hello world' again: " + expensiveTransform.apply("hello world"));
        System.out.println("Transform 'goodbye world': " + expensiveTransform.apply("goodbye world"));
        
        System.out.println();
    }
    
    // 8. FUNCTIONAL ERROR HANDLING
    static void functionalErrorHandling() {
        System.out.println("8. FUNCTIONAL ERROR HANDLING");
        System.out.println("Handling errors without exceptions\n");
        
        // Try monad pattern
        System.out.println("Try monad pattern:");
        
        Try<Integer> success = Try.of(() -> Integer.parseInt("42"));
        Try<Integer> failure = Try.of(() -> Integer.parseInt("not-a-number"));
        
        String result1 = success
            .map(n -> n * 2)
            .map(n -> "Result: " + n)
            .recover(ex -> "Error: " + ex.getMessage());
        
        String result2 = failure
            .map(n -> n * 2)
            .map(n -> "Result: " + n)
            .recover(ex -> "Error: " + ex.getMessage());
        
        System.out.println("  Success case: " + result1);
        System.out.println("  Failure case: " + result2);
        
        // Validation accumulation
        System.out.println("\nValidation accumulation:");
        
        List<String> errors = new ArrayList<>();
        
        ValidationResult nameResult = validateName("", errors);
        ValidationResult ageResult = validateAge("-5", errors);
        ValidationResult emailResult = validateEmail("invalid-email", errors);
        
        if (errors.isEmpty()) {
            System.out.println("  All validations passed");
        } else {
            System.out.println("  Validation errors:");
            errors.forEach(error -> System.out.println("    " + error));
        }
        
        // Railway-oriented programming
        System.out.println("\nRailway-oriented programming:");
        
        Result<String> pipeline1 = Result.success("  hello world  ")
            .flatMap(this::validateNotEmpty)
            .map(String::trim)
            .map(String::toUpperCase)
            .flatMap(this::validateLength);
        
        Result<String> pipeline2 = Result.success("")
            .flatMap(this::validateNotEmpty)
            .map(String::trim)
            .map(String::toUpperCase)
            .flatMap(this::validateLength);
        
        System.out.println("  Pipeline 1: " + pipeline1.fold(err -> "Error: " + err, val -> "Success: " + val));
        System.out.println("  Pipeline 2: " + pipeline2.fold(err -> "Error: " + err, val -> "Success: " + val));
        
        System.out.println();
    }
    
    // Helper methods and classes
    static Function<List<Integer>, Integer> createProcessor(Function<List<Integer>, Integer> processor) {
        return list -> {
            System.out.println("  Processing list: " + list);
            return processor.apply(list);
        };
    }
    
    static List<Function<Integer, Integer>> createMathOperations() {
        return Arrays.asList(
            x -> x + 10,
            x -> x * 2,
            x -> x * x,
            x -> -x
        );
    }
    
    static <T, U> Function<U, T> partialApply(BiFunction<T, U, T> function, T firstArg) {
        return secondArg -> function.apply(firstArg, secondArg);
    }
    
    static Function<List<String>, List<String>> createWordProcessor() {
        Function<String, String> trim = String::trim;
        Function<String, String> toLowerCase = String::toLowerCase;
        Function<String, String> capitalize = str -> 
            str.isEmpty() ? str : str.substring(0, 1).toUpperCase() + str.substring(1);
        
        Function<String, String> processor = trim.andThen(toLowerCase).andThen(capitalize);
        
        return words -> words.stream().map(processor).collect(Collectors.toList());
    }
    
    static <T> Function<T, T> memoize(Function<T, T> function) {
        Map<T, T> cache = new ConcurrentHashMap<>();
        return input -> cache.computeIfAbsent(input, function);
    }
    
    static ValidationResult validateName(String name, List<String> errors) {
        if (name == null || name.trim().isEmpty()) {
            errors.add("Name cannot be empty");
            return new ValidationResult(false, "Invalid name");
        }
        return new ValidationResult(true, "Valid name");
    }
    
    static ValidationResult validateAge(String age, List<String> errors) {
        try {
            int ageInt = Integer.parseInt(age);
            if (ageInt < 0 || ageInt > 120) {
                errors.add("Age must be between 0 and 120");
                return new ValidationResult(false, "Invalid age");
            }
        } catch (NumberFormatException e) {
            errors.add("Age must be a number");
            return new ValidationResult(false, "Invalid age format");
        }
        return new ValidationResult(true, "Valid age");
    }
    
    static ValidationResult validateEmail(String email, List<String> errors) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            errors.add("Email must contain @ and .");
            return new ValidationResult(false, "Invalid email");
        }
        return new ValidationResult(true, "Valid email");
    }
    
    Result<String> validateNotEmpty(String str) {
        return str.isEmpty() ? Result.failure("String is empty") : Result.success(str);
    }
    
    Result<String> validateLength(String str) {
        return str.length() < 3 ? Result.failure("String too short") : Result.success(str);
    }
    
    // Helper classes
    static class Calculator {
        public int calculate(int a, int b, BinaryOperator<Integer> operation) {
            return operation.apply(a, b);
        }
    }
    
    static class ValidationResult {
        boolean valid;
        String message;
        
        ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }
        
        @Override
        public String toString() {
            return valid ? "Valid: " + message : "Invalid: " + message;
        }
    }
    
    static class Maybe<T> {
        private final Optional<T> value;
        
        private Maybe(Optional<T> value) {
            this.value = value;
        }
        
        static <T> Maybe<T> of(T value) {
            return new Maybe<>(Optional.ofNullable(value));
        }
        
        static <T> Maybe<T> empty() {
            return new Maybe<>(Optional.empty());
        }
        
        <U> Maybe<U> map(Function<T, U> mapper) {
            return new Maybe<>(value.map(mapper));
        }
        
        <U> Maybe<U> flatMap(Function<T, Maybe<U>> mapper) {
            return value.map(mapper).orElse(Maybe.empty());
        }
        
        Maybe<T> filter(Predicate<T> predicate) {
            return new Maybe<>(value.filter(predicate));
        }
        
        Optional<T> getValue() {
            return value;
        }
    }
    
    static class Either<L, R> {
        private final L left;
        private final R right;
        private final boolean isRight;
        
        private Either(L left, R right, boolean isRight) {
            this.left = left;
            this.right = right;
            this.isRight = isRight;
        }
        
        static <L, R> Either<L, R> left(L value) {
            return new Either<>(value, null, false);
        }
        
        static <L, R> Either<L, R> right(R value) {
            return new Either<>(null, value, true);
        }
        
        <U> Either<L, U> map(Function<R, U> mapper) {
            return isRight ? Either.right(mapper.apply(right)) : Either.left(left);
        }
        
        <U> U fold(Function<L, U> leftMapper, Function<R, U> rightMapper) {
            return isRight ? rightMapper.apply(right) : leftMapper.apply(left);
        }
    }
    
    static class ImmutablePerson {
        final String name;
        final int age;
        final String email;
        
        ImmutablePerson(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }
        
        ImmutablePerson withName(String newName) {
            return new ImmutablePerson(newName, this.age, this.email);
        }
        
        ImmutablePerson withAge(int newAge) {
            return new ImmutablePerson(this.name, newAge, this.email);
        }
        
        ImmutablePerson withEmail(String newEmail) {
            return new ImmutablePerson(this.name, this.age, newEmail);
        }
        
        @Override
        public String toString() {
            return name + " (" + age + ", " + email + ")";
        }
    }
    
    static class LazyValue<T> {
        private final Supplier<T> supplier;
        private T value;
        private boolean computed = false;
        
        LazyValue(Supplier<T> supplier) {
            this.supplier = supplier;
        }
        
        T get() {
            if (!computed) {
                value = supplier.get();
                computed = true;
            }
            return value;
        }
    }
    
    static class Try<T> {
        private final T value;
        private final Exception exception;
        
        private Try(T value, Exception exception) {
            this.value = value;
            this.exception = exception;
        }
        
        static <T> Try<T> of(Supplier<T> supplier) {
            try {
                return new Try<>(supplier.get(), null);
            } catch (Exception e) {
                return new Try<>(null, e);
            }
        }
        
        <U> Try<U> map(Function<T, U> mapper) {
            if (exception != null) {
                return new Try<>(null, exception);
            }
            try {
                return new Try<>(mapper.apply(value), null);
            } catch (Exception e) {
                return new Try<>(null, e);
            }
        }
        
        T recover(Function<Exception, T> recovery) {
            return exception != null ? recovery.apply(exception) : value;
        }
    }
    
    static class Result<T> {
        private final T value;
        private final String error;
        private final boolean isSuccess;
        
        private Result(T value, String error, boolean isSuccess) {
            this.value = value;
            this.error = error;
            this.isSuccess = isSuccess;
        }
        
        static <T> Result<T> success(T value) {
            return new Result<>(value, null, true);
        }
        
        static <T> Result<T> failure(String error) {
            return new Result<>(null, error, false);
        }
        
        <U> Result<U> map(Function<T, U> mapper) {
            return isSuccess ? Result.success(mapper.apply(value)) : Result.failure(error);
        }
        
        <U> Result<U> flatMap(Function<T, Result<U>> mapper) {
            return isSuccess ? mapper.apply(value) : Result.failure(error);
        }
        
        <U> U fold(Function<String, U> errorMapper, Function<T, U> successMapper) {
            return isSuccess ? successMapper.apply(value) : errorMapper.apply(error);
        }
    }
}