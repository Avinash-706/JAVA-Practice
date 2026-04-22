import java.util.HashMap;
import java.util.Map;

public class HashMapTwo {
    public static void main(String[] args) {
        
        // Frequency Counter using HashMap
        System.out.println("--- Frequency Counter ---");
        String text = "hello world";
        Map<Character, Integer> freqMap = new HashMap<>();
        
        for(char c : text.toCharArray()) {
            if(c != ' ') {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }
        
        System.out.println("Character frequencies: " + freqMap);
        
        // Word Counter
        System.out.println("\n--- Word Counter ---");
        String sentence = "java is great and java is powerful";
        String[] words = sentence.split(" ");
        Map<String, Integer> wordCount = new HashMap<>();
        
        for(String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        System.out.println("Word frequencies: " + wordCount);
        
        // Merge operation
        System.out.println("\n--- Merge Operation ---");
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 10);
        map1.put("B", 20);
        map1.put("C", 30);
        
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("B", 25);
        map2.put("C", 35);
        map2.put("D", 40);
        
        System.out.println("Map1: " + map1);
        System.out.println("Map2: " + map2);
        
        // Merge map2 into map1 (sum values for common keys)
        map2.forEach((key, value) -> {
            map1.merge(key, value, (v1, v2) -> v1 + v2);
        });
        
        System.out.println("After merge: " + map1);
        
        // compute - compute new value for a key
        System.out.println("\n--- Compute Operations ---");
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 85);
        scores.put("Bob", 90);
        scores.put("Charlie", 78);
        
        System.out.println("Original scores: " + scores);
        
        // Add bonus to Alice
        scores.compute("Alice", (key, value) -> value + 10);
        System.out.println("After bonus to Alice: " + scores);
        
        // computeIfPresent - compute only if key exists
        scores.computeIfPresent("Bob", (key, value) -> value + 5);
        System.out.println("After bonus to Bob: " + scores);
        
        // computeIfAbsent - compute only if key doesn't exist
        scores.computeIfAbsent("David", key -> 80);
        System.out.println("After adding David: " + scores);
        
        // Nested HashMap (HashMap of HashMap)
        System.out.println("\n--- Nested HashMap ---");
        Map<String, Map<String, Integer>> studentGrades = new HashMap<>();
        
        Map<String, Integer> aliceGrades = new HashMap<>();
        aliceGrades.put("Math", 90);
        aliceGrades.put("Science", 85);
        aliceGrades.put("English", 88);
        
        Map<String, Integer> bobGrades = new HashMap<>();
        bobGrades.put("Math", 78);
        bobGrades.put("Science", 92);
        bobGrades.put("English", 80);
        
        studentGrades.put("Alice", aliceGrades);
        studentGrades.put("Bob", bobGrades);
        
        System.out.println("Student Grades: " + studentGrades);
        
        // Access nested values
        System.out.println("\nAlice's Math grade: " + studentGrades.get("Alice").get("Math"));
        System.out.println("Bob's Science grade: " + studentGrades.get("Bob").get("Science"));
        
        // Iterate nested HashMap
        System.out.println("\n--- All Student Grades ---");
        studentGrades.forEach((student, grades) -> {
            System.out.println(student + ":");
            grades.forEach((subject, grade) -> {
                System.out.println("  " + subject + ": " + grade);
            });
        });
        
        // Remove if condition matches
        System.out.println("\n--- Remove If ---");
        Map<Integer, String> items = new HashMap<>();
        items.put(1, "Apple");
        items.put(2, "Banana");
        items.put(3, "Cherry");
        items.put(4, "Date");
        
        System.out.println("Original items: " + items);
        
        // Remove entries where key is even
        items.entrySet().removeIf(entry -> entry.getKey() % 2 == 0);
        System.out.println("After removing even keys: " + items);
        
        // Replace all values
        System.out.println("\n--- Replace All ---");
        Map<String, Integer> prices = new HashMap<>();
        prices.put("Apple", 100);
        prices.put("Banana", 50);
        prices.put("Cherry", 150);
        
        System.out.println("Original prices: " + prices);
        
        // Apply 10% discount
        prices.replaceAll((key, value) -> (int)(value * 0.9));
        System.out.println("After 10% discount: " + prices);
    }
}
