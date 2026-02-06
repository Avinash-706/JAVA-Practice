//wajp to convert each primitive into wrapper class and vice-versa;

public class Wrapper {

    public static void byteWrapper(){
        byte a = 10;
        Byte x  = a;
        System.out.println("Implicit Boxing: " + x);

        Byte y = Byte.valueOf(a);
        System.out.println("Explicit Boxing: " + y);

        byte i = x;
        System.out.println("Implicit Unboxing: " + i);
        
        byte j = x.byteValue();
        System.out.println("Explicit Unboxing: " + j);
    }

    public static void intWrapper(){
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

    public static void shortWrapper(){
        short a = 10;
        Short x  = a;
        System.out.println("Implicit Boxing: " + x);

        Short y = Short.valueOf(a);
        System.out.println("Explicit Boxing: " + y);

        short i = x;
        System.out.println("Implicit Unboxing: " + i);
        
        short j = x.shortValue();
        System.out.println("Explicit Unboxing: " + j); 
    }

    public static void longWrapper(){
        long a = 10;
        Long x  = a;
        System.out.println("Implicit Boxing: " + x);

        Long y = Long.valueOf(a);
        System.out.println("Explicit Boxing: " + y);

        long i = x;
        System.out.println("Implicit Unboxing: " + i);
        
        long j = x.longValue();
        System.out.println("Explicit Unboxing: " + j); 
    }

    public static void floatWrapper(){
        float a = 10.0f;
        Float x  = a;
        System.out.println("Implicit Boxing: " + x);

        Float y = Float.valueOf(a);
        System.out.println("Explicit Boxing: " + y);

        float i = x;
        System.out.println("Implicit Unboxing: " + i);
        
        float j = x.floatValue();
        System.out.println("Explicit Unboxing: " + j);
    }

    public static void doubleWrapper(){
        double a = 10;
        Double x  = a;
        System.out.println("Implicit Boxing: " + x);

        Double y = Double.valueOf(a);
        System.out.println("Explicit Boxing: " + y);

        double i = x;
        System.out.println("Implicit Unboxing: " + i);
        
        double j = x.doubleValue();
        System.out.println("Explicit Unboxing: " + j);
    }

    public static void charWrapper(){
        char a = 'a';
        Character x  = a;
        System.out.println("Implicit Boxing: " + x);

        Character y = Character.valueOf(a);
        System.out.println("Explicit Boxing: " + y);

        char i = x;
        System.out.println("Implicit Unboxing: " + i);
        
        char j = x.charValue();
        System.out.println("Explicit Unboxing: " + j);
    }

    public static void  booleanWrapper(){
        boolean a = true;
        Boolean x  = a;
        System.out.println("Implicit Boxing: " + x);

        Boolean y = Boolean.valueOf(a);
        System.out.println("Explicit Boxing: " + y);

        boolean i = x;
        System.out.println("Implicit Unboxing: " + i);
        
        boolean j = x.booleanValue();
        System.out.println("Explicit Unboxing: " + j);
    }
    
    public static void main(String[] args) {
        byteWrapper();
        intWrapper();
        shortWrapper();
        longWrapper();
        floatWrapper();
        doubleWrapper();
        charWrapper();
        booleanWrapper();
    }
}