import java.util.List;

class Emp {
    int id;
    String name;
    double sal;

    public Emp(int id, String name, double sal) {
        this.id = id;
        this.name = name;
        this.sal = sal;
    }

    public void printDetails() {
        System.out.println("----------");
        System.out.println("ID: " + this.id);
        System.out.println("NAME: " + this.name);
        System.out.println("SAL: " + this.sal);
    }
}

public class MethodReference02 {
    public static void main(String[] args) {
        List<Emp> emps = List.of(
            new Emp(1, "Pawan", 85678),
            new Emp(2, "Rohit", 76584),
            new Emp(3, "Aditya", 78901)
        );

        // Printing the list object directly (calls toString)
        System.out.println(emps);

        // Using Lambda Expression
        emps.forEach(emp -> emp.printDetails());

        // Using Method Reference (shorthand for lambda)
        System.out.println("\nUsing method reference");
        emps.forEach(Emp::printDetails);
    }
}