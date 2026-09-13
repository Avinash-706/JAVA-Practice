package localdatetime;

import java.time.LocalDateTime;

public class WithOperations {

    public static void withOperations() {

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current DateTime : " + now);

        // with() operations for Date components
        LocalDateTime withYear = now.withYear(2030);
        System.out.println("\nwithYear(2030) : " + withYear);

        LocalDateTime withMonth = now.withMonth(12);
        System.out.println("withMonth(12) : " + withMonth);

        LocalDateTime withDayOfMonth = now.withDayOfMonth(15);
        System.out.println("withDayOfMonth(15) : " + withDayOfMonth);

        LocalDateTime withDayOfYear = now.withDayOfYear(100);
        System.out.println("withDayOfYear(100) : " + withDayOfYear);

        // with() operations for Time components
        LocalDateTime withHour = now.withHour(10);
        System.out.println("\nwithHour(10) : " + withHour);

        LocalDateTime withMinute = now.withMinute(45);
        System.out.println("withMinute(45) : " + withMinute);

        LocalDateTime withSecond = now.withSecond(30);
        System.out.println("withSecond(30) : " + withSecond);

        LocalDateTime withNano = now.withNano(123456789);
        System.out.println("withNano(123456789) : " + withNano);

        // Chained with() operations
        LocalDateTime chainedWith = now.withYear(2025).withMonth(6).withDayOfMonth(15)
                                        .withHour(18).withMinute(30).withSecond(0);
        System.out.println("\nChained With (2025-06-15 18:30:00) : " + chainedWith);

        // Truncate to specific units
        LocalDateTime truncatedToMinutes = now.withSecond(0).withNano(0);
        System.out.println("\nTruncated to Minutes : " + truncatedToMinutes);

        LocalDateTime truncatedToHours = now.withMinute(0).withSecond(0).withNano(0);
        System.out.println("Truncated to Hours : " + truncatedToHours);

        LocalDateTime truncatedToDays = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
        System.out.println("Truncated to Days (Midnight) : " + truncatedToDays);
    }
}
