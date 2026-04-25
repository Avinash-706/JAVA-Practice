@FunctionalInterface
interface Factorial {
    int findFactorial(int n);
}

class AbsBody implements Factorial {
    @Override
    public int findFactorial(int n) {
        if (n <= 1)
            return 1;
        else
            return n * findFactorial(n - 1);
    }
}

public class FactorialSolution {

    public static void main(String[] args) {

        // Using Concrete Class
        Factorial f1 = new AbsBody();
        System.out.println("Recursive : " + f1.findFactorial(5));

        // Using Anonymous Inner Class
        Factorial f2 = new Factorial() {
            @Override
            public int findFactorial(int n) {
                int fact = 1;
                for (int i = n; i >= 1; i--) {
                    fact = fact * i;
                }
                return fact;
            }
        };
        System.out.println("Anonymous : " + f2.findFactorial(5));

        // Using Lambda Expression
        Factorial f3 = (n) -> {
            int fact = 1;
            for (int i = n; i >= 1; i--) {
                fact = fact * i;
            }
            return fact;
        };
        System.out.println("Lambda     : " + f3.findFactorial(5));
    }
}