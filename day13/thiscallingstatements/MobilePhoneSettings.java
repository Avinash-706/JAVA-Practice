import java.util.Scanner;

public class MobilePhoneSettings {
    String brand;
    int storage;
    boolean is5G;

    // Default Constructor
    public MobilePhoneSettings(){
        this.brand = "Unknown";
        this.storage = 64;
        this.is5G = false;
    }

    // Constructor – Brand only
    public MobilePhoneSettings(String brand){
        this();     // calls default constructor first
        this.brand = brand;
    }

    // Constructor – Storage only
    public MobilePhoneSettings(int storage){
        this();     // calls default constructor first
        this.storage = storage;
    }

    // Constructor – 5G only
    public MobilePhoneSettings(boolean is5G){
        this();     // calls default constructor first
        this.is5G = is5G;
    }

    // Constructor – Brand + Storage
    public MobilePhoneSettings(String brand, int storage){
        this(brand);   // call brand constructor
        this.storage = storage;
    }

    // Constructor – Brand + 5G
    public MobilePhoneSettings(String brand, boolean is5G){
        this(brand);   // call brand constructor
        this.is5G = is5G;
    }

    // Constructor – Storage + 5G
    public MobilePhoneSettings(int storage, boolean is5G){
        this(storage); // call storage constructor
        this.is5G = is5G;
    }

    // Constructor – Brand + Storage + 5G
    public MobilePhoneSettings(String brand, int storage, boolean is5G){
        this(brand, storage);   // call brand + storage constructor
        this.is5G = is5G;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean brand_check, storage_check, is5G_check;
        String brand = "";
        int storage = 0;
        boolean is5g = false;

        System.out.print("Do You Want to modify Brand Name ? (true / false) : ");
        brand_check = sc.nextBoolean();
        if (brand_check) {
            System.out.print("Enter Brand : ");
            brand = sc.next();
        }

        System.out.print("Do You Want to modify Storage ? (true / false) : ");
        storage_check = sc.nextBoolean();
        if (storage_check) {
            System.out.print("Enter Storage : ");
            storage = sc.nextInt();
        }

        System.out.print("Do You Want to upgrade to 5G ? (true / false) : ");
        is5G_check = sc.nextBoolean();
        if (is5G_check) {
            System.out.print("Enter 5G Support (true/false) : ");
            is5g = sc.nextBoolean();
        }

        MobilePhoneSettings obj;

        if (brand_check && !storage_check && !is5G_check)           obj = new MobilePhoneSettings(brand);
        else if (!brand_check && storage_check && !is5G_check)      obj = new MobilePhoneSettings(storage);
        else if (!brand_check && !storage_check && is5G_check)      obj = new MobilePhoneSettings(is5g);
        else if (brand_check && storage_check && !is5G_check)       obj = new MobilePhoneSettings(brand, storage);
        else if (brand_check && !storage_check && is5G_check)       obj = new MobilePhoneSettings(brand, is5g);
        else if (!brand_check && storage_check && is5G_check)       obj = new MobilePhoneSettings(storage, is5g);
        else                                                        obj = new MobilePhoneSettings(brand, storage, is5g);

        System.out.println("\n==== FINAL PHONE SETTINGS ====");
        System.out.println("Brand   : " + obj.brand);
        System.out.println("Storage : " + obj.storage + " GB");
        System.out.println("5G      : " + obj.is5G);
    }
}