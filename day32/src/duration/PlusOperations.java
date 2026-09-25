package duration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class PlusOperations {

    public static void plusOperations() {

        Duration duration = Duration.ofHours(2);
        System.out.println("Original Duration : " + duration);

        // Plus Days
        Duration plusDays = duration.plusDays(1);
        System.out.println("\nplusDays(1) : " + plusDays);

        // Plus Hours
        Duration plusHours = duration.plusHours(3);
        System.out.println("\nplusHours(3) : " + plusHours);

        // Plus Minutes
        Duration plusMinutes = duration.plusMinutes(30);
        System.out.println("plusMinutes(30) : " + plusMinutes);

        // Plus Seconds
        Duration plusSeconds = duration.plusSeconds(45);
        System.out.println("plusSeconds(45) : " + plusSeconds);

        // Plus Millis
        Duration plusMillis = duration.plusMillis(500);
        System.out.println("plusMillis(500) : " + plusMillis);

        // Plus Nanos
        Duration plusNanos = duration.plusNanos(1_000_000);
        System.out.println("plusNanos(1_000_000) : " + plusNanos);

        // Plus using ChronoUnit
        Duration plusUnit = duration.plus(45, ChronoUnit.MINUTES);
        System.out.println("\nplus(45, ChronoUnit.MINUTES) : " + plusUnit);

        // Chained Plus Operations
        Duration chainedPlus = duration.plusHours(1).plusMinutes(30).plusSeconds(45);
        System.out.println("\nChained Plus (1h 30m 45s) : " + chainedPlus);

        // Add two durations
        Duration duration1 = Duration.ofHours(2);
        Duration duration2 = Duration.ofMinutes(30);
        Duration total = duration1.plus(duration2);
        System.out.println("\nDuration1 (2h) + Duration2 (30m) : " + total);
    }
}
