package localdate;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;

public class AdjusterOperations {

    public static void adjusterDemo() {

        System.out.println("\n===== TEMPORAL ADJUSTERS =====");

        LocalDate today = LocalDate.now();
        System.out.println("Today's Date : " + today);

        // First day of current month
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("\nFirst Day of Current Month : " + firstDayOfMonth);

        // Last day of current month
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("Last Day of Current Month  : " + lastDayOfMonth);

        // First day of year
        LocalDate firstDayOfYear = today.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("\nFirst Day of Year : " + firstDayOfYear);

        // Last day of year
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last Day of Year  : " + lastDayOfYear);

        // Next Monday
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("\nNext Monday Date : " + nextMonday);

        // Previous Friday
        LocalDate previousFriday = today.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        System.out.println("Previous Friday Date : " + previousFriday);
    }
}
