package duration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class LocalDateVsLocalDateTimeDemo {

    public static void demonstrateError() {

        System.out.println("\n===== WHY LocalDate FAILS with Duration.between() =====");

        // LocalDateTime works PERFECTLY with Duration.between()
        LocalDateTime startDateTime = LocalDateTime.of(2026, 8, 1, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2026, 8, 1, 15, 30);
        Duration duration = Duration.between(startDateTime, endDateTime);
        System.out.println("\nLocalDateTime works with Duration:");
        System.out.println("Start: " + startDateTime);
        System.out.println("End  : " + endDateTime);
        System.out.println("Duration: " + duration);
        System.out.println("Reason: LocalDateTime has TIME component (hours, minutes, seconds)");

        // LocalDate FAILS with Duration.between()
        System.out.println("\n\nLocalDate FAILS with Duration:");
        LocalDate startDate = LocalDate.of(2026, 8, 1);
        LocalDate endDate = LocalDate.of(2026, 8, 10);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date  : " + endDate);
        
        // UNCOMMENT THIS LINE TO SEE THE ERROR:
        // Duration wrongDuration = Duration.between(startDate, endDate);
        
        System.out.println("\n// Duration wrongDuration = Duration.between(startDate, endDate);");
        System.out.println("// ERROR: UnsupportedTemporalTypeException: Unsupported unit: Seconds");
        System.out.println("\nWhy Error?");
        System.out.println("- Duration measures TIME (hours, minutes, seconds, nanos)");
        System.out.println("- LocalDate has NO TIME information (only year, month, day)");
        System.out.println("- Cannot calculate seconds between dates without time!");

        // SOLUTION: Use Period for LocalDate (date-based)
        System.out.println("\n\nSOLUTION: Use Period.between() for LocalDate:");
        Period period = Period.between(startDate, endDate);
        System.out.println("Period: " + period);
        System.out.println("Days: " + period.getDays());
        System.out.println("Months: " + period.getMonths());
        System.out.println("Years: " + period.getYears());

        // SUMMARY TABLE
        System.out.println("\n\n===== SUMMARY =====");
        System.out.println("┌─────────────────┬──────────────┬─────────────────────────┐");
        System.out.println("│ Temporal Type   │ Works With   │ Reason                  │");
        System.out.println("├─────────────────┼──────────────┼─────────────────────────┤");
        System.out.println("│ Instant         │ Duration     │ Has time (UTC)          │");
        System.out.println("│ LocalTime       │ Duration     │ Has time                │");
        System.out.println("│ LocalDateTime   │ Duration     │ Has date + time         │");
        System.out.println("│ ZonedDateTime   │ Duration     │ Has date + time + zone  │");
        System.out.println("│ LocalDate       │ Period       │ Only date, NO time      │");
        System.out.println("└─────────────────┴──────────────┴─────────────────────────┘");

        System.out.println("\nKey Rule:");
        System.out.println("Duration = TIME-based (hours, minutes, seconds)");
        System.out.println("Period   = DATE-based (years, months, days)");
    }
}
