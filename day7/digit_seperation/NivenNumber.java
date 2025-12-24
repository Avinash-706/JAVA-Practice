
import java.util.Scanner;

public class NivenNumber {

    public static boolean checkNiven(int n) {
        int temp = n;
        
        int sum = 0;
        while(temp != 0){
            sum += temp % 10;
            temp = temp / 10;
        }

        return  (n%sum == 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Number : ");
        int n = sc.nextInt();

        System.out.println(n + (checkNiven(n) ? " is " : " is Not ") + "a Niven Number");
    }
}
