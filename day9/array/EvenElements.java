import java.util.Scanner;

public class EvenElements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the Array : ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.print("Enter Number of The Array : ");
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Even Number In The Array are : ");
        for(int i = 0 ; i < n ; i++){
            if(arr[i] % 2 == 0)     System.out.print(arr[i] + "  ");
        }
    }    
}
