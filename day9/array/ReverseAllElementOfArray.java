import java.util.Scanner;

public class ReverseAllElementOfArray {

    // Logic
    public static int reverse(int num) {
        int temp = num;

        int rev = 0;
        while (temp != 0) {
            int digit = temp%10;
            rev = rev*10 + digit;
            temp = temp/10;
        }

        return rev;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Size of The Array : ");
        int n = sc.nextInt();
        int[] arr =  new int[n];
        
        //  Input
        System.out.print("Enter Elements of the Array : ");
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
            arr[i] = reverse(arr[i]);
        }


        // Reverse 
        System.out.println("Reverse of Elements : ");
        for(int i = 0 ; i < n ; i++){
            System.out.print(arr[i] + " ");
        }
    }
}