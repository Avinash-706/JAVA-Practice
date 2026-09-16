package instant;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class MinusOperations {

    public static void minusOperations() {

        Instant now = Instant.now();
        System.out.println("Current Instant : " + now);

        // Minus Seconds
        Instant minusSeconds = now.minusSeconds(60);
        System.out.println("\nminusSeconds(60) : " + minusSeconds);
        System.out.println("minusSeconds(3600) : " + now.minusSeconds(3600));

        // Minus Milliseconds
        Instant minusMillis = now.minusMillis(1000);
        System.out.println("\nminusMillis(1000) : " + minusMillis);
        System.out.println("minusMillis(60000) : " + now.minusMillis(60000));

        // Minus Nanos
        Instant minusNanos = now.minusNanos(1_000_000);
        System.out.println("\nminusNanos(1_000_000) : " + minusNanos);
        System.out.println("minusNanos(1_000_000_000) : " + now.minusNanos(1_000_000_000));

        // Minus using ChronoUnit (Temporal units)
        Instant minusDays = now.minus(1, ChronoUnit.DAYS);
        System.out.println("\nminus(1, ChronoUnit.DAYS) : " + minusDays);

        Instant minusHours = now.minus(2, ChronoUnit.HOURS);
        System.out.println("minus(2, ChronoUnit.HOURS) : " + minusHours);

        Instant minusMinutes = now.minus(30, ChronoUnit.MINUTES);
        System.out.println("minus(30, ChronoUnit.MINUTES) : " + minusMinutes);

        // Chained Minus Operations
        Instant chainedMinus = now.minusSeconds(60).minusMillis(500).minus(2, ChronoUnit.HOURS);
        System.out.println("\nChained Minus (2h 60s 500ms) : " + chainedMinus);

        // Note: Instant doesn't support months/years directly as they are date-based
        // Convert to ZonedDateTime for date-based operations
        System.out.println("\nNote: For month/year operations, convert to ZonedDateTime first");
    }
}
