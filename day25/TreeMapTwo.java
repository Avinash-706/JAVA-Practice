import java.util.TreeMap;
import java.util.Map;
import java.util.Comparator;

public class TreeMapTwo {
    public static void main(String[] args) {
        
        // TreeMap with String keys (Alphabetical order)
        System.out.println("--- TreeMap with String Keys ---");
        TreeMap<String, Integer> scores = new TreeMap<>();
        
        scores.put("John", 85);
        scores.put("Alice", 92);
        scores.put("Bob", 78);
        scores.put("Charlie", 88);
        scores.put("David", 95);
        
        System.out.println("Scores (Alphabetical): " + scores);
        // {Alice=92, Bob=78, Charlie=88, David=95, John=85}
        
        // NavigableMap methods
        System.out.println("\n--- NavigableMap Methods ---");
        TreeMap<Integer, String> tm = new TreeMap<>();
        tm.put(10, "Ten");
        tm.put(20, "Twenty");
        tm.put(30, "Thirty");
        tm.put(40, "Forty");
        tm.put(50, "Fifty");
        tm.put(60, "Sixty");
        tm.put(70, "Seventy");
        
        System.out.println("Original TreeMap: " + tm);
        
        // lowerKey - greatest key less than given key
        System.out.println("\nlowerKey(40): " + tm.lowerKey(40));     // 30
        
        // floorKey - greatest key less than or equal to given key
        System.out.println("floorKey(40): " + tm.floorKey(40));       // 40
        
        // ceilingKey - least key greater than or equal to given key
        System.out.println("ceilingKey(40): " + tm.ceilingKey(40));   // 40
        
        // higherKey - least key greater than given key
        System.out.println("higherKey(40): " + tm.higherKey(40));     // 50
        
        // lowerEntry, floorEntry, ceilingEntry, higherEntry
        System.out.println("\nlowerEntry(40): " + tm.lowerEntry(40));
        System.out.println("higherEntry(40): " + tm.higherEntry(40));
        
        // Subset operations
        System.out.println("\n--- Subset Operations ---");
        
        // headMap - entries with keys less than specified
        System.out.println("headMap(40): " + tm.headMap(40));
        // {10=Ten, 20=Twenty, 30=Thirty}
        
        // tailMap - entries with keys greater than or equal to specified
        System.out.println("tailMap(40): " + tm.tailMap(40));
        // {40=Forty, 50=Fifty, 60=Sixty, 70=Seventy}
        
        // subMap - entries with keys in range [from, to)
        System.out.println("subMap(20, 60): " + tm.subMap(20, 60));
        // {20=Twenty, 30=Thirty, 40=Forty, 50=Fifty}
        
        // Descending order
        System.out.println("\n--- Descending Order ---");
        System.out.println("descendingMap(): " + tm.descendingMap());
        // {70=Seventy, 60=Sixty, 50=Fifty, 40=Forty, 30=Thirty, 20=Twenty, 10=Ten}
        
        System.out.println("descendingKeySet(): " + tm.descendingKeySet());
        // [70, 60, 50, 40, 30, 20, 10]
        
        // Custom Comparator - Reverse order
        System.out.println("\n--- Custom Comparator (Reverse Order) ---");
        TreeMap<Integer, String> reverseMap = new TreeMap<>(Comparator.reverseOrder());
        
        reverseMap.put(10, "Ten");
        reverseMap.put(20, "Twenty");
        reverseMap.put(30, "Thirty");
        reverseMap.put(40, "Forty");
        reverseMap.put(50, "Fifty");
        
        System.out.println("Reverse order TreeMap: " + reverseMap);
        // {50=Fifty, 40=Forty, 30=Thirty, 20=Twenty, 10=Ten}
        
        // Custom Comparator - String by length
        System.out.println("\n--- Custom Comparator (String by length) ---");
        TreeMap<String, Integer> lengthMap = new TreeMap<>((a, b) -> {
            if(a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        });
        
        lengthMap.put("Java", 1);
        lengthMap.put("Python", 2);
        lengthMap.put("C", 3);
        lengthMap.put("JavaScript", 4);
        lengthMap.put("Go", 5);
        lengthMap.put("Ruby", 6);
        
        System.out.println("Sorted by key length: " + lengthMap);
        // {C=3, Go=5, Java=1, Ruby=6, Python=2, JavaScript=4}
        
        // Practical Example: Leaderboard
        System.out.println("\n--- Leaderboard (Sorted by Score) ---");
        TreeMap<Integer, String> leaderboard = new TreeMap<>(Comparator.reverseOrder());
        
        leaderboard.put(1500, "Alice");
        leaderboard.put(1200, "Bob");
        leaderboard.put(1800, "Charlie");
        leaderboard.put(1000, "David");
        leaderboard.put(1600, "Eve");
        
        System.out.println("Leaderboard (High to Low):");
        leaderboard.forEach((score, name) -> {
            System.out.println(name + ": " + score);
        });
        
        // Practical Example: Event Timeline
        System.out.println("\n--- Event Timeline (Sorted by Time) ---");
        TreeMap<Integer, String> timeline = new TreeMap<>();
        
        timeline.put(900, "Meeting Start");
        timeline.put(1000, "Coffee Break");
        timeline.put(1030, "Presentation");
        timeline.put(1200, "Lunch");
        timeline.put(1300, "Workshop");
        timeline.put(1500, "Meeting End");
        
        System.out.println("Timeline:");
        timeline.forEach((time, event) -> {
            int hours = time / 100;
            int minutes = time % 100;
            System.out.printf("%02d:%02d - %s%n", hours, minutes, event);
        });
        
        // Range Queries
        System.out.println("\n--- Range Queries ---");
        TreeMap<Integer, String> data = new TreeMap<>();
        data.put(10, "A");
        data.put(20, "B");
        data.put(30, "C");
        data.put(40, "D");
        data.put(50, "E");
        data.put(60, "F");
        
        System.out.println("Original: " + data);
        
        // Get entries between 20 and 50 (inclusive)
        System.out.println("Entries from 20 to 50: " + data.subMap(20, true, 50, true));
        
        // Get entries less than 40
        System.out.println("Entries less than 40: " + data.headMap(40, false));
        
        // Get entries greater than or equal to 30
        System.out.println("Entries >= 30: " + data.tailMap(30, true));
    }
}
