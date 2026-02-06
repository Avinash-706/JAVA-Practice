import java.util.ArrayList;
import java.util.List;


public class ListOne {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();

        arr.add(10); /*
            * add(Integer) -> int -> Integer -> Object
            * Upcasting
            * Autoboxing is the automatic conversion of a primitive type into its corresponding wrapper class by the Java compiler.
        */

        // arr.add('a');

        System.out.println(arr);
    }
}
