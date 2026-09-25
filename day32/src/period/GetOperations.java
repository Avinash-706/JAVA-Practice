package period;

import java.time.Period;
import java.time.LocalDate;

public class GetOperations {

    public static void getValues() {

        System.out.println("\n===== GET VALUES FROM PERIOD =====");

        Period period = Period.parse("P3Y7M15D");
        System.out.println("Period : " + period);

        // Get individual components
        int years = period.getYears();
        System.out.println("\ngetYears() : " + years);

        int months = period.getMonths();
        System.out.println("getMonths() : " + months);

        int days = period.getDays();
        System.out.println("getDays() : " + days);

        // Get chronology
        System.out.println("\ngetChronology() : " + period.getChronology());

        // Get units
        System.out.println("getUnits() : " + period.getUnits());

        // Important: Period does NOT provide total values like Duration
        // getMonths() returns months component (0-11), NOT total months
        System.out.println("\n\nIMPORTANT NOTE:");
        System.out.println("Period does NOT calculate total months or total days");
        System.out.println("getMonths() returns ONLY the months component (0-11)");
        System.out.println("Example: P2Y6M = 2 years AND 6 months");
        System.out.println("         getYears() = 2, getMonths() = 6");
        System.out.println("         NOT 30 total months!");

        // Practical example: Age breakdown
        LocalDate birthDate = LocalDate.of(1992, 2, 7);
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        
        System.out.println("\n\nAge Breakdown Example:");
        System.out.println("Birth Date : " + birthDate);
        System.out.println("Today : " + today);
        System.out.println("Period : " + age);
        System.out.println("\nIndividual Components:");
        System.out.println("Years : " + age.getYears());
        System.out.println("Months : " + age.getMonths());
        System.out.println("Days : " + age.getDays());

        // To get total days, use ChronoUnit.DAYS.between()
        long totalDays = java.time.temporal.ChronoUnit.DAYS.between(birthDate, today);
        System.out.println("\nTotal Days (using ChronoUnit) : " + totalDays);

        // Check if zero
        Period zeroPeriod = Period.ZERO;
        System.out.println("\n\nPeriod.ZERO.isZero() : " + zeroPeriod.isZero());
        System.out.println("age.isZero() : " + age.isZero());
    }
}
