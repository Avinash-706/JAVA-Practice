import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

public class HashMapOne {
    public static void main(String[] args) {
        
        // Creating HashMap
        Map<Integer, String> hm = new HashMap<>();
        
        // Adding key-value pairs using put()
        hm.put(1, "Java");
        hm.put(2, "Python");
        hm.put(3, "JavaScript");
        hm.put(4, "C++");
        hm.put(5, "Ruby");
        
        System.out.println("HashMap: " + hm);
        
        // Adding duplicate key (value will be replaced)
        hm.put(2, "Kotlin");
        System.out.println("\nAfter put(2, \"Kotlin\"): " + hm);
        
        // Adding null key (only one null key allowed)
        hm.put(null, "NullKey");
        System.out.println("After adding null key: " + hm);
        
        // Adding null values (multiple null values allowed)
        hm.put(6, null);
        hm.put(7, null);
        System.out.println("After adding null values: " + hm);
        
        // Size
        System.out.println("\nSize: " + hm.size());
        
        // Get value by key
        System.out.println("\nget(1): " + hm.get(1));
        System.out.println("get(2): " + hm.get(2));
        System.out.println("get(100): " + hm.get(100)); // null (key not found)
        
        // Check if key exists
        System.out.println("\ncontainsKey(3): " + hm.containsKey(3));
        System.out.println("containsKey(100): " + hm.containsKey(100));
        
        // Check if value exists
        System.out.println("\ncontainsValue(\"Java\"): " + hm.containsValue("Java"));
        System.out.println("containsValue(\"Go\"): " + hm.containsValue("Go"));
        
        // Remove by key
        String removed = hm.remove(3);
        System.out.println("\nremove(3): " + removed);
        System.out.println("After remove: " + hm);
        
        // putIfAbsent - adds only if key doesn't exist
        hm.putIfAbsent(8, "Swift");
        hm.putIfAbsent(1, "NewValue"); // Won't add, key 1 exists
        System.out.println("\nAfter putIfAbsent: " + hm);
        
        // replace - replaces value if key exists
        hm.replace(1, "Java Updated");
        System.out.println("\nAfter replace(1, \"Java Updated\"): " + hm);
        
        // getOrDefault - returns default if key not found
        System.out.println("\ngetOrDefault(1, \"Default\"): " + hm.getOrDefault(1, "Default"));
        System.out.println("getOrDefault(100, \"Default\"): " + hm.getOrDefault(100, "Default"));
        
        // Get all keys
        System.out.println("\n--- All Keys ---");
        Set<Integer> keys = hm.keySet();
        System.out.println("Keys: " + keys);
        
        // Get all values
        System.out.println("\n--- All Values ---");
        Collection<String> values = hm.values();
        System.out.println("Values: " + values);
        
        // Get all entries (key-value pairs)
        System.out.println("\n--- All Entries ---");
        Set<Map.Entry<Integer, String>> entries = hm.entrySet();
        for(Map.Entry<Integer, String> entry : entries) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        
        // Traversal using forEach
        System.out.println("\n--- Traversal using forEach ---");
        hm.forEach((key, value) -> {
            System.out.println(key + " => " + value);
        });
        
        // isEmpty and clear
        System.out.println("\nisEmpty(): " + hm.isEmpty());
        hm.clear();
        System.out.println("After clear: " + hm);
        System.out.println("isEmpty(): " + hm.isEmpty());
    }
}
