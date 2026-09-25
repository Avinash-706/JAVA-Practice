package period;

import java.time.Period;
import java.time.temporal.ChronoUnit;

public class PlusOperations {

    public static void plusOperations() {

        Period period = Period.of(1, 6, 10);
        System.out.println("Original Period : " + period);

        // Plus Years
        Period plusYears = period.plusYears(2);
        System.out.println("\nplusYears(2) : " + plusYears);

        // Plus Months
        Period plusMonths = period.plusMonths(3);
        System.out.println("\nplusMonths(3) : " + plusMonths);

        // Plus Days
        Period plusDays = period.plusDays(15);
        System.out.println("plusDays(15) : " + plusDays);

        // Chained Plus Operations
        Period chainedPlus = period.plusYears(1).plusMonths(2).plusDays(5);
        System.out.println("\nChained Plus (1y 2m 5d) : " + chainedPlus);

        // Add two periods
        Period period1 = Period.ofYears(2);
        Period period2 = Period.ofMonths(6);
        Period total = period1.plus(period2);
        System.out.println("\nPeriod1 (2 years) : " + period1);
        System.out.println("Period2 (6 months) : " + period2);
        System.out.println("Total (period1 + period2) : " + total);

        // Note about ChronoUnit
        System.out.println("\n\nNote: Period doesn't support plus(long, ChronoUnit) like Duration");
        System.out.println("Use plusYears(), plusMonths(), plusDays() methods instead");
        System.out.println("Period only supports Years, Months, and Days units");
    }
}
