import java.util.*;
public class SecondSmallestDigit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number :  ");
        int n = sc.nextInt();

        int small = 10;
        int second = 10;
        int temp = n;
        
        while (temp != 0) {
            int digit = temp % 10;

            if(digit < small){
                second =  small;
                small = digit;
            }
            else if(digit < second && digit >= small)   second = digit;

            temp = temp / 10;
        }

        System.out.println("Smallest Digits of " + n + " :  " + small);
        System.out.print("Second Smallest Digits of " + n + " :  " + second);
    }
}