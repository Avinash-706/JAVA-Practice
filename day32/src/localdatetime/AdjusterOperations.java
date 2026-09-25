package localdatetime;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;

public class AdjusterOperations {

    public static void adjusterDemo() {

        System.out.println("\n===== TEMPORAL ADJUSTERS =====");

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current DateTime : " + now);

        // First day of current month
        LocalDateTime firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("\nFirst Day of Current Month : " + firstDayOfMonth);

        // Last day of current month
        LocalDateTime lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("Last Day of Current Month  : " + lastDayOfMonth);

        // First day of next month
        LocalDateTime firstDayOfNextMonth = now.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("\nFirst Day of Next Month : " + firstDayOfNextMonth);

        // First day of year
        LocalDateTime firstDayOfYear = now.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("\nFirst Day of Year : " + firstDayOfYear);

        // Last day of year
        LocalDateTime lastDayOfYear = now.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last Day of Year  : " + lastDayOfYear);

        // Next Monday
        LocalDateTime nextMonday = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("\nNext Monday DateTime : " + nextMonday);

        // Previous Friday
        LocalDateTime previousFriday = now.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        System.out.println("Previous Friday DateTime : " + previousFriday);

        // Next or Same Monday
        LocalDateTime nextOrSameMonday = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println("\nNext or Same Monday : " + nextOrSameMonday);

        // Previous or Same Friday
        LocalDateTime prevOrSameFriday = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
        System.out.println("Previous or Same Friday : " + prevOrSameFriday);
    }
}
