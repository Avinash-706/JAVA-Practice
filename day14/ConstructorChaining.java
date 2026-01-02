import java.util.Scanner;

public class ConstructorChaining {
    String brand;
    int ramSize;
    double price;

    public ConstructorChaining(){
        this.brand = "Unknown";
        this.ramSize = 8;
        this.price = 50000.0;
    }

    public ConstructorChaining(String brand){
        this();
        this.brand = brand;
    }

    public ConstructorChaining(String brand, int ramSize){
        this(brand);
        this.ramSize = ramSize;
    }
    
    public ConstructorChaining(String brand, int ramSize, double price){
        this(brand, ramSize);
        this.price = price;
    }

    public void printDetails() {
        System.out.println("\n==== FINAL PHONE SETTINGS ====");
        System.out.println("Brand   : " + this.brand);
        System.out.println("Ram : " + this.ramSize + " GB");
        System.out.println("Price      : " + this.price);       
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String brand = "Unknown";
        int ramSize = 8;
        double price = 50000.0;


        boolean brand_check, ram_check, price_check;

        System.out.print("Do You Want to modify Brand Name ? (true / false) : ");
        brand_check = sc.nextBoolean();
        if (brand_check) {
            System.out.print("Enter Brand : ");
            brand = sc.next();
        }

        System.out.print("Do You Want to modify Ram ? (true / false) : ");
        ram_check = sc.nextBoolean();
        if (ram_check) {
            System.out.print("Enter Ram : ");
            ramSize = sc.nextInt();
        }

        System.out.print("Do You Want to Enter Price ? (true / false) : ");
        price_check = sc.nextBoolean();
        if (price_check) {
            System.out.print("Enter Price (true/false) : ");
            price = sc.nextDouble();
        }

        ConstructorChaining obj = new ConstructorChaining();

        if (brand_check && !ram_check && !price_check)           obj = new ConstructorChaining(brand);
        else if (brand_check && ram_check && !price_check)       obj = new ConstructorChaining(brand, ramSize);
        else if (brand_check && ram_check && price_check )       obj = new ConstructorChaining(brand, ramSize, price);

        obj.printDetails();
    }
}