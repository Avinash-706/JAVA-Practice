
public class GreetMessage {
    public static void greet()                      {System.out.println("Hello !!");}
    public static void greet(String name)           {System.out.println("Hello, " + name + " !!");}
    public static void greet(String name, int age)  {System.out.println("Hello, " + name + " !! You are "+ age + " years old");}

    public static void main(String[] args){
        greet();
        greet("Avinash");
        greet("Avinash", 21);
    }   
}
