import java.util.*;

public class FirstRepeatingElement {
    public static void main(String[] args) {
        int[] arr = {10, 5, 3, 4, 3, 5, 6};

        Set<Integer> seen = new HashSet<>();

        for (int num : arr) {
            if (!seen.add(num)) {
                System.out.println("First Repeating Element: " + num);
                return;
            }
        }

        System.out.println("No repeating element");
    }
}
