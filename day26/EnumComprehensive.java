import java.util.*;

// 1. Basic Enum
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// 2. Enum with Fields and Methods
enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6),
    JUPITER(1.9e+27, 7.1492e7),
    SATURN(5.688e+26, 6.0268e7),
    URANUS(8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);
    
    private final double mass;   // in kilograms
    private final double radius; // in meters
    
    // Constructor
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
    
    // Getter methods
    public double getMass() { return mass; }
    public double getRadius() { return radius; }
    
    // Universal gravitational constant (m3 kg-1 s-2)
    public static final double G = 6.67300E-11;
    
    // Calculate surface gravity
    public double surfaceGravity() {
        return G * mass / (radius * radius);
    }
    
    // Calculate weight on this planet
    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
}

// 3. Enum with Abstract Methods
enum Operation {
    PLUS("+") {
        public double apply(double x, double y) { return x + y; }
    },
    MINUS("-") {
        public double apply(double x, double y) { return x - y; }
    },
    TIMES("*") {
        public double apply(double x, double y) { return x * y; }
    },
    DIVIDE("/") {
        public double apply(double x, double y) { return x / y; }
    };
    
    private final String symbol;
    
    Operation(String symbol) {
        this.symbol = symbol;
    }
    
    public String getSymbol() { return symbol; }
    
    // Abstract method - must be implemented by each constant
    public abstract double apply(double x, double y);
    
    @Override
    public String toString() {
        return symbol;
    }
}

// 4. Enum implementing Interface
interface Describable {
    String getDescription();
}

enum Color implements Describable {
    RED("Red is the color of fire and blood"),
    GREEN("Green is the color of nature"),
    BLUE("Blue is the color of sky and ocean"),
    YELLOW("Yellow is the color of sun and gold"),
    BLACK("Black is the absence of color"),
    WHITE("White is the combination of all colors");
    
    private final String description;
    
    Color(String description) {
        this.description = description;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
}

// 5. Enum with Nested Enum
enum Vehicle {
    CAR(VehicleType.LAND, 4),
    MOTORCYCLE(VehicleType.LAND, 2),
    BICYCLE(VehicleType.LAND, 2),
    BOAT(VehicleType.WATER, 0),
    AIRPLANE(VehicleType.AIR, 0),
    HELICOPTER(VehicleType.AIR, 0);
    
    private final VehicleType type;
    private final int wheels;
    
    Vehicle(VehicleType type, int wheels) {
        this.type = type;
        this.wheels = wheels;
    }
    
    public VehicleType getType() { return type; }
    public int getWheels() { return wheels; }
    
    // Nested enum
    enum VehicleType {
        LAND, WATER, AIR
    }
}

// 6. Enum with Static Methods
enum Grade {
    A(90, 100), B(80, 89), C(70, 79), D(60, 69), F(0, 59);
    
    private final int minScore;
    private final int maxScore;
    
    Grade(int minScore, int maxScore) {
        this.minScore = minScore;
        this.maxScore = maxScore;
    }
    
    public int getMinScore() { return minScore; }
    public int getMaxScore() { return maxScore; }
    
    // Static method to get grade by score
    public static Grade getGradeByScore(int score) {
        for (Grade grade : Grade.values()) {
            if (score >= grade.minScore && score <= grade.maxScore) {
                return grade;
            }
        }
        throw new IllegalArgumentException("Invalid score: " + score);
    }
}

public class EnumComprehensive {
    public static void main(String[] args) {
        
        System.out.println("=== COMPREHENSIVE ENUM DEMONSTRATION ===\n");
        
        // 1. Basic Enum Usage
        System.out.println("1. BASIC ENUM USAGE:");
        Day today = Day.FRIDAY;
        System.out.println("Today is: " + today);
        System.out.println("Day name: " + today.name());
        System.out.println("Day ordinal: " + today.ordinal());
        
        System.out.println("\nAll days:");
        for (Day day : Day.values()) {
            System.out.println(day.ordinal() + ": " + day.name());
        }
        
        // valueOf() method
        Day parsedDay = Day.valueOf("MONDAY");
        System.out.println("Parsed day: " + parsedDay);
        
        // 2. Enum with Fields and Methods
        System.out.println("\n\n2. ENUM WITH FIELDS AND METHODS:");
        double earthWeight = 70.0; // kg
        System.out.println("Weight on different planets (for " + earthWeight + " kg):");
        
        for (Planet planet : Planet.values()) {
            double weight = planet.surfaceWeight(earthWeight);
            System.out.printf("%-10s: %6.2f kg (gravity: %.2f m/s²)%n", 
                            planet.name(), weight, planet.surfaceGravity());
        }
        
        // 3. Enum with Abstract Methods
        System.out.println("\n\n3. ENUM WITH ABSTRACT METHODS:");
        double x = 10.0, y = 3.0;
        
        for (Operation op : Operation.values()) {
            double result = op.apply(x, y);
            System.out.printf("%.1f %s %.1f = %.2f%n", x, op.getSymbol(), y, result);
        }
        
        // 4. Enum implementing Interface
        System.out.println("\n\n4. ENUM IMPLEMENTING INTERFACE:");
        for (Color color : Color.values()) {
            System.out.println(color.name() + ": " + color.getDescription());
        }
        
        // 5. Nested Enum
        System.out.println("\n\n5. NESTED ENUM:");
        for (Vehicle vehicle : Vehicle.values()) {
            System.out.printf("%-12s: Type=%s, Wheels=%d%n", 
                            vehicle.name(), vehicle.getType(), vehicle.getWheels());
        }
        
        // Group vehicles by type
        System.out.println("\nVehicles grouped by type:");
        Map<Vehicle.VehicleType, List<Vehicle>> vehiclesByType = new HashMap<>();
        
        for (Vehicle vehicle : Vehicle.values()) {
            vehiclesByType.computeIfAbsent(vehicle.getType(), k -> new ArrayList<>()).add(vehicle);
        }
        
        vehiclesByType.forEach((type, vehicles) -> {
            System.out.println(type + ": " + vehicles);
        });
        
        // 6. Enum with Static Methods
        System.out.println("\n\n6. ENUM WITH STATIC METHODS:");
        int[] scores = {95, 87, 76, 65, 45, 92, 58, 83};
        
        System.out.println("Score to Grade conversion:");
        for (int score : scores) {
            Grade grade = Grade.getGradeByScore(score);
            System.out.printf("Score %d: Grade %s (%d-%d)%n", 
                            score, grade.name(), grade.getMinScore(), grade.getMaxScore());
        }
        
        // 7. Enum in Switch Statement
        System.out.println("\n\n7. ENUM IN SWITCH STATEMENT:");
        Day[] workDays = {Day.MONDAY, Day.WEDNESDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY};
        
        for (Day day : workDays) {
            String message = getDayMessage(day);
            System.out.println(day + ": " + message);
        }
        
        // 8. Enum Comparison
        System.out.println("\n\n8. ENUM COMPARISON:");
        Day day1 = Day.MONDAY;
        Day day2 = Day.FRIDAY;
        Day day3 = Day.MONDAY;
        
        System.out.println("day1 == day3: " + (day1 == day3));
        System.out.println("day1.equals(day3): " + day1.equals(day3));
        System.out.println("day1.compareTo(day2): " + day1.compareTo(day2));
        System.out.println("day2.compareTo(day1): " + day2.compareTo(day1));
        
        // 9. EnumSet and EnumMap
        System.out.println("\n\n9. ENUMSET AND ENUMMAP:");
        
        // EnumSet - efficient Set implementation for enums
        EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        EnumSet<Day> allDays = EnumSet.allOf(Day.class);
        
        System.out.println("Weekdays: " + weekdays);
        System.out.println("Weekend: " + weekend);
        System.out.println("All days: " + allDays);
        
        // EnumMap - efficient Map implementation for enum keys
        EnumMap<Day, String> dayActivities = new EnumMap<>(Day.class);
        dayActivities.put(Day.MONDAY, "Start of work week");
        dayActivities.put(Day.WEDNESDAY, "Midweek");
        dayActivities.put(Day.FRIDAY, "TGIF!");
        dayActivities.put(Day.SATURDAY, "Weekend fun");
        dayActivities.put(Day.SUNDAY, "Rest day");
        
        System.out.println("\nDay activities:");
        dayActivities.forEach((day, activity) -> 
            System.out.println(day + ": " + activity));
        
        // 10. Enum Best Practices
        System.out.println("\n\n10. ENUM BEST PRACTICES:");
        
        // Use enum instead of constants
        System.out.println("✓ Use enum instead of public static final constants");
        System.out.println("✓ Enum constants are implicitly public static final");
        System.out.println("✓ Enum provides type safety");
        System.out.println("✓ Enum can have methods and fields");
        System.out.println("✓ Use EnumSet and EnumMap for better performance");
        System.out.println("✓ Enum can implement interfaces but cannot extend classes");
        
        System.out.println("\n=== ENUM BUILT-IN METHODS ===");
        System.out.println("• name(): Returns the name of enum constant");
        System.out.println("• ordinal(): Returns the position (0-based index)");
        System.out.println("• values(): Returns array of all enum constants");
        System.out.println("• valueOf(String): Converts string to enum constant");
        System.out.println("• compareTo(): Compares based on ordinal values");
        System.out.println("• equals(): Checks equality (same as ==)");
    }
    
    // Helper method for switch statement demonstration
    private static String getDayMessage(Day day) {
        switch (day) {
            case MONDAY:
                return "Start of the work week!";
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
                return "Midweek grind";
            case FRIDAY:
                return "TGIF - Thank God It's Friday!";
            case SATURDAY:
            case SUNDAY:
                return "Weekend relaxation";
            default:
                return "Unknown day";
        }
    }
}