public class ToString {
    int id;
    String name;

    public ToString(int id, String name){
        this.id = id;
        this.name = name;
    }

    // @Override
    // public String toString(){
    //     return "P7 [id =" + id + ", name =" + name + "]";  
    // }

    public static void main(String[] args) {
        ToString ref = new ToString(1, "Mohit");
        System.out.println(ref);
        System.out.println(ref.toString());
    }
}
