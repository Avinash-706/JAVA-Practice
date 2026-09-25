package duration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class GetOperations {

    public static void getValues() {

        System.out.println("\n===== GET VALUES FROM DURATION =====");

        Duration duration = Duration.parse("PT2H30M45S");
        System.out.println("Duration : " + duration);

        // Get total values
        long seconds = duration.getSeconds();
        System.out.println("\nTotal Seconds : " + seconds);

        int nanos = duration.getNano();
        System.out.println("Nano Adjustment : " + nanos);

        // Convert to different units
        long toDays = duration.toDays();
        System.out.println("\nConvert to Days : " + toDays);

        long toHours = duration.toHours();
        System.out.println("Convert to Hours : " + toHours);

        long toMinutes = duration.toMinutes();
        System.out.println("Convert to Minutes : " + toMinutes);

        long toSeconds = duration.toSeconds();
        System.out.println("Convert to Seconds : " + toSeconds);

        long toMillis = duration.toMillis();
        System.out.println("Convert to Milliseconds : " + toMillis);

        long toNanos = duration.toNanos();
        System.out.println("Convert to Nanoseconds : " + toNanos);

        // Extract parts using ChronoUnit
        long daysPart = duration.toDaysPart();
        int hoursPart = duration.toHoursPart();
        int minutesPart = duration.toMinutesPart();
        int secondsPart = duration.toSecondsPart();
        int nanosPart = duration.toNanosPart();

        System.out.println("\n\nExtract Individual Parts :");
        System.out.println("Days Part : " + daysPart);
        System.out.println("Hours Part (0-23) : " + hoursPart);
        System.out.println("Minutes Part (0-59) : " + minutesPart);
        System.out.println("Seconds Part (0-59) : " + secondsPart);
        System.out.println("Nanos Part (0-999,999,999) : " + nanosPart);

        System.out.println("\nFormatted: " + daysPart + " days, " + hoursPart + " hours, " 
                          + minutesPart + " minutes, " + secondsPart + " seconds");

        // Example with larger duration
        Duration longDuration = Duration.ofHours(50);
        System.out.println("\n\nLong Duration : " + longDuration);
        System.out.println("Total Hours : " + longDuration.toHours());
        System.out.println("Days Part : " + longDuration.toDaysPart());
        System.out.println("Hours Part : " + longDuration.toHoursPart());
        System.out.println("Formatted: " + longDuration.toDaysPart() + " days and " 
                          + longDuration.toHoursPart() + " hours");
    }
}
