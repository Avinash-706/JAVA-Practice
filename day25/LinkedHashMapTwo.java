import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTwo {
    public static void main(String[] args) {
        
        // LRU Cache using LinkedHashMap
        System.out.println("--- LRU Cache Implementation ---");
        
        // Create LinkedHashMap with access order (not insertion order)
        // Parameters: initialCapacity, loadFactor, accessOrder
        Map<Integer, String> lruCache = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > 3; // Keep only 3 entries
            }
        };
        
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");
        System.out.println("After adding 3 entries: " + lruCache);
        
        // Access entry 1 (moves to end)
        lruCache.get(1);
        System.out.println("After accessing 1: " + lruCache);
        
        // Add new entry (removes least recently used)
        lruCache.put(4, "Four");
        System.out.println("After adding 4 (removes 2): " + lruCache);
        
        // Access entry 3
        lruCache.get(3);
        System.out.println("After accessing 3: " + lruCache);
        
        // Add another entry
        lruCache.put(5, "Five");
        System.out.println("After adding 5 (removes 1): " + lruCache);
        
        // Maintaining Order Example
        System.out.println("\n--- Maintaining Insertion Order ---");
        Map<String, Integer> studentRanks = new LinkedHashMap<>();
        
        studentRanks.put("Alice", 1);
        studentRanks.put("Bob", 2);
        studentRanks.put("Charlie", 3);
        studentRanks.put("David", 4);
        studentRanks.put("Eve", 5);
        
        System.out.println("Student Ranks (Insertion Order): " + studentRanks);
        
        // Update a value (order maintained)
        studentRanks.put("Charlie", 10);
        System.out.println("After updating Charlie: " + studentRanks);
        
        // Remove and re-add (goes to end)
        studentRanks.remove("Bob");
        studentRanks.put("Bob", 2);
        System.out.println("After removing and re-adding Bob: " + studentRanks);
        
        // Processing in Order
        System.out.println("\n--- Processing in Insertion Order ---");
        Map<String, String> tasks = new LinkedHashMap<>();
        
        tasks.put("Task1", "Design");
        tasks.put("Task2", "Development");
        tasks.put("Task3", "Testing");
        tasks.put("Task4", "Deployment");
        
        System.out.println("Tasks in order:");
        tasks.forEach((task, description) -> {
            System.out.println(task + ": " + description);
        });
        
        // Frequency Counter with Order
        System.out.println("\n--- Frequency Counter (Maintains First Occurrence Order) ---");
        String text = "hello world hello java world";
        String[] words = text.split(" ");
        
        Map<String, Integer> wordFreq = new LinkedHashMap<>();
        for(String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }
        
        System.out.println("Word frequencies (first occurrence order): " + wordFreq);
        
        // Menu System Example
        System.out.println("\n--- Menu System (Order Matters) ---");
        Map<Integer, String> menu = new LinkedHashMap<>();
        
        menu.put(1, "File");
        menu.put(2, "Edit");
        menu.put(3, "View");
        menu.put(4, "Tools");
        menu.put(5, "Help");
        
        System.out.println("Menu:");
        menu.forEach((id, name) -> {
            System.out.println(id + ". " + name);
        });
        
        // Configuration Settings (Order preserved)
        System.out.println("\n--- Configuration Settings ---");
        Map<String, String> config = new LinkedHashMap<>();
        
        config.put("host", "localhost");
        config.put("port", "8080");
        config.put("username", "admin");
        config.put("password", "secret");
        config.put("database", "mydb");
        
        System.out.println("Configuration (in order):");
        config.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });
        
        // Comparison: LinkedHashMap vs HashMap
        System.out.println("\n--- LinkedHashMap vs HashMap Order ---");
        
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        Map<String, Integer> hashMap = new java.util.HashMap<>();
        
        String[] items = {"Zebra", "Apple", "Mango", "Banana", "Cherry"};
        
        for(int i = 0; i < items.length; i++) {
            linkedMap.put(items[i], i);
            hashMap.put(items[i], i);
        }
        
        System.out.println("LinkedHashMap (Insertion Order): " + linkedMap);
        System.out.println("HashMap (No Order): " + hashMap);
    }
}
