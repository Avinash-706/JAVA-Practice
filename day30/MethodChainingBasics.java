// =====================================================================
//          METHOD CHAINING - BASICS TO INTERMEDIATE
// =====================================================================
// Method Chaining: Calling multiple methods on same object in single statement
// Each method returns 'this' to enable chaining

// BASIC EXAMPLE: Student Class with Method Chaining
class Student {
    private int id;
    private String name;
    private String city;
    private int age;
    
    public Student() {
        this.id = 0;
        this.name = "Unknown";
        this.city = "Unknown";
        this.age = 0;
    }
    
    // Each setter returns 'this' for chaining
    public Student setId(int id) {
        this.id = id;
        return this;  // Returns current object
    }
    
    public Student setName(String name) {
        this.name = name;
        return this;
    }
    
    public Student setCity(String city) {
        this.city = city;
        return this;
    }
    
    public Student setAge(int age) {
        this.age = age;
        return this;
    }
    
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + 
                         ", City: " + city + ", Age: " + age);
    }
    
    @Override
    public String toString() {
        return "Student[id=" + id + ", name=" + name + 
               ", city=" + city + ", age=" + age + "]";
    }
}

// EXAMPLE 2: Bank Account with Method Chaining
class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;
    private String accountType;
    
    public BankAccount setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }
    
    public BankAccount setHolderName(String holderName) {
        this.holderName = holderName;
        return this;
    }
    
    public BankAccount setBalance(double balance) {
        this.balance = balance;
        return this;
    }
    
    public BankAccount setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }
    
    public BankAccount deposit(double amount) {
        this.balance += amount;
        System.out.println("Deposited: " + amount);
        return this;
    }
    
    public BankAccount withdraw(double amount) {
        if(balance >= amount) {
            this.balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance");
        }
        return this;
    }
    
    public void displayBalance() {
        System.out.println("Account: " + accountNumber + 
                         ", Balance: " + balance);
    }
}

// EXAMPLE 3: Calculator with Method Chaining
class Calculator {
    private double result;
    
    public Calculator() {
        this.result = 0;
    }
    
    public Calculator add(double value) {
        result += value;
        return this;
    }
    
    public Calculator subtract(double value) {
        result -= value;
        return this;
    }
    
    public Calculator multiply(double value) {
        result *= value;
        return this;
    }
    
    public Calculator divide(double value) {
        if(value != 0) {
            result /= value;
        } else {
            System.out.println("Cannot divide by zero");
        }
        return this;
    }
    
    public Calculator power(double exponent) {
        result = Math.pow(result, exponent);
        return this;
    }
    
    public double getResult() {
        return result;
    }
    
    public Calculator reset() {
        result = 0;
        return this;
    }
}

public class MethodChainingBasics {
    
    public static void main(String[] args) {
        
        System.out.println("=== EXAMPLE 1: Student Class ===\n");
        
        // Without method chaining (traditional way)
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("Avinash");
        s1.setCity("Mumbai");
        s1.setAge(22);
        s1.display();
        
        System.out.println("\n--- With Method Chaining ---");
        
        // With method chaining (fluent style)
        Student s2 = new Student()
            .setId(2)
            .setName("Rahul")
            .setCity("Delhi")
            .setAge(23);
        s2.display();
        
        // One-liner with chaining
        new Student().setId(3).setName("Priya").setCity("Bangalore").setAge(21).display();
        
        System.out.println("\n=== EXAMPLE 2: Bank Account ===\n");
        
        BankAccount account = new BankAccount()
            .setAccountNumber("ACC001")
            .setHolderName("John Doe")
            .setAccountType("Savings")
            .setBalance(10000);
        
        account.displayBalance();
        
        // Chaining operations
        account.deposit(5000)
               .withdraw(2000)
               .deposit(1000)
               .withdraw(500);
        
        account.displayBalance();
        
        System.out.println("\n=== EXAMPLE 3: Calculator ===\n");
        
        Calculator calc = new Calculator();
        
        // Complex calculation with chaining
        double result = calc.add(10)
                           .multiply(5)
                           .subtract(20)
                           .divide(2)
                           .getResult();
        
        System.out.println("Result: " + result);
        
        // Another calculation
        result = calc.reset()
                    .add(100)
                    .divide(4)
                    .multiply(3)
                    .power(2)
                    .getResult();
        
        System.out.println("Result: " + result);
        
        System.out.println("\n=== ADVANTAGES OF METHOD CHAINING ===");
        System.out.println("1. Cleaner and more readable code");
        System.out.println("2. Reduces lines of code");
        System.out.println("3. Fluent interface (reads like English)");
        System.out.println("4. Less repetition of object name");
        System.out.println("5. Commonly used in modern frameworks");
        
        System.out.println("\n=== KEY CONCEPT ===");
        System.out.println("'this' keyword returns current object");
        System.out.println("Enables calling next method on same object");
        System.out.println("Creates a chain of method calls");
    }
}
