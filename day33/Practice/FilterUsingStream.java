package Practice;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class FilterUsingStream{
    public static void main(String[] args){
        List<Integer> l = List.of(1,2,3,4,5,6,7,8,9,10);

        System.out.println("Print Even Number of the List : ");
        l.stream().filter(n -> n%2 ==0).forEach(System.out::println);
    } 
}