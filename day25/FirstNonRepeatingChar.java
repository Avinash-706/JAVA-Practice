import java.util.*;

public class FirstNonRepeatingChar {
    public static void main(String[] args) {
        String s = "swiss";

        Set<Character> unique = new LinkedHashSet<>();
        Set<Character> repeated = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (!repeated.contains(c)) {
                if (!unique.add(c)) {
                    unique.remove(c);
                    repeated.add(c);
                }
            }
        }

        if (!unique.isEmpty()) {
            System.out.println("First Non-Repeating Character: " + unique.iterator().next());
        } else {
            System.out.println("No non-repeating character");
        }
    }
}
