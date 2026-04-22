import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapOne {
    public static void main(String[] args) {
        
        // Creating LinkedHashMap (Maintains insertion order)
        Map<Integer, String> lhm = new LinkedHashMap<>();
        
        // Adding key-value pairs
        lhm.put(5, "Five");
        lhm.put(1, "One");
        lhm.put(3, "Three");
        lhm.put(2, "Two");
        lhm.put(4, "Four");
        
        System.out.println("LinkedHashMap (Insertion Order): " + lhm);
        // {5=Five, 1=One, 3=Three, 2=Two, 4=Four}
        
        // Compare with HashMap (No order)
        Map<Integer, String> hm = new java.util.HashMap<>();
        hm.put(5, "Five");
        hm.put(1, "One");
        hm.put(3, "Three");
        hm.put(2, "Two");
        hm.put(4, "Four");
        
        System.out.println("HashMap (No Order): " + hm);
        // Order may vary
        
        // Adding duplicate key (value will be replaced, order maintained)
        lhm.put(3, "Three Updated");
        System.out.println("\nAfter put(3, \"Three Updated\"): " + lhm);
        
        // Adding null key (only one null key allowed)
        lhm.put(null, "NullKey");
        System.out.println("After adding null key: " + lhm);
        
        // Adding null values (multiple null values allowed)
        lhm.put(6, null);
        lhm.put(7, null);
        System.out.println("After adding null values: " + lhm);
        
        // Size
        System.out.println("\nSize: " + lhm.size());
        
        // Get value by key
        System.out.println("\nget(1): " + lhm.get(1));
        System.out.println("get(3): " + lhm.get(3));
        
        // Check if key exists
        System.out.println("\ncontainsKey(2): " + lhm.containsKey(2));
        System.out.println("containsKey(100): " + lhm.containsKey(100));
        
        // Check if value exists
        System.out.println("\ncontainsValue(\"Five\"): " + lhm.containsValue("Five"));
        System.out.println("containsValue(\"Ten\"): " + lhm.containsValue("Ten"));
        
        // Remove by key
        String removed = lhm.remove(2);
        System.out.println("\nremove(2): " + removed);
        System.out.println("After remove: " + lhm);
        
        // putIfAbsent
        lhm.putIfAbsent(8, "Eight");
        lhm.putIfAbsent(1, "NewValue"); // Won't add, key 1 exists
        System.out.println("\nAfter putIfAbsent: " + lhm);
        
        // Get all keys (in insertion order)
        System.out.println("\n--- All Keys (Insertion Order) ---");
        Set<Integer> keys = lhm.keySet();
        System.out.println("Keys: " + keys);
        
        // Get all entries (in insertion order)
        System.out.println("\n--- All Entries (Insertion Order) ---");
        Set<Map.Entry<Integer, String>> entries = lhm.entrySet();
        for(Map.Entry<Integer, String> entry : entries) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        
        // Traversal using forEach (maintains insertion order)
        System.out.println("\n--- Traversal using forEach ---");
        lhm.forEach((key, value) -> {
            System.out.println(key + " => " + value);
        });
        
        // Clear
        System.out.println("\nisEmpty(): " + lhm.isEmpty());
        lhm.clear();
        System.out.println("After clear: " + lhm);
        System.out.println("isEmpty(): " + lhm.isEmpty());
    }
}
