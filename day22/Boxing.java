/**
 * implicit & Explicit Boxing
 */

public class Boxing {
    public static void main(String[] args) {
        int a = 10;
        Integer x  = a;
        System.out.println("Implicit Boxing: " + x);

        Integer y = Integer.valueOf(a);
        System.out.println("Explicit Boxing: " + y);

        int i = x;
        System.out.println("Implicit Unboxing: " + i);
        
        int j = x.intValue();
        System.out.println("Explicit Unboxing: " + j);
    }
}
