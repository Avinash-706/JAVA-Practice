package zoneddatetime;

import java.time.ZonedDateTime;
import java.time.ZoneId;

public class WithOperations {

    public static void withOperations() {

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current ZonedDateTime : " + now);

        // with() operations for Date components
        ZonedDateTime withYear = now.withYear(2030);
        System.out.println("\nwithYear(2030) : " + withYear);

        ZonedDateTime withMonth = now.withMonth(12);
        System.out.println("withMonth(12) : " + withMonth);

        ZonedDateTime withDayOfMonth = now.withDayOfMonth(15);
        System.out.println("withDayOfMonth(15) : " + withDayOfMonth);

        ZonedDateTime withDayOfYear = now.withDayOfYear(100);
        System.out.println("withDayOfYear(100) : " + withDayOfYear);

        // with() operations for Time components
        ZonedDateTime withHour = now.withHour(10);
        System.out.println("\nwithHour(10) : " + withHour);

        ZonedDateTime withMinute = now.withMinute(45);
        System.out.println("withMinute(45) : " + withMinute);

        ZonedDateTime withSecond = now.withSecond(30);
        System.out.println("withSecond(30) : " + withSecond);

        ZonedDateTime withNano = now.withNano(123456789);
        System.out.println("withNano(123456789) : " + withNano);

        // Chained with() operations
        ZonedDateTime chainedWith = now.withYear(2025).withMonth(6).withDayOfMonth(15)
                                        .withHour(18).withMinute(30).withSecond(0);
        System.out.println("\nChained With (2025-06-15 18:30:00) : " + chainedWith);

        // Truncate to specific units
        ZonedDateTime truncatedToMinutes = now.withSecond(0).withNano(0);
        System.out.println("\nTruncated to Minutes : " + truncatedToMinutes);

        ZonedDateTime truncatedToHours = now.withMinute(0).withSecond(0).withNano(0);
        System.out.println("Truncated to Hours : " + truncatedToHours);

        ZonedDateTime truncatedToDays = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
        System.out.println("Truncated to Days (Midnight) : " + truncatedToDays);
    }
}
