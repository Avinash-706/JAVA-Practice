package zoneddatetime;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class ZoneID {

    public static void printZoneId() {

        System.out.println("\n===== ZONE ID OPERATIONS =====");

        // Get system default zone
        ZoneId systemZone = ZoneId.systemDefault();
        System.out.println("System Default Zone : " + systemZone);

        // Create specific zones
        ZoneId indiaZone = ZoneId.of("Asia/Kolkata");
        System.out.println("\nZoneId.of('Asia/Kolkata') : " + indiaZone);

        ZoneId newYorkZone = ZoneId.of("America/New_York");
        System.out.println("ZoneId.of('America/New_York') : " + newYorkZone);

        ZoneId tokyoZone = ZoneId.of("Asia/Tokyo");
        System.out.println("ZoneId.of('Asia/Tokyo') : " + tokyoZone);

        ZoneId londonZone = ZoneId.of("Europe/London");
        System.out.println("ZoneId.of('Europe/London') : " + londonZone);

        ZoneId parisZone = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId.of('Europe/Paris') : " + parisZone);

        // Get all available zone IDs (600+ zones)
        Set<String> availableZones = ZoneId.getAvailableZoneIds();
        System.out.println("\nTotal Available Zones : " + availableZones.size());
        
        System.out.println("\nSample Major Zones :");
        availableZones.stream()
                .filter(zone -> zone.startsWith("America/") || zone.startsWith("Asia/") || zone.startsWith("Europe/"))
                .limit(10)
                .forEach(zone -> System.out.println("  - " + zone));

        // Get zone from ZonedDateTime
        ZonedDateTime now = ZonedDateTime.now();
        ZoneId extractedZone = now.getZone();
        System.out.println("\nExtracted Zone from ZonedDateTime : " + extractedZone);

        // Create ZonedDateTime with different zones
        System.out.println("\n\nCurrent Time in Different Zones :");
        ZonedDateTime indiaTime = ZonedDateTime.now(indiaZone);
        System.out.println("India (Asia/Kolkata) : " + indiaTime);

        ZonedDateTime newYorkTime = ZonedDateTime.now(newYorkZone);
        System.out.println("New York (America/New_York) : " + newYorkTime);

        ZonedDateTime tokyoTime = ZonedDateTime.now(tokyoZone);
        System.out.println("Tokyo (Asia/Tokyo) : " + tokyoTime);

        ZonedDateTime londonTime = ZonedDateTime.now(londonZone);
        System.out.println("London (Europe/London) : " + londonTime);
    }
}
