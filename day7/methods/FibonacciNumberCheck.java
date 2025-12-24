import java.util.*;

// Check Weather A number is in the Fibonacci Series or Not
public class FibonacciNumberCheck {
    
    public static boolean fiboCheck(int n){
        if(n == 0 || n == 1)    return true;
        
        int a  = 0;
        int b = 1;
        
        while(b < n){
            int temp = b;
            b = a + b;
            a = temp;
        }

        return  b == n;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a Number : ");
        int n = sc.nextInt();

        System.out.print(n + " : " + (fiboCheck(n) ? "Yes, Available" : "No, Not Available") + " in Fibonaaci Series");
    }
}
