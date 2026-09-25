package period;

import java.time.Period;
import java.time.LocalDate;

public class UseCaseExamples {

    public static void useCases() {

        System.out.println("\n===== REAL-WORLD USE CASES =====");

        // Use Case 1: Age Calculator
        System.out.println("\n1. Age Calculator:");
        LocalDate birthDate = LocalDate.of(1990, 2, 7);
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        
        System.out.println("Birth Date : " + birthDate);
        System.out.println("Today : " + today);
        System.out.println("Age : " + age.getYears() + " years, " 
                          + age.getMonths() + " months, " + age.getDays() + " days");

        // Use Case 2: Loan/EMI Duration
        System.out.println("\n\n2. Loan Duration Calculator:");
        LocalDate loanStart = LocalDate.of(2024, 1, 1);
        LocalDate loanEnd = LocalDate.of(2029, 1, 1);
        Period loanDuration = Period.between(loanStart, loanEnd);
        
        System.out.println("Loan Start : " + loanStart);
        System.out.println("Loan End : " + loanEnd);
        System.out.println("Loan Duration : " + loanDuration);
        System.out.println("Total Years : " + loanDuration.getYears());

        // Use Case 3: Subscription Period
        System.out.println("\n\n3. Subscription Validity:");
        LocalDate subscriptionStart = LocalDate.now();
        Period subscriptionPeriod = Period.ofMonths(6);
        LocalDate subscriptionEnd = subscriptionStart.plus(subscriptionPeriod);
        
        System.out.println("Subscription Start : " + subscriptionStart);
        System.out.println("Subscription Period : " + subscriptionPeriod);
        System.out.println("Subscription End : " + subscriptionEnd);

        // Use Case 4: Project Duration
        System.out.println("\n\n4. Project Timeline:");
        LocalDate projectStart = LocalDate.of(2026, 9, 1);
        LocalDate projectEnd = LocalDate.of(2027, 3, 15);
        Period projectDuration = Period.between(projectStart, projectEnd);
        
        System.out.println("Project Start : " + projectStart);
        System.out.println("Project End : " + projectEnd);
        System.out.println("Project Duration : " + projectDuration);
        System.out.println("Breakdown: " + projectDuration.getMonths() + " months, " + projectDuration.getDays() + " days");

        // Use Case 5: Employee Tenure
        System.out.println("\n\n5. Employee Tenure Calculation:");
        LocalDate joiningDate = LocalDate.of(2020, 5, 15);
        LocalDate currentDate = LocalDate.now();
        Period tenure = Period.between(joiningDate, currentDate);
        
        System.out.println("Joining Date : " + joiningDate);
        System.out.println("Current Date : " + currentDate);
        System.out.println("Tenure : " + tenure.getYears() + " years, " 
                          + tenure.getMonths() + " months, " + tenure.getDays() + " days");

        // Use Case 6: Warranty Period Check
        System.out.println("\n\n6. Warranty Period Check:");
        LocalDate purchaseDate = LocalDate.of(2025, 8, 1);
        Period warrantyPeriod = Period.ofYears(2);
        LocalDate warrantyEnd = purchaseDate.plus(warrantyPeriod);
        LocalDate checkDate = LocalDate.now();
        
        System.out.println("Purchase Date : " + purchaseDate);
        System.out.println("Warranty Period : " + warrantyPeriod);
        System.out.println("Warranty End : " + warrantyEnd);
        System.out.println("Check Date : " + checkDate);
        
        if (checkDate.isBefore(warrantyEnd)) {
            Period remaining = Period.between(checkDate, warrantyEnd);
            System.out.println("Status: VALID - Remaining: " + remaining.getYears() + "y " 
                              + remaining.getMonths() + "m " + remaining.getDays() + "d");
        } else {
            System.out.println("Status: EXPIRED");
        }

        // Use Case 7: Retirement Calculator
        System.out.println("\n\n7. Years Until Retirement:");
        LocalDate currentAge = LocalDate.of(1990, 1, 1);
        LocalDate retirementAge = LocalDate.of(2050, 1, 1);
        Period yearsToRetirement = Period.between(LocalDate.now(), retirementAge);
        
        System.out.println("Current Date : " + LocalDate.now());
        System.out.println("Retirement Date : " + retirementAge);
        System.out.println("Years to Retirement : " + yearsToRetirement.getYears() + " years");
    }
}
