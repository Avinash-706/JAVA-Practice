import java.util.*;

// Basic enum for demonstration
enum StudentNames {
    ADITYA, DEVANSH, VISHNU, ANIMESH, MOHIT, PAWAN, NITISH, KABIR
}

// Enum with additional functionality
enum Priority {
    LOW(1), MEDIUM(2), HIGH(3), CRITICAL(4);
    
    private final int level;
    
    Priority(int level) {
        this.level = level;
    }
    
    public int getLevel() {
        return level;
    }
}

// Enum for status tracking
enum TaskStatus {
    PENDING("Task is waiting to be started"),
    IN_PROGRESS("Task is currently being worked on"),
    COMPLETED("Task has been finished"),
    CANCELLED("Task has been cancelled");
    
    private final String description;
    
    TaskStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}

public class EnumOne {
    public static void main(String[] args) {
        
        System.out.println("=== BASIC ENUM OPERATIONS ===\n");
        
        // 1. Basic enum usage
        System.out.println("1. BASIC ENUM USAGE:");
        
        // Display all student names
        System.out.println("All student names:");
        StudentNames[] allNames = StudentNames.values();
        for (int i = 0; i < allNames.length; i++) {
            System.out.println(i + ": " + allNames[i].name() + " (ordinal: " + allNames[i].ordinal() + ")");
        }
        
        // 2. Finding specific enum value
        System.out.println("\n2. FINDING SPECIFIC ENUM VALUE:");
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a student name to search: ");
        String searchName = sc.next().toUpperCase();
        
        try {
            StudentNames foundStudent = StudentNames.valueOf(searchName);
            System.out.println("✓ " + searchName + " is present at position " + foundStudent.ordinal());
            
            // Check if it's the first or last student
            if (foundStudent.ordinal() == 0) {
                System.out.println("  This is the first student in the list.");
            } else if (foundStudent.ordinal() == StudentNames.values().length - 1) {
                System.out.println("  This is the last student in the list.");
            } else {
                System.out.println("  Previous: " + StudentNames.values()[foundStudent.ordinal() - 1]);
                System.out.println("  Next: " + StudentNames.values()[foundStudent.ordinal() + 1]);
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("✗ " + searchName + " is not found in the student list.");
            System.out.println("Available students: " + Arrays.toString(StudentNames.values()));
        }
        
        // 3. Enum with methods and fields
        System.out.println("\n3. ENUM WITH METHODS AND FIELDS:");
        
        System.out.println("Priority levels:");
        for (Priority priority : Priority.values()) {
            System.out.println(priority.name() + " - Level: " + priority.getLevel());
        }
        
        // Find priority by level
        System.out.print("\nEnter priority level (1-4): ");
        int level = sc.nextInt();
        
        Priority foundPriority = null;
        for (Priority p : Priority.values()) {
            if (p.getLevel() == level) {
                foundPriority = p;
                break;
            }
        }
        
        if (foundPriority != null) {
            System.out.println("Priority for level " + level + ": " + foundPriority.name());
        } else {
            System.out.println("No priority found for level " + level);
        }
        
        // 4. Enum in switch statement
        System.out.println("\n4. ENUM IN SWITCH STATEMENT:");
        
        TaskStatus[] statuses = {TaskStatus.PENDING, TaskStatus.IN_PROGRESS, TaskStatus.COMPLETED, TaskStatus.CANCELLED};
        
        for (TaskStatus status : statuses) {
            String action = getActionForStatus(status);
            System.out.println(status.name() + ": " + status.getDescription());
            System.out.println("  → Action: " + action);
        }
        
        // 5. Enum comparison and ordering
        System.out.println("\n5. ENUM COMPARISON AND ORDERING:");
        
        Priority p1 = Priority.LOW;
        Priority p2 = Priority.HIGH;
        Priority p3 = Priority.LOW;
        
        System.out.println("p1 (LOW) == p3 (LOW): " + (p1 == p3));
        System.out.println("p1.equals(p3): " + p1.equals(p3));
        System.out.println("p1.compareTo(p2): " + p1.compareTo(p2) + " (negative: p1 comes before p2)");
        System.out.println("p2.compareTo(p1): " + p2.compareTo(p1) + " (positive: p2 comes after p1)");
        
        // 6. EnumSet operations
        System.out.println("\n6. ENUMSET OPERATIONS:");
        
        EnumSet<Priority> highPriorities = EnumSet.of(Priority.HIGH, Priority.CRITICAL);
        EnumSet<Priority> lowPriorities = EnumSet.range(Priority.LOW, Priority.MEDIUM);
        EnumSet<Priority> allPriorities = EnumSet.allOf(Priority.class);
        
        System.out.println("High priorities: " + highPriorities);
        System.out.println("Low priorities: " + lowPriorities);
        System.out.println("All priorities: " + allPriorities);
        
        // Set operations
        EnumSet<Priority> combined = EnumSet.copyOf(highPriorities);
        combined.addAll(lowPriorities);
        System.out.println("Combined: " + combined);
        
        // 7. EnumMap operations
        System.out.println("\n7. ENUMMAP OPERATIONS:");
        
        EnumMap<TaskStatus, Integer> statusCount = new EnumMap<>(TaskStatus.class);
        statusCount.put(TaskStatus.PENDING, 5);
        statusCount.put(TaskStatus.IN_PROGRESS, 3);
        statusCount.put(TaskStatus.COMPLETED, 12);
        statusCount.put(TaskStatus.CANCELLED, 1);
        
        System.out.println("Task status counts:");
        statusCount.forEach((status, count) -> 
            System.out.println("  " + status.name() + ": " + count + " tasks"));
        
        // 8. Practical example: Student attendance
        System.out.println("\n8. PRACTICAL EXAMPLE - STUDENT ATTENDANCE:");
        
        EnumMap<StudentNames, Boolean> attendance = new EnumMap<>(StudentNames.class);
        Random random = new Random();
        
        // Simulate random attendance
        for (StudentNames student : StudentNames.values()) {
            attendance.put(student, random.nextBoolean());
        }
        
        System.out.println("Today's attendance:");
        int presentCount = 0;
        for (Map.Entry<StudentNames, Boolean> entry : attendance.entrySet()) {
            String status = entry.getValue() ? "Present" : "Absent";
            System.out.println("  " + entry.getKey().name() + ": " + status);
            if (entry.getValue()) presentCount++;
        }
        
        System.out.println("\nAttendance Summary:");
        System.out.println("  Total students: " + StudentNames.values().length);
        System.out.println("  Present: " + presentCount);
        System.out.println("  Absent: " + (StudentNames.values().length - presentCount));
        System.out.println("  Attendance rate: " + String.format("%.1f%%", 
            (presentCount * 100.0) / StudentNames.values().length));
        
        // 9. Enum iteration patterns
        System.out.println("\n9. ENUM ITERATION PATTERNS:");
        
        System.out.println("Forward iteration:");
        for (StudentNames name : StudentNames.values()) {
            System.out.print(name.name() + " ");
        }
        
        System.out.println("\n\nReverse iteration:");
        StudentNames[] names = StudentNames.values();
        for (int i = names.length - 1; i >= 0; i--) {
            System.out.print(names[i].name() + " ");
        }
        
        System.out.println("\n\nUsing streams:");
        Arrays.stream(StudentNames.values())
              .map(StudentNames::name)
              .forEach(name -> System.out.print(name + " "));
        
        System.out.println("\n\n=== ENUM BUILT-IN METHODS SUMMARY ===");
        StudentNames sample = StudentNames.ADITYA;
        System.out.println("Sample enum: " + sample);
        System.out.println("• name(): " + sample.name());
        System.out.println("• ordinal(): " + sample.ordinal());
        System.out.println("• toString(): " + sample.toString());
        System.out.println("• getClass(): " + sample.getClass());
        System.out.println("• values().length: " + StudentNames.values().length);
        
        sc.close();
    }
    
    // Helper method for switch statement demonstration
    private static String getActionForStatus(TaskStatus status) {
        switch (status) {
            case PENDING:
                return "Assign to team member";
            case IN_PROGRESS:
                return "Monitor progress and provide support";
            case COMPLETED:
                return "Review and mark as done";
            case CANCELLED:
                return "Archive and document reason";
            default:
                return "Unknown action";
        }
    }
}