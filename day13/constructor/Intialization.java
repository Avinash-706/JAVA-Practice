public class Intialization {
    
    public Intialization(){
        // no argument constructor
        System.out.println("-- I am Constructor --");
    }

    {
        System.out.println("-- Non Static Block --");
    }

    public static void main(String[] args) {
        System.out.println("Main start");
        Intialization ref1 = new Intialization();
        System.out.println("Main End");
    }
}