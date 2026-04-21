import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class HashSetTwo {
    public static void main(String[] args) {
        
        // Creating HashSet
        Set<Integer> hs = new HashSet<>();
        
        // Adding elements
        hs.add(10);
        hs.add(20);
        hs.add(30);
        hs.add(40);
        hs.add(50);
        
        System.out.println("HashSet: " + hs);
        
        // Adding duplicate (will not be added)
        boolean added = hs.add(20);
        System.out.println("\nAdding duplicate 20: " + added);
        System.out.println("After adding duplicate: " + hs);
        
        // Adding null
        hs.add(null);
        System.out.println("\nAfter adding null: " + hs);
        
        // Size
        System.out.println("\nSize: " + hs.size());
        
        // Contains
        System.out.println("Contains 30? " + hs.contains(30));
        System.out.println("Contains 100? " + hs.contains(100));
        
        // Remove
        hs.remove(30);
        System.out.println("\nAfter remove(30): " + hs);
        
        // Traversal using for-each
        System.out.println("\nTraversal using for-each:");
        for(Integer num : hs) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // Traversal using Iterator
        System.out.println("\nTraversal using Iterator:");
        Iterator<Integer> itr = hs.iterator();
        while(itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        
        // Set operations
        Set<Integer> set1 = new HashSet<>();
        set1.add(10);
        set1.add(20);
        set1.add(30);
        
        Set<Integer> set2 = new HashSet<>();
        set2.add(30);
        set2.add(40);
        set2.add(50);
        
        System.out.println("\nSet1: " + set1);
        System.out.println("Set2: " + set2);
        
        // Union
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("\nUnion: " + union);
        
        // Intersection
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);
        
        // Difference
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (set1 - set2): " + difference);
        
        // isEmpty and clear
        System.out.println("\nisEmpty(): " + hs.isEmpty());
        hs.clear();
        System.out.println("After clear: " + hs);
        System.out.println("isEmpty(): " + hs.isEmpty());
    }
}
