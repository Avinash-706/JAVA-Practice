package localdate;

import java.time.LocalDate;

public class MinusOperations {
    public static void minusOperations() {
        LocalDate today = LocalDate.now();
        System.out.println("Today : " + today);

        LocalDate customDate = LocalDate.of( 1990,  2,  7);

        LocalDate yesterday = today.minusDays(1);
        System.out.println("Yesterday's Date - minusDay(1) : " + yesterday);
        System.out.println("10 Day's Back Date : " + today.minusDays(10));

        LocalDate pastMonthDate = today.minusMonths(1);
        System.out.println("\nPast Month Date - minusMonth(1) : " + pastMonthDate);
        System.out.println("10 Month's Back Date : " + today.minusMonths(10));
        
        LocalDate pastYearDate = today.minusYears(1);
        System.out.println("\nPast Year Date - minusYear(1) : " + pastYearDate);
        System.out.println("10 Year's Back Date : " + today.minusYears(10));
        
        LocalDate pastWeekDate = today.minusWeeks(1);;
        System.out.println("\nPast Week Date - minusYear(1) : " + pastWeekDate);
        System.out.println("10 Week's Back Date : " + today.minusWeeks(10));

    }
}
