
public class PrintNumber_DoWhile {

    public static void printLoop1(int n1, int n2) {
        System.out.println("\n\nPrint :\t" + n1 + " to " + n2);
        do{
            System.out.print(n1++ + "\t");
        }
        while(n1 <= n2);  
    }
    
    public static void printLoop2(int n1, int n2) {
        System.out.println("\n\nPrint :\t" + n1 + " to " + n2);
        do{
            System.out.print(n1-- + "\t");
        }
        while(n1 >= n2);  
    }

    public static void main(String[] args) {

        printLoop1(-111,-101);
        printLoop2(123 , 113);
        printLoop2( 5  ,  -5);
        printLoop1(-10, 0);
    }
}
