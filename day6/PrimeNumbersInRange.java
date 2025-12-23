import java.util.Scanner;

public class PrimeNumbersInRange {

    public static boolean primeCheck(int n) {
        boolean bool = true;
        if (n == 0 || n == 1)
            bool = false;

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                bool = false;
                break;
            }
        }
        return bool;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a Number : ");
        int n = sc.nextInt();

        System.out.println("Prime Numbers In Range 1 to " + n + " : ");
        for(int i = 1 ; i <= n ; i++)   if(primeCheck(i))   System.out.println(i) ;
    }
}
