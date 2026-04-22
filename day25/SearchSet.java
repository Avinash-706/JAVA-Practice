import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchSet {

    public static void main(String[] args) {
        List<Integer> list =  new ArrayList<>(Arrays.asList(10, 22, 23, 12, 14,14, null, 25, 11, 20, 16, null, 10));
        Set<Integer> set = new HashSet<>(list);
        
        int num = 10;
        System.out.println(set.contains(num) ? "Present" : "Absent");
    }
}
