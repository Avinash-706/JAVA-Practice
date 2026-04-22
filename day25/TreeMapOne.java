import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

public class TreeMapOne {
    public static void main(String[] args) {
        
        // Creating TreeMap (Sorted by keys in ascending order)
        TreeMap<Integer, String> tm = new TreeMap<>();
        
        // Adding key-value pairs
        tm.put(5, "Five");
        tm.put(1, "One");
        tm.put(3, "Three");
        tm.put(2, "Two");
        tm.put(4, "Four");
        
        System.out.println("TreeMap (Sorted by Keys): " + tm);
        // {1=One, 2=Two, 3=Three, 4=Four, 5=Five}
        
        // Adding duplicate key (value will be replaced)
        tm.put(3, "Three Updated");
        System.out.println("\nAfter put(3, \"Three Updated\"): " + tm);
        
        // TreeMap does not allow null keys
        // tm.put(null, "NullKey"); // NullPointerException
        
        // TreeMap allows null values
        tm.put(6, null);
        tm.put(7, null);
        System.out.println("After adding null values: " + tm);
        
        // Size
        System.out.println("\nSize: " + tm.size());
        
        // Get value by key
        System.out.println("\nget(1): " + tm.get(1));
        System.out.println("get(3): " + tm.get(3));
        
        // Check if key exists
        System.out.println("\ncontainsKey(2): " + tm.containsKey(2));
        System.out.println("containsKey(100): " + tm.containsKey(100));
        
        // Check if value exists
        System.out.println("\ncontainsValue(\"Five\"): " + tm.containsValue("Five"));
        System.out.println("containsValue(\"Ten\"): " + tm.containsValue("Ten"));
        
        // First and Last keys
        System.out.println("\nfirstKey(): " + tm.firstKey());
        System.out.println("lastKey(): " + tm.lastKey());
        
        // First and Last entries
        System.out.println("\nfirstEntry(): " + tm.firstEntry());
        System.out.println("lastEntry(): " + tm.lastEntry());
        
        // Remove by key
        String removed = tm.remove(2);
        System.out.println("\nremove(2): " + removed);
        System.out.println("After remove: " + tm);
        
        // pollFirstEntry and pollLastEntry (remove and return)
        System.out.println("\npollFirstEntry(): " + tm.pollFirstEntry());
        System.out.println("After pollFirstEntry: " + tm);
        
        System.out.println("\npollLastEntry(): " + tm.pollLastEntry());
        System.out.println("After pollLastEntry: " + tm);
        
        // Get all keys (in sorted order)
        System.out.println("\n--- All Keys (Sorted Order) ---");
        Set<Integer> keys = tm.keySet();
        System.out.println("Keys: " + keys);
        
        // Get all entries (in sorted order)
        System.out.println("\n--- All Entries (Sorted Order) ---");
        Set<Map.Entry<Integer, String>> entries = tm.entrySet();
        for(Map.Entry<Integer, String> entry : entries) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        
        // Traversal using forEach (sorted order)
        System.out.println("\n--- Traversal using forEach ---");
        tm.forEach((key, value) -> {
            System.out.println(key + " => " + value);
        });
        
        // Clear
        System.out.println("\nisEmpty(): " + tm.isEmpty());
        tm.clear();
        System.out.println("After clear: " + tm);
        System.out.println("isEmpty(): " + tm.isEmpty());
    }
}
