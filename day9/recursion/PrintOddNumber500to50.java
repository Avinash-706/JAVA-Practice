public class PrintOddNumber500to50 {
    
    public static void printNumber(int num) {
        if(num < 50)        return  ;
        if(num % 2 != 0)    System.out.println(num );
        
        printNumber(num - 1);
    }

    public static void main(String[] args){
        printNumber(500);
    }
}