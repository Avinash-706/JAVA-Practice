// Multiple Inheritance using Interfaces with Downcasting

interface Father {
    void setFatherAge(int age);
    int getFatherAge();
}

interface Mother {
    void setMotherAge(int age);
    int getMotherAge();
}

class Son implements Father, Mother {

    int fatherAge;
    int motherAge;

    public Son() {
        System.out.println("-- Son Class Constructor --");
    }

    // Father interface methods
    @Override
    public void setFatherAge(int age) {
        this.fatherAge = age;
    }

    @Override
    public int getFatherAge() {
        System.out.print("Father Age: ");
        return fatherAge;
    }

    // Mother interface methods
    @Override
    public void setMotherAge(int age) {
        this.motherAge = age;
    }

    @Override
    public int getMotherAge() {
        System.out.print("Mother Age: ");
        return motherAge;
    }

    void showSon() {
        System.out.println("Son belongs to both Father and Mother");
    }
}

public class MultipleInheritanceDowncasting {
    public static void main(String[] args) {

        // UPCASTING
        Father f = new Son();   
        f.setFatherAge(50);
        System.out.println(f.getFatherAge());

        // DOWNCASTING
        Son s = (Son) f;        
        s.setMotherAge(45);
        System.out.println(s.getMotherAge());

        s.showSon();
    }
}