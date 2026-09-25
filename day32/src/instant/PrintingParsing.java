package instant;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PrintingParsing {

    public static void printAndParseInstant() {

        // Instant represents a point in time (timestamp) in UTC
        // It's the number of seconds/nanoseconds since January 1, 1970 00:00:00 UTC (Unix Epoch)
        Instant now = Instant.now();
        System.out.println("Current Instant (UTC) : " + now);

        // Get epoch seconds and milliseconds
        long epochSecond = now.getEpochSecond();
        long epochMilli = now.toEpochMilli();
        int nano = now.getNano();

        System.out.println("\nDetails : ");
        System.out.println("Epoch Seconds (since 1970-01-01 UTC) : " + epochSecond);
        System.out.println("Epoch Milliseconds : " + epochMilli);
        System.out.println("Nano Adjustment : " + nano);

        // System.currentTimeMillis() gives same as Instant
        long currentMillis = System.currentTimeMillis();
        System.out.println("\nSystem.currentTimeMillis() : " + currentMillis);
        System.out.println("Instant.now().toEpochMilli() : " + epochMilli);

        // Create Instant from epoch seconds
        Instant fromEpochSecond = Instant.ofEpochSecond(1609459200);
        System.out.println("\nInstant from Epoch Second (1609459200) : " + fromEpochSecond);

        // Create Instant from epoch milliseconds
        Instant fromEpochMilli = Instant.ofEpochMilli(1609459200000L);
        System.out.println("Instant from Epoch Milli (1609459200000) : " + fromEpochMilli);

        // Parsing (ISO-8601 format)
        // Z means UTC timezone (Zero offset from UTC)
        String instantString = "2023-04-24T03:18:17.448Z";
        Instant parsedInstant = Instant.parse(instantString);
        System.out.println("\nParsed Instant (ISO-8601 with Z) : " + parsedInstant);
        System.out.println("Note: Z means UTC timezone (Zero offset)");

        // Constants
        System.out.println("\nInstant.EPOCH (1970-01-01 00:00:00 UTC) : " + Instant.EPOCH);
        System.out.println("Instant.MIN : " + Instant.MIN);
        System.out.println("Instant.MAX : " + Instant.MAX);

        // Convert to ZonedDateTime to see in different timezone
        ZonedDateTime inIndia = now.atZone(ZoneId.of("Asia/Kolkata"));
        System.out.println("\nCurrent Instant in India Timezone : " + inIndia);

        ZonedDateTime inNewYork = now.atZone(ZoneId.of("America/New_York"));
        System.out.println("Current Instant in New York Timezone : " + inNewYork);
    }
}
