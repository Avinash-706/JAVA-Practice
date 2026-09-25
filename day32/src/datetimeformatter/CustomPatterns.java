package datetimeformatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomPatterns {

    public static void customPatterns() {

        System.out.println("\n===== CUSTOM PATTERN FORMATTERS =====");
        System.out.println("Create your own date/time formats\n");

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        // Pattern Symbols (as per reference.png):
        // y - year            M - month of year      d - day of month
        // H - hour (0-23)     h - hour (1-12)        m - minute
        // s - second          S - fraction           a - am/pm
        // E - day of week     D - day of year        F - day of week in month
        // w - week of year    W - week of month
        // k - hour (1-24)     K - hour of am/pm (0-11)
        // z - timezone name   Z - timezone offset

        System.out.println("=== Date Patterns ===");
        
        // dd/MM/yyyy format
        DateTimeFormatter pattern1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("dd/MM/yyyy : " + date.format(pattern1));

        // MM-dd-yyyy format (US style)
        DateTimeFormatter pattern2 = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        System.out.println("MM-dd-yyyy : " + date.format(pattern2));

        // Full month name
        DateTimeFormatter pattern3 = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        System.out.println("dd MMMM yyyy : " + date.format(pattern3));

        // Short month name
        DateTimeFormatter pattern4 = DateTimeFormatter.ofPattern("dd MMM yyyy");
        System.out.println("dd MMM yyyy : " + date.format(pattern4));

        // Day of week with date
        DateTimeFormatter pattern5 = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        System.out.println("EEEE, dd MMMM yyyy : " + date.format(pattern5));

        // Short day of week
        DateTimeFormatter pattern6 = DateTimeFormatter.ofPattern("E, dd MMM yyyy");
        System.out.println("E, dd MMM yyyy : " + date.format(pattern6));

        System.out.println("\n=== Time Patterns ===");

        // 24-hour format
        DateTimeFormatter pattern7 = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("HH:mm:ss : " + time.format(pattern7));

        // 12-hour format with AM/PM
        DateTimeFormatter pattern8 = DateTimeFormatter.ofPattern("hh:mm:ss a");
        System.out.println("hh:mm:ss a : " + time.format(pattern8));

        // Hour and minute only
        DateTimeFormatter pattern9 = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("HH:mm : " + time.format(pattern9));

        System.out.println("\n=== DateTime Patterns ===");

        // Full DateTime
        DateTimeFormatter pattern10 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("dd/MM/yyyy HH:mm:ss : " + dateTime.format(pattern10));

        // With AM/PM
        DateTimeFormatter pattern11 = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a");
        System.out.println("dd-MMM-yyyy hh:mm a : " + dateTime.format(pattern11));

        // Full text format
        DateTimeFormatter pattern12 = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy 'at' hh:mm a");
        System.out.println("Full text : " + dateTime.format(pattern12));

        System.out.println("\n\n⚠ IMPORTANT: Case matters!");
        System.out.println("MM = month (01-12), mm = minute (00-59)");
        System.out.println("HH = hour 0-23, hh = hour 1-12");
        System.out.println("yyyy = year (2026), yy = year (26)");
    }
}
