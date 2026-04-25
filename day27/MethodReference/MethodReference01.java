import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface Add{
    int add(int a, int b);
}

public class MethodReference01 {
    public static void main(String[] args) {

        //INTEGER EXAMPLE
        Add ref1 = (a, b) -> a+b;
        System.out.println("Using Lambda Exp : " + ref1.add(20, 10));

        Add ref2 = Integer :: sum;
        System.out.println("Using Method Reference : " + ref2.add(10, 20));


        // ARRAY LIST / FOREACH EXAMPLE
        List<String> names = Arrays.asList(
            "Avinash", "Devansh", "Girish", "Kshitiz", "Nitish"
        );

        System.out.println("\nNAMES :" + names);

        System.out.println("\nUsing Lambda Function :" );
        names.forEach(name -> System.out.println(name));

        System.out.println("\nUsing Method Reference :" );
        names.forEach(System.out::println);
    }
}
