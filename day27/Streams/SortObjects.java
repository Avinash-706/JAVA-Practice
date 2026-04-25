// 1. Write a Java Stream one-liner to sort Students objects based on name and display the result.
// 2. Write a Java Stream one-liner to sort Students objects by marks and print them.
// 3. ⁠Write a Java Stream one-liner to add 5 grace marks to each student and print the updated marks.

import java.util.Comparator;
import java.util.List;

class Students {
    int id;
    String name;
    double marks;

    Students(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
}

public class SortObjects {
    public static void main(String[] args) {
        List<Students> st = List.of(
            new Students(1, "Pawan", 85.67),
            new Students(2, "Rohit", 76.58),
            new Students(3, "Aditya", 78.9),
            new Students(4, "Pramod", 90.5)
        );

        System.out.println("\nSorted Accoding to Name: ");
        st.stream().sorted(Comparator.comparing(s -> s.name)).forEach(s -> System.out.println(s.name));

        System.out.println("\nSorted Accoding to Marks: ");
        st.stream().sorted(Comparator.comparing(s -> s.marks)).forEach(s -> System.out.println(s.marks));

        System.out.println("\nPrint Marks by adding 5 Grace Marks: ");
        st.stream().map(s -> s.marks + 5).forEach(s -> System.out.println(s));
    }
}