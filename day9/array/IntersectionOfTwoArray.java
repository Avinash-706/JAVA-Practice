import java.util.Scanner;

public class IntersectionOfTwoArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Size of The Array 1 and 2: ");
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int[] arr1 =  new int[n1];
        int[] arr2 =  new int[n2];
        
        //  Input
        System.out.print("Enter Elements of the Array1 : ");
        for(int i = 0 ; i < n1 ; i++)   arr1[i] = sc.nextInt();

        System.out.print("Enter Elements of the Array2 : ");
        for(int i = 0 ; i < n2 ; i++)   arr2[i] = sc.nextInt();

        System.out.print("Union of Two Arrays : ");
        for(int i = 0 ; i < n1; i++){
            int x = arr1[i];
            for(int j = 0; j < n2 ; j++){
                if(x == arr2[j]){
                    System.out.print(x + " ");
                    break;
                }
            }
        }
    }
}
