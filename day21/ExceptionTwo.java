
public class ExceptionTwo {

    public static void main(String[] args) {

        try {
            Parent p = new Parent(); 
            Child c = (Child) p;

        } catch (ClassCastException e) {
            System.out.println(e.getClass() + ": handled");
        }
    }
}

class Parent {
}

class Child extends Parent {
}
