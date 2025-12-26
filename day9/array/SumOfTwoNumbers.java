public class SumOfTwoNumbers {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int n = 4;
        int target = 9;
        
        for(int i = 0 ; i < n ; i++){
            int check_sum = nums[i];

            int[] res = new int[2];
            res[0] = i;

            for(int j = i+1 ; j < n ; j++){
                if(check_sum + nums[j] == target){
                    res[1] = j;
                    check_sum += nums[j];
                    break;
                }
            }

            if(check_sum == target){
                System.out.println("Index are :\t[" + res[0] + " , " + res[1] + "]");
                break;
            }
        }
    }
}
