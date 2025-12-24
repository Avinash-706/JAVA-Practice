import java.util.Scanner;

public class DisariumNumber {

    public static int countDigits(int n) {
        int temp = n;
        int count = 0;

        while (temp != 0) {
            count++;
            temp = temp / 10;
        }

        return count;
    }

    public static int powerSum(int n, int count) {
        int temp = n;
        int c = count;

        int sum = 0;
        while (temp != 0) {
            int digit = temp % 10;
            sum  +=  Math.pow(digit, c);
            c--;
            temp = temp / 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int count = countDigits(n);
        
        System.out.println("Sum of Power of Digits : " + powerSum(n, count));
    }
}