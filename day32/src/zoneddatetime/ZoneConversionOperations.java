package zoneddatetime;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.Instant;

public class ZoneConversionOperations {

    public static void zoneConversionDemo() {

        System.out.println("\n===== ZONE CONVERSION =====");

        // Current time in India
        ZonedDateTime indiaTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Original Time (India) : " + indiaTime);

        // Convert to New York timezone (same instant)
        ZonedDateTime newYorkTime = indiaTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("\nConverted to New York (Same Instant) : " + newYorkTime);

        // Convert to Tokyo timezone (same instant)
        ZonedDateTime tokyoTime = indiaTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println("Converted to Tokyo (Same Instant) : " + tokyoTime);

        // Convert to London timezone (same instant)
        ZonedDateTime londonTime = indiaTime.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println("Converted to London (Same Instant) : " + londonTime);

        // Change timezone but keep same local time (NOT same instant)
        ZonedDateTime withZoneSameLocal = indiaTime.withZoneSameLocal(ZoneId.of("America/New_York"));
        System.out.println("\n\nChanged to New York Zone (Same Local Time) : " + withZoneSameLocal);
        System.out.println("Note: Clock time remains same, but it's a different instant");

        // Convert ZonedDateTime to LocalDateTime (loses timezone info)
        LocalDateTime localDateTime = indiaTime.toLocalDateTime();
        System.out.println("\n\nConverted to LocalDateTime (No Zone) : " + localDateTime);

        // Convert ZonedDateTime to Instant (UTC)
        Instant instant = indiaTime.toInstant();
        System.out.println("Converted to Instant (UTC) : " + instant);

        // Create ZonedDateTime from LocalDateTime
        LocalDateTime ldt = LocalDateTime.of(2025, 6, 15, 18, 30);
        ZonedDateTime zdtFromLocal = ldt.atZone(ZoneId.of("Asia/Kolkata"));
        System.out.println("\n\nZonedDateTime from LocalDateTime : " + zdtFromLocal);

        // Create ZonedDateTime from Instant
        Instant now = Instant.now();
        ZonedDateTime zdtFromInstant = now.atZone(ZoneId.of("America/New_York"));
        System.out.println("ZonedDateTime from Instant : " + zdtFromInstant);
    }
}
