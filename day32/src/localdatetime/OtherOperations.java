package localdatetime;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class OtherOperations {

    public static void otherDateTimeOperations() {

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current DateTime : " + now);

        // Extract LocalDate from LocalDateTime
        LocalDate date = now.toLocalDate();
        System.out.println("\nExtracted LocalDate : " + date);

        // Extract LocalTime from LocalDateTime
        LocalTime time = now.toLocalTime();
        System.out.println("Extracted LocalTime : " + time);

        // Combine LocalDate and LocalTime to create LocalDateTime
        LocalDate customDate = LocalDate.of(2025, 12, 25);
        LocalTime customTime = LocalTime.of(18, 30, 45);
        LocalDateTime combined = LocalDateTime.of(customDate, customTime);
        System.out.println("\nCombined LocalDate + LocalTime : " + combined);

        // Alternative way to combine
        LocalDateTime combinedAlt = customDate.atTime(customTime);
        System.out.println("Combined using atTime() : " + combinedAlt);

        // Create LocalDateTime at specific time
        LocalDateTime atStartOfDay = customDate.atStartOfDay();
        System.out.println("\natStartOfDay() : " + atStartOfDay);

        LocalDateTime atTime = customDate.atTime(12, 0);
        System.out.println("atTime(12, 0) : " + atTime);

        // Constants
        System.out.println("\nLocalDateTime.MIN : " + LocalDateTime.MIN);
        System.out.println("LocalDateTime.MAX : " + LocalDateTime.MAX);

        // Leap year check
        System.out.println("\nIs " + now.getYear() + " a Leap Year ? : " + now.toLocalDate().isLeapYear());

        // Length of month and year
        System.out.println("\nLength of Current Month : " + now.toLocalDate().lengthOfMonth() + " days");
        System.out.println("Length of Current Year  : " + now.toLocalDate().lengthOfYear() + " days");
    }
}
