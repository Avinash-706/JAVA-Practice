package period;

import java.time.Period;

public class WithOperations {

    public static void withOperations() {

        System.out.println("\n===== WITH OPERATIONS =====");

        Period period = Period.of(3, 6, 15);
        System.out.println("Original Period : " + period);

        // With Years
        Period withYears = period.withYears(5);
        System.out.println("\nwithYears(5) : " + withYears);

        // With Months
        Period withMonths = period.withMonths(10);
        System.out.println("withMonths(10) : " + withMonths);

        // With Days
        Period withDays = period.withDays(20);
        System.out.println("withDays(20) : " + withDays);

        // Chained With Operations
        Period chainedWith = period.withYears(2).withMonths(8).withDays(10);
        System.out.println("\nChained With (2y 8m 10d) : " + chainedWith);

        // Set to zero
        Period resetYears = period.withYears(0);
        System.out.println("\nwithYears(0) : " + resetYears);

        Period resetAll = period.withYears(0).withMonths(0).withDays(0);
        System.out.println("Reset all to zero : " + resetAll);
        System.out.println("Same as Period.ZERO ? : " + resetAll.equals(Period.ZERO));

        // Negative values
        Period withNegative = period.withYears(-2);
        System.out.println("\nwithYears(-2) : " + withNegative);
    }
}
