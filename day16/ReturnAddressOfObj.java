public class ReturnAddressOfObj{

    static{
        System.out.println("Hello World");
    }

    public static ReturnAddressOfObj print() {
        return new ReturnAddressOfObj();
    }

    public static void main(String[] args) {
        System.out.println(print());

        ReturnAddressOfObj ref1 = print();
        ReturnAddressOfObj ref2 = print();

        System.out.println(ref1);
        System.out.println(ref1);
        System.out.println(ref2);
    }
}