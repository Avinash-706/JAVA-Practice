package datetimeformatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class PredefinedFormatters {

    public static void predefinedFormatters() {

        System.out.println("\n===== PREDEFINED FORMATTERS =====");
        System.out.println("Java provides built-in formatters\n");

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        // ISO Formatters
        System.out.println("=== ISO Formatters ===");
        System.out.println("ISO_LOCAL_DATE : " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("ISO_LOCAL_TIME : " + time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("ISO_LOCAL_DATE_TIME : " + dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        // Date formatters
        System.out.println("\n=== Date Formatters ===");
        System.out.println("ISO_DATE : " + date.format(DateTimeFormatter.ISO_DATE));
        System.out.println("BASIC_ISO_DATE : " + date.format(DateTimeFormatter.BASIC_ISO_DATE));
        
        // Time formatters
        System.out.println("\n=== Time Formatters ===");
        System.out.println("ISO_TIME : " + time.format(DateTimeFormatter.ISO_TIME));

        // DateTime formatters
        System.out.println("\n=== DateTime Formatters ===");
        System.out.println("ISO_DATE_TIME : " + dateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        
        // RFC formatters (for web/HTTP)
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("\n=== RFC Formatter (Web/HTTP) ===");
        System.out.println("RFC_1123_DATE_TIME : " + zonedDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME));

        System.out.println("\n\nNote: Use predefined formatters for standard formats");
        System.out.println("Create custom formatters using ofPattern() for custom formats");
    }
}
