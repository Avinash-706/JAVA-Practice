import java.util.*;

class Fibonacci {
    static void fib(int n, int a, int b) {
        if (n-- == 0)   return;     //base condition

        System.out.print(a + " ");
        fib(n, b, a + b);
    }

    public static void main(String[] args) {
        System.out.print("Enter Number : ");
        fib(new Scanner(System.in).nextInt(), 0, 1);
    }
}