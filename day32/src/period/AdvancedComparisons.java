package period;

import java.time.Period;
import java.time.LocalDate;

public class AdvancedComparisons {

    public static void advancedComparisons() {

        System.out.println("\n===== ADVANCED COMPARISONS =====");

        // Problem: Cannot directly compare periods
        System.out.println("=== Problem: Comparing Periods ===");
        Period period1 = Period.ofMonths(13);
        Period period2 = Period.of(1, 1, 0); // 1 year 1 month
        
        System.out.println("Period 1 : " + period1 + " (13 months)");
        System.out.println("Period 2 : " + period2 + " (1 year 1 month)");
        
        // They are equal in duration but equals() returns false
        System.out.println("period1.equals(period2) : " + period1.equals(period2));
        System.out.println("Why? Different representation, not normalized");

        // Solution 1: Normalize before comparing
        System.out.println("\n\n=== Solution 1: Normalize ===");
        Period normalized1 = period1.normalized();
        Period normalized2 = period2.normalized();
        
        System.out.println("Normalized Period 1 : " + normalized1);
        System.out.println("Normalized Period 2 : " + normalized2);
        System.out.println("Are they equal now? : " + normalized1.equals(normalized2));

        // Solution 2: Use toTotalMonths() for comparison
        System.out.println("\n\n=== Solution 2: toTotalMonths() ===");
        long totalMonths1 = period1.toTotalMonths();
        long totalMonths2 = period2.toTotalMonths();
        
        System.out.println("Period 1 total months : " + totalMonths1);
        System.out.println("Period 2 total months : " + totalMonths2);
        System.out.println("Are they equal? : " + (totalMonths1 == totalMonths2));

        // Solution 3: Apply to a reference date and compare
        System.out.println("\n\n=== Solution 3: Apply to Reference Date ===");
        LocalDate referenceDate = LocalDate.of(2026, 1, 1);
        LocalDate result1 = referenceDate.plus(period1);
        LocalDate result2 = referenceDate.plus(period2);
        
        System.out.println("Reference Date : " + referenceDate);
        System.out.println("After adding Period 1 : " + result1);
        System.out.println("After adding Period 2 : " + result2);
        System.out.println("Results are equal? : " + result1.equals(result2));

        // Real-world use case: Which subscription is longer?
        System.out.println("\n\n=== Use Case: Compare Subscription Plans ===");
        Period basicPlan = Period.ofMonths(6);
        Period premiumPlan = Period.ofMonths(12);
        Period annualPlan = Period.ofYears(1);
        
        System.out.println("Basic Plan : " + basicPlan);
        System.out.println("Premium Plan : " + premiumPlan);
        System.out.println("Annual Plan : " + annualPlan);
        
        System.out.println("\nCompare using toTotalMonths():");
        System.out.println("Basic : " + basicPlan.toTotalMonths() + " months");
        System.out.println("Premium : " + premiumPlan.toTotalMonths() + " months");
        System.out.println("Annual : " + annualPlan.toTotalMonths() + " months");
        
        if (premiumPlan.toTotalMonths() == annualPlan.toTotalMonths()) {
            System.out.println("Premium and Annual plans are SAME duration!");
        }

        // Caveat: Days cannot be compared this way
        System.out.println("\n\n=== CAVEAT: Days Cannot Be Compared ===");
        Period period3 = Period.ofDays(40);
        Period period4 = Period.of(0, 1, 10); // 1 month 10 days
        
        System.out.println("Period 3 : " + period3);
        System.out.println("Period 4 : " + period4);
        System.out.println("toTotalMonths() ignores days!");
        System.out.println("Period 3 total months : " + period3.toTotalMonths());
        System.out.println("Period 4 total months : " + period4.toTotalMonths());
        
        System.out.println("\nFor accurate comparison with days:");
        System.out.println("Convert to actual dates using a reference date");
    }
}
