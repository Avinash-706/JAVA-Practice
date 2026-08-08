// =====================================================================
//          METHOD CHAINING WITH INHERITANCE
// =====================================================================
// Challenge: Maintaining method chaining in inheritance hierarchy
// Solution: Self-referencing generics (Curiously Recurring Template Pattern)

// PROBLEM: Method chaining breaks with inheritance
class Animal {
    protected String name;
    protected int age;
    
    public Animal setName(String name) {
        this.name = name;
        return this;  // Returns Animal, not Dog!
    }
    
    public Animal setAge(int age) {
        this.age = age;
        return this;
    }
    
    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class Dog extends Animal {
    private String breed;
    
    public Dog setBreed(String breed) {
        this.breed = breed;
        return this;
    }
    
    @Override
    public void display() {
        System.out.println("Name: " + name + ", Age: " + age + ", Breed: " + breed);
    }
}

// SOLUTION 1: Self-referencing generics (CRTP)
abstract class Vehicle<T extends Vehicle<T>> {
    protected String brand;
    protected String model;
    protected int year;
    
    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }
    
    public T setBrand(String brand) {
        this.brand = brand;
        return self();
    }
    
    public T setModel(String model) {
        this.model = model;
        return self();
    }
    
    public T setYear(int year) {
        this.year = year;
        return self();
    }
    
    public void display() {
        System.out.println("Brand: " + brand + ", Model: " + model + ", Year: " + year);
    }
}

class Car extends Vehicle<Car> {
    private int doors;
    
    public Car setDoors(int doors) {
        this.doors = doors;
        return self();
    }
    
    @Override
    public void display() {
        System.out.println("Brand: " + brand + ", Model: " + model + 
                         ", Year: " + year + ", Doors: " + doors);
    }
}

class Motorcycle extends Vehicle<Motorcycle> {
    private String type;
    
    public Motorcycle setType(String type) {
        this.type = type;
        return self();
    }
    
    @Override
    public void display() {
        System.out.println("Brand: " + brand + ", Model: " + model + 
                         ", Year: " + year + ", Type: " + type);
    }
}

// SOLUTION 2: Builder pattern with inheritance
abstract class Person {
    protected String firstName;
    protected String lastName;
    protected int age;
    
    protected Person(Builder<?> builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
    }
    
    public abstract static class Builder<T extends Builder<T>> {
        protected String firstName;
        protected String lastName;
        protected int age;
        
        @SuppressWarnings("unchecked")
        protected T self() {
            return (T) this;
        }
        
        public T firstName(String firstName) {
            this.firstName = firstName;
            return self();
        }
        
        public T lastName(String lastName) {
            this.lastName = lastName;
            return self();
        }
        
        public T age(int age) {
            this.age = age;
            return self();
        }
        
        public abstract Person build();
    }
    
    @Override
    public String toString() {
        return "firstName='" + firstName + "', lastName='" + lastName + "', age=" + age;
    }
}

class Employee extends Person {
    private String employeeId;
    private String department;
    
    protected Employee(Builder builder) {
        super(builder);
        this.employeeId = builder.employeeId;
        this.department = builder.department;
    }
    
    public static class Builder extends Person.Builder<Builder> {
        private String employeeId;
        private String department;
        
        public Builder employeeId(String employeeId) {
            this.employeeId = employeeId;
            return self();
        }
        
        public Builder department(String department) {
            this.department = department;
            return self();
        }
        
        @Override
        public Employee build() {
            return new Employee(this);
        }
    }
    
    @Override
    public String toString() {
        return "Employee{" + super.toString() + 
               ", employeeId='" + employeeId + "', department='" + department + "'}";
    }
}

class Manager extends Employee {
    private int teamSize;
    
    protected Manager(Builder builder) {
        super(builder);
        this.teamSize = builder.teamSize;
    }
    
    public static class Builder extends Employee.Builder {
        private int teamSize;
        
        public Builder teamSize(int teamSize) {
            this.teamSize = teamSize;
            return this;
        }
        
        @Override
        public Builder employeeId(String employeeId) {
            super.employeeId(employeeId);
            return this;
        }
        
        @Override
        public Builder department(String department) {
            super.department(department);
            return this;
        }
        
        @Override
        public Builder firstName(String firstName) {
            super.firstName(firstName);
            return this;
        }
        
        @Override
        public Builder lastName(String lastName) {
            super.lastName(lastName);
            return this;
        }
        
        @Override
        public Builder age(int age) {
            super.age(age);
            return this;
        }
        
        @Override
        public Manager build() {
            Manager manager = new Manager(this);
            return manager;
        }
    }
    
    @Override
    public String toString() {
        return "Manager{" + super.toString() + ", teamSize=" + teamSize + "}";
    }
}

// EXAMPLE 3: Shape hierarchy with fluent interface
abstract class Shape<T extends Shape<T>> {
    protected String color;
    protected boolean filled;
    
    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }
    
    public T setColor(String color) {
        this.color = color;
        return self();
    }
    
    public T setFilled(boolean filled) {
        this.filled = filled;
        return self();
    }
    
    public abstract double area();
    
    public void display() {
        System.out.println("Color: " + color + ", Filled: " + filled + ", Area: " + area());
    }
}

class Circle extends Shape<Circle> {
    private double radius;
    
    public Circle setRadius(double radius) {
        this.radius = radius;
        return self();
    }
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape<Rectangle> {
    private double width;
    private double height;
    
    public Rectangle setWidth(double width) {
        this.width = width;
        return self();
    }
    
    public Rectangle setHeight(double height) {
        this.height = height;
        return self();
    }
    
    @Override
    public double area() {
        return width * height;
    }
}

public class MethodChainingInheritance {
    
    public static void main(String[] args) {
        
        System.out.println("=== PROBLEM: Broken Method Chaining ===\n");
        
        // This works but requires casting
        Dog dog1 = new Dog();
        dog1.setName("Buddy");
        dog1.setAge(3);
        dog1.setBreed("Labrador");
        dog1.display();
        
        // This doesn't work - setName returns Animal, not Dog
        // Dog dog2 = new Dog().setName("Max").setBreed("Bulldog");  // ERROR!
        
        System.out.println("\n=== SOLUTION 1: Self-Referencing Generics ===\n");
        
        // Car with fluent chaining
        Car car = new Car()
            .setBrand("Toyota")
            .setModel("Camry")
            .setYear(2024)
            .setDoors(4);
        
        car.display();
        
        // Motorcycle with fluent chaining
        Motorcycle bike = new Motorcycle()
            .setBrand("Harley-Davidson")
            .setModel("Street 750")
            .setYear(2023)
            .setType("Cruiser");
        
        bike.display();
        
        System.out.println("\n=== SOLUTION 2: Builder with Inheritance ===\n");
        
        // Employee builder
        Employee emp = new Employee.Builder()
            .firstName("John")
            .lastName("Doe")
            .age(30)
            .employeeId("EMP001")
            .department("IT")
            .build();
        
        System.out.println(emp);
        
        // Manager builder (extends Employee)
        Manager mgr = new Manager.Builder()
            .firstName("Jane")
            .lastName("Smith")
            .age(35)
            .employeeId("MGR001")
            .department("Engineering")
            .teamSize(10)
            .build();
        
        System.out.println(mgr);
        
        System.out.println("\n=== SOLUTION 3: Shape Hierarchy ===\n");
        
        // Circle with chaining
        Circle circle = new Circle()
            .setColor("Red")
            .setFilled(true)
            .setRadius(5.0);
        
        circle.display();
        
        // Rectangle with chaining
        Rectangle rect = new Rectangle()
            .setColor("Blue")
            .setFilled(false)
            .setWidth(10.0)
            .setHeight(5.0);
        
        rect.display();
        
        System.out.println("\n=== KEY CONCEPTS ===");
        System.out.println("1. CRTP: Curiously Recurring Template Pattern");
        System.out.println("2. Self-referencing generics: <T extends Class<T>>");
        System.out.println("3. self() method returns correct subclass type");
        System.out.println("4. Maintains fluent interface in inheritance");
        System.out.println("5. Type-safe method chaining");
        
        System.out.println("\n=== ADVANTAGES ===");
        System.out.println("1. Fluent API works with inheritance");
        System.out.println("2. Type-safe at compile time");
        System.out.println("3. No casting needed");
        System.out.println("4. IDE auto-completion works correctly");
        
        System.out.println("\n=== DISADVANTAGES ===");
        System.out.println("1. More complex code");
        System.out.println("2. Requires understanding of generics");
        System.out.println("3. Verbose type declarations");
    }
}
