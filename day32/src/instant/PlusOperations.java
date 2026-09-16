package instant;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PlusOperations {

    public static void plusOperations() {

        Instant now = Instant.now();
        System.out.println("Current Instant : " + now);

        // Plus Seconds
        Instant plusSeconds = now.plusSeconds(60);
        System.out.println("\nplusSeconds(60) : " + plusSeconds);
        System.out.println("plusSeconds(3600) : " + now.plusSeconds(3600));

        // Plus Milliseconds
        Instant plusMillis = now.plusMillis(1000);
        System.out.println("\nplusMillis(1000) : " + plusMillis);
        System.out.println("plusMillis(60000) : " + now.plusMillis(60000));

        // Plus Nanos
        Instant plusNanos = now.plusNanos(1_000_000);
        System.out.println("\nplusNanos(1_000_000) : " + plusNanos);
        System.out.println("plusNanos(1_000_000_000) : " + now.plusNanos(1_000_000_000));

        // Plus using ChronoUnit (Temporal units)
        Instant plusDays = now.plus(1, ChronoUnit.DAYS);
        System.out.println("\nplus(1, ChronoUnit.DAYS) : " + plusDays);

        Instant plusHours = now.plus(2, ChronoUnit.HOURS);
        System.out.println("plus(2, ChronoUnit.HOURS) : " + plusHours);

        Instant plusMinutes = now.plus(30, ChronoUnit.MINUTES);
        System.out.println("plus(30, ChronoUnit.MINUTES) : " + plusMinutes);

        // Chained Plus Operations
        Instant chainedPlus = now.plusSeconds(60).plusMillis(500).plus(2, ChronoUnit.HOURS);
        System.out.println("\nChained Plus (2h 60s 500ms) : " + chainedPlus);

        // Note: Instant doesn't support months/years directly as they are date-based
        // Convert to ZonedDateTime for date-based operations
        System.out.println("\nNote: For month/year operations, convert to ZonedDateTime first");
    }
}
