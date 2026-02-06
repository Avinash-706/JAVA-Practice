import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Write a Java program that performs the following using an ArrayList of integers:
- Add the following numbers to the list: 10, 5, 20, 15, 25.
- Insert the number 12 at index 2.
- Print all elements of the list.
- Check if the number 15 exists in the list and print an appropriate message.
- Sort the list in ascending order.
- Remove the number 5 from the list.
- Print the final list and its size.
 */
public class ListTwo {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();

        arr.addAll(Arrays.asList(10, 5, 20, 15, 25));

        arr.add(2, 12);


        System.out.println(arr);

        System.out.println("Number 15 exists: " + arr.contains(15));

        Collections.sort(arr);

        arr.remove(Integer.valueOf(5));

        System.out.println(arr);


    }
}
