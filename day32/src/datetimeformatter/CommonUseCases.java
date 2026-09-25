package datetimeformatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CommonUseCases {

    public static void commonUseCases() {

        System.out.println("\n===== COMMON USE CASES =====");
        System.out.println("Real-world DateTimeFormatter applications\n");

        // Use Case 1: Display date in user-friendly format
        System.out.println("=== Use Case 1: User-Friendly Display ===");
        LocalDate today = LocalDate.now();
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        System.out.println("Today is: " + today.format(displayFormatter));

        // Use Case 2: Log timestamp
        System.out.println("\n=== Use Case 2: Log Timestamp ===");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter logFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Log Entry: [" + now.format(logFormatter) + "] Application started");

        // Use Case 3: File naming with timestamp
        System.out.println("\n=== Use Case 3: File Naming ===");
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String filename = "backup_" + now.format(fileFormatter) + ".zip";
        System.out.println("Generated filename: " + filename);

        // Use Case 4: Invoice/Receipt formatting
        System.out.println("\n=== Use Case 4: Invoice Date ===");
        LocalDate invoiceDate = LocalDate.of(2026, 9, 5);
        DateTimeFormatter invoiceFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        System.out.println("Invoice Date: " + invoiceDate.format(invoiceFormatter));

        // Use Case 5: Meeting reminder
        System.out.println("\n=== Use Case 5: Meeting Reminder ===");
        LocalDateTime meetingTime = LocalDateTime.of(2026, 9, 10, 15, 30);
        DateTimeFormatter meetingFormatter = DateTimeFormatter.ofPattern("EEEE, MMM dd 'at' hh:mm a");
        System.out.println("Meeting scheduled for: " + meetingTime.format(meetingFormatter));

        // Use Case 6: API response formatting (ISO-8601)
        System.out.println("\n=== Use Case 6: API Response (ISO-8601) ===");
        ZonedDateTime apiTime = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter apiFormatter = DateTimeFormatter.ISO_INSTANT;
        System.out.println("API Response: " + apiTime.toInstant().toString());

        // Use Case 7: Database timestamp
        System.out.println("\n=== Use Case 7: Database Timestamp ===");
        DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println("DB Timestamp: " + now.format(dbFormatter));

        // Use Case 8: Email date formatting
        System.out.println("\n=== Use Case 8: Email Date ===");
        ZonedDateTime emailTime = ZonedDateTime.now();
        DateTimeFormatter emailFormatter = DateTimeFormatter.RFC_1123_DATE_TIME;
        System.out.println("Email Date: " + emailTime.format(emailFormatter));

        // Use Case 9: Parsing user input
        System.out.println("\n=== Use Case 9: Parse User Input ===");
        String userInput = "05/09/2026";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate = LocalDate.parse(userInput, inputFormatter);
        System.out.println("User entered: " + userInput);
        System.out.println("Parsed as: " + parsedDate);
        System.out.println("Stored in DB: " + parsedDate); // Stores as ISO format

        // Use Case 10: Birthday reminder
        System.out.println("\n=== Use Case 10: Birthday Reminder ===");
        LocalDate birthday = LocalDate.of(1992, 2, 7);
        DateTimeFormatter birthdayFormatter = DateTimeFormatter.ofPattern("MMMM dd");
        System.out.println("Birthday reminder: " + birthday.format(birthdayFormatter));

        System.out.println("\n\n✓ DateTimeFormatter is essential for:");
        System.out.println("  - User interface display");
        System.out.println("  - Log files");
        System.out.println("  - File naming");
        System.out.println("  - Database operations");
        System.out.println("  - API responses");
        System.out.println("  - Parsing user input");
    }
}
