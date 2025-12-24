import java.util.Scanner;

// analyze a number in 4 paramters
public class AnalyzeNumber {

    public static int reverse(int num) {
        int temp = num;
        int rev = 0;

        while(temp != 0){
            int digit = temp%10;
            rev = rev*10 + digit;
            temp = temp/10;
        }

        return rev;
    }

    public static boolean palindromeCheck(int num, int rev) {
        return num == rev;
    }

    public static int printFactorial(int n) {
        if (n <= 1)     return 1;
        int res = 1;

        for (int i = n; i > 1; i--)     res *= i;
        return  res;
    }

    public static boolean isStrongNumber(int num) {
        int temp = num;
        int res = 0;

        while (temp != 0) {
            int digit = temp % 10;
            res += printFactorial(digit);
            temp = temp / 10;
        }
        return res == num;
    }

    public static boolean primeCheck(int digit) {
        if(digit == 0 || digit == 1)    return    false;

        for(int i = 2 ; i < digit ; i++){
            if(digit % i == 0)  return   false;
        }
        return  true;
    }

    public static int countPrimeDigits(int n) {
        int count = 0;
        int temp = n;

        while(temp != 0){
            int digit = temp % 10;
            if(primeCheck(digit))   count++;
            temp = temp / 10;
        }

        return  count;
    }

    public static void printAnalysis(int rev, boolean check_palindrome, boolean check_strong, int count_prime_digits) {
        System.out.println("--\tPrinting the Analysis\t--");
        System.out.println("Reverse of the Number : " + rev);
        System.out.println("Is the Number Palindrome ? " + check_palindrome);
        System.out.println("Is it a Strong Number    ? " + check_palindrome);
        System.out.println("Total Number of Prime Digits in it : " + count_prime_digits);
    }

    public static void operations(int num){
        int rev = reverse(num);
        boolean check_palindrome =  palindromeCheck(num, rev); 
        boolean check_strong = isStrongNumber(num);
        int count_prime_digits = countPrimeDigits(num);
        printAnalysis(rev, check_palindrome, check_strong, count_prime_digits);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a Number : ");
        int num = sc.nextInt();

        operations(num);
    }
}