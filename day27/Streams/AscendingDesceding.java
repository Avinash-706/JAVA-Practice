// 1. Write a Java Stream one-liner to sort Students objects by id in ascending order and print them.
// 2. Write a Java Stream one-liner to sort Students objects by id in descending order and print them.

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AscendingDesceding {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(100, 25, 30, 41, 15, 68 , 71, 88, 19 , 10);
        System.out.println("Original List : " + numbers);
        
        System.out.println("\nPrint all elements in ascending order: ");
        numbers.stream().sorted().forEach(System.out::println);
        
        System.out.println("\nPrint all elements in descending order: ");
        numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }
}