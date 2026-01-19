public class HashCode {
    int id;
    String name;

    public HashCode(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) {
        HashCode ref = new HashCode(1, "Mohit");
        HashCode ref1 = new HashCode(1, "Mohit");
        
        System.out.println(ref == ref1);
        System.out.println(ref.equals(ref1));

        System.out.println(ref.hashCode());
        System.out.println(Integer.toHexString(ref.hashCode()));    //15db9742
        System.out.println(ref);                                    //HashCode@15db9742

        System.out.println(ref.getClass());                         //class HashCode
        System.out.println(ref.getClass().getName());               //HashCode

        System.out.println(ref.getClass().getName() + "@" + Integer.toHexString(ref.hashCode()));  
    }
}
