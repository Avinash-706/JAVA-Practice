public class DemoClass {

    // -------- STATIC INITIALIZATION BLOCK 1 --------
    static {
        System.out.println("Static Initialization Block 1 executed");
    }

    // -------- STATIC INITIALIZATION BLOCK 2 --------
    static {
        System.out.println("Static Initialization Block 2 executed");
    }

    // -------- INSTANCE INITIALIZATION BLOCK --------
    {
        System.out.println("Instance Initialization Block executed");
    }

    // -------- CONSTRUCTOR --------
    public DemoClass() {
        System.out.println("Constructor executed");
    }

    // -------- NON-STATIC METHOD --------
    public void displayMessage() {
        System.out.println("Non-static method displayMessage() called");
    }

    // -------- STATIC METHOD ONE --------
    public static void staticMethodOne() {
        System.out.println("Static Method One called");
    }

    // -------- STATIC METHOD TWO --------
    public static void staticMethodTwo() {
        System.out.println("Static Method Two called");
    }


    public static void main(String[] args) {

        // Calling static methods using class name
        DemoClass.staticMethodOne();
        DemoClass.staticMethodTwo();

        // Creating object (triggers SIBs, IIB, and constructor)
        DemoClass obj = new DemoClass();

        // Calling non-static method
        obj.displayMessage();
    }
}