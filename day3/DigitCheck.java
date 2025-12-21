import java.util.Scanner;

class DigitCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a character: ");
        char ch = sc.next().charAt(0);

        if (ch >= '0' && ch <= '9')     System.out.println(ch + " is a DIGIT");
        else                            System.out.println(ch + " is NOT a DIGIT");

        sc.close();
    }
}
