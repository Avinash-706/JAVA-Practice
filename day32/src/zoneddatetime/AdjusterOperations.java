package zoneddatetime;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;

public class AdjusterOperations {

    public static void adjusterDemo() {

        System.out.println("\n===== TEMPORAL ADJUSTERS =====");

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current ZonedDateTime : " + now);

        // First day of current month
        ZonedDateTime firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("\nFirst Day of Current Month : " + firstDayOfMonth);

        // Last day of current month
        ZonedDateTime lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("Last Day of Current Month  : " + lastDayOfMonth);

        // First day of next month
        ZonedDateTime firstDayOfNextMonth = now.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("\nFirst Day of Next Month : " + firstDayOfNextMonth);

        // First day of year
        ZonedDateTime firstDayOfYear = now.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("\nFirst Day of Year : " + firstDayOfYear);

        // Last day of year
        ZonedDateTime lastDayOfYear = now.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last Day of Year  : " + lastDayOfYear);

        // Next Monday
        ZonedDateTime nextMonday = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("\nNext Monday ZonedDateTime : " + nextMonday);

        // Previous Friday
        ZonedDateTime previousFriday = now.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        System.out.println("Previous Friday ZonedDateTime : " + previousFriday);

        // Next or Same Monday
        ZonedDateTime nextOrSameMonday = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println("\nNext or Same Monday : " + nextOrSameMonday);

        // Previous or Same Friday
        ZonedDateTime prevOrSameFriday = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
        System.out.println("Previous or Same Friday : " + prevOrSameFriday);
    }
}
