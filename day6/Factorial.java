import java.util.Scanner;

public class Factorial {

    public static int printFactorial(int n) {
        if(n <= 1)  return 1;
        int res = 1;
        
        for(int i = n ; i > 1; i--)     res *= i;
        return  res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number : ");
        int n = sc.nextInt();

        System.out.println("Factorial of " + n + " : " + printFactorial(n));
    }
}
