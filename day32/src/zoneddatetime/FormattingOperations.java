package zoneddatetime;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FormattingOperations {

    public static void formatZonedDateTime() {

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Original ZonedDateTime : " + now);

        // ISO format (default)
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        System.out.println("\nISO Format : " + now.format(isoFormatter));

        // Custom format - Date, Time and Zone
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z");
        System.out.println("\nFormatted (dd-MM-yyyy HH:mm:ss z) : " + now.format(formatter1));

        // With Zone ID
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a VV");
        System.out.println("Formatted (dd/MM/yyyy hh:mm a VV) : " + now.format(formatter2));

        // Full month and day names with timezone
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm:ss z");
        System.out.println("\nFormatted (Full Names) : " + now.format(formatter3));

        // Custom text in format
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy 'Year' MM 'Month' dd 'Day' HH 'hours' mm 'minutes' z");
        System.out.println("Formatted (Custom Text) : " + now.format(formatter4));

        // Short format with timezone
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm z");
        System.out.println("\nFormatted (Short) : " + now.format(formatter5));

        // With offset
        DateTimeFormatter formatter6 = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a XXX");
        System.out.println("Formatted (With Offset) : " + now.format(formatter6));

        // RFC 1123 format
        DateTimeFormatter rfc1123 = DateTimeFormatter.RFC_1123_DATE_TIME;
        System.out.println("\nRFC 1123 Format : " + now.format(rfc1123));
    }
}
