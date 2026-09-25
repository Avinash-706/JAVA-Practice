package zoneddatetime;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class OtherOperations {

    public static void otherZonedDateTimeOperations() {

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current ZonedDateTime : " + now);

        // Extract LocalDate from ZonedDateTime
        LocalDate date = now.toLocalDate();
        System.out.println("\nExtracted LocalDate : " + date);

        // Extract LocalTime from ZonedDateTime
        LocalTime time = now.toLocalTime();
        System.out.println("Extracted LocalTime : " + time);

        // Extract LocalDateTime from ZonedDateTime
        LocalDateTime localDateTime = now.toLocalDateTime();
        System.out.println("Extracted LocalDateTime : " + localDateTime);

        // Get offset
        ZoneOffset offset = now.getOffset();
        System.out.println("\nZone Offset : " + offset);

        // Get total offset seconds
        int totalSeconds = offset.getTotalSeconds();
        System.out.println("Total Offset Seconds : " + totalSeconds);

        // Combine LocalDateTime and ZoneId to create ZonedDateTime
        LocalDateTime ldt = LocalDateTime.of(2025, 12, 25, 18, 30, 45);
        ZonedDateTime combined = ZonedDateTime.of(ldt, ZoneId.of("America/New_York"));
        System.out.println("\nCombined LocalDateTime + ZoneId : " + combined);

        // Alternative way to create ZonedDateTime
        ZonedDateTime atZone = ldt.atZone(ZoneId.of("Europe/London"));
        System.out.println("Using atZone() : " + atZone);

        // Create ZonedDateTime at start of day
        LocalDate specificDate = LocalDate.of(2025, 12, 25);
        ZonedDateTime atStartOfDay = specificDate.atStartOfDay(ZoneId.of("Asia/Kolkata"));
        System.out.println("\natStartOfDay() with Zone : " + atStartOfDay);

        // Leap year check
        System.out.println("\nIs " + now.getYear() + " a Leap Year ? : " + now.toLocalDate().isLeapYear());

        // Length of month and year
        System.out.println("\nLength of Current Month : " + now.toLocalDate().lengthOfMonth() + " days");
        System.out.println("Length of Current Year  : " + now.toLocalDate().lengthOfYear() + " days");

        // Get epoch second
        long epochSecond = now.toEpochSecond();
        System.out.println("\nEpoch Second : " + epochSecond);

        // Get zone rules
        System.out.println("\nZone Rules : " + now.getZone().getRules());
    }
}
