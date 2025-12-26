public class SumOfAllNumber1to5 {
    public static int printSum(int n, int sum) {
        if(n > 5)  return sum;
        
        sum += n;
        return printSum(++n, sum);
    }
    public static void main(String[] args) {
        System.out.println(printSum(1, 0));
    }   
}