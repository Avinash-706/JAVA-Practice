package duration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class MinusOperations {

    public static void minusOperations() {

        Duration duration = Duration.ofHours(5);
        System.out.println("Original Duration : " + duration);

        // Minus Days
        Duration minusDays = duration.minusDays(0);
        System.out.println("\nminusDays(0) : " + minusDays + " (no change)");

        // Minus Hours
        Duration minusHours = duration.minusHours(2);
        System.out.println("\nminusHours(2) : " + minusHours);

        // Minus Minutes
        Duration minusMinutes = duration.minusMinutes(30);
        System.out.println("minusMinutes(30) : " + minusMinutes);

        // Minus Seconds
        Duration minusSeconds = duration.minusSeconds(45);
        System.out.println("minusSeconds(45) : " + minusSeconds);

        // Minus Millis
        Duration minusMillis = duration.minusMillis(500);
        System.out.println("minusMillis(500) : " + minusMillis);

        // Minus Nanos
        Duration minusNanos = duration.minusNanos(1_000_000);
        System.out.println("minusNanos(1_000_000) : " + minusNanos);

        // Minus using ChronoUnit
        Duration minusUnit = duration.minus(45, ChronoUnit.MINUTES);
        System.out.println("\nminus(45, ChronoUnit.MINUTES) : " + minusUnit);

        // Chained Minus Operations
        Duration chainedMinus = duration.minusHours(1).minusMinutes(30).minusSeconds(45);
        System.out.println("\nChained Minus (1h 30m 45s) : " + chainedMinus);

        // Subtract two durations
        Duration duration1 = Duration.ofHours(5);
        Duration duration2 = Duration.ofMinutes(30);
        Duration remaining = duration1.minus(duration2);
        System.out.println("\nDuration1 (5h) - Duration2 (30m) : " + remaining);

        // Negative Duration
        Duration negative = Duration.ofHours(2).minusHours(5);
        System.out.println("\nNegative Duration (2h - 5h) : " + negative);
        System.out.println("Is Negative ? : " + negative.isNegative());
    }
}
