public class MaximumVal {
    public static void main(String[] args) {
        int a = 10, b = 15, c = 11;

        System.out.print("Maximum Number : " + a + " , " + b + " , " + c + " : ");
        //max between 3 numbers
        if(a > b && a > c)  System.out.println(a);
        else if(b > c)      System.out.println(b);
        else                System.out.println(c);
    }
}