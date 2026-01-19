interface Father{
    abstract public void test();
    abstract public int add(int a, int b);
}


class Son implements Father{
    @Override
    public void test(){
        System.out.println("Abstract Method Override");
    }
    @Override
    public int add(int a, int b){
        return a+b;
    }
}


public class Interface1 {
    public static void main(String[] args) {
        System.out.println("Main Start");
        // Father ref = new Father; //CTE Cannnot instantiate the type Father

        Father ref = new Son();
        ref.test();
        System.out.println(ref.add(10,20));
        System.out.println("Main End");
    }
}