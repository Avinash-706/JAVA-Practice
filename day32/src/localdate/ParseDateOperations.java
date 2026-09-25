package localdate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseDateOperations {

    public static void parseDemo() {

        System.out.println("\n===== PARSE DATE OPERATIONS =====");

        // Default ISO format (yyyy-MM-dd)
        String isoDate = "2026-02-08";
        LocalDate parsedIsoDate = LocalDate.parse(isoDate);

        System.out.println("String Date (ISO) : " + isoDate);
        System.out.println("Parsed LocalDate  : " + parsedIsoDate);

        // Custom format (dd-MM-yyyy)
        String customDateStr = "07-02-1990";
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate customParsedDate =
                LocalDate.parse(customDateStr, formatter);

        System.out.println("\nString Date (Custom) : " + customDateStr);
        System.out.println("Parsed LocalDate     : " + customParsedDate);

        // Extract values
        System.out.println("\nExtracted Details : ");
        System.out.println("Day   : " + customParsedDate.getDayOfMonth());
        System.out.println("Month : " + customParsedDate.getMonth());
        System.out.println("Year  : " + customParsedDate.getYear());
    }
}
