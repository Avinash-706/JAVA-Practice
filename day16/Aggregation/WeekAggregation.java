// WEEK AGGREGATION : HAS-A Relationship

// Address: Independent class (exist WITHOUT Student)
class Address {
    String city;
    String state;

    public Address(String city, String state){
        this.city = city;
        this.state = state;
    }
}

// Student HAS-A Address
class Student {
    int id;
    String name;

    // Student contains a reference to Address (does NOT create Address object itself)
    Address address;

    public Student (int id, String name, Address ad) {
        this.id = id;
        this.name = name;
        this.address = ad;   // Address is passed from outside
    }

    public void display(){
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);

        // USING ADDRESS OBJECT VIA REFERENCE
        System.out.println(
            "Address: " + address.city + " " + address.state
        );
    }
}

public class WeekAggregation {
    public static void main(String[] args) {

        // object created independently
        Address ad = new Address("Asansol", "West Bengal");

        // Student uses existing Address object
        // AGGREGATION (weak association)
        Student st = new Student(1, "Avinash", ad);

        st.display();
    }
}