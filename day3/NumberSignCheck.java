import java.util.Scanner;

class NumberSignCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        if (n > 0)      System.out.println(n + " is a POSITIVE number");
        else if (n < 0) System.out.println(n + " is a NEGATIVE number");
        else            System.out.println("The number is ZERO");

        sc.close();
    }
}
