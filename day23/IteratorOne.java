import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorOne {
    public static void main(String[] args) {
        List<Integer> li = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> list = new ArrayList<>();
        List<Integer>  list2 = new List.of(1,2,3,4);

        Iterator<Integer> it = li.iterator();
 
        while (it.hasNext()) {
            int val = it.next();
            list.add(val);
            it.remove();
        }
        
        System.out.println(li);
        System.out.println(list);
        /**it.remove(); 
            * There is no element left in Array
            * So it will give IllegalStateException
        */
    }
}
