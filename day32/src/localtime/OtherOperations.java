package localtime;

import java.time.LocalTime;

public class OtherOperations {

    public static void otherTimeOperations() {

        LocalTime now = LocalTime.now();
        System.out.println("Current Time : " + now);

        // with() operations
        LocalTime withHour = now.withHour(10);
        System.out.println("\nwithHour(10) : " + withHour);

        LocalTime withMinute = now.withMinute(45);
        System.out.println("withMinute(45) : " + withMinute);

        LocalTime withSecond = now.withSecond(30);
        System.out.println("withSecond(30) : " + withSecond);

        // Truncate
        LocalTime truncatedToMinutes = now.withSecond(0).withNano(0);
        System.out.println("\nTruncated to Minutes : " + truncatedToMinutes);

        // Constants
        System.out.println("\nLocalTime.MIN : " + LocalTime.MIN);
        System.out.println("LocalTime.MAX : " + LocalTime.MAX);
        System.out.println("LocalTime.NOON : " + LocalTime.NOON);
        System.out.println("LocalTime.MIDNIGHT : " + LocalTime.MIDNIGHT);
    }
}
