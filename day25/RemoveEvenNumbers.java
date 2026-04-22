import java.util.*;

public class RemoveEvenNumbers {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) {
                it.remove();
            }
        }

        System.out.println(set);
    }
}
