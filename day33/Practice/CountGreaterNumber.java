package Practice;
import java.util.List;

public class CountGreaterNumber {
    public static void main(String[] args) {
        List<Integer> l = List.of(50,2,355,4,55,16,87,8,19,100);

        System.out.println("Count of Number Greater than 50 : ");   
        System.out.println(l.stream().filter(n -> n > 50).count());
    }
} 
