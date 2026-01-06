class Father{
    int a = 10;
}

class Son extends Father{
    int b = 20;
}

public class Inheritance1 {
    public static void main(String[] args) {
        Son ref = new Son();
        System.out.println(ref.a);
        System.out.println(ref.b);
    }
}
