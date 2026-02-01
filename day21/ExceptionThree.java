
import java.util.InputMismatchException;
import java.util.Scanner;

class ExceptionThree{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        try {
            System.out.print("Enter a Value: ");
            int a = sc.nextInt();

            System.out.print("Enter a Value: ");
            int b = sc.nextInt();
            int ans = a/b;
            System.out.println("Result: " + ans);

        } catch (ArithmeticException e) {
            // e.printStackTrace();
            System.out.println(e + ": Handled");
        } catch (InputMismatchException e) {
            // e.printStackTrace();
            System.out.println(e + ": Handled");
        } finally {
            sc.close();
            System.out.println("try-catch ends");
        }

        System.out.println("--------MAIN_END--------");
    }
}