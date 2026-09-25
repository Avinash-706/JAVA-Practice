package period;

import java.time.Period;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PeriodVsDurationDemo {

    public static void demonstrateDifference() {

        System.out.println("\n===== PERIOD vs DURATION =====");

        // Period - Date-based (years, months, days)
        LocalDate date1 = LocalDate.of(2024, 1, 15);
        LocalDate date2 = LocalDate.of(2026, 8, 30);
        Period period = Period.between(date1, date2);
        
        System.out.println("\nPERIOD (Date-based):");
        System.out.println("Start Date : " + date1);
        System.out.println("End Date   : " + date2);
        System.out.println("Period     : " + period);
        System.out.println("Breakdown  : " + period.getYears() + " years, " 
                          + period.getMonths() + " months, " + period.getDays() + " days");

        // Duration - Time-based (hours, minutes, seconds)
        LocalDateTime dateTime1 = LocalDateTime.of(2026, 8, 30, 10, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2026, 8, 30, 17, 30);
        Duration duration = Duration.between(dateTime1, dateTime2);
        
        System.out.println("\n\nDURATION (Time-based):");
        System.out.println("Start DateTime : " + dateTime1);
        System.out.println("End DateTime   : " + dateTime2);
        System.out.println("Duration       : " + duration);
        System.out.println("In Hours       : " + duration.toHours());
        System.out.println("In Minutes     : " + duration.toMinutes());

        // Key Differences
        System.out.println("\n\n===== KEY DIFFERENCES =====");
        System.out.println("┌─────────────┬─────────────────────────┬─────────────────────────┐");
        System.out.println("│ Feature     │ Period                  │ Duration                │");
        System.out.println("├─────────────┼─────────────────────────┼─────────────────────────┤");
        System.out.println("│ Type        │ Date-based              │ Time-based              │");
        System.out.println("│ Units       │ Years, Months, Days     │ Hours, Minutes, Seconds │");
        System.out.println("│ Works With  │ LocalDate               │ LocalTime, LocalDateTime│");
        System.out.println("│             │                         │ Instant, ZonedDateTime  │");
        System.out.println("│ Format      │ P2Y3M10D                │ PT2H30M45S              │");
        System.out.println("│ Use Case    │ Age, Loan Duration      │ Execution Time, Timeout │");
        System.out.println("└─────────────┴─────────────────────────┴─────────────────────────┘");

        // When to use Period
        System.out.println("\n\nWhen to use PERIOD:");
        System.out.println("✓ Calculating age from birth date");
        System.out.println("✓ Loan duration in years/months");
        System.out.println("✓ Subscription validity period");
        System.out.println("✓ Project timelines");
        System.out.println("✓ Employee tenure");
        System.out.println("✓ Warranty periods");

        // When to use Duration
        System.out.println("\n\nWhen to use DURATION:");
        System.out.println("✓ Measuring code execution time");
        System.out.println("✓ API timeout checking");
        System.out.println("✓ Meeting/event duration");
        System.out.println("✓ Working hours calculation");
        System.out.println("✓ Video/audio duration");
        System.out.println("✓ Timer functionality");

        // Common mistake
        System.out.println("\n\n⚠ COMMON MISTAKE:");
        System.out.println("Period.between(LocalDate, LocalDate) ✓ Works");
        System.out.println("Duration.between(LocalDate, LocalDate) ✗ Runtime Error!");
        System.out.println("\nAlways match the right class with the right temporal type!");
    }
}
