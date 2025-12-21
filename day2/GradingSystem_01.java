import java.util.*;

public class GradingSystem_01 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Marks : ");
        int marks = sc.nextInt();
        
        // Grading System Using If Else Statements
        if   (marks >= 90 && marks <= 100)  System.out.print("Grade A");
        else if(marks >= 75 && marks < 90)  System.out.print("Grade B");
        else if(marks >= 60 && marks < 75)  System.out.print("Grade C");
        else if(marks >= 40 && marks < 60)  System.out.print("Grade D");
        else if(marks <  40 && marks >= 0)  System.out.print("FAIL!!");
        else                                System.out.print("Invalid Marks!!");
    }
}
