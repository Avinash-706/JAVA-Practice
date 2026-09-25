package datetimeformatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FormattingOperations {

    public static void formattingDemo() {

        System.out.println("\n===== FORMATTING OPERATIONS =====");
        System.out.println("Convert Date/Time objects to String\n");

        // Format LocalDate
        System.out.println("=== Formatting LocalDate ===");
        LocalDate date = LocalDate.of(2026, 9, 5);
        
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate1 = date.format(formatter1);
        System.out.println("LocalDate : " + date);
        System.out.println("Formatted (dd/MM/yyyy) : " + formattedDate1);

        // Format LocalTime
        System.out.println("\n=== Formatting LocalTime ===");
        LocalTime time = LocalTime.of(14, 30, 45);
        
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = time.format(formatter2);
        System.out.println("LocalTime : " + time);
        System.out.println("Formatted (hh:mm:ss a) : " + formattedTime);

        // Format LocalDateTime
        System.out.println("\n=== Formatting LocalDateTime ===");
        LocalDateTime dateTime = LocalDateTime.of(2026, 9, 5, 14, 30, 45);
        
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a");
        String formattedDateTime = dateTime.format(formatter3);
        System.out.println("LocalDateTime : " + dateTime);
        System.out.println("Formatted (dd-MMM-yyyy hh:mm a) : " + formattedDateTime);

        // Format ZonedDateTime with timezone
        System.out.println("\n=== Formatting ZonedDateTime ===");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2026, 9, 5, 14, 30, 45, 0, ZoneId.of("Asia/Kolkata"));
        
        // With timezone name
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z");
        String formatted4 = zonedDateTime.format(formatter4);
        System.out.println("ZonedDateTime : " + zonedDateTime);
        System.out.println("Formatted (with timezone name) : " + formatted4);

        // With timezone offset (recommended)
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss XXX");
        String formatted5 = zonedDateTime.format(formatter5);
        System.out.println("Formatted (with offset XXX) : " + formatted5);

        System.out.println("\n⚠ IMPORTANT for ZonedDateTime:");
        System.out.println("Use XXX for offset (+05:30) instead of z (IST)");
        System.out.println("Reason: 'IST' can be India or Israel, offset is unambiguous");

        // Indian format
        System.out.println("\n=== Indian Date Format ===");
        DateTimeFormatter indianFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Indian Style : " + date.format(indianFormat));

        // US format
        System.out.println("\n=== US Date Format ===");
        DateTimeFormatter usFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println("US Style : " + date.format(usFormat));
    }
}
