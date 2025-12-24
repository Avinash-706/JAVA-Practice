import java.util.*;

public class SumOfDigitOfNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number :  ");
        int digit = sc.nextInt();

        int temp = digit;
        int res = 0;
        while(temp != 0){
            res += temp % 10;
            temp = temp / 10;
        }

        System.out.print("Sum of Digits of " + digit + " :  " + res);
    }
}
