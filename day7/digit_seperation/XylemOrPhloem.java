import java.util.*;

public class XylemOrPhloem {

    public static boolean check(int n){
        int temp  = n;
        
        int outer_sum = 0;
        int inner_sum = 0;

        while(temp != 0){
            int digit = temp % 10;
            
            if(temp == n || temp < 10)  outer_sum += digit;
            else            inner_sum += digit;

            temp = temp/10;
        }

        return (inner_sum  == outer_sum);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a Number : ");
        int n = sc.nextInt();

        System.out.println(n + ((check(n) ? " : Xylem Number" : " : Phloem Number")));
    } 
}
