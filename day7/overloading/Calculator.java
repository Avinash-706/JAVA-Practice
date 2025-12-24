public class Calculator {
    public static int add3(int a, int b, int c) {
        return a + b + c;
    }

    public static int multiply3(int a, int b, int c) {
        return a * b * c;
    }

    public static int subtract4(int a, int b, int c, int d) {
        return a - b - c - d;
    }

    public static int add2(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println("Add 3 Numbers :\t" + add3(10, 20, 30));
        System.out.println("Multiply 3 Numbers :\t" + multiply3(2, 3, 4));
        System.out.println("Subtract 4 Numbers :\t" + subtract4(10, 3, 4, 2));
        System.out.println("Add 2 Numbers :\t" + add2(10, 20));
    }
    
}
