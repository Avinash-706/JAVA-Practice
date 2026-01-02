public class NonStaticInsideStatic {
    static int a = 10;

    public void test() {
        System.out.println("Test Start");
        System.out.println("Directly" + a);
        System.out.println("With the help of ClassName : " + NonStaticInsideStatic.a);
        System.out.println("With the help This : " + this.a);
        System.out.println("Test End");
    }

    public static void main(String[] args) {    
        System.out.println("Main Start");
        NonStaticInsideStatic ref =  new NonStaticInsideStatic();
        ref.test();
        System.out.println("Main End");
    }
    
}
