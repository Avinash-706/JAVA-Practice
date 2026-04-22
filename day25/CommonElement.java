import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonElement {
    public static void main(String[] args) {
        List<Integer> l1 =  new ArrayList<>(Arrays.asList(10, 12, 13, 12, 14,14, null, 15, 11, 10, 16, null, 10));
        List<Integer> l2 =  new ArrayList<>(Arrays.asList(10, 22, 23, 12, 14,14, null, 25, 11, 20, 16, null, 10));

        Set<Integer> set = new HashSet<>();

        for(Integer i : l1) {
            set.add(i);
        }

        for(Integer i : l2) {
            if(!set.add(i)) {
                System.out.print(i + " ");
            }
        }
    }
}
