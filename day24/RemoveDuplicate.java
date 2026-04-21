
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicate {
    public static void main(String[] args) {
        List<Integer> list =  new ArrayList<>(Arrays.asList(10, 12, 13, 12, 14,14, null, 15, 11, 10, 16, null, 10));
        System.out.println("Original List: " + list);

        List<Integer> uniqueList = new ArrayList<>();

        Set<Integer> hs = new HashSet<>();
        for(Integer i:list){
            if(hs.add(i)) {
                uniqueList.add(i);
            }
        }

        System.out.println("Without Duplicate: " + uniqueList);
    }
}
