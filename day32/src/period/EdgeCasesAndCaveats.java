package period;

import java.time.Period;
import java.time.LocalDate;

public class EdgeCasesAndCaveats {

    public static void demonstrateEdgeCases() {

        System.out.println("\n===== EDGE CASES & CAVEATS =====");
        System.out.println("Important for production code!\n");

        // Edge Case 1: Month-end dates
        System.out.println("=== Edge Case 1: Month-End Dates ===");
        LocalDate jan31 = LocalDate.of(2026, 1, 31);
        Period oneMonth = Period.ofMonths(1);
        LocalDate result1 = jan31.plus(oneMonth);
        
        System.out.println("Start Date : " + jan31 + " (Jan 31)");
        System.out.println("Add 1 month : " + result1);
        System.out.println("Expected Feb 31? -> Adjusted to Feb 28 (or 29)");

        // Edge Case 2: Leap year handling
        System.out.println("\n\n=== Edge Case 2: Leap Year ===");
        LocalDate feb29_2024 = LocalDate.of(2024, 2, 29); // Leap year
        Period oneYear = Period.ofYears(1);
        LocalDate result2 = feb29_2024.plus(oneYear);
        
        System.out.println("Start Date : " + feb29_2024 + " (Leap year)");
        System.out.println("Add 1 year : " + result2);
        System.out.println("Feb 29 -> Feb 28 (2025 is not leap year)");

        // Edge Case 3: Adding days vs months (different results)
        System.out.println("\n\n=== Edge Case 3: 30 Days vs 1 Month ===");
        LocalDate startDate = LocalDate.of(2026, 1, 31);
        
        LocalDate plus30Days = startDate.plusDays(30);
        LocalDate plus1Month = startDate.plusMonths(1);
        
        System.out.println("Start Date : " + startDate);
        System.out.println("Add 30 days : " + plus30Days);
        System.out.println("Add 1 month : " + plus1Month);
        System.out.println("Notice: Different results!");

        // Edge Case 4: Negative periods
        System.out.println("\n\n=== Edge Case 4: Negative Periods ===");
        LocalDate date = LocalDate.of(2026, 3, 31);
        Period negativePeriod = Period.ofMonths(-1);
        LocalDate result4 = date.plus(negativePeriod);
        
        System.out.println("Start Date : " + date);
        System.out.println("Add -1 month : " + result4);
        System.out.println("March 31 -> Feb 28 (adjusted)");

        // Edge Case 5: Order matters in calculations
        System.out.println("\n\n=== Edge Case 5: Order Matters ===");
        LocalDate base = LocalDate.of(2026, 1, 31);
        
        // Method 1: Add year first, then month
        LocalDate method1 = base.plusYears(1).plusMonths(1);
        
        // Method 2: Add combined period
        LocalDate method2 = base.plus(Period.of(1, 1, 0));
        
        System.out.println("Base Date : " + base);
        System.out.println("Method 1 (year then month) : " + method1);
        System.out.println("Method 2 (combined period) : " + method2);
        System.out.println("Same result: " + method1.equals(method2));

        // Important caveat: toTotalMonths()
        System.out.println("\n\n=== IMPORTANT: toTotalMonths() ===");
        Period period1 = Period.of(2, 6, 0); // 2 years 6 months
        long totalMonths = period1.toTotalMonths();
        
        System.out.println("Period : " + period1);
        System.out.println("Total Months : " + totalMonths);
        System.out.println("Calculation: (2 * 12) + 6 = 30 months");
        
        Period period2 = Period.of(1, 18, 0); // 1 year 18 months
        System.out.println("\nPeriod : " + period2);
        System.out.println("Total Months : " + period2.toTotalMonths());
        System.out.println("Note: Not normalized, so (1 * 12) + 18 = 30 months");

        // Production tip
        System.out.println("\n\n=== PRODUCTION TIPS ===");
        System.out.println("✓ Always test month-end dates (28-31)");
        System.out.println("✓ Consider leap years for Feb 29");
        System.out.println("✓ 30 days ≠ 1 month (context matters)");
        System.out.println("✓ Use toTotalMonths() for comparisons");
        System.out.println("✓ Document expected behavior for edge cases");
        System.out.println("✓ Write unit tests for date calculations");
    }
}
