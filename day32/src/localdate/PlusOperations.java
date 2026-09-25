package localdate;

import java.time.LocalDate;

public class PlusOperations {
    public static void plusOperations() {
        LocalDate today = LocalDate.now();
        System.out.println("Today : " + today);

        LocalDate customDate = LocalDate.of( 1990,  2,  7);

        LocalDate tommorow = today.plusDays(1);
        System.out.println("\nTommorow's Date - plusDays(1) : " + tommorow);
        System.out.println("Date After 10 Day : " + today.plusDays(10));

        LocalDate nextMonthDate = today.plusMonths(1);
        System.out.println("\nNext Month Date - plusMonths(1) : " + nextMonthDate);
        System.out.println("Date After 10 Month : " + today.plusMonths(10));
        LocalDate nextYearDate = today.plusYears(1);
        System.out.println("\nNext Year Date - plusYears(1) : " + nextYearDate);
        System.out.println("Date After 10 Year : " + today.plusYears(10));
        
        LocalDate nextWeekDate = today.plusWeeks(1);;
        System.out.println("\nNext Week Date - plusWeeks(1) : " + nextWeekDate);
        System.out.println("Date After 10 Week : " + today.plusWeeks(10));

    }
}
