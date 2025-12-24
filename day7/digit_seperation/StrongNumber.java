import java.util.Scanner;

//  Strong Number
public class StrongNumber {
    public static int printFactorial(int n) {
        if (n <= 1)     return 1;
        int res = 1;

        for (int i = n; i > 1; i--)     res *= i;
        return  res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Number : ");
        int n = sc.nextInt();

        int temp = n;
        int res = 0;

        while (temp != 0) {
            int digit = temp % 10;
            res += printFactorial(digit);
            temp = temp / 10;
        }

        System.out.println( "Is " + n + " a Strong Number  ?  : " + ((res == n) ? "Yes" : " No"));
    }
}
