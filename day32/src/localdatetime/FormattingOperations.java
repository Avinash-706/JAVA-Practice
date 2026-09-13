package localdatetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormattingOperations {

    public static void formatDateTime() {

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Original DateTime : " + now);

        // ISO format (default)
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        System.out.println("\nISO Format : " + now.format(isoFormatter));

        // Custom format - Date and Time
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("\nFormatted (dd-MM-yyyy HH:mm:ss) : " + now.format(formatter1));

        // 12-hour format with AM/PM
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        System.out.println("Formatted (dd/MM/yyyy hh:mm a) : " + now.format(formatter2));

        // Full month and day names
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm:ss");
        System.out.println("\nFormatted (Full Names) : " + now.format(formatter3));

        // Custom text in format
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy 'Year' MM 'Month' dd 'Day' HH 'hours' mm 'minutes'");
        System.out.println("Formatted (Custom Text) : " + now.format(formatter4));

        // Short format
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        System.out.println("\nFormatted (Short) : " + now.format(formatter5));

        // Indian style format
        DateTimeFormatter formatter6 = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a");
        System.out.println("Formatted (Indian Style) : " + now.format(formatter6));
    }
}
