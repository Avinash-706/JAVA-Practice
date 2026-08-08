// =====================================================================
//          BUILDER PATTERN - ADVANCED METHOD CHAINING
// =====================================================================
// Builder Pattern: Creational design pattern using method chaining
// Solves: Complex object construction with many optional parameters

// PROBLEM: Constructor with many parameters (Telescoping Constructor Anti-pattern)
class PersonWithoutBuilder {
    private String firstName;
    private String lastName;
    private int age;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    
    // Telescoping constructors - BAD PRACTICE
    public PersonWithoutBuilder(String firstName, String lastName) {
        this(firstName, lastName, 0);
    }
    
    public PersonWithoutBuilder(String firstName, String lastName, int age) {
        this(firstName, lastName, age, null);
    }
    
    public PersonWithoutBuilder(String firstName, String lastName, int age, String phone) {
        this(firstName, lastName, age, phone, null);
    }
    
    public PersonWithoutBuilder(String firstName, String lastName, int age, 
                               String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }
    // ... many more constructors needed!
}

// SOLUTION: Builder Pattern with Method Chaining
class Person {
    // Required parameters
    private final String firstName;
    private final String lastName;
    
    // Optional parameters
    private final int age;
    private final String phone;
    private final String email;
    private final String address;
    private final String city;
    private final String state;
    private final String zipCode;
    
    // Private constructor - only Builder can create Person
    private Person(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.email = builder.email;
        this.address = builder.address;
        this.city = builder.city;
        this.state = builder.state;
        this.zipCode = builder.zipCode;
    }
    
    // Static nested Builder class
    public static class Builder {
        // Required parameters
        private final String firstName;
        private final String lastName;
        
        // Optional parameters - initialized to default values
        private int age = 0;
        private String phone = "";
        private String email = "";
        private String address = "";
        private String city = "";
        private String state = "";
        private String zipCode = "";
        
        // Constructor with required parameters
        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        
        // Setter methods return Builder for chaining
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder address(String address) {
            this.address = address;
            return this;
        }
        
        public Builder city(String city) {
            this.city = city;
            return this;
        }
        
        public Builder state(String state) {
            this.state = state;
            return this;
        }
        
        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }
        
        // Build method creates and returns Person object
        public Person build() {
            return new Person(this);
        }
    }
    
    @Override
    public String toString() {
        return "Person{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", age=" + age +
               ", phone='" + phone + '\'' +
               ", email='" + email + '\'' +
               ", address='" + address + '\'' +
               ", city='" + city + '\'' +
               ", state='" + state + '\'' +
               ", zipCode='" + zipCode + '\'' +
               '}';
    }
}

// EXAMPLE 2: Computer Builder Pattern
class Computer {
    // Required
    private final String CPU;
    private final String RAM;
    
    // Optional
    private final String storage;
    private final String GPU;
    private final String motherboard;
    private final String powerSupply;
    private final boolean hasWiFi;
    private final boolean hasBluetooth;
    
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
        this.motherboard = builder.motherboard;
        this.powerSupply = builder.powerSupply;
        this.hasWiFi = builder.hasWiFi;
        this.hasBluetooth = builder.hasBluetooth;
    }
    
    public static class Builder {
        // Required
        private final String CPU;
        private final String RAM;
        
        // Optional
        private String storage = "256GB SSD";
        private String GPU = "Integrated";
        private String motherboard = "Standard";
        private String powerSupply = "500W";
        private boolean hasWiFi = false;
        private boolean hasBluetooth = false;
        
        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }
        
        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder GPU(String GPU) {
            this.GPU = GPU;
            return this;
        }
        
        public Builder motherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }
        
        public Builder powerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }
        
        public Builder hasWiFi(boolean hasWiFi) {
            this.hasWiFi = hasWiFi;
            return this;
        }
        
        public Builder hasBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
    
    @Override
    public String toString() {
        return "Computer{" +
               "CPU='" + CPU + '\'' +
               ", RAM='" + RAM + '\'' +
               ", storage='" + storage + '\'' +
               ", GPU='" + GPU + '\'' +
               ", motherboard='" + motherboard + '\'' +
               ", powerSupply='" + powerSupply + '\'' +
               ", hasWiFi=" + hasWiFi +
               ", hasBluetooth=" + hasBluetooth +
               '}';
    }
}

public class BuilderPatternAdvanced {
    
    public static void main(String[] args) {
        
        System.out.println("=== BUILDER PATTERN DEMONSTRATION ===\n");
        
        // Creating Person with only required fields
        Person person1 = new Person.Builder("John", "Doe")
            .build();
        
        System.out.println("Person 1 (minimal):");
        System.out.println(person1);
        
        // Creating Person with some optional fields
        Person person2 = new Person.Builder("Jane", "Smith")
            .age(30)
            .email("jane.smith@email.com")
            .phone("123-456-7890")
            .build();
        
        System.out.println("\nPerson 2 (with some fields):");
        System.out.println(person2);
        
        // Creating Person with all fields
        Person person3 = new Person.Builder("Bob", "Johnson")
            .age(35)
            .phone("987-654-3210")
            .email("bob.johnson@email.com")
            .address("123 Main St")
            .city("New York")
            .state("NY")
            .zipCode("10001")
            .build();
        
        System.out.println("\nPerson 3 (complete):");
        System.out.println(person3);
        
        System.out.println("\n=== COMPUTER BUILDER ===\n");
        
        // Basic computer
        Computer basicPC = new Computer.Builder("Intel i3", "8GB")
            .build();
        
        System.out.println("Basic PC:");
        System.out.println(basicPC);
        
        // Gaming computer
        Computer gamingPC = new Computer.Builder("Intel i9", "32GB")
            .storage("2TB NVMe SSD")
            .GPU("NVIDIA RTX 4090")
            .motherboard("ASUS ROG")
            .powerSupply("1000W")
            .hasWiFi(true)
            .hasBluetooth(true)
            .build();
        
        System.out.println("\nGaming PC:");
        System.out.println(gamingPC);
        
        System.out.println("\n=== BUILDER PATTERN ADVANTAGES ===");
        System.out.println("1. Avoids telescoping constructor anti-pattern");
        System.out.println("2. Clear and readable object construction");
        System.out.println("3. Immutable objects (final fields)");
        System.out.println("4. Named parameters (method names are self-documenting)");
        System.out.println("5. Flexible - can set only needed parameters");
        System.out.println("6. Validation can be done in build() method");
        
        System.out.println("\n=== WHEN TO USE BUILDER PATTERN ===");
        System.out.println("1. Class has many parameters (4+)");
        System.out.println("2. Many parameters are optional");
        System.out.println("3. Need immutable objects");
        System.out.println("4. Want clear, readable object creation");
    }
}
