package datetimeformatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommonMistakesAndTips {

    public static void mistakesAndTips() {

        System.out.println("\n===== COMMON MISTAKES & TIPS =====");
        System.out.println("Learn from these common errors\n");

        LocalDate date = LocalDate.of(2026, 9, 5);
        LocalDateTime dateTime = LocalDateTime.of(2026, 9, 5, 14, 30, 45);

        // Mistake 1: MM vs mm confusion
        System.out.println("=== Mistake 1: MM (month) vs mm (minute) ===");
        System.out.println("Date: " + date);
        
        DateTimeFormatter correctFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("✓ Correct (MM for month): " + date.format(correctFormatter));
        
        // This would be wrong for date (mm is for minutes)
        System.out.println("✗ Wrong: Using 'mm' for month will cause issues");
        System.out.println("  'dd/mm/yyyy' treats mm as minute (always 00 for LocalDate)");

        // Mistake 2: Pattern doesn't match string
        System.out.println("\n\n=== Mistake 2: Pattern Mismatch ===");
        String dateString = "05-09-2026";
        System.out.println("String to parse: " + dateString);
        
        try {
            DateTimeFormatter wrongPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate parsed = LocalDate.parse(dateString, wrongPattern);
            System.out.println("Parsed: " + parsed);
        } catch (DateTimeParseException e) {
            System.out.println("✗ ERROR: Pattern 'dd/MM/yyyy' doesn't match string with '-'");
        }
        
        DateTimeFormatter correctPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsed = LocalDate.parse(dateString, correctPattern);
        System.out.println("✓ Correct: Pattern 'dd-MM-yyyy' matches the string");
        System.out.println("  Parsed: " + parsed);

        // Mistake 3: Case sensitivity
        System.out.println("\n\n=== Mistake 3: Case Sensitivity ===");
        System.out.println("DateTime: " + dateTime);
        
        DateTimeFormatter format24 = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("✓ HH (0-23): " + dateTime.format(format24));
        
        DateTimeFormatter format12 = DateTimeFormatter.ofPattern("hh:mm:ss a");
        System.out.println("✓ hh (1-12): " + dateTime.format(format12));
        
        System.out.println("✗ Don't confuse: HH ≠ hh");

        // Mistake 4: Year format
        System.out.println("\n\n=== Mistake 4: Year Format ===");
        DateTimeFormatter yyyy = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter yy = DateTimeFormatter.ofPattern("yy");
        System.out.println("✓ yyyy (4-digit): " + date.format(yyyy));
        System.out.println("✓ yy (2-digit): " + date.format(yy));
        System.out.println("Tip: Always use yyyy for clarity");

        // Mistake 5: Timezone with LocalDateTime
        System.out.println("\n\n=== Mistake 5: Timezone with LocalDateTime ===");
        System.out.println("LocalDateTime: " + dateTime);
        System.out.println("✗ ERROR: Cannot format timezone with LocalDateTime");
        System.out.println("  LocalDateTime has no timezone information");
        System.out.println("✓ Solution: Use ZonedDateTime for timezone formatting");

        // Best Practices
        System.out.println("\n\n===== BEST PRACTICES =====");
        System.out.println("1. Use MM for month, mm for minute");
        System.out.println("2. Pattern must EXACTLY match the string format");
        System.out.println("3. Case matters: HH ≠ hh, MM ≠ mm");
        System.out.println("4. Use yyyy instead of yy for years");
        System.out.println("5. Test with various date/time values");
        System.out.println("6. Document the expected format in comments");
        System.out.println("7. Use try-catch for parsing user input");
        System.out.println("8. For timezone, use XXX (offset) not z (name)");

        // Tips
        System.out.println("\n\n===== TIPS =====");
        System.out.println("✓ Use predefined formatters when possible");
        System.out.println("✓ Cache DateTimeFormatter instances (they're immutable)");
        System.out.println("✓ Test edge cases: Feb 29, month-end dates");
        System.out.println("✓ Consider locale for international apps");
        System.out.println("✓ Use ISO-8601 for APIs and databases");
        System.out.println("✓ Keep formatting consistent across your application");
    }
}
