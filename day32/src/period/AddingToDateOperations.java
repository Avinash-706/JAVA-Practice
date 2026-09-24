package period;

import java.time.Period;
import java.time.LocalDate;

public class AddingToDateOperations {

    public static void addingPeriodToDate() {

        System.out.println("\n===== ADDING PERIOD TO DATE =====");
        System.out.println("Most common real-world operation!\n");

        LocalDate today = LocalDate.now();
        System.out.println("Today : " + today);

        // Add Period to LocalDate using plus()
        Period sixMonths = Period.ofMonths(6);
        LocalDate futureDate = today.plus(sixMonths);
        System.out.println("\nAdd 6 months : " + futureDate);

        // Or directly
        LocalDate futureDate2 = today.plusMonths(6);
        System.out.println("Using plusMonths(6) : " + futureDate2);

        // Subtract Period from LocalDate using minus()
        Period oneYear = Period.ofYears(1);
        LocalDate pastDate = today.minus(oneYear);
        System.out.println("\nSubtract 1 year : " + pastDate);

        // Real-world example: Calculate subscription end date
        System.out.println("\n\n=== Use Case: Subscription End Date ===");
        LocalDate subscriptionStart = LocalDate.of(2026, 9, 5);
        Period subscriptionPeriod = Period.ofMonths(12);
        LocalDate subscriptionEnd = subscriptionStart.plus(subscriptionPeriod);
        
        System.out.println("Subscription Start : " + subscriptionStart);
        System.out.println("Subscription Period : " + subscriptionPeriod);
        System.out.println("Subscription End : " + subscriptionEnd);

        // Real-world example: Calculate due date
        System.out.println("\n\n=== Use Case: Payment Due Date ===");
        LocalDate invoiceDate = LocalDate.of(2026, 9, 1);
        Period paymentTerms = Period.ofDays(30);
        LocalDate dueDate = invoiceDate.plus(paymentTerms);
        
        System.out.println("Invoice Date : " + invoiceDate);
        System.out.println("Payment Terms : " + paymentTerms + " (30 days)");
        System.out.println("Due Date : " + dueDate);

        // Real-world example: Project deadline calculation
        System.out.println("\n\n=== Use Case: Project Deadline ===");
        LocalDate projectStart = LocalDate.of(2026, 10, 1);
        Period projectDuration = Period.of(0, 3, 15); // 3 months 15 days
        LocalDate projectDeadline = projectStart.plus(projectDuration);
        
        System.out.println("Project Start : " + projectStart);
        System.out.println("Project Duration : " + projectDuration);
        System.out.println("Project Deadline : " + projectDeadline);

        // Real-world example: Trial period end
        System.out.println("\n\n=== Use Case: Free Trial End ===");
        LocalDate trialStart = LocalDate.now();
        Period trialPeriod = Period.ofDays(14); // 14-day free trial
        LocalDate trialEnd = trialStart.plus(trialPeriod);
        
        System.out.println("Trial Start : " + trialStart);
        System.out.println("Trial Period : " + trialPeriod);
        System.out.println("Trial End : " + trialEnd);

        // Chained operations
        System.out.println("\n\n=== Chained Period Operations ===");
        LocalDate baseDate = LocalDate.of(2026, 1, 1);
        LocalDate calculatedDate = baseDate
                .plus(Period.ofYears(1))
                .plus(Period.ofMonths(6))
                .plus(Period.ofDays(15));
        
        System.out.println("Base Date : " + baseDate);
        System.out.println("Add 1 year + 6 months + 15 days : " + calculatedDate);
    }
}
