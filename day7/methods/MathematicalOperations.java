import java.util.Scanner;

public class MathematicalOperations {

    public static int findMax(int a, int b, int c){
        return Math.max(Math.max(a, b), c);
    }

    public static int findMin(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }

    public static boolean isPrime(int n) {
        if(n == 0 || n == 1)    return  false;

        for(int i = 2 ; i <= n/2 ; i++){
            if(n % i == 0)      return  false;
        }

        return  true;
    }

    public static int fibo(int n) {
        if(n <= 1)  return n;
        return fibo(n-1) + fibo(n-2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Input a : ");
        int a = sc.nextInt();
        System.out.print("Enter Input b : ");
        int b = sc.nextInt();
        System.out.print("Enter Input c : ");
        int c = sc.nextInt();

        System.out.println("------------------------------------");    
        System.out.println("Maximum Value : " + findMax(a, b, c));
        System.out.println("Minimum Value : " + findMin(a, b, c));
        System.out.println("------------------------------------");

        System.out.print("Enter Input n : ");
        int n = sc.nextInt();
        
        System.out.println("Is Prime Number ? : " + isPrime(n));
        System.out.println(n + "-th Fibonaaci Number : " + fibo(n));
    }
}
