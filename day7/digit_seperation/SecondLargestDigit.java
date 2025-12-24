import java.util.*;
public class SecondLargestDigit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number :  ");
        int n = sc.nextInt();

        int largest = 0;
        int second_largest = 0;
        int temp = n;
        
        while (temp != 0) {
            int digit = temp % 10;

            if(digit > largest){
                second_largest =  largest;
                largest = digit;
            }
            else if(digit > second_largest && digit <= largest)   second_largest = digit;

            temp = temp / 10;
        }

        System.out.println("Largest Digits of " + n + " :  " + largest);
        System.out.print("Second Largest Digits of " + n + " :  " + second_largest);
    }
}