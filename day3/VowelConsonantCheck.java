import java.util.Scanner;

class VowelConsonantCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a character: ");
        char ch = sc.next().charAt(0);

        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U')
                System.out.println(ch + " is a VOWEL");
            else
                System.out.println(ch + " is a CONSONANT");
        }
        
        else  System.out.println("Invalid input (not an alphabet)");

        sc.close();
    }
}
