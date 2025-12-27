import java.util.*;

public class MoveZerorsToEnd {

    public static void moveZeroes(int[] nums) {
        int j = 0; // pointer for placing non-zero elements

        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                // swap nums[i] and nums[j]
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] nums = new int[n];
        
        System.out.print("Enter array elements: ");
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }
        
        moveZeroes(nums);
        System.out.println("Output : " + Arrays.toString(nums));
        
        sc.close();
    }
}
