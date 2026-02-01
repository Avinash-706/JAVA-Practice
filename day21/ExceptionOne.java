
/**
 * WAJP to handle nullpointer exception
 * WAJP to handle class Cast Exception
 */
public class ExceptionOne{
    public static void main(String[] args) {
        try {
            String s = null;
            System.out.println(s.length());
        } catch(NullPointerException e){
            System.out.println(e+ " : handled");
        }

    }
}