
import java.util.*;

/**
 * Q. You are given a Java program that defines two classes: Employee and Company.
- The Employee class has attributes for employee ID, name, and salary. It also 
contains a static method to create new Employee objects and a method to display 
employee details.
- The Company class maintains a list of employees and displays their details.	
	
 */

public class ListThree {
    public static void main(String[] args) {
        Employee e1 = Employee.createEmployee(101, "Kshitij", 900000);
        Employee e2 = Employee.createEmployee(102, "Kumar", 900000);
        Employee e3 = Employee.createEmployee(103, "Yadav", 900000);

        Company cap = new Company();
        cap.addEmployee(e1);
        cap.addEmployee(e2);
        cap.addEmployee(e3);

        cap.displayEmployees();
    }
}


class Employee {
    protected int id;
    protected String name;
    protected double sal;

    Employee(int id, String name, double sal) {
        this.id = id;
        this.name = name;
        this.sal = sal;
    }

    public static Employee createEmployee(int id, String name, double sal) {
        return new Employee(id, name, sal);
    }

    public void display(){
        System.out.printf("%d\t%s\t%.1f%n", this.id, this.name, this.sal);
    }

}

class Company {
    protected List<Employee> arr;

    public Company() {
        this.arr = new ArrayList<>();
    }

    public void addEmployee(Employee e){
        arr.add(e);
    }

    public void displayEmployees(){
        System.out.println("Here are the list of all Employee");

        System.out.println("ID\tName\tSalary");
        for(Employee e : this.arr){
            e.display();
        }
    }

}