
public class FactorialOfOddNumber {
    public static int printFactorial(int n) {
        if (n <= 1)
            return 1;
        int res = 1;

        for (int i = n; i > 1; i--)
            res *= i;
        return res;
    }

    public static void main(String[] args) {
        for(int i = 10 ; i >= 1 ; i--){
            if(i % 2 != 0)  System.out.println("Factorial of " + i + " : " + printFactorial(i));
        }
    }
}
