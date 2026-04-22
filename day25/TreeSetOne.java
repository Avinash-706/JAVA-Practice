import java.util.TreeSet;
import java.util.Set;
import java.util.Iterator;

public class TreeSetOne {
    public static void main(String[] args) {
        
        // Creating TreeSet (Sorted order by default)
        TreeSet<Integer> ts = new TreeSet<>();
        
        // Adding elements
        ts.add(50);
        ts.add(20);
        ts.add(70);
        ts.add(10);
        ts.add(30);
        ts.add(60);
        ts.add(40);
        
        System.out.println("TreeSet (Sorted): " + ts);
        // [10, 20, 30, 40, 50, 60, 70]
        
        // Adding duplicate (will not be added)
        boolean added = ts.add(30);
        System.out.println("\nAdding duplicate 30: " + added);
        System.out.println("After adding duplicate: " + ts);
        
        // TreeSet does not allow null
        // ts.add(null); // NullPointerException
        
        // Size
        System.out.println("\nSize: " + ts.size());
        
        // Contains
        System.out.println("Contains 40? " + ts.contains(40));
        System.out.println("Contains 100? " + ts.contains(100));
        
        // First and Last elements
        System.out.println("\nFirst element: " + ts.first());
        System.out.println("Last element: " + ts.last());
        
        // Remove
        ts.remove(30);
        System.out.println("\nAfter remove(30): " + ts);
        
        // Poll operations (remove and return)
        System.out.println("\npollFirst(): " + ts.pollFirst());
        System.out.println("After pollFirst: " + ts);
        
        System.out.println("\npollLast(): " + ts.pollLast());
        System.out.println("After pollLast: " + ts);
        
        // Traversal using for-each
        System.out.println("\nTraversal using for-each:");
        for(Integer num : ts) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Traversal using Iterator
        System.out.println("\nTraversal using Iterator:");
        Iterator<Integer> itr = ts.iterator();
        while(itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        
        // Clear
        System.out.println("\nBefore clear: " + ts);
        System.out.println("isEmpty(): " + ts.isEmpty());
        
        ts.clear();
        System.out.println("After clear: " + ts);
        System.out.println("isEmpty(): " + ts.isEmpty());
    }
}
