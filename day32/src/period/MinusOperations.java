package period;

import java.time.Period;

public class MinusOperations {

    public static void minusOperations() {

        Period period = Period.of(5, 8, 20);
        System.out.println("Original Period : " + period);

        // Minus Years
        Period minusYears = period.minusYears(2);
        System.out.println("\nminusYears(2) : " + minusYears);

        // Minus Months
        Period minusMonths = period.minusMonths(3);
        System.out.println("\nminusMonths(3) : " + minusMonths);

        // Minus Days
        Period minusDays = period.minusDays(10);
        System.out.println("minusDays(10) : " + minusDays);

        // Chained Minus Operations
        Period chainedMinus = period.minusYears(1).minusMonths(2).minusDays(5);
        System.out.println("\nChained Minus (1y 2m 5d) : " + chainedMinus);

        // Subtract two periods
        Period period1 = Period.ofYears(5);
        Period period2 = Period.ofMonths(6);
        Period remaining = period1.minus(period2);
        System.out.println("\nPeriod1 (5 years) : " + period1);
        System.out.println("Period2 (6 months) : " + period2);
        System.out.println("Remaining (period1 - period2) : " + remaining);

        // Negative Period
        Period negative = Period.ofYears(2).minusYears(5);
        System.out.println("\nNegative Period (2y - 5y) : " + negative);
        System.out.println("Is Negative ? : " + negative.isNegative());

        // More negative examples
        Period negativeMonths = Period.ofMonths(-3);
        System.out.println("\nPeriod.ofMonths(-3) : " + negativeMonths);
        System.out.println("Is Negative ? : " + negativeMonths.isNegative());
    }
}
