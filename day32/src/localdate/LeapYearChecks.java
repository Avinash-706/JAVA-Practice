package localdate;

import java.time.LocalDate;

public class LeapYearChecks {

    public static void leapYearDemo() {

        System.out.println("\n===== LEAP YEAR CHECK =====");

        // Current date
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        System.out.println("Today's Date : " + today);
        System.out.println("Current Year : " + currentYear);

        // Check leap year for current year
        System.out.print("Is " + currentYear + " a Leap Year ? : ");
        if (today.isLeapYear()) {
            System.out.println("Yes, Leap Year");
        } else {
            System.out.println("No, Not a Leap Year");
        }

        // Custom year check
        LocalDate customDate = LocalDate.of(2024, 2, 7);
        int customYear = customDate.getYear();

        System.out.println("\nCustom Date : " + customDate);
        System.out.print("Is " + customYear + " a Leap Year ? : ");
        if (customDate.isLeapYear()) {
            System.out.println("Yes, Leap Year");
        } else {
            System.out.println("No, Not a Leap Year");
        }
    }
}
