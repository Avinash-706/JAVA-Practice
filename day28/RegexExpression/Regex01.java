public class Regex01 {
    public static void main(String[] args) {
        System.out.println("Suraj".matches("S...j"));   //true
        System.out.println("Suraj".matches("S..j"));    //false
        System.out.println("Sraj".matches("S...j"));    //false
        System.out.println("suraj".matches("S...j"));   //false
        System.out.println("Siraj".matches("S...j"));   //true
        System.out.println("Sairaj".matches("S...j"));  //false
        System.out.println("S123j".matches("S...j"));   //true

        System.out.println();

        System.out.println("j".matches("S*j"));         //true
        System.out.println("Sj".matches("S*j"));        //true
        System.out.println("SSj".matches("S*j"));       //true
        System.out.println("SSSj".matches("S*j"));      //true
        System.out.println("Sabcj".matches("S*j"));     //false
        System.out.println("sSj".matches("S*j"));       //false
        System.out.println("jj".matches("S*j"));        //false
        
        System.out.println();
        System.out.println("colr".matches("colo?r"));   //true 
        System.out.println("color".matches("colo?r"));  //true
        System.out.println("coloor".matches("colo?r")); //false
        System.out.println("abc".matches("ab(c)?"));    //true
        System.out.println("ab".matches("ab(c)?"));     //true
        System.out.println("abcd".matches("ab(c)?"));   //false
        

        System.out.println();

        System.out.println("Aditya".matches("A*d+i*t+y+a+"));
        System.out.println("AAAdddiiityaaa".matches("A*d+i*t+y+a+"));
        System.out.println("Addittyya".matches("A*d+i*t+y+a+"));
        System.out.println("dityaaa".matches("A*d+i*t+y+a+"));
        System.out.println("Adtttyya".matches("A*d+i*t+y+a+"));

        System.out.println();

        System.out.println("Suraj".matches(".*"));
        System.out.println("SURAJ".matches(".*"));
        System.out.println("suraj".matches(".*"));
        System.out.println("SuRaJ".matches(".*"));
        System.out.println("Suraj@123".matches(".*"));
        System.out.println("Su$1%r123aj@123".matches(".*"));
        System.out.println("123&$a1?j@123".matches(".*"));
        System.out.println("12345".matches(".*"));

        System.out.println();

        System.out.println("Hello World".matches("^[A-Za-z0-9]+( [A-Za-z0-9]+)+$")); // true
        System.out.println("Java 8".matches("^[A-Za-z0-9]+( [A-Za-z0-9]+)+$"));     // true
        System.out.println("Hello".matches("^[A-Za-z0-9]+( [A-Za-z0-9]+)+$"));     // false
        System.out.println("Java8".matches("^[A-Za-z0-9]+( [A-Za-z0-9]+)+$"));     // false
        System.out.println(" Hello World".matches("^[A-Za-z0-9]+( [A-Za-z0-9]+)+$")); // false
        System.out.println("Hello  World".matches("^[A-Za-z0-9]+( [A-Za-z0-9]+)+$")); // false

    }
}
