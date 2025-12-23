package day6.while_loop;
public class PrintNumberInRange {

    public static void printLoop1(int n1, int n2){
        while(n1 >= n2)     System.out.println(n1--);
    }

    public static void printLoop2(int n1, int n2) {
        while(n1 <= n2)     System.out.println(n1++);
    }
    public static void main(String[] args) {

        int n1 = -123, n2 = -129;
        System.out.println("Print :\t" + n1 + " to " + n2);
        printLoop1(n1, n2);

        n1 = 79; n2 = 84;
        System.out.println("Print :\t" + n1 + " to " + n2);
        printLoop2(n1, n2);

        n1 = 0; n2 = -9;
        System.out.println("Print :\t" + n1 + " to " + n2);
        printLoop1(n1, n2);

    }

}
