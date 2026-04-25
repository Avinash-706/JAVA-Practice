import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

public class Stream01 {
    public static void main(String[] args) {
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6 , 7, 8, 9 , 10);
        System.out.println("To Print All Elements one by one");
        numbers.stream().forEach(System.out::println);


        System.out.println("\nTo Print only Even Numbers: ");
        numbers.stream().filter(n -> n%2 == 0).forEach(System.out::println);


        System.out.println("\nTo print odd number with extra value 3: ");
        numbers.stream().filter(n -> n%2 != 0).map(n -> n+3).forEach(System.out::println);
       
        
        System.out.println("\nAdd 3 in each value and print odd number: ");
        numbers.stream().map(n -> n + 3).filter(n -> n%2 != 0).forEach(System.out::println);


        System.out.println("\nFind Even numbers and stored in another list");
        List<Integer> even_collector = numbers.stream().filter(n -> n%2 == 0).collect(Collectors.toList());
        System.out.println(even_collector);


        List<String> names = List.of(
            "Henry", "Avinash", "Devansh", "Mark", "Kshitiz", "Nitish"
        );
        System.out.println("\n Print Objects end with 'sh' using Stream: ");
        names.stream().filter(name -> name.endsWith("sh")).forEach(System.out::println);


        List<Students> st = List.of(
            new Students(1, "Pawan", 85.67),
            new Students(2, "Rohit", 76.58),
            new Students(3, "Aditya", 78.9),
            new Students(4, "Pramod", 90.5)
        );
        System.out.println("\nPrint only those who got marks more than 80 using stream: ");
        st.stream().filter(s -> s.marks > 80.0).forEach(s -> System.out.println(s.name));
    }
}