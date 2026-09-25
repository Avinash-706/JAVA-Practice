package zoneddatetime;

import java.time.ZonedDateTime;
import java.time.ZoneId;

public class MinusOperations {

    public static void minusOperations() {

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current ZonedDateTime : " + now);

        // Minus Days
        ZonedDateTime minusDays = now.minusDays(1);
        System.out.println("\nminusDays(1) : " + minusDays);
        System.out.println("minusDays(10) : " + now.minusDays(10));

        // Minus Months
        ZonedDateTime minusMonths = now.minusMonths(1);
        System.out.println("\nminusMonths(1) : " + minusMonths);
        System.out.println("minusMonths(10) : " + now.minusMonths(10));

        // Minus Years
        ZonedDateTime minusYears = now.minusYears(1);
        System.out.println("\nminusYears(1) : " + minusYears);
        System.out.println("minusYears(10) : " + now.minusYears(10));

        // Minus Weeks
        ZonedDateTime minusWeeks = now.minusWeeks(1);
        System.out.println("\nminusWeeks(1) : " + minusWeeks);
        System.out.println("minusWeeks(10) : " + now.minusWeeks(10));

        // Minus Hours
        ZonedDateTime minusHours = now.minusHours(1);
        System.out.println("\nminusHours(1) : " + minusHours);
        System.out.println("minusHours(24) : " + now.minusHours(24));

        // Minus Minutes
        ZonedDateTime minusMinutes = now.minusMinutes(20);
        System.out.println("\nminusMinutes(20) : " + minusMinutes);
        System.out.println("minusMinutes(120) : " + now.minusMinutes(120));

        // Minus Seconds
        ZonedDateTime minusSeconds = now.minusSeconds(30);
        System.out.println("\nminusSeconds(30) : " + minusSeconds);
        System.out.println("minusSeconds(3600) : " + now.minusSeconds(3600));

        // Minus Nanos
        ZonedDateTime minusNanos = now.minusNanos(500_000);
        System.out.println("\nminusNanos(500_000) : " + minusNanos);

        // Chained Minus Operations
        ZonedDateTime chainedMinus = now.minusYears(1).minusMonths(2).minusDays(3)
                                         .minusHours(4).minusMinutes(5).minusSeconds(6);
        System.out.println("\nChained Minus (1y 2m 3d 4h 5min 6s) : " + chainedMinus);
    }
}
