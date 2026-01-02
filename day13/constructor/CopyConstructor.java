//Example of Copy Constructor
public class CopyConstructor {
    int id;
    String name;

    public CopyConstructor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CopyConstructor(CopyConstructor obj) {
        this.id = obj.id;
        this.name = obj.name;
    }

    public static void main(String[] args) {
        System.out.println("Main Start");
        CopyConstructor ref1 = new CopyConstructor(1, "rohit");
        System.out.println(ref1.id);
        System.out.println(ref1.name);

        System.out.println("After Copy Constructor");
        CopyConstructor ref2 = new CopyConstructor(ref1);
        System.out.println(ref2.id);
        System.out.println(ref2.name);
    }
}