import java.util.*;

public class DifferenceOfSets {
    public static void main(String[] args) {
        Set<Integer> setA = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> setB = new HashSet<>(Arrays.asList(3, 4));

        Set<Integer> difference = new HashSet<>(setA);
        difference.removeAll(setB);

        System.out.println(difference);
    }
}
