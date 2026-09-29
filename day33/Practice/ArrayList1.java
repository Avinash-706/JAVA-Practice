// 1.Write a Java program to:
// Create an ArrayList of student names
// Add at least 5 names
// Display all names using a loop

import java.util.ArrayList;
import java.util.List;

public class ArrayList1{
    public static void main(String[] args) {  
        ArrayList<String> arr = new ArrayList<>();
        
        arr.add("Avinash");
        arr.add("BabalPreet");
        arr.add("Kabir");
        arr.add("Kshitiz");
        arr.add("Chamar");

        System.out.println("Names Using For Loop : ");
        for(String s : arr) System.out.println(s);
    }
}