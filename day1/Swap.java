class Swap{
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        System.out.println("Before : \n" + "a : " + a + "\nb : " + b);
        System.out.println("SWAPPING....");
        int c = a;
        a = b;
        b = c;
        System.out.println("After  : \n" + "a : " + a + "\nb : " + b);
    }
}