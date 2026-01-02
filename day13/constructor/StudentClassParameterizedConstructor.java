import java.util.Scanner;

public class StudentClassParameterizedConstructor {

    int id;
    String name;
    float percent;
    static String college;

    static {
        System.out.println("-- Welcome to Student Information System --");
        college = "Lovely Professional University";
    }

    public StudentClassParameterizedConstructor() {
        this.id = 0;
        this.name = "";
        this.percent = 0;
    }

    public StudentClassParameterizedConstructor(int id) {
        this.id = id;
    }

    public StudentClassParameterizedConstructor(String name) {
        this.name = name;
    }

    public StudentClassParameterizedConstructor(float percent) {
        this.percent = percent;
    }

    public StudentClassParameterizedConstructor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StudentClassParameterizedConstructor(int id, float percent) {
        this.id = id;
        this.percent = percent;
    }

    public StudentClassParameterizedConstructor(String name, float percent) {
        this.name = name;
        this.percent = percent;
    }

    public StudentClassParameterizedConstructor(int id, String name, float percent) {
        this.id = id;
        this.name = name;
        this.percent = percent;
    }

    public void printDetails() {
        System.out.println("ID      : " + id);
        System.out.println("Name    : " + name);
        System.out.println("Percent : " + percent);
        System.out.println("College : " + college);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean id_check, name_check, percent_check;
        int id = 0;
        String name = "";
        float percent = 0;

        System.out.print("Do You Know ID ? (true / false) : ");
        id_check = sc.nextBoolean();
        if (id_check) {
            System.out.print("Enter ID : ");
            id = sc.nextInt();
        }

        System.out.print("Do You Know Name ? (true / false) : ");
        name_check = sc.nextBoolean();
        if (name_check) {
            System.out.print("Enter Name : ");
            name = sc.next();
        }

        System.out.print("Do You Know Percent ? (true / false) : ");
        percent_check = sc.nextBoolean();
        if (percent_check) {
            System.out.print("Enter Percent : ");
            percent = sc.nextFloat();
        }

        StudentClassParameterizedConstructor obj;

        if (id_check && !name_check && !percent_check)      obj = new StudentClassParameterizedConstructor(id);
        else if (!id_check && name_check && !percent_check) obj = new StudentClassParameterizedConstructor(name);
        else if (!id_check && !name_check && percent_check) obj = new StudentClassParameterizedConstructor(percent);
        else if (id_check && name_check && !percent_check)  obj = new StudentClassParameterizedConstructor(id, name);
        else if (id_check && !name_check && percent_check)  obj = new StudentClassParameterizedConstructor(id, percent);
        else if (!id_check && name_check && percent_check)  obj = new StudentClassParameterizedConstructor(name, percent);
        else                                                obj = new StudentClassParameterizedConstructor(id, name, percent);

        obj.printDetails();
    }
}