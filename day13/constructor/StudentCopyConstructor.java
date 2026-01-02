import java.util.*;

public class StudentCopyConstructor {
    int id;
    String name;
    int[] marks;

    static {
        System.out.println("-- Welcome to Student Information System --");
    }

    public StudentCopyConstructor(int id, String name, int[] marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    
    // deep copy
    public StudentCopyConstructor(StudentCopyConstructor obj) {
        this.id = obj.id;
        this.name = obj.name;
        this.marks = new int[obj.marks.length];

        for(int i = 0 ; i < obj.marks.length ; i++){
            this.marks[i] = obj.marks[i];
        }
    }

    public void printDetails() {
        System.out.println("ID      : " + id);
        System.out.println("Name    : " + name);
        for(int i = 0 ; i < 3 ; i++){
            System.out.println("Marks " + (i + 1) + " : " + this.marks[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int id = 0;
        String name = "";
        int[] marks = new int[3];

        System.out.print("Enter ID : ");
        id = sc.nextInt();

        System.out.print("Enter Name : ");
        name = sc.next();

        for(int i = 0 ; i < 3 ; i++){
            System.out.print("Enter Marks " + (i + 1) + " : " );
            marks[i] = sc.nextInt();
        }

        StudentCopyConstructor obj1 = new StudentCopyConstructor(id, name, marks);

        StudentCopyConstructor obj2 = new StudentCopyConstructor(obj1);
        obj2.marks[0] = 1;


        obj2.printDetails();
    }
}