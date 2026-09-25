package datetimeformatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ParsingOperations {

    public static void parsingDemo() {

        System.out.println("\n===== PARSING OPERATIONS =====");
        System.out.println("Convert String to Date/Time objects\n");

        // Case 1: Default parsing (ISO format)
        System.out.println("=== Case 1: Default ISO Format ===");
        String isoDate = "2026-09-05";
        LocalDate parsedDate1 = LocalDate.parse(isoDate);
        System.out.println("String : " + isoDate);
        System.out.println("Parsed LocalDate : " + parsedDate1);

        // Case 2: Custom format parsing (dd/MM/yyyy)
        System.out.println("\n=== Case 2: Custom Format (dd/MM/yyyy) ===");
        String customDateStr = "05/09/2026";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate2 = LocalDate.parse(customDateStr, formatter1);
        System.out.println("String : " + customDateStr);
        System.out.println("Pattern : dd/MM/yyyy");
        System.out.println("Parsed LocalDate : " + parsedDate2);

        // Case 3: Parsing with slashes (/)
        System.out.println("\n=== Case 3: String with / separator ===");
        String dateWithSlash = "25/04/2004";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate3 = LocalDate.parse(dateWithSlash, formatter2);
        System.out.println("String : " + dateWithSlash);
        System.out.println("Cannot parse with LocalDate.parse() alone");
        System.out.println("Need DateTimeFormatter with pattern dd/MM/yyyy");
        System.out.println("Parsed LocalDate : " + parsedDate3);

        // Parsing LocalTime
        System.out.println("\n=== Parsing LocalTime ===");
        String timeStr = "14:30:45";
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime parsedTime = LocalTime.parse(timeStr, formatter3);
        System.out.println("String : " + timeStr);
        System.out.println("Pattern : HH:mm:ss");
        System.out.println("Parsed LocalTime : " + parsedTime);

        // Parsing LocalDateTime
        System.out.println("\n=== Parsing LocalDateTime ===");
        String dateTimeStr = "05-Sep-2026 14:30";
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm", java.util.Locale.ENGLISH);
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeStr, formatter4);
        System.out.println("String : " + dateTimeStr);
        System.out.println("Pattern : dd-MMM-yyyy HH:mm");
        System.out.println("Parsed LocalDateTime : " + parsedDateTime);

        // Parsing with full month name
        System.out.println("\n=== Parsing with Full Month Name ===");
        String fullMonthStr = "05 September 2026";
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("dd MMMM yyyy", java.util.Locale.ENGLISH);
        LocalDate parsedDate4 = LocalDate.parse(fullMonthStr, formatter5);
        System.out.println("String : " + fullMonthStr);
        System.out.println("Pattern : dd MMMM yyyy");
        System.out.println("Parsed LocalDate : " + parsedDate4);

        // Important Note
        System.out.println("\n\n⚠ CRITICAL: Pattern must match the string EXACTLY!");
        System.out.println("String format: '05/09/2026' needs pattern: 'dd/MM/yyyy'");
        System.out.println("String format: '05-09-2026' needs pattern: 'dd-MM-yyyy'");
        System.out.println("Mismatch will throw DateTimeParseException!");
        
        System.out.println("\n⚠ Case Sensitivity:");
        System.out.println("MM = month (09), mm = minute (09)");
        System.out.println("Wrong pattern will cause errors or wrong results!");
    }
}
