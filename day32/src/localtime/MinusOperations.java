package localtime;

import java.time.LocalTime;

public class MinusOperations {

    public static void minusOperations(){

        LocalTime now = LocalTime.now();
        System.out.println("Current Time : " + now);

        LocalTime minusHours = now.minusHours(1);
        System.out.println("\nminusHours(1) : " + minusHours);

        LocalTime minusMinutes = now.minusMinutes(20);
        System.out.println("minusMinutes(20) : " + minusMinutes);

        LocalTime minusSeconds = now.minusSeconds(30);
        System.out.println("minusSeconds(30) : " + minusSeconds);

        LocalTime minusNanos = now.minusNanos(500_000);
        System.out.println("minusNanos(500_000) : " + minusNanos);

        LocalTime chainedMinus = now.minusHours(2).minusMinutes(10).minusSeconds(5);
        System.out.println("\nChained Minus (2h 10m 5s) : " + chainedMinus);
    }
}
