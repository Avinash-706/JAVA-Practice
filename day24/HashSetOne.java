import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HashSetOne {
    public static void main(String[] args) {
        
        // Creating HashSet with heterogeneous objects
        Set<Object> hs = new HashSet<>();

        // Adding elements
        hs.add(10);
        hs.add(10);  // Duplicate - will not be added
        hs.add(12);
        hs.add('a');
        hs.add("Kshitij");
        hs.add(null);  // HashSet allows one null

        System.out.println("HashSet: " + hs);

        // Traversal using for-each
        System.out.println("\nTraversal using for-each:");
        for(Object obj : hs) {
            System.out.print(obj + " ");
        }
        System.out.println();

        // Traversal using Iterator
        System.out.println("\nTraversal using Iterator:");
        Iterator<Object> itr = hs.iterator();
        while(itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();

        // Adding more elements
        hs = addElements(hs);
        System.out.println("\nAfter adding more elements: " + hs);
        
        // Size
        System.out.println("\nSize: " + hs.size());
        
        // Contains
        System.out.println("Contains \"20\": " + hs.contains("20"));
        System.out.println("Contains 20: " + hs.contains(20));
        
        // ContainsAll
        System.out.println("Contains all [10, 20, 30, 40]? " + hs.containsAll(List.of(10, 20, 30, 40)));
        
        // Remove
        System.out.println("\nRemove 20: " + hs.remove(20));
        System.out.println("After remove: " + hs);

        // Clear
        System.out.println("\nBefore clear: " + hs);
        hs.clear();
        System.out.println("After clear: " + hs);
        System.out.println("isEmpty: " + hs.isEmpty());
    }

    public static Set<Object> addElements(Set<Object> hs) {
        List<Integer> numbers = List.of(10, 20, 30, 40);

        hs.add(90);
        hs.add("Avinash");
        hs.addAll(numbers);

        return hs;
    }
}
