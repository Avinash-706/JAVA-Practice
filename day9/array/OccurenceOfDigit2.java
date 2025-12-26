public class OccurenceOfDigit2 {

    public static int countOccurence(int num, int k) {
        int count = 0;

        int temp = num;
        while(temp != 0){
            int digit = temp%10;
            if(digit == k)  count++;
            temp = temp/10;
        }

        return count;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] arr = {37, 823, 122, 2322, 6017};
        int k  = 2;

        int[] res = new int[5];
        int max_count = Integer.MIN_VALUE;
        int index = 0;

        System.out.println("Occurence of digit " + k +" in each number : ");
        for(int  i = 0 ; i < n ; i++){
            res[i] = countOccurence(arr[i], k);

            if(max_count <= res[i]){
                max_count = res[i];
                index = i;
            }

            System.out.println("Digit " + k +" in " + arr[i] + " = "+ res[i]);
        }

        System.out.println("Element Having Maximum Digit "+ k + " in itself : " + arr[index] );
        
    }
    
}
