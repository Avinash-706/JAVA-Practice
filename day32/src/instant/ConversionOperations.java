package instant;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class ConversionOperations {

    public static void conversionDemo() {

        System.out.println("\n===== INSTANT CONVERSION =====");

        Instant now = Instant.now();
        System.out.println("Current Instant (UTC) : " + now);

        // Convert Instant to ZonedDateTime (with timezone)
        ZonedDateTime indiaTime = now.atZone(ZoneId.of("Asia/Kolkata"));
        System.out.println("\nConverted to ZonedDateTime (India) : " + indiaTime);

        ZonedDateTime newYorkTime = now.atZone(ZoneId.of("America/New_York"));
        System.out.println("Converted to ZonedDateTime (New York) : " + newYorkTime);

        ZonedDateTime utcTime = now.atZone(ZoneId.of("UTC"));
        System.out.println("Converted to ZonedDateTime (UTC) : " + utcTime);

        // Convert Instant to OffsetDateTime
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.UTC);
        System.out.println("\nConverted to OffsetDateTime (UTC) : " + offsetDateTime);

        OffsetDateTime offsetIndia = now.atOffset(ZoneOffset.ofHoursMinutes(5, 30));
        System.out.println("Converted to OffsetDateTime (+05:30) : " + offsetIndia);

        // Convert ZonedDateTime to Instant
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        Instant fromZoned = zdt.toInstant();
        System.out.println("\n\nZonedDateTime : " + zdt);
        System.out.println("Converted to Instant : " + fromZoned);

        // Convert Instant to old Java Date (for legacy code)
        Date date = Date.from(now);
        System.out.println("\nConverted to java.util.Date : " + date);

        // Convert old Java Date to Instant
        Date legacyDate = new Date();
        Instant fromDate = legacyDate.toInstant();
        System.out.println("\njava.util.Date : " + legacyDate);
        System.out.println("Converted to Instant : " + fromDate);

        // Note: Cannot directly convert to LocalDateTime without timezone
        // Must specify timezone first
        System.out.println("\n\nNote: Instant cannot convert directly to LocalDateTime");
        System.out.println("Must use atZone() to add timezone first");
        LocalDateTime localDateTime = now.atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("Instant -> ZonedDateTime -> LocalDateTime : " + localDateTime);
    }
}
