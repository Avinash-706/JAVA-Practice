
import java.util.Scanner;

class NotEligibleException extends Exception {

    public NotEligibleException(String msg){
        System.out.println(msg);
    }
}

public class CustomException {

    public static void main(String[] args) throws NotEligibleException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your age: ");
        int age = sc.nextInt();
        eligibleForVoting(age);

    }

    public static void eligibleForVoting(int age)  throws NotEligibleException {
        if(age >= 18) {
            System.out.println("Eligible for voting");
        }
        else {
            throw new NotEligibleException("sxfdk");
        }
    }

}