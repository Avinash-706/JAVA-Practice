package Practice;
// 2.Write a Java program to:
// Store integers in an ArrayList
// Count how many times a given number appears in the list

import java.util.ArrayList;
import java.util.Collections;

public class FrequencyOfNumber {
    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(10);
        numbers.add(30);
        numbers.add(10);
        numbers.add(20);

        int target = 10;
        int count = Collections.frequency(numbers, target);

        System.out.println("Number " + target + " appears " + count + " times");
    }
}