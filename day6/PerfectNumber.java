import java.util.Scanner;

public class PerfectNumber {

    public static boolean perfectCheck(int n) {
        if(n <= 1)      return false;

        int res = 1;
        for(int i = 2 ; i < n ; i++)   if(n % i == 0)  res += i;

        return  res == n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a Number : ");
        int n = sc.nextInt();

        System.out.println(n + " : " + (perfectCheck(n) ? " Perfect Number " : " NOT a Perfect Number"));
    }
}