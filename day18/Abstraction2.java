abstract class Father{
    static int a = 10;
    int b = 20;

    public static void test(){
        System.out.println("Static Method");
    }
    public void demo(){
        System.out.println("Non Static method");
    }
    
    //abstract
    abstract public void abs();

    static{
        System.out.println("Static Initializer Block");
    }

    {
        System.out.println("Instance Intializer Block");
    }

    public Father(){
        System.out.println("Constructor");
    }
}

class Son extends Father{
    @Override
    public void abs(){
        System.out.println("Abstract Method");
    }
}

public class Abstraction2 {
    public static void main(String[] args) {
        System.out.println("Main Start");
        // Father ref = new Father(); // CTE Cannot instantiate the type Father

        Father ref = new Son();
        System.out.println(ref.a);
        System.out.println(ref.b);
        ref.test();        
        ref.demo();        
        ref.abs();                
        System.out.println("Main End");
    }
}
