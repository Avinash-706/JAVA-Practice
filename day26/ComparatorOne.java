import java.util.*;

// Student class implementing Comparable for natural ordering
class Students implements Comparable<Students> {
    int id;
    String name;
    double marks;
    
    public Students(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    
    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s', marks=%.1f}", id, name, marks);
    }
    
    // Natural ordering by id (ascending)
    @Override
    public int compareTo(Students other) {
        return Integer.compare(this.id, other.id);
    }
    
    // Getters for accessing fields
    public int getId() { return id; }
    public String getName() { return name; }
    public double getMarks() { return marks; }
}

// Comparator for sorting by name (alphabetical)
class SortByNames implements Comparator<Students> {
    @Override
    public int compare(Students s1, Students s2) {
        return s1.name.compareTo(s2.name);
    }
}

// Comparator for sorting by marks (ascending)
class SortByMarks implements Comparator<Students> {
    @Override
    public int compare(Students s1, Students s2) {
        return Double.compare(s1.marks, s2.marks);
    }
}

// Comparator for sorting by marks (descending)
class SortByMarksDescending implements Comparator<Students> {
    @Override
    public int compare(Students s1, Students s2) {
        return Double.compare(s2.marks, s1.marks); // Reversed for descending
    }
}

// Multi-field comparator: First by marks (desc), then by name (asc)
class SortByMarksAndName implements Comparator<Students> {
    @Override
    public int compare(Students s1, Students s2) {
        // First compare by marks (descending)
        int result = Double.compare(s2.marks, s1.marks);
        if (result != 0) {
            return result;
        }
        // If marks are equal, compare by name (ascending)
        return s1.name.compareTo(s2.name);
    }
}

public class ComparatorOne {
    public static void main(String[] args) {
        
        System.out.println("=== COMPARABLE VS COMPARATOR DEMONSTRATION ===\n");
        
        // Create list of students
        List<Students> students = new ArrayList<>();
        students.add(new Students(103, "Murali", 75.5));
        students.add(new Students(101, "Aditya", 85.5));
        students.add(new Students(105, "Vishnu", 80.5));
        students.add(new Students(102, "Pramod", 90.5));
        students.add(new Students(104, "Suraj", 85.5)); // Same marks as Aditya
        students.add(new Students(106, "Anita", 90.5));  // Same marks as Pramod
        
        System.out.println("Original List:");
        printStudents(students);
        
        // 1. Natural ordering using Comparable (by ID)
        System.out.println("\n1. NATURAL ORDERING (Comparable - by ID ascending):");
        List<Students> temp = new ArrayList<>(students);
        Collections.sort(temp); // Uses compareTo() method
        printStudents(temp);
        
        // 2. Custom ordering using Comparator classes
        System.out.println("\n2. CUSTOM ORDERING USING COMPARATOR CLASSES:");
        
        // Sort by name (alphabetical)
        temp = new ArrayList<>(students);
        Collections.sort(temp, new SortByNames());
        System.out.println("\nSorted by Name (alphabetical):");
        printStudents(temp);
        
        // Sort by marks (ascending)
        temp = new ArrayList<>(students);
        Collections.sort(temp, new SortByMarks());
        System.out.println("\nSorted by Marks (ascending):");
        printStudents(temp);
        
        // Sort by marks (descending)
        temp = new ArrayList<>(students);
        Collections.sort(temp, new SortByMarksDescending());
        System.out.println("\nSorted by Marks (descending):");
        printStudents(temp);
        
        // Multi-field sorting
        temp = new ArrayList<>(students);
        Collections.sort(temp, new SortByMarksAndName());
        System.out.println("\nSorted by Marks (desc) then Name (asc):");
        printStudents(temp);
        
        // 3. Anonymous inner class comparators
        System.out.println("\n3. ANONYMOUS INNER CLASS COMPARATORS:");
        
        // Sort by name length
        temp = new ArrayList<>(students);
        Collections.sort(temp, new Comparator<Students>() {
            @Override
            public int compare(Students s1, Students s2) {
                return Integer.compare(s1.name.length(), s2.name.length());
            }
        });
        System.out.println("\nSorted by Name Length:");
        printStudents(temp);
        
        // 4. Lambda expression comparators (Java 8+)
        System.out.println("\n4. LAMBDA EXPRESSION COMPARATORS:");
        
        temp = new ArrayList<>(students);
        
        // Sort by ID using lambda
        temp.sort((s1, s2) -> Integer.compare(s1.id, s2.id));
        System.out.println("\nSorted by ID (lambda):");
        printStudents(temp);
        
        // Sort by marks using lambda
        temp.sort((s1, s2) -> Double.compare(s2.marks, s1.marks));
        System.out.println("\nSorted by Marks descending (lambda):");
        printStudents(temp);
        
        // 5. Method reference and Comparator.comparing()
        System.out.println("\n5. METHOD REFERENCE AND COMPARATOR.COMPARING():");
        
        temp = new ArrayList<>(students);
        
        // Using Comparator.comparing()
        temp.sort(Comparator.comparing(Students::getName));
        System.out.println("\nSorted by Name (method reference):");
        printStudents(temp);
        
        // Chained comparators
        temp.sort(Comparator.comparing(Students::getMarks).reversed()
                           .thenComparing(Students::getName));
        System.out.println("\nSorted by Marks (desc) then Name (asc) - chained:");
        printStudents(temp);
        
        // 6. TreeSet with different comparators
        System.out.println("\n6. TREESET WITH DIFFERENT COMPARATORS:");
        
        // TreeSet with natural ordering (by ID)
        TreeSet<Students> naturalSet = new TreeSet<>(students);
        System.out.println("\nTreeSet (natural ordering by ID):");
        naturalSet.forEach(System.out::println);
        
        // TreeSet with custom comparator (by marks descending)
        TreeSet<Students> marksSet = new TreeSet<>(new SortByMarksDescending());
        marksSet.addAll(students);
        System.out.println("\nTreeSet (by marks descending):");
        marksSet.forEach(System.out::println);
        
        // 7. Practical examples
        System.out.println("\n7. PRACTICAL EXAMPLES:");
        
        // Find top 3 students by marks
        temp = new ArrayList<>(students);
        temp.sort(Comparator.comparing(Students::getMarks).reversed());
        System.out.println("\nTop 3 students by marks:");
        temp.stream().limit(3).forEach(System.out::println);
        
        // Find students with same marks
        System.out.println("\nStudents grouped by marks:");
        Map<Double, List<Students>> studentsByMarks = new HashMap<>();
        for (Students student : students) {
            studentsByMarks.computeIfAbsent(student.getMarks(), k -> new ArrayList<>()).add(student);
        }
        
        studentsByMarks.entrySet().stream()
            .sorted(Map.Entry.<Double, List<Students>>comparingByKey().reversed())
            .forEach(entry -> {
                System.out.println("Marks " + entry.getKey() + ": " + 
                    entry.getValue().stream()
                        .map(Students::getName)
                        .sorted()
                        .reduce((a, b) -> a + ", " + b)
                        .orElse(""));
            });
        
        // 8. Performance comparison
        System.out.println("\n8. PERFORMANCE NOTES:");
        System.out.println("• Comparable: Single sorting sequence, modifies original class");
        System.out.println("• Comparator: Multiple sorting sequences, separate classes");
        System.out.println("• Lambda expressions: Concise syntax, better readability");
        System.out.println("• Method references: Even more concise for simple cases");
        System.out.println("• Chained comparators: Handle complex multi-field sorting");
        
        System.out.println("\n=== COMPARISON SUMMARY ===");
        System.out.println("Comparable:");
        System.out.println("  ✓ Natural ordering");
        System.out.println("  ✓ Single sorting logic");
        System.out.println("  ✗ Modifies original class");
        System.out.println("  ✗ Only one sorting sequence");
        
        System.out.println("\nComparator:");
        System.out.println("  ✓ Custom ordering");
        System.out.println("  ✓ Multiple sorting sequences");
        System.out.println("  ✓ Doesn't modify original class");
        System.out.println("  ✓ Can sort objects you don't own");
    }
    
    // Helper method to print students
    private static void printStudents(List<Students> students) {
        students.forEach(System.out::println);
    }
}
