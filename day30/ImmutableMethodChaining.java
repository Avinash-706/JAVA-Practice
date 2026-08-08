// =====================================================================
//          IMMUTABLE OBJECTS WITH METHOD CHAINING
// =====================================================================
// Immutable: Object state cannot be modified after creation
// Method chaining with immutable objects returns NEW objects

// MUTABLE vs IMMUTABLE comparison
class MutablePerson {
    private String name;
    private int age;
    
    public MutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Returns same object (mutable)
    public MutablePerson setName(String name) {
        this.name = name;  // Modifies existing object
        return this;
    }
    
    public MutablePerson setAge(int age) {
        this.age = age;  // Modifies existing object
        return this;
    }
    
    @Override
    public String toString() {
        return "MutablePerson{name='" + name + "', age=" + age + "}";
    }
}

// IMMUTABLE version - Thread-safe, safer
final class ImmutablePerson {
    private final String name;
    private final int age;
    
    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Returns NEW object (immutable)
    public ImmutablePerson withName(String name) {
        return new ImmutablePerson(name, this.age);  // Creates new object
    }
    
    public ImmutablePerson withAge(int age) {
        return new ImmutablePerson(this.name, age);  // Creates new object
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    @Override
    public String toString() {
        return "ImmutablePerson{name='" + name + "', age=" + age + "}";
    }
}

// EXAMPLE 2: Immutable Point (like String class)
final class Point {
    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point withX(int x) {
        return new Point(x, this.y);
    }
    
    public Point withY(int y) {
        return new Point(this.x, y);
    }
    
    public Point move(int dx, int dy) {
        return new Point(this.x + dx, this.y + dy);
    }
    
    public Point scale(int factor) {
        return new Point(this.x * factor, this.y * factor);
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}

// EXAMPLE 3: Immutable Money (Financial calculations)
final class Money {
    private final double amount;
    private final String currency;
    
    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    public Money add(double value) {
        return new Money(this.amount + value, this.currency);
    }
    
    public Money subtract(double value) {
        return new Money(this.amount - value, this.currency);
    }
    
    public Money multiply(double factor) {
        return new Money(this.amount * factor, this.currency);
    }
    
    public Money divide(double divisor) {
        if(divisor == 0) throw new IllegalArgumentException("Cannot divide by zero");
        return new Money(this.amount / divisor, this.currency);
    }
    
    public Money withCurrency(String currency) {
        return new Money(this.amount, currency);
    }
    
    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    
    @Override
    public String toString() {
        return String.format("%.2f %s", amount, currency);
    }
}

// EXAMPLE 4: Immutable Date Range
final class DateRange {
    private final String startDate;
    private final String endDate;
    
    public DateRange(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public DateRange withStartDate(String startDate) {
        return new DateRange(startDate, this.endDate);
    }
    
    public DateRange withEndDate(String endDate) {
        return new DateRange(this.startDate, endDate);
    }
    
    public DateRange extendBy(int days) {
        return new DateRange(this.startDate, this.endDate + "+" + days + "days");
    }
    
    @Override
    public String toString() {
        return "DateRange{" + startDate + " to " + endDate + "}";
    }
}

public class ImmutableMethodChaining {
    
    public static void main(String[] args) {
        
        System.out.println("=== MUTABLE vs IMMUTABLE ===\n");
        
        // Mutable - modifies same object
        MutablePerson mutable = new MutablePerson("John", 25);
        System.out.println("Original: " + mutable);
        
        MutablePerson modified = mutable.setName("Jane").setAge(30);
        System.out.println("Modified: " + modified);
        System.out.println("Original after modification: " + mutable);
        System.out.println("Same object? " + (mutable == modified));  // true
        
        System.out.println("\n--- Immutable ---");
        
        // Immutable - creates new objects
        ImmutablePerson immutable = new ImmutablePerson("John", 25);
        System.out.println("Original: " + immutable);
        
        ImmutablePerson changed = immutable.withName("Jane").withAge(30);
        System.out.println("Changed: " + changed);
        System.out.println("Original after change: " + immutable);
        System.out.println("Same object? " + (immutable == changed));  // false
        
        System.out.println("\n=== IMMUTABLE POINT ===\n");
        
        Point p1 = new Point(10, 20);
        System.out.println("Original point: " + p1);
        
        // Each operation creates new Point
        Point p2 = p1.withX(30);
        System.out.println("After withX(30): " + p2);
        System.out.println("Original unchanged: " + p1);
        
        // Chaining creates multiple new objects
        Point p3 = p1.move(5, 10).scale(2);
        System.out.println("After move(5,10).scale(2): " + p3);
        System.out.println("Original still unchanged: " + p1);
        
        System.out.println("\n=== IMMUTABLE MONEY ===\n");
        
        Money salary = new Money(50000, "USD");
        System.out.println("Initial salary: " + salary);
        
        // Financial calculations with immutability
        Money bonus = salary.multiply(0.1);
        System.out.println("Bonus (10%): " + bonus);
        
        Money totalCompensation = salary.add(bonus.getAmount());
        System.out.println("Total compensation: " + totalCompensation);
        
        Money monthlyPay = totalCompensation.divide(12);
        System.out.println("Monthly pay: " + monthlyPay);
        
        // Original unchanged
        System.out.println("Original salary unchanged: " + salary);
        
        // Chaining operations
        Money finalAmount = new Money(1000, "USD")
            .add(500)
            .multiply(1.1)
            .subtract(200);
        System.out.println("Chained calculation: " + finalAmount);
        
        System.out.println("\n=== IMMUTABLE DATE RANGE ===\n");
        
        DateRange range1 = new DateRange("2024-01-01", "2024-01-31");
        System.out.println("Original range: " + range1);
        
        DateRange range2 = range1.withEndDate("2024-02-28");
        System.out.println("Extended range: " + range2);
        System.out.println("Original unchanged: " + range1);
        
        System.out.println("\n=== ADVANTAGES OF IMMUTABILITY ===");
        System.out.println("1. Thread-safe (no synchronization needed)");
        System.out.println("2. Safer (cannot be modified accidentally)");
        System.out.println("3. Can be cached and reused");
        System.out.println("4. Easier to reason about (no hidden state changes)");
        System.out.println("5. Can be used as HashMap keys safely");
        
        System.out.println("\n=== DISADVANTAGES ===");
        System.out.println("1. Creates many objects (memory overhead)");
        System.out.println("2. Slower for frequent modifications");
        System.out.println("3. More verbose code");
        
        System.out.println("\n=== WHEN TO USE IMMUTABLE ===");
        System.out.println("1. Value objects (Money, Date, Point)");
        System.out.println("2. Multi-threaded environments");
        System.out.println("3. HashMap keys");
        System.out.println("4. API return values");
        System.out.println("5. Configuration objects");
        
        System.out.println("\n=== REAL-WORLD EXAMPLES ===");
        System.out.println("String class - immutable");
        System.out.println("Integer, Double, etc. - immutable");
        System.out.println("LocalDate, LocalDateTime - immutable");
        System.out.println("BigDecimal, BigInteger - immutable");
    }
}
