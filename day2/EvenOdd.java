import java.util.Scanner;

class EvenOdd{
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Input    : ");
        int a = sc.nextInt();
        
        // ternary operator
        System.out.println("Given Number " + a + " : " + ((a%2 == 0) ? "Even" : "Odd"));

        // by only using if
        if(a % 2 == 0)  System.out.println("Given Number " + a + " : " + "Even");
        if(a % 2 != 0)  System.out.println("Given Number " + a + " : " + "Odd");
    }
}