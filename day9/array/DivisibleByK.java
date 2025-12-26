import java.util.Scanner;

public class DivisibleByK {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Size of The Array : ");
        int n = sc.nextInt();
        int[] arr =  new int[n];
        
        System.out.print("Enter the value of K : ");
        int k = sc.nextInt();
        
        //  Input & Validation
        int count = 0;
        System.out.print("Enter Elements of the Array : ");
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
            if(arr[i] % k == 0)     count++;
        }
        
        System.out.println("Count of Number which are divisible by " + k + " : " + count);
    }
}
