package period;

import java.time.Period;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CreationOperations {

    public static void createPeriod() {

        System.out.println("\n===== CREATING PERIOD =====");

        // Period represents date-based amount (years, months, days)
        // P format: P = Period
        // P2Y3M10D = 2 Years, 3 Months, 10 Days

        // Create Period between two LocalDates
        LocalDate startDate = LocalDate.of(2020, 1, 15);
        LocalDate endDate = LocalDate.of(2026, 8, 30);
        Period betweenDates = Period.between(startDate, endDate);
        System.out.println("Period between " + startDate + " and " + endDate);
        System.out.println("Result: " + betweenDates);

        // Create Period using of() methods
        Period ofYears = Period.ofYears(5);
        System.out.println("\nPeriod.ofYears(5) : " + ofYears);

        Period ofMonths = Period.ofMonths(8);
        System.out.println("Period.ofMonths(8) : " + ofMonths);

        Period ofWeeks = Period.ofWeeks(3);
        System.out.println("Period.ofWeeks(3) : " + ofWeeks + " (converted to days)");

        Period ofDays = Period.ofDays(15);
        System.out.println("Period.ofDays(15) : " + ofDays);

        // Create Period with multiple units
        Period combined = Period.of(2, 6, 10);
        System.out.println("\nPeriod.of(2, 6, 10) : " + combined);
        System.out.println("Meaning: 2 Years, 6 Months, 10 Days");

        // Parse Period from string
        Period parsed = Period.parse("P3Y2M15D");
        System.out.println("\nPeriod.parse('P3Y2M15D') : " + parsed);
        System.out.println("Format: P = Period, Y = Years, M = Months, D = Days");

        Period parsed2 = Period.parse("P1Y");
        System.out.println("Period.parse('P1Y') : " + parsed2);

        Period parsed3 = Period.parse("P6M");
        System.out.println("Period.parse('P6M') : " + parsed3);

        // Constants
        System.out.println("\nPeriod.ZERO : " + Period.ZERO);

        // Real example: Age calculation
        LocalDate birthDate = LocalDate.of(1990, 2, 7);
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        System.out.println("\n\nAge Calculation Example:");
        System.out.println("Birth Date : " + birthDate);
        System.out.println("Today : " + today);
        System.out.println("Age : " + age);
        System.out.println("Age in words: " + age.getYears() + " years, " 
                          + age.getMonths() + " months, " + age.getDays() + " days");

        // WHY Period DOESN'T WORK with LocalTime or Instant?
        // Period is DATE-BASED (years, months, days)
        // LocalTime has NO DATE information (only hours, minutes, seconds)
        // Use Duration for time-based differences!
        System.out.println("\n\nNote: Period.between() works ONLY with LocalDate");
        System.out.println("Reason: Period is date-based, not time-based");
        System.out.println("Use Duration for LocalTime, LocalDateTime, Instant");
    }
}
