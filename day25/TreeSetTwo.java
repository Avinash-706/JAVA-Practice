import java.util.TreeSet;
import java.util.Comparator;
import java.util.Iterator;

public class TreeSetTwo {
    public static void main(String[] args) {
        
        // TreeSet with String (Natural ordering - Alphabetical)
        System.out.println("--- TreeSet with Strings ---");
        TreeSet<String> names = new TreeSet<>();
        
        names.add("John");
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("David");
        
        System.out.println("Sorted names: " + names);
        // [Alice, Bob, Charlie, David, John]
        
        // Subset operations
        System.out.println("\n--- Subset Operations ---");
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(10);
        ts.add(20);
        ts.add(30);
        ts.add(40);
        ts.add(50);
        ts.add(60);
        ts.add(70);
        
        System.out.println("Original TreeSet: " + ts);
        
        // headSet - elements less than specified
        System.out.println("headSet(40): " + ts.headSet(40));
        // [10, 20, 30]
        
        // tailSet - elements greater than or equal to specified
        System.out.println("tailSet(40): " + ts.tailSet(40));
        // [40, 50, 60, 70]
        
        // subSet - elements in range [from, to)
        System.out.println("subSet(20, 60): " + ts.subSet(20, 60));
        // [20, 30, 40, 50]
        
        // NavigableSet methods
        System.out.println("\n--- NavigableSet Methods ---");
        System.out.println("lower(40): " + ts.lower(40));     // 30 (< 40)
        System.out.println("floor(40): " + ts.floor(40));     // 40 (<= 40)
        System.out.println("ceiling(40): " + ts.ceiling(40)); // 40 (>= 40)
        System.out.println("higher(40): " + ts.higher(40));   // 50 (> 40)
        
        // Descending order
        System.out.println("\n--- Descending Order ---");
        System.out.println("Descending Set: " + ts.descendingSet());
        // [70, 60, 50, 40, 30, 20, 10]
        
        // Descending Iterator
        System.out.print("Descending Iterator: ");
        Iterator<Integer> descItr = ts.descendingIterator();
        while(descItr.hasNext()) {
            System.out.print(descItr.next() + " ");
        }
        System.out.println();
        
        // Custom Comparator - Reverse order
        System.out.println("\n--- Custom Comparator (Reverse Order) ---");
        TreeSet<Integer> reverseSet = new TreeSet<>(Comparator.reverseOrder());
        
        reverseSet.add(50);
        reverseSet.add(20);
        reverseSet.add(70);
        reverseSet.add(10);
        reverseSet.add(30);
        
        System.out.println("Reverse order TreeSet: " + reverseSet);
        // [70, 50, 30, 20, 10]
        
        // Custom Comparator - String by length
        System.out.println("\n--- Custom Comparator (String by length) ---");
        TreeSet<String> lengthSet = new TreeSet<>((a, b) -> {
            if(a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        });
        
        lengthSet.add("Java");
        lengthSet.add("Python");
        lengthSet.add("C");
        lengthSet.add("JavaScript");
        lengthSet.add("Go");
        lengthSet.add("Ruby");
        
        System.out.println("Sorted by length: " + lengthSet);
        // [C, Go, Java, Ruby, Python, JavaScript]
    }
}
