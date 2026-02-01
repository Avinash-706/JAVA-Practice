import java.util.HashSet;

public class University{
    public static void main(String[] args) {
        HashSet<Student> set = new HashSet<>();

        Student st1 = new Student(501, "Rahul", "CSE");
        Student st2 = new Student(501, "Rahul", "IT");

        set.add(st1);
        set.add(st2);

        System.out.println(set.size());
        System.out.println(st1);

    }
}

class Student {
    private int reg;
    private String name;
    private String dep;

    public Student(int reg, String name, String dep) {
        this.reg = reg;
        this.name = name;
        this.dep = dep;
    }

    @Override
    public String toString() {
        return "Student[regNo=" + this.reg + ", name=" + this.name + ", department=" + this.dep + "]";
    }

    @Override
    public boolean equals(java.lang.Object stu) {
        Student obj = (Student)stu;
        return this.reg == obj.reg;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(reg);
    }
}
