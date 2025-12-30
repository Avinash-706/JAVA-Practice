public class StaticVariables {

    //global varaible or class variable
    static int a = 10;
    static int b = a;   // static variable
    public static void main(String[] args) {
        System.out.println("Main Start");
        System.out.println(a);
        test();
        System.out.println("Main End");

    }

    public static void test() {
        System.out.println("Test Start");
        int a = 25;
        System.out.println("Local Variable : " + a);
        System.out.println("Global Variable / Static Variable : " + StaticVariables.a);
        System.out.println("Test End");
    }

    static{
        System.out.println("Welcome to Capgemini");
    }
    static{
        System.out.println("Welcome to LPU");
    }

    
    
}
