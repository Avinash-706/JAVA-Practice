public class Equals {
    int id;
    String name;

    public Equals(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj){
        Equals ref = (Equals) obj;
        return  this.id == ref.id && this.name == ref.name;
    }

    public static void main(String[] args) {
        Equals ref1 = new Equals(1, "Mohit");
        Equals ref2 = new Equals(1, "Mohit");
        
        System.out.println("Ref 1 : " + ref1);
        System.out.println("Ref 2 : " + ref2);
        
        System.out.println(ref1 == ref2);       //compare address
        System.out.println(ref1.equals(ref2));

        // before Overidding equals() -> compares address
        // after Overidding equals() -> compares content
    }
}