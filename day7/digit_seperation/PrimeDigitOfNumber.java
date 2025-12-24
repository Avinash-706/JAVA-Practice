import java.util.*;

public class PrimeDigitOfNumber {
    public static boolean primeCheck(int n) {
        if(n == 0 || n == 1)  return false;

        for(int i = 2 ; i < n ; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number :  ");
        int digit = sc.nextInt();

        System.out.print("Prime Digits of " + digit + " :  ");
        while(digit != 0){
            int temp = digit%10;
            if(primeCheck(temp))  System.out.print(temp + " ");
            digit = digit / 10;
        }
    }
    
}
