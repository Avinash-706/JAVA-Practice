class Father {
    static int a = 10;

    public static void test() {
        System.out.println("Father Class static method");
    }

    public Father() {
        System.out.println("Father cons");
    }

    // SIB → executes at class loading
    static {
        System.out.println("SIB Father class");
    }

    // IIB → executes before constructor (object creation)
    {
        System.out.println("IIB Father class");
    }
}

class Son extends Father {
    static int b = 20;

    public Son() {
        super();                 // calls Father constructor
        System.out.println("Son cons");
    }

    public static void demo() {
        System.out.println("Son Class static method");
    }

    // IIB → executes before Son constructor
    {
        System.out.println("IIB Son class");
    }

    // SIB → executes at class loading
    static {
        System.out.println("SIB Son class");
    }
}

public class Inheritance3 {
    static {
        System.out.println("SIB Driver Class");
    }

    public static void main(String[] args) {
        System.out.println("Main Start");

        // example 1 → object creation
        Son ref = new Son();
    }
}


// ========================================
// DETAILED EXECUTION FLOW
// STEP 1 : DRIVER CLASS LOADING

// -> Inheritance3 class loads
// -> SIB Driver Class

// STEP 2 : main() STARTS

// -> Main Start

// STEP 3 : OBJECT CREATION (new Son())

// -> Parent class loads first
// -> SIB Father class

// -> Child class loads
// -> SIB Son class

// STEP 4 : OBJECT INITIALIZATION

// -> Father IIB executes
// -> IIB Father class

// -> Father constructor executes
// -> Father cons

// -> Son IIB executes
// -> IIB Son class

// -> Son constructor executes
// -> Son cons


// ONE-LINE EXAM RULE 🧠
// Class loading → SIB, Object creation → IIB → Constructor, Parent before Child.