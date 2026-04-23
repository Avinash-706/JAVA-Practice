import java.util.*;

// Basic Comparable Implementation
class Student implements Comparable<Student> {
    int id;
    String name;
    double marks;
    
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    
    // Natural ordering by id (ascending)
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }
    
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', marks=" + marks + "}";
    }
}

// Employee class with different natural ordering
class Employee implements Comparable<Employee> {
    int empId;
    String name;
    double salary;
    String department;
    
    public Employee(int empId, String name, double salary, String department) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
    
    // Natural ordering by salary (descending - higher salary first)
    @Override
    public int compareTo(Employee other) {
        return Double.compare(other.salary, this.salary);
    }
    
    @Override
    public String toString() {
        return "Employee{id=" + empId + ", name='" + name + "', salary=" + salary + ", dept='" + department + "'}";
    }
}

// Book class with String comparison
class Book implements Comparable<Book> {
    String title;
    String author;
    int pages;
    double price;
    
    public Book(String title, String author, int pages, double price) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
    }
    
    // Natural ordering by title (alphabetical)
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }
    
    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', pages=" + pages + ", price=" + price + "}";
    }
}

public class ComparableBasics {
    public static void main(String[] args) {
        
        System.out.println("=== COMPARABLE INTERFACE DEMONSTRATION ===\n");
        
        // 1. Student sorting by id (natural ordering)
        System.out.println("1. STUDENT SORTING (Natural ordering by ID):");
        List<Student> students = new ArrayList<>();
        students.add(new Student(103, "Alice", 85.5));
        students.add(new Student(101, "Bob", 92.0));
        students.add(new Student(105, "Charlie", 78.5));
        students.add(new Student(102, "Diana", 88.0));
        
        System.out.println("Before sorting:");
        students.forEach(System.out::println);
        
        Collections.sort(students);  // Uses compareTo() method
        
        System.out.println("\nAfter sorting (by ID ascending):");
        students.forEach(System.out::println);
        
        // 2. Employee sorting by salary (descending)
        System.out.println("\n\n2. EMPLOYEE SORTING (Natural ordering by Salary):");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(201, "John", 75000, "IT"));
        employees.add(new Employee(202, "Sarah", 85000, "HR"));
        employees.add(new Employee(203, "Mike", 65000, "Finance"));
        employees.add(new Employee(204, "Lisa", 95000, "IT"));
        
        System.out.println("Before sorting:");
        employees.forEach(System.out::println);
        
        Collections.sort(employees);
        
        System.out.println("\nAfter sorting (by Salary descending):");
        employees.forEach(System.out::println);
        
        // 3. Book sorting by title (alphabetical)
        System.out.println("\n\n3. BOOK SORTING (Natural ordering by Title):");
        List<Book> books = new ArrayList<>();
        books.add(new Book("Java Programming", "James Gosling", 500, 45.99));
        books.add(new Book("Data Structures", "Robert Sedgewick", 600, 55.99));
        books.add(new Book("Algorithms", "Thomas Cormen", 800, 75.99));
        books.add(new Book("Clean Code", "Robert Martin", 400, 35.99));
        
        System.out.println("Before sorting:");
        books.forEach(System.out::println);
        
        Collections.sort(books);
        
        System.out.println("\nAfter sorting (by Title alphabetical):");
        books.forEach(System.out::println);
        
        // 4. TreeSet automatic sorting
        System.out.println("\n\n4. TREESET AUTOMATIC SORTING:");
        TreeSet<Student> studentSet = new TreeSet<>();
        studentSet.add(new Student(108, "Eve", 90.0));
        studentSet.add(new Student(106, "Frank", 82.5));
        studentSet.add(new Student(110, "Grace", 87.0));
        studentSet.add(new Student(107, "Henry", 79.5));
        
        System.out.println("TreeSet (automatically sorted by ID):");
        studentSet.forEach(System.out::println);
        
        // 5. Comparable with primitive wrapper classes
        System.out.println("\n\n5. PRIMITIVE WRAPPER CLASSES (Built-in Comparable):");
        
        List<Integer> numbers = Arrays.asList(45, 12, 78, 23, 56, 34);
        System.out.println("Numbers before sorting: " + numbers);
        Collections.sort(numbers);
        System.out.println("Numbers after sorting: " + numbers);
        
        List<String> names = Arrays.asList("Zebra", "Apple", "Mango", "Banana");
        System.out.println("\nNames before sorting: " + names);
        Collections.sort(names);
        System.out.println("Names after sorting: " + names);
        
        // 6. Demonstrating compareTo() return values
        System.out.println("\n\n6. COMPARETO() RETURN VALUES:");
        Student s1 = new Student(101, "Test1", 80);
        Student s2 = new Student(102, "Test2", 85);
        Student s3 = new Student(101, "Test3", 90);
        
        System.out.println("s1.compareTo(s2): " + s1.compareTo(s2) + " (negative: s1 < s2)");
        System.out.println("s2.compareTo(s1): " + s2.compareTo(s1) + " (positive: s2 > s1)");
        System.out.println("s1.compareTo(s3): " + s1.compareTo(s3) + " (zero: s1 == s3)");
        
        System.out.println("\n=== KEY POINTS ===");
        System.out.println("• Comparable provides natural ordering for a class");
        System.out.println("• compareTo() returns: negative (<0), zero (=0), positive (>0)");
        System.out.println("• Only one sorting sequence per class");
        System.out.println("• Used with Collections.sort() and TreeSet/TreeMap");
        System.out.println("• Must be consistent with equals()");
    }
}