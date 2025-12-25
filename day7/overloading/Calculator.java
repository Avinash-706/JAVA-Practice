public class Calculator {

    public static int add(int a, int b)                         {return a + b ;}
    public static int add(int a, int b, int c)                  {return a + b + c ;}
    public static int add(int a, int b, int c, int d)           {return a + b + c + d ;}
    public static int add(int a, int b, int c, int d, int e)    {return a + b + c + d + e ;}

    public static int subtract(int a, int b)                         {return a - b ;}
    public static int subtract(int a, int b, int c)                  {return a - b - c ;}
    public static int subtract(int a, int b, int c, int d)           {return a - b - c - d ;}
    public static int subtract(int a, int b, int c, int d, int e)    {return a - b - c - d - e ;}

    public static int multiply(int a, int b)                         {return a * b ;}
    public static int multiply(int a, int b, int c)                  {return a * b * c ;}
    public static int multiply(int a, int b, int c, int d)           {return a * b * c * d ;}
    public static int multiply(int a, int b, int c, int d, int e)    {return a * b * c * d * e ;}

    public static int divide(int a, int b)                           {return a / b;}


    public static void main(String[] args) {

        System.out.println("\nAdd 2 Numbers :\t" +  add(10, 20));
        System.out.println("Add 3 Numbers :\t" +    add(10, 20, 30));
        System.out.println("Add 4 Numbers :\t" +    add(10, 20, 30, 40));
        System.out.println("Add 5 Numbers :\t" +    add(10, 20, 30, 40, 50));

        System.out.println("\nSubtract 2 Numbers :\t" + subtract(10, 3));
        System.out.println("Subtract 3 Numbers :\t" +   subtract(10, 3, 4));
        System.out.println("Subtract 4 Numbers :\t" +   subtract(10, 3, 4, 2));
        System.out.println("Subtract 5 Numbers :\t" +   subtract(10, 3, 4, 2, 1));

        System.out.println("\nMultiply 2 Numbers :\t" + multiply(2, 3));
        System.out.println("Multiply 3 Numbers :\t" +   multiply(2, 3, 4));
        System.out.println("Multiply 4 Numbers :\t" +   multiply(2, 3, 4, 5));
        System.out.println("Multiply 5 Numbers :\t" +   multiply(2, 3, 4, 5, 6));

        System.out.println("\nDivide 2 Numbers   :\t" + divide(4, 2));
    }
}
