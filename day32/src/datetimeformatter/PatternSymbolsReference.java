package datetimeformatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PatternSymbolsReference {

    public static void patternSymbolsDemo() {

        System.out.println("\n===== PATTERN SYMBOLS REFERENCE =====");
        System.out.println("Complete guide to DateTimeFormatter symbols\n");

        LocalDate date = LocalDate.of(2026, 2, 7);
        LocalTime time = LocalTime.of(14, 5, 9);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.of("Asia/Kolkata"));

        System.out.println("Sample Date: " + date);
        System.out.println("Sample Time: " + time);
        System.out.println("Sample DateTime: " + dateTime);
        System.out.println();

        // Year patterns
        System.out.println("=== YEAR PATTERNS ===");
        System.out.println("y    : " + date.format(DateTimeFormatter.ofPattern("y")) + " (year)");
        System.out.println("yy   : " + date.format(DateTimeFormatter.ofPattern("yy")) + " (2-digit year)");
        System.out.println("yyyy : " + date.format(DateTimeFormatter.ofPattern("yyyy")) + " (4-digit year)");

        // Month patterns
        System.out.println("\n=== MONTH PATTERNS ===");
        System.out.println("M    : " + date.format(DateTimeFormatter.ofPattern("M")) + " (month)");
        System.out.println("MM   : " + date.format(DateTimeFormatter.ofPattern("MM")) + " (2-digit month)");
        System.out.println("MMM  : " + date.format(DateTimeFormatter.ofPattern("MMM")) + " (short month name)");
        System.out.println("MMMM : " + date.format(DateTimeFormatter.ofPattern("MMMM")) + " (full month name)");

        // Day patterns
        System.out.println("\n=== DAY PATTERNS ===");
        System.out.println("d    : " + date.format(DateTimeFormatter.ofPattern("d")) + " (day of month)");
        System.out.println("dd   : " + date.format(DateTimeFormatter.ofPattern("dd")) + " (2-digit day)");
        System.out.println("D    : " + date.format(DateTimeFormatter.ofPattern("D")) + " (day of year)");
        System.out.println("E    : " + date.format(DateTimeFormatter.ofPattern("E")) + " (day of week short)");
        System.out.println("EEEE : " + date.format(DateTimeFormatter.ofPattern("EEEE")) + " (day of week full)");

        // Hour patterns
        System.out.println("\n=== HOUR PATTERNS ===");
        System.out.println("H    : " + time.format(DateTimeFormatter.ofPattern("H")) + " (hour 0-23)");
        System.out.println("HH   : " + time.format(DateTimeFormatter.ofPattern("HH")) + " (hour 00-23)");
        System.out.println("h    : " + time.format(DateTimeFormatter.ofPattern("h")) + " (hour 1-12)");
        System.out.println("hh   : " + time.format(DateTimeFormatter.ofPattern("hh")) + " (hour 01-12)");
        System.out.println("k    : " + time.format(DateTimeFormatter.ofPattern("k")) + " (hour 1-24)");
        System.out.println("K    : " + time.format(DateTimeFormatter.ofPattern("K")) + " (hour 0-11)");

        // Minute and second patterns
        System.out.println("\n=== MINUTE & SECOND PATTERNS ===");
        System.out.println("m    : " + time.format(DateTimeFormatter.ofPattern("m")) + " (minute)");
        System.out.println("mm   : " + time.format(DateTimeFormatter.ofPattern("mm")) + " (minute 2-digit)");
        System.out.println("s    : " + time.format(DateTimeFormatter.ofPattern("s")) + " (second)");
        System.out.println("ss   : " + time.format(DateTimeFormatter.ofPattern("ss")) + " (second 2-digit)");
        System.out.println("S    : " + time.format(DateTimeFormatter.ofPattern("S")) + " (fraction of second)");

        // AM/PM
        System.out.println("\n=== AM/PM PATTERN ===");
        System.out.println("a    : " + time.format(DateTimeFormatter.ofPattern("a")) + " (AM/PM marker)");

        // Week patterns
        System.out.println("\n=== WEEK PATTERNS ===");
        System.out.println("w    : " + date.format(DateTimeFormatter.ofPattern("w")) + " (week of year)");
        System.out.println("W    : " + date.format(DateTimeFormatter.ofPattern("W")) + " (week of month)");
        System.out.println("F    : " + date.format(DateTimeFormatter.ofPattern("F")) + " (day of week in month)");

        // Timezone patterns
        System.out.println("\n=== TIMEZONE PATTERNS ===");
        System.out.println("z    : " + zonedDateTime.format(DateTimeFormatter.ofPattern("z")) + " (timezone name)");
        System.out.println("Z    : " + zonedDateTime.format(DateTimeFormatter.ofPattern("Z")) + " (timezone offset)");
        System.out.println("XXX  : " + zonedDateTime.format(DateTimeFormatter.ofPattern("XXX")) + " (timezone offset with colon)");

        System.out.println("\n\n===== QUICK REFERENCE =====");
        System.out.println("y - year            M - month of year      d - day of month");
        System.out.println("H - hour (0-23)     h - hour (1-12)        m - minute");
        System.out.println("s - second          S - fraction           a - am/pm");
        System.out.println("E - day of week     D - day of year        F - day of week in month");
        System.out.println("w - week of year    W - week of month");
        System.out.println("k - hour (1-24)     K - hour (0-11)");
        System.out.println("z - timezone name   Z - timezone offset    XXX - offset with colon");
    }
}
