abstract class Father{
    abstract public void test();
    abstract public int add(int a, int b);
}

class Son extends Father{
    @Override
    public void test(){
        System.out.println("Test Method Override");
    }
    @Override
    public int add(int a, int b){
        return a+b;
    }
    public void demo(){
        System.out.println("Son Class Demo Method");
    }
}

public class Abstraction1 {
    public static void main(String[] args) {
        System.out.println("Main Start");
        // Father ref = new Father(); // CTE Cannot instantiate the type Father

        Father ref = new Son();
        ref.test();
        // ref.demo(); // Cannot access

        // to access ref.demo() we need to perform downcasting
        Son obj = (Son) ref;
        obj.demo();
    }
}
