import java.util.List;
import java.util.Arrays;

class Students {
    int id;
    String name;
    double marks;

    Students(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + marks;
    }
}

public class ForEach {
    public static void main(String[] args) {

        List<String> names = Arrays.asList(
            "Avinash", "Devansh", "Girish", "Kshitiz", "Nitish"
        );

        System.out.println("\nNAMES:");
        names.forEach(name -> System.out.println(name));

        List<Integer> numbers = Arrays.asList(10, 21, 24, 32, 23, 14, 15);
        System.out.println("\nNUMBERS:");
        numbers.forEach(num -> System.out.println(num));

        List<Students> st = List.of(
            new Students(3, "Mark", 75.5),
            new Students(4, "Alen", 85.5),
            new Students(1, "Vishnu", 80.5),
            new Students(2, "Pramod", 95.5)
        );

        System.out.println("\nSTUDENTS:");
        st.forEach(student -> System.out.println(student));
    }
}
