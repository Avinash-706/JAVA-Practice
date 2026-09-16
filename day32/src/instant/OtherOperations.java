package instant;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.Duration;

public class OtherOperations {

    public static void otherInstantOperations() {

        Instant now = Instant.now();
        System.out.println("Current Instant : " + now);

        // Check if Instant is supported by a temporal unit
        boolean supportsSeconds = now.isSupported(ChronoUnit.SECONDS);
        System.out.println("\nSupports SECONDS ? : " + supportsSeconds);

        boolean supportsMonths = now.isSupported(ChronoUnit.MONTHS);
        System.out.println("Supports MONTHS ? : " + supportsMonths + " (Instant is time-based, not date-based)");

        // Get value from Instant
        long epochSecond = now.getEpochSecond();
        int nano = now.getNano();
        System.out.println("\ngetEpochSecond() : " + epochSecond);
        System.out.println("getNano() : " + nano);

        // Create Duration between two Instants
        Instant past = now.minusSeconds(3600);
        Duration duration = Duration.between(past, now);
        System.out.println("\nDuration between past and now : " + duration);
        System.out.println("Duration in seconds : " + duration.getSeconds());
        System.out.println("Duration in minutes : " + duration.toMinutes());

        // Calculate time until a future instant
        Instant future = now.plusSeconds(7200);
        long secondsUntil = now.until(future, ChronoUnit.SECONDS);
        System.out.println("\nSeconds until future instant : " + secondsUntil);

        // Range check
        Instant min = Instant.EPOCH;
        Instant max = Instant.now();
        Instant check = Instant.ofEpochSecond(1000000000);
        
        System.out.println("\nRange Check :");
        System.out.println("Is " + check + " after EPOCH ? : " + check.isAfter(min));
        System.out.println("Is " + check + " before NOW ? : " + check.isBefore(max));

        // Constants demonstration
        System.out.println("\n\nInstant Constants :");
        System.out.println("EPOCH (1970-01-01 00:00:00 UTC) : " + Instant.EPOCH);
        System.out.println("MIN : " + Instant.MIN);
        System.out.println("MAX : " + Instant.MAX);

        // Time measurement example
        Instant start = Instant.now();
        // Simulate some work
        for (int i = 0; i < 1000000; i++) {
            Math.sqrt(i);
        }
        Instant end = Instant.now();
        long elapsed = Duration.between(start, end).toMillis();
        System.out.println("\n\nTime measurement example :");
        System.out.println("Operation took : " + elapsed + " milliseconds");
    }
}
