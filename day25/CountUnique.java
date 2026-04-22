

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountUnique {
    public static void main(String[] args) {
        List<Integer> list =  new ArrayList<>(Arrays.asList(10, 12, 13, 12, 14,14, null, 15, 11, 10, 16, null, 10));
        System.out.println("Original List: " + list);

        int count = 0;

        Set<Integer> hs = new HashSet<>();
        for(Integer i:list){
            if(hs.add(i)) {
                count++;
            }
        }

        System.out.println("Unique Count: " + count);
    }
}
