public class MergeTwoArray {
    public static void main(String[] args) {
        
        int n1 = 4;
        int[] a = {10,20,30,40};
        
        int n2 = 5;
        int[] b = {1 ,2 ,3 ,4 ,5 };

        int[] res = new int[n1 + n2];
        
        for(int i = 0 ; i < (n1 + n2) ; i++){
            if(i < n1)       res[i] = a[i];
            else             res[i] = b[i%n1];
        }
        
        System.out.println("Merged Array : ");
        for(int i = 0 ; i < n1 + n2 ; i++)  System.out.print(res[i] + " ");
    }   
}
