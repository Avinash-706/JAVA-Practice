import java.util.HashSet;

public class Ecom{
    public static void main(String[] args){
        Product p1 = new Product(101, "Laptop", 50000);
        Product p2 = new Product(101, "Laptop", 50000);

        HashSet<Product> set = new HashSet<>();

        set.add(p1);
        set.add(p2);

        System.out.println(set.size());

        for(Product p:set){
            System.out.println(p);
        }
    }
}

class Product {
    private int id;
    private String name;
    private double price;


    Product(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return "Product[id="+this.id+ ", name="+this.name+", price="+this.price+"]";
    }
    @Override
    public boolean equals(java.lang.Object p){
        Product obj = (Product)p;
        return obj.id == this.id;
    }   
    @Override
    public int hashCode(){
        return Integer.hashCode(this.id);
    }
}