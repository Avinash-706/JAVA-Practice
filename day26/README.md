# ☕ Master Guide: Comparable, Comparator & Enum

<div align="center">

![Java](https://img.shields.io/badge/JAVA-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![JVM](https://img.shields.io/badge/JVM-Internal-5382a1?style=for-the-badge&logo=openjdk&logoColor=white)
![Advanced](https://img.shields.io/badge/Advanced-Concepts-red?style=for-the-badge)

</div>

<hr style="border: 1px solid rgb(98, 117, 187)">

<div align="center">
<table>
<tr>
<td align="center">
<br />
<img src="../day22/favicon.png" width="120" height="120" style="border-radius: 50%; object-fit: cover;">
<h3>© 2026 Avinash Dhanuka</h3>
<p>Master Guide: Java Core & Frameworks</p>
<p><em>Crafted with ❤️ for Object-Oriented Architecture</em></p>

<a href="https://github.com/Avinash-706" target="_blank">
<img src="https://img.shields.io/badge/GitHub-Avinash--706-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub">
</a>

<a href="https://mail.google.com/mail/?view=cm&fs=1&to=avunashdhanuka@gmail.com&su=Java%20Advanced%20Query&body=☕%20Hello%20Avinash,%0D%0A%0D%0AMy%20name%20is%20[Your%20Name]%20and%20I%20have%20a%20doubt%20regarding%20Java%20Advanced%20Concepts.%0D%0A%0D%0A🔹%20Topic:%20[Comparable/Comparator/Enum]%0D%0A🔹%20Question:%20[Type%20your%20question]%0D%0A%0D%0AThank%20you!" target="_blank">

<img src="https://img.shields.io/badge/📧_Contact_Me_via_Gmail-2563EB?style=for-the-badge&logo=gmail&logoColor=white" alt="Gmail">

</a>
<br />
<br />
</td>
</tr>
</table>
</div>

> **Author's Note:** This comprehensive guide explores advanced Java concepts essential for professional development. Master Comparable for natural ordering, Comparator for flexible custom sorting, and Enum for type-safe constants. These concepts are fundamental for building robust, maintainable applications and are frequently tested in technical interviews.

---

## 📑 Table of Contents
1. [Comparable Interface](#1-comparable-interface)
   - [Natural Ordering Concepts](#11-natural-ordering-concepts)
   - [Implementation Patterns](#12-implementation-patterns)
   - [Best Practices](#13-best-practices)
2. [Comparator Interface](#2-comparator-interface)
   - [Custom Sorting Strategies](#21-custom-sorting-strategies)
   - [Lambda Expressions & Method References](#22-lambda-expressions--method-references)
   - [Advanced Comparator Techniques](#23-advanced-comparator-techniques)
3. [Enum (Enumeration)](#3-enum-enumeration)
   - [Basic to Advanced Enum Features](#31-basic-to-advanced-enum-features)
   - [Enum with Methods & Fields](#32-enum-with-methods--fields)
   - [EnumSet & EnumMap](#33-enumset--enummap)
4. [Comparison Matrix](#4-comparison-matrix)
5. [Real-World Applications](#5-real-world-applications)
6. [Interview Patterns](#6-interview-patterns)

<div align="right">
<sub><em>Comprehensive notes by Avinash Dhanuka | For educational purposes</em></sub>
</div>

---

## 1. Comparable Interface

### 📌 Definition
**Comparable** is a functional interface in `java.lang` package that defines the **natural ordering** of objects. Classes implementing Comparable can be sorted automatically using `Collections.sort()` and used in sorted collections like `TreeSet` and `TreeMap`.

### 1.1 Natural Ordering Concepts

#### 📋 Characteristics

| Property | Value |
| :--- | :--- |
| **Package** | `java.lang` |
| **Since** | JDK 1.2 |
| **Type** | Functional Interface |
| **Method** | `int compareTo(T o)` |
| **Purpose** | Define natural ordering |
| **Sorting Logic** | Inside the same class |
| **Sequences** | Only one per class |

#### 🔍 How compareTo() Works

```java
public int compareTo(T other) {
    // Returns:
    // negative value (< 0) if this < other
    // zero (0) if this == other  
    // positive value (> 0) if this > other
}
```

#### 💻 Basic Implementation

```java
class Student implements Comparable<Student> {
    int id;
    String name;
    double marks;
    
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id); // Natural ordering by ID
    }
}
```

### 1.2 Implementation Patterns

#### 1️⃣ Primitive Comparisons
```java
// Integer comparison
return Integer.compare(this.id, other.id);

// Double comparison  
return Double.compare(this.salary, other.salary);

// String comparison (alphabetical)
return this.name.compareTo(other.name);
```

#### 2️⃣ Reverse Ordering
```java
// Descending order (higher values first)
return Integer.compare(other.id, this.id);
```

#### 3️⃣ Multi-field Comparison
```java
@Override
public int compareTo(Employee other) {
    // First by department
    int result = this.department.compareTo(other.department);
    if (result != 0) return result;
    
    // Then by salary (descending)
    return Double.compare(other.salary, this.salary);
}
```

### 1.3 Best Practices

#### ✅ Do's
- **Consistent with equals()**: If `x.compareTo(y) == 0`, then `x.equals(y)` should be `true`
- **Transitive**: If `x.compareTo(y) > 0` and `y.compareTo(z) > 0`, then `x.compareTo(z) > 0`
- **Symmetric**: `sgn(x.compareTo(y)) == -sgn(y.compareTo(x))`
- **Use wrapper class methods**: `Integer.compare()`, `Double.compare()`

#### ❌ Don'ts
- **Don't use subtraction**: `this.id - other.id` (can cause overflow)
- **Don't ignore null checks**: Handle null values appropriately
- **Don't break consistency**: Ensure compareTo() aligns with equals()

---

## 2. Comparator Interface

### 📌 Definition
**Comparator** is a functional interface in `java.util` package that defines **custom sorting logic** external to the class being sorted. It enables multiple sorting sequences and doesn't require modifying the original class.

### 2.1 Custom Sorting Strategies

#### 📋 Characteristics

| Property | Value |
| :--- | :--- |
| **Package** | `java.util` |
| **Since** | JDK 1.2 |
| **Type** | Functional Interface |
| **Methods** | `compare(T o1, T o2)`, `equals(Object obj)` |
| **Purpose** | Define custom ordering |
| **Sorting Logic** | Separate class/lambda |
| **Sequences** | Multiple possible |

#### 💻 Implementation Approaches

#### 1️⃣ Traditional Class Implementation
```java
class SortByName implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}

// Usage
Collections.sort(students, new SortByName());
```

#### 2️⃣ Anonymous Inner Class
```java
Collections.sort(students, new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s2.marks, s1.marks); // Descending
    }
});
```

### 2.2 Lambda Expressions & Method References

#### 3️⃣ Lambda Expressions (Java 8+)
```java
// Simple lambda
students.sort((s1, s2) -> s1.name.compareTo(s2.name));

// Method reference
students.sort(Comparator.comparing(Student::getName));

// Reverse order
students.sort(Comparator.comparing(Student::getMarks).reversed());
```

#### 4️⃣ Chained Comparators
```java
students.sort(
    Comparator.comparing(Student::getDepartment)
             .thenComparing(Student::getMarks, Comparator.reverseOrder())
             .thenComparing(Student::getName)
);
```

### 2.3 Advanced Comparator Techniques

#### 🎯 Utility Methods

| Method | Purpose | Example |
| :--- | :--- | :--- |
| `comparing()` | Extract key for comparison | `Comparator.comparing(Student::getName)` |
| `comparingInt()` | Compare integers | `Comparator.comparingInt(Student::getId)` |
| `comparingDouble()` | Compare doubles | `Comparator.comparingDouble(Student::getMarks)` |
| `reversed()` | Reverse order | `nameComparator.reversed()` |
| `thenComparing()` | Chain comparators | `byName.thenComparing(byAge)` |
| `nullsFirst()` | Nulls first | `Comparator.nullsFirst(naturalOrder())` |
| `nullsLast()` | Nulls last | `Comparator.nullsLast(naturalOrder())` |

#### 💻 Complex Sorting Example
```java
// Multi-criteria sorting with null safety
products.sort(
    Comparator.comparing(Product::getCategory, Comparator.nullsLast(String::compareTo))
             .thenComparing(Product::getRating, Comparator.reverseOrder())
             .thenComparing(Product::getPrice)
             .thenComparing(Product::getName, Comparator.nullsLast(String::compareTo))
);
```

---

## 3. Enum (Enumeration)

### 📌 Definition
**Enum** is a special data type introduced in JDK 1.5 that represents a **fixed set of constants**. Enums provide type safety, prevent invalid values, and can contain methods, fields, and constructors.

### 3.1 Basic to Advanced Enum Features

#### 📋 Characteristics

| Property | Value |
| :--- | :--- |
| **Keyword** | `enum` |
| **Since** | JDK 1.5 |
| **Constants** | `public static final` by default |
| **Instantiation** | Cannot use `new` |
| **Inheritance** | Extends `java.lang.Enum` |
| **Interfaces** | Can implement interfaces |

#### 💻 Evolution from Basic to Advanced

#### 1️⃣ Basic Enum
```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```

#### 2️⃣ Enum with Fields and Constructor
```java
enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6);
    
    private final double mass;
    private final double radius;
    
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
    
    public double surfaceGravity() {
        return G * mass / (radius * radius);
    }
}
```

#### 3️⃣ Enum with Abstract Methods
```java
enum Operation {
    PLUS("+") {
        public double apply(double x, double y) { return x + y; }
    },
    MINUS("-") {
        public double apply(double x, double y) { return x - y; }
    };
    
    private final String symbol;
    Operation(String symbol) { this.symbol = symbol; }
    
    public abstract double apply(double x, double y);
}
```

### 3.2 Enum with Methods & Fields

#### 🔍 Built-in Methods

| Method | Return Type | Description |
| :--- | :--- | :--- |
| `name()` | String | Returns enum constant name |
| `ordinal()` | int | Returns position (0-based) |
| `values()` | Enum[] | Returns all constants |
| `valueOf(String)` | Enum | Converts string to enum |
| `compareTo(Enum)` | int | Compares based on ordinal |
| `equals(Object)` | boolean | Checks equality |

#### 💻 Advanced Enum Features
```java
enum OrderStatus {
    PLACED(1, "Order has been placed"),
    PROCESSING(2, "Order is being processed"),
    SHIPPED(3, "Order has been shipped"),
    DELIVERED(4, "Order delivered successfully");
    
    private final int code;
    private final String message;
    
    OrderStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    // Getters
    public int getCode() { return code; }
    public String getMessage() { return message; }
    
    // Static method to find by code
    public static OrderStatus findByCode(int code) {
        for (OrderStatus status : values()) {
            if (status.code == code) return status;
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
    
    // Instance method
    public boolean isCompleted() {
        return this == DELIVERED;
    }
}
```

### 3.3 EnumSet & EnumMap

#### 🎯 EnumSet - Efficient Set Implementation
```java
// Create EnumSet
EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
EnumSet<Day> allDays = EnumSet.allOf(Day.class);

// Set operations
EnumSet<Day> workDays = EnumSet.copyOf(weekdays);
workDays.add(Day.SATURDAY); // Add Saturday as work day
```

#### 🗺️ EnumMap - Efficient Map Implementation
```java
// Create EnumMap
EnumMap<Day, String> dayActivities = new EnumMap<>(Day.class);
dayActivities.put(Day.MONDAY, "Team meeting");
dayActivities.put(Day.FRIDAY, "Code review");

// Iterate
dayActivities.forEach((day, activity) -> 
    System.out.println(day + ": " + activity));
```

---

## 4. Comparison Matrix

| Feature | Comparable | Comparator | Enum |
| :--- | :---: | :---: | :---: |
| **Package** | java.lang | java.util | Built-in type |
| **Purpose** | Natural ordering | Custom ordering | Type-safe constants |
| **Modification** | Modifies class | External logic | Immutable constants |
| **Sequences** | Single | Multiple | N/A |
| **Lambda Support** | ❌ | ✅ | ❌ |
| **Null Handling** | Manual | Built-in utilities | Not applicable |
| **Performance** | Fast | Slightly slower | Very fast |
| **Use Case** | Default sorting | Flexible sorting | Fixed values |

---

## 5. Real-World Applications

### 🎯 Comparable Use Cases
- **Student Management**: Natural ordering by student ID
- **Employee Records**: Default sorting by employee number
- **Product Catalog**: Alphabetical ordering by product name
- **Financial Records**: Chronological ordering by date

### 🎯 Comparator Use Cases
- **E-commerce**: Sort products by price, rating, popularity
- **Data Analysis**: Multi-criteria sorting for reports
- **Gaming**: Leaderboards with complex scoring
- **File Management**: Sort by name, size, date, type

### 🎯 Enum Use Cases
- **Order Processing**: Order status tracking
- **Payment Systems**: Payment method validation
- **Game Development**: Player states, difficulty levels
- **Configuration**: Application settings and modes

---

## 6. Interview Patterns

### 🎓 Common Questions

#### 1️⃣ Comparable vs Comparator
```java
// Q: Implement both approaches for Student sorting
class Student implements Comparable<Student> {
    // Natural ordering by ID
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }
}

// Custom sorting by marks (descending)
Comparator<Student> byMarks = (s1, s2) -> 
    Double.compare(s2.marks, s1.marks);
```

#### 2️⃣ Multi-field Sorting
```java
// Q: Sort employees by department, then salary (desc), then name
employees.sort(
    Comparator.comparing(Employee::getDepartment)
             .thenComparing(Employee::getSalary, Comparator.reverseOrder())
             .thenComparing(Employee::getName)
);
```

#### 3️⃣ Enum with Business Logic
```java
// Q: Implement order status with validation
enum OrderStatus {
    PLACED, CONFIRMED, SHIPPED, DELIVERED, CANCELLED;
    
    public boolean canTransitionTo(OrderStatus newStatus) {
        switch (this) {
            case PLACED: return newStatus == CONFIRMED || newStatus == CANCELLED;
            case CONFIRMED: return newStatus == SHIPPED || newStatus == CANCELLED;
            case SHIPPED: return newStatus == DELIVERED;
            default: return false;
        }
    }
}
```

### 🏆 Best Practices Summary

#### ✅ Comparable Best Practices
- Implement for classes with natural ordering
- Ensure consistency with equals()
- Use wrapper class comparison methods
- Handle null values appropriately

#### ✅ Comparator Best Practices
- Use lambda expressions for simple comparisons
- Chain comparators for multi-field sorting
- Utilize null-safe comparison utilities
- Prefer method references when possible

#### ✅ Enum Best Practices
- Use enum instead of constants
- Add methods for business logic
- Implement interfaces when needed
- Use EnumSet/EnumMap for collections

---

## 📚 Summary

### Key Takeaways

1. **Comparable**: Single natural ordering, modifies original class
2. **Comparator**: Multiple custom orderings, external sorting logic
3. **Enum**: Type-safe constants with methods and fields
4. **Performance**: All three are optimized for their specific use cases
5. **Modern Java**: Lambda expressions enhance Comparator flexibility

### When to Use What

- **Comparable**: When objects have obvious natural ordering
- **Comparator**: When you need multiple sorting options
- **Enum**: When you have a fixed set of related constants

---

<div align="center">

### 🎯 Master These Concepts

**Comparable** → Natural ordering for objects  
**Comparator** → Flexible custom sorting  
**Enum** → Type-safe constant management

---

<sub>**© 2026 Avinash Dhanuka** | Java Advanced Concepts Master Guide</sub>

<sub>📧 [avunashdhanuka@gmail.com](mailto:avunashdhanuka@gmail.com) | 🔗 [GitHub: Avinash-706](https://github.com/Avinash-706)</sub>

</div>