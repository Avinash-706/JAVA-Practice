public class DifferentObject {

    String user_name;
    static int a;

    public static void main(String[] args) {
        System.out.println("Main Start");
        DifferentObject ref1 = new DifferentObject();
        ref1.user_name = "Devansh";
        ref1.a = 10;
        DifferentObject ref2 = new DifferentObject();
        ref2.user_name = "Vishnu";
        ref2.a = 11;
        DifferentObject ref3 = new DifferentObject();
        ref3.user_name = "Mohit";
        ref3.a = 12;

        System.out.println("Non-Static variable USER NAME 1 : " + ref1.user_name);
        System.out.println("Static Integer Value 1 :" + ref1.a);

        System.out.println("Non-Static variable USER NAME 2 : " + ref2.user_name);
        System.out.println("Static Integer Value 2 :" + ref2.a);

        System.out.println("Non-Static variable USER NAME 3 : " + ref3.user_name);
        System.out.println("Static Integer Value 3 :" + ref3.a);
    }
}
