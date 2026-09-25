package duration;

import java.time.Duration;

public class CompareOperations {

    public static void compareDuration() {

        Duration duration1 = Duration.ofHours(2);
        Duration duration2 = Duration.ofMinutes(90);
        Duration duration3 = Duration.ofHours(3);

        System.out.println("Duration1 (2 hours) : " + duration1);
        System.out.println("Duration2 (90 minutes) : " + duration2);
        System.out.println("Duration3 (3 hours) : " + duration3);

        // compareTo method
        // Returns: negative if this < other, zero if equal, positive if this > other
        int compare1 = duration1.compareTo(duration2);
        System.out.println("\nduration1.compareTo(duration2) : " + compare1);
        if (compare1 > 0) {
            System.out.println("Duration1 is GREATER than Duration2");
        } else if (compare1 < 0) {
            System.out.println("Duration1 is LESS than Duration2");
        } else {
            System.out.println("Duration1 is EQUAL to Duration2");
        }

        int compare2 = duration1.compareTo(duration3);
        System.out.println("\nduration1.compareTo(duration3) : " + compare2);
        if (compare2 > 0) {
            System.out.println("Duration1 is GREATER than Duration3");
        } else if (compare2 < 0) {
            System.out.println("Duration1 is LESS than Duration3");
        } else {
            System.out.println("Duration1 is EQUAL to Duration3");
        }

        // equals method
        System.out.println("\nduration1.equals(duration2) : " + duration1.equals(duration2));
        System.out.println("duration1.equals(duration1) : " + duration1.equals(duration1));

        // Check if zero
        Duration zeroDuration = Duration.ZERO;
        System.out.println("\nIs Duration.ZERO zero ? : " + zeroDuration.isZero());
        System.out.println("Is duration1 zero ? : " + duration1.isZero());

        // Check if negative
        Duration negativeDuration = Duration.ofHours(-2);
        System.out.println("\nIs Duration.ofHours(-2) negative ? : " + negativeDuration.isNegative());
        System.out.println("Is duration1 negative ? : " + duration1.isNegative());

        // Absolute value
        Duration abs = negativeDuration.abs();
        System.out.println("\nAbsolute of " + negativeDuration + " : " + abs);

        // Negate (change sign)
        Duration negated = duration1.negated();
        System.out.println("Negated " + duration1 + " : " + negated);

        // Use case: Compare execution times
        System.out.println("\n\nUse Case Example:");
        Duration threshold = Duration.ofMillis(1);
        Duration executionTime = Duration.ofNanos(500000);
        
        System.out.println("Threshold : " + threshold);
        System.out.println("Execution Time : " + executionTime);
        
        if (executionTime.compareTo(threshold) > 0) {
            System.out.println("Execution time EXCEEDED threshold!");
        } else {
            System.out.println("Execution time WITHIN threshold.");
        }
    }
}
