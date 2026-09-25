package instant;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TruncateOperations {

    public static void truncateDemo() {

        System.out.println("\n===== TRUNCATE OPERATIONS =====");

        Instant now = Instant.now();
        System.out.println("Original Instant : " + now);

        // Truncate to seconds (removes nanoseconds)
        Instant truncatedToSeconds = now.truncatedTo(ChronoUnit.SECONDS);
        System.out.println("\nTruncated to Seconds : " + truncatedToSeconds);

        // Truncate to millis
        Instant truncatedToMillis = now.truncatedTo(ChronoUnit.MILLIS);
        System.out.println("Truncated to Millis : " + truncatedToMillis);

        // Truncate to minutes
        Instant truncatedToMinutes = now.truncatedTo(ChronoUnit.MINUTES);
        System.out.println("Truncated to Minutes : " + truncatedToMinutes);

        // Truncate to hours
        Instant truncatedToHours = now.truncatedTo(ChronoUnit.HOURS);
        System.out.println("Truncated to Hours : " + truncatedToHours);

        // Truncate to days
        Instant truncatedToDays = now.truncatedTo(ChronoUnit.DAYS);
        System.out.println("Truncated to Days : " + truncatedToDays);

        System.out.println("\nNote: Truncating removes the precision below the specified unit");
    }
}
