public class ThisKeyword {
    // global varaible or class variable

    public void test() {
        System.out.println("Test Start");
        System.out.println("This : "+ this);
        System.out.println("Test End");
    }

    public static void main(String[] args) {
        System.out.println("Main Start");
        ThisKeyword ref = new ThisKeyword();
        ref.test();
        System.out.println("Main End");

    }
}