public class OverloadingExample {

    public static void display(int id){
        System.out.println("ID : " + id);
    }

    public static void display(String name){
        System.out.println("Name : " + name);

    }

    public void display(int id, double price){
        System.out.println("ID : " + id);
        System.out.println("Price : " + price);

    }

    public void display(){
        System.out.println("Default Message");
    }

    public static void main(String[] args) {
        int id = 5;
        String name = "Avinash";
        double price = 100.0;

        OverloadingExample obj1 = new OverloadingExample();
        OverloadingExample.display(id);
        OverloadingExample.display(name);

        obj1.display(id, price);
        obj1.display();
    }
}
