class Father {
    static int a = 10;

    static {
        System.out.println("SIB Father class");
    }

    public static void test() {
        System.out.println("Father Class static method");
    }
}

class Son extends Father {
    static int b = 20;

    static {
        System.out.println("SIB Son class");
    }

    public static void demo() {
        System.out.println("Son Class static method");
    }
}

public class Inheritance2 {

    static {
        System.out.println("SIB Driver Class");
    }

    public static void main(String[] args) {

        System.out.println("Main Start");

        // ================= EXAMPLE 1 =================
        // static access using object reference
        Son ref = new Son();

        System.out.println(ref.a);
        ref.test();
        System.out.println(ref.b);
        ref.demo();

        // ================= EXAMPLE 2 =================
        // static access using class name

        System.out.println(Father.a);
        Father.test();
        System.out.println(Son.a);
        System.out.println(Son.b);
        Son.demo();
    }
}


// EXECUTION FLOW (COMMON)
// -> Driver class loads
// -> SIB Driver Class

// -> main() starts
// -> Main Start

// -> Father class loads (parent first)
// -> SIB Father class

// -> Son class loads
// -> SIB Son class


// ========================================
// EXAMPLE 1 : OBJECT REFERENCE

// -> Son ref = new Son()
// -> Father already loaded
// -> Son already loaded

// -> ref.a → Father.a
// -> ref.test() → Father.test()
// -> ref.b → Son.b
// -> ref.demo() → Son.demo()

// ========================================
// EXAMPLE 2 : CLASS NAME

// -> Father.a
// -> Father.test()
// -> Son.a
// -> Son.b
// -> Son.demo()

// (No object creation)