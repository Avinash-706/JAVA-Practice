package Practice;
// 2.Write a Java program using Stream API to:
// Store names in a list
// Convert all names to uppercase
// Display the result

import java.util.ArrayList;

public class ConvertToUpperCase {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        
        arr.add("Avinash");
        arr.add("BabalPreet");
        arr.add("Kabir");
        arr.add("Kshitiz");
        arr.add("Chamar");

        System.out.println("Names of Candidate IN Uppper Case : ");
        arr.stream().map(s -> s.toUpperCase()).forEach(System.out::println);
    }
}
