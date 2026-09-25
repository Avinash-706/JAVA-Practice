package localtime;

import java.time.LocalTime;

public class PlusOperations {

    public static void plusOperations() {

        LocalTime now = LocalTime.now();
        System.out.println("Current Time : " + now);

        LocalTime plusHours = now.plusHours(2);
        System.out.println("\nplusHours(2) : " + plusHours);

        LocalTime plusMinutes = now.plusMinutes(30);
        System.out.println("plusMinutes(30) : " + plusMinutes);

        LocalTime plusSeconds = now.plusSeconds(45);
        System.out.println("plusSeconds(45) : " + plusSeconds);

        LocalTime plusNanos = now.plusNanos(1_000_000);
        System.out.println("plusNanos(1_000_000) : " + plusNanos);

        LocalTime chainedPlus = now.plusHours(1).plusMinutes(15).plusSeconds(10);
        System.out.println("\nChained Plus (1h 15m 10s) : " + chainedPlus);
    }
}
