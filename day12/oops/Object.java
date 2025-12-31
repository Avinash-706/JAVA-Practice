public class Object {

    //global varaible or class variable
    static int a = 10;
    public static void main(String[] args) {
        System.out.println("Main Start");
        System.out.println("Address of Object : " + new Object());
        System.out.println(new Object().a);

        //Class Name is a non-Primitive Datatype which is used to store addrress of an object
        Object ref = new Object();
        System.out.println("Address of Object : " + ref);
        System.out.println("Main End");
    }    
}