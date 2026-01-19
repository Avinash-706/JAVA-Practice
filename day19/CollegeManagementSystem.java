class Person {
    public void showRole() {
        System.out.println("I am a Person");
    }
}

class Student extends Person {

    @Override
    public void showRole() {
        System.out.println("I am a Student");
    }

    public void study() {
        System.out.println("I am studying");
    }
}

class Teacher extends Person {
    @Override
    public void showRole() {
        System.out.println("I am a Teacher");
    }

    public void teach() {
        System.out.println("I am teaching");
    }
}

interface Sports {

    void play();
}

interface Cultural {
    void perform();
}

class CollegeStudent extends Student implements Sports, Cultural {
    @Override
    public void play() {
        System.out.println("I am playing Sports");
    }

    @Override
    public void perform() {
        System.out.println("I am performing Cultural Event");
    }

    public void collegeLife() {
        System.out.println("I am a College Student");
    }
}

public class CollegeManagementSystem {
    public static void main(String[] args) {

        // Direct object
        CollegeStudent cs = new CollegeStudent();
        cs.showRole();
        cs.play();
        cs.perform();
        cs.collegeLife();
        cs.study();

        System.out.println("----- UPCASTING -----");

        // Upcasting to Person
        Person p = cs;
        p.showRole();   // Runtime Polymorphism

        System.out.println("----- DOWNCASTING -----");

        // Downcasting back to Student
        Student s = (Student) p;
        s.study();
        s.showRole();

        System.out.println("----- INTERFACE REFERENCES -----");

        Sports sp = cs;
        sp.play();

        Cultural c = cs;
        c.perform();
    }
}
