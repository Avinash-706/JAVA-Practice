package duration;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CreationOperations {

    public static void createDuration() {

        System.out.println("\n===== CREATING DURATION =====");

        // Duration represents time-based amount (hours, minutes, seconds, nanos)
        // PT format: P = Period, T = Time separator
        // PT0S = Period of Time 0 Seconds

        // Create Duration between two Instants
        Instant start = Instant.now();
        // Simulate some work
        for (int i = 0; i < 1000000; i++) {
            Math.sqrt(i);
        }
        Instant end = Instant.now();
        Duration betweenInstants = Duration.between(start, end);
        System.out.println("Duration between two Instants : " + betweenInstants);

        // Create Duration between two LocalTimes
        LocalTime morning = LocalTime.of(9, 0);
        LocalTime evening = LocalTime.of(17, 30);
        Duration workingHours = Duration.between(morning, evening);
        System.out.println("\nDuration between 9:00 AM and 5:30 PM : " + workingHours);

        // Create Duration between two LocalDateTimes
        LocalDateTime startDateTime = LocalDateTime.of(2026, 8, 1, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2026, 8, 1, 15, 30);
        Duration meetingDuration = Duration.between(startDateTime, endDateTime);
        System.out.println("Duration of meeting : " + meetingDuration);

        // WHY LocalDate DOESN'T WORK with Duration.between() ?
        // Duration is TIME-BASED (hours, minutes, seconds)
        // LocalDate has NO TIME information (only year, month, day)
        // Error: UnsupportedTemporalTypeException - Unsupported unit: Seconds
        // LocalDate date1 = LocalDate.of(2026, 8, 1);
        // LocalDate date2 = LocalDate.of(2026, 8, 10);
        // Duration wrongDuration = Duration.between(date1, date2); // COMPILE ERROR!
        
        System.out.println("\nNote: Duration.between() does NOT work with LocalDate");
        System.out.println("Reason: Duration is time-based, LocalDate has no time component");
        System.out.println("Use Period.between() for date-based differences (days, months, years)");

        // Create Duration using of() methods
        Duration ofDays = Duration.ofDays(2);
        System.out.println("\nDuration.ofDays(2) : " + ofDays);

        Duration ofHours = Duration.ofHours(5);
        System.out.println("Duration.ofHours(5) : " + ofHours);

        Duration ofMinutes = Duration.ofMinutes(30);
        System.out.println("Duration.ofMinutes(30) : " + ofMinutes);

        Duration ofSeconds = Duration.ofSeconds(90);
        System.out.println("Duration.ofSeconds(90) : " + ofSeconds);

        Duration ofMillis = Duration.ofMillis(1500);
        System.out.println("Duration.ofMillis(1500) : " + ofMillis);

        Duration ofNanos = Duration.ofNanos(1_000_000);
        System.out.println("Duration.ofNanos(1_000_000) : " + ofNanos);

        // Create Duration using of() with ChronoUnit
        Duration ofChronoUnit = Duration.of(10, ChronoUnit.MINUTES);
        System.out.println("\nDuration.of(10, ChronoUnit.MINUTES) : " + ofChronoUnit);

        // Parse Duration from string
        Duration parsed = Duration.parse("PT15M30S");
        System.out.println("\nDuration.parse('PT15M30S') : " + parsed);
        System.out.println("Explanation: PT = Period of Time, 15M = 15 Minutes, 30S = 30 Seconds");

        // Constants
        System.out.println("\nDuration.ZERO : " + Duration.ZERO);
    }
}
