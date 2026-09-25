package instant;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class CompareOperations {

    public static void compareInstant() {

        Instant now = Instant.now();
        Instant pastInstant = now.minusSeconds(60);
        Instant futureInstant = now.plusSeconds(60);

        System.out.println("Now            : " + now);
        System.out.println("Past Instant   : " + pastInstant);
        System.out.println("Future Instant : " + futureInstant);

        // Check if Now is After Past
        System.out.print("\nnow.isAfter(pastInstant) : ");
        if (now.isAfter(pastInstant)) {
            System.out.println("Haan Ji, Yes");
        } else {
            System.out.println("No, Naa Ji");
        }

        // Check if Now is Before Future
        System.out.print("now.isBefore(futureInstant) : ");
        if (now.isBefore(futureInstant)) {
            System.out.println("Haan Ji, Yes");
        } else {
            System.out.println("No, Naa Ji");
        }

        // Check equality
        System.out.print("\nnow.equals(pastInstant) : ");
        System.out.println(now.equals(pastInstant));

        System.out.print("now.equals(now) : ");
        System.out.println(now.equals(now));

        // compareTo method (-1, 0, 1)
        int comparison = now.compareTo(pastInstant);
        System.out.println("\nnow.compareTo(pastInstant) : " + comparison + " (positive means now is after)");

        // Calculate duration between two instants
        long secondsBetween = ChronoUnit.SECONDS.between(pastInstant, now);
        System.out.println("\nSeconds between pastInstant and now : " + secondsBetween);

        long millisBetween = ChronoUnit.MILLIS.between(pastInstant, now);
        System.out.println("Milliseconds between pastInstant and now : " + millisBetween);

        long daysBetween = ChronoUnit.DAYS.between(pastInstant, now);
        System.out.println("Days between pastInstant and now : " + daysBetween);

        // Check if specific instant
        System.out.print("\nIs now equals to Instant.EPOCH ? : ");
        System.out.println(now.equals(Instant.EPOCH));
    }
}
