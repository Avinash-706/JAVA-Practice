package localdatetime;

import java.time.LocalDateTime;

public class PlusOperations {

    public static void plusOperations() {

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current DateTime : " + now);

        // Plus Days
        LocalDateTime plusDays = now.plusDays(1);
        System.out.println("\nplusDays(1) : " + plusDays);
        System.out.println("plusDays(10) : " + now.plusDays(10));

        // Plus Months
        LocalDateTime plusMonths = now.plusMonths(1);
        System.out.println("\nplusMonths(1) : " + plusMonths);
        System.out.println("plusMonths(10) : " + now.plusMonths(10));

        // Plus Years
        LocalDateTime plusYears = now.plusYears(1);
        System.out.println("\nplusYears(1) : " + plusYears);
        System.out.println("plusYears(10) : " + now.plusYears(10));

        // Plus Weeks
        LocalDateTime plusWeeks = now.plusWeeks(1);
        System.out.println("\nplusWeeks(1) : " + plusWeeks);
        System.out.println("plusWeeks(10) : " + now.plusWeeks(10));

        // Plus Hours
        LocalDateTime plusHours = now.plusHours(2);
        System.out.println("\nplusHours(2) : " + plusHours);
        System.out.println("plusHours(24) : " + now.plusHours(24));

        // Plus Minutes
        LocalDateTime plusMinutes = now.plusMinutes(30);
        System.out.println("\nplusMinutes(30) : " + plusMinutes);
        System.out.println("plusMinutes(120) : " + now.plusMinutes(120));

        // Plus Seconds
        LocalDateTime plusSeconds = now.plusSeconds(45);
        System.out.println("\nplusSeconds(45) : " + plusSeconds);
        System.out.println("plusSeconds(3600) : " + now.plusSeconds(3600));

        // Plus Nanos
        LocalDateTime plusNanos = now.plusNanos(1_000_000);
        System.out.println("\nplusNanos(1_000_000) : " + plusNanos);

        // Chained Plus Operations
        LocalDateTime chainedPlus = now.plusYears(1).plusMonths(2).plusDays(3)
                                        .plusHours(4).plusMinutes(5).plusSeconds(6);
        System.out.println("\nChained Plus (1y 2m 3d 4h 5min 6s) : " + chainedPlus);
    }
}
