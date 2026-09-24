package period;

import java.time.Period;

public class NormalizeOperations {

    public static void normalizeDemo() {

        System.out.println("\n===== NORMALIZE OPERATIONS =====");

        // Normalize converts months to years when months >= 12
        Period period = Period.of(1, 15, 10);
        System.out.println("Original Period : " + period);
        System.out.println("Years: " + period.getYears() + ", Months: " + period.getMonths() + ", Days: " + period.getDays());

        Period normalized = period.normalized();
        System.out.println("\nnormalized() : " + normalized);
        System.out.println("Years: " + normalized.getYears() + ", Months: " + normalized.getMonths() + ", Days: " + normalized.getDays());
        System.out.println("Explanation: 15 months = 1 year + 3 months");

        // Another example
        Period period2 = Period.of(2, 25, 5);
        System.out.println("\n\nOriginal Period : " + period2);
        System.out.println("Years: " + period2.getYears() + ", Months: " + period2.getMonths() + ", Days: " + period2.getDays());

        Period normalized2 = period2.normalized();
        System.out.println("\nnormalized() : " + normalized2);
        System.out.println("Years: " + normalized2.getYears() + ", Months: " + normalized2.getMonths() + ", Days: " + normalized2.getDays());
        System.out.println("Explanation: 2 years + 25 months = 4 years + 1 month");

        // Days are NOT normalized (30 days != 1 month, varies by month)
        Period periodWithDays = Period.of(0, 0, 40);
        System.out.println("\n\nOriginal Period with 40 days : " + periodWithDays);
        
        Period normalizedDays = periodWithDays.normalized();
        System.out.println("normalized() : " + normalizedDays);
        System.out.println("Notice: Days remain 40 (NOT converted to months)");
        System.out.println("Reason: Months have different day counts (28-31 days)");

        // Negative normalization
        Period negativePeriod = Period.of(-1, -15, -10);
        System.out.println("\n\nOriginal Negative Period : " + negativePeriod);
        
        Period normalizedNegative = negativePeriod.normalized();
        System.out.println("normalized() : " + normalizedNegative);

        // Summary
        System.out.println("\n\nSUMMARY:");
        System.out.println("normalize() converts months to years (12 months = 1 year)");
        System.out.println("normalize() does NOT convert days to months (variable month lengths)");
        System.out.println("Only affects years and months components");
    }
}
