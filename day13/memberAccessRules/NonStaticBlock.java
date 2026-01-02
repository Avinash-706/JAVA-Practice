public class NonStaticBlock {
    {
        // non static block or instance intializer block
        System.out.println("--- New Object created for NonStaticBlock class ---");
    }

    public static void main(String[] args) {
        System.out.println("Main Start");
        NonStaticBlock ref1 = new NonStaticBlock();
        NonStaticBlock ref2 = new NonStaticBlock();
        NonStaticBlock ref3 = new NonStaticBlock();

        System.out.println("Main End");
    }
}
