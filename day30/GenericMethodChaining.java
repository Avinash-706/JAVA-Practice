// =====================================================================
//          GENERIC METHOD CHAINING - TYPE-SAFE BUILDERS
// =====================================================================
// Generics with method chaining for type-safe, reusable builders

// EXAMPLE 1: Generic Builder Base Class
abstract class Builder<T> {
    protected T instance;
    
    public Builder(T instance) {
        this.instance = instance;
    }
    
    public T build() {
        return instance;
    }
}

// EXAMPLE 2: Generic Repository with Fluent API
class Repository<T> {
    private Class<T> entityClass;
    private String tableName;
    private java.util.List<String> conditions;
    private String orderBy;
    private int limit;
    
    public Repository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.conditions = new java.util.ArrayList<>();
    }
    
    public Repository<T> from(String tableName) {
        this.tableName = tableName;
        return this;
    }
    
    public Repository<T> where(String condition) {
        conditions.add(condition);
        return this;
    }
    
    public Repository<T> orderBy(String column) {
        this.orderBy = column;
        return this;
    }
    
    public Repository<T> limit(int limit) {
        this.limit = limit;
        return this;
    }
    
    public java.util.List<T> findAll() {
        System.out.println("Finding all " + entityClass.getSimpleName() + 
                         " from " + tableName);
        if(!conditions.isEmpty()) {
            System.out.println("Conditions: " + conditions);
        }
        if(orderBy != null) {
            System.out.println("Order by: " + orderBy);
        }
        if(limit > 0) {
            System.out.println("Limit: " + limit);
        }
        return new java.util.ArrayList<>();
    }
}

// EXAMPLE 3: Generic Response Builder
class Response<T> {
    private int statusCode;
    private String message;
    private T data;
    private java.util.Map<String, String> headers;
    
    private Response() {
        headers = new java.util.HashMap<>();
    }
    
    public static <T> ResponseBuilder<T> builder() {
        return new ResponseBuilder<>();
    }
    
    public static class ResponseBuilder<T> {
        private Response<T> response;
        
        public ResponseBuilder() {
            response = new Response<>();
        }
        
        public ResponseBuilder<T> statusCode(int statusCode) {
            response.statusCode = statusCode;
            return this;
        }
        
        public ResponseBuilder<T> message(String message) {
            response.message = message;
            return this;
        }
        
        public ResponseBuilder<T> data(T data) {
            response.data = data;
            return this;
        }
        
        public ResponseBuilder<T> header(String key, String value) {
            response.headers.put(key, value);
            return this;
        }
        
        public Response<T> build() {
            return response;
        }
    }
    
    @Override
    public String toString() {
        return "Response{" +
               "statusCode=" + statusCode +
               ", message='" + message + '\'' +
               ", data=" + data +
               ", headers=" + headers +
               '}';
    }
}

// EXAMPLE 4: Generic Optional with Fluent API
class Optional<T> {
    private T value;
    
    private Optional(T value) {
        this.value = value;
    }
    
    public static <T> Optional<T> of(T value) {
        if(value == null) {
            throw new NullPointerException("Value cannot be null");
        }
        return new Optional<>(value);
    }
    
    public static <T> Optional<T> ofNullable(T value) {
        return new Optional<>(value);
    }
    
    public static <T> Optional<T> empty() {
        return new Optional<>(null);
    }
    
    public boolean isPresent() {
        return value != null;
    }
    
    public T get() {
        if(value == null) {
            throw new NullPointerException("No value present");
        }
        return value;
    }
    
    public T orElse(T defaultValue) {
        return value != null ? value : defaultValue;
    }
    
    public Optional<T> filter(java.util.function.Predicate<T> predicate) {
        if(!isPresent()) {
            return this;
        }
        return predicate.test(value) ? this : empty();
    }
    
    public <U> Optional<U> map(java.util.function.Function<T, U> mapper) {
        if(!isPresent()) {
            return empty();
        }
        return Optional.ofNullable(mapper.apply(value));
    }
    
    public void ifPresent(java.util.function.Consumer<T> consumer) {
        if(isPresent()) {
            consumer.accept(value);
        }
    }
    
    @Override
    public String toString() {
        return isPresent() ? "Optional[" + value + "]" : "Optional.empty";
    }
}

// EXAMPLE 5: Generic Pair with Fluent Operations
class Pair<K, V> {
    private K key;
    private V value;
    
    private Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }
    
    public K getKey() { return key; }
    public V getValue() { return value; }
    
    public <U> Pair<K, U> mapValue(java.util.function.Function<V, U> mapper) {
        return new Pair<>(key, mapper.apply(value));
    }
    
    public <U> Pair<U, V> mapKey(java.util.function.Function<K, U> mapper) {
        return new Pair<>(mapper.apply(key), value);
    }
    
    public Pair<V, K> swap() {
        return new Pair<>(value, key);
    }
    
    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}

// Sample entities for demonstration
class User {
    String name;
    int age;
    
    User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}

class Product {
    String name;
    double price;
    
    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }
}

public class GenericMethodChaining {
    
    public static void main(String[] args) {
        
        System.out.println("=== GENERIC REPOSITORY ===\n");
        
        // Type-safe repository for User
        Repository<User> userRepo = new Repository<>(User.class);
        userRepo.from("users")
               .where("age > 18")
               .where("city = 'Mumbai'")
               .orderBy("name")
               .limit(10)
               .findAll();
        
        System.out.println();
        
        // Type-safe repository for Product
        Repository<Product> productRepo = new Repository<>(Product.class);
        productRepo.from("products")
                  .where("price < 1000")
                  .orderBy("price")
                  .limit(5)
                  .findAll();
        
        System.out.println("\n=== GENERIC RESPONSE BUILDER ===\n");
        
        // Response with User data
        Response<User> userResponse = Response.<User>builder()
            .statusCode(200)
            .message("Success")
            .data(new User("John", 25))
            .header("Content-Type", "application/json")
            .build();
        
        System.out.println(userResponse);
        
        // Response with Product list
        java.util.List<Product> products = java.util.Arrays.asList(
            new Product("Laptop", 50000),
            new Product("Mouse", 500)
        );
        
        Response<java.util.List<Product>> productResponse = 
            Response.<java.util.List<Product>>builder()
                .statusCode(200)
                .message("Products fetched")
                .data(products)
                .header("Cache-Control", "no-cache")
                .build();
        
        System.out.println(productResponse);
        
        System.out.println("\n=== GENERIC OPTIONAL ===\n");
        
        // Optional with value
        Optional<String> opt1 = Optional.of("Hello");
        System.out.println("opt1: " + opt1);
        System.out.println("Is present: " + opt1.isPresent());
        System.out.println("Value: " + opt1.get());
        
        // Optional empty
        Optional<String> opt2 = Optional.empty();
        System.out.println("\nopt2: " + opt2);
        System.out.println("Is present: " + opt2.isPresent());
        System.out.println("Or else: " + opt2.orElse("Default"));
        
        // Chaining operations
        Optional<Integer> opt3 = Optional.of("12345")
            .map(String::length)
            .filter(len -> len > 3);
        
        System.out.println("\nChained optional: " + opt3);
        opt3.ifPresent(len -> System.out.println("Length: " + len));
        
        System.out.println("\n=== GENERIC PAIR ===\n");
        
        // Pair with different types
        Pair<String, Integer> pair1 = Pair.of("Age", 25);
        System.out.println("Original: " + pair1);
        
        // Map value
        Pair<String, String> pair2 = pair1.mapValue(age -> age + " years");
        System.out.println("Mapped value: " + pair2);
        
        // Map key
        Pair<Integer, Integer> pair3 = Pair.of("Age", 25)
            .mapKey(String::length);
        System.out.println("Mapped key: " + pair3);
        
        // Swap
        Pair<Integer, String> pair4 = pair1.swap();
        System.out.println("Swapped: " + pair4);
        
        System.out.println("\n=== ADVANTAGES OF GENERIC METHOD CHAINING ===");
        System.out.println("1. Type safety at compile time");
        System.out.println("2. Reusable for different types");
        System.out.println("3. No casting needed");
        System.out.println("4. IDE auto-completion support");
        System.out.println("5. Catches errors early");
        
        System.out.println("\n=== REAL-WORLD EXAMPLES ===");
        System.out.println("Stream API: Stream<T>");
        System.out.println("Optional: Optional<T>");
        System.out.println("CompletableFuture: CompletableFuture<T>");
        System.out.println("Builder patterns in frameworks");
    }
}
