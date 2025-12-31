public class OverloadMainMethod {
    
    public static void main(int n) {
        System.out.println(" -- Overloaded Main Method Start -- ");
        System.out.println(n);
        System.out.println("Overloaded Main Method End");

    }

    public static void main(String[] args) {
        System.out.println(" -- MAIN START -- ");
        main(10);
        System.out.println("MAIN END");
    }
}