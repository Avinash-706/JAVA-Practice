package localdatetime;

import java.time.LocalDateTime;

public class CompareOperations {

    public static void compareDateTime() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime pastDateTime = now.minusHours(1);
        LocalDateTime futureDateTime = now.plusHours(1);

        System.out.println("Now            : " + now);
        System.out.println("Past DateTime  : " + pastDateTime);
        System.out.println("Future DateTime: " + futureDateTime);

        // Check if Now is After Past
        System.out.print("\nnow.isAfter(pastDateTime) : ");
        if (now.isAfter(pastDateTime)) {
            System.out.println("Haan Ji, Yes");
        } else {
            System.out.println("No, Naa Ji");
        }

        // Check if Now is Before Future
        System.out.print("now.isBefore(futureDateTime) : ");
        if (now.isBefore(futureDateTime)) {
            System.out.println("Haan Ji, Yes");
        } else {
            System.out.println("No, Naa Ji");
        }

        // Check equality
        System.out.print("\nnow.equals(pastDateTime) : ");
        System.out.println(now.equals(pastDateTime));

        System.out.print("now.equals(now) : ");
        System.out.println(now.equals(now));

        // Check if today is any specific DateTime
        System.out.print("\nIs " + now + ".equals(" + LocalDateTime.of(2026, 2, 8, 12, 30) + ") ? : ");
        if (now.equals(LocalDateTime.of(2026, 2, 8, 12, 30))) {
            System.out.println("It is " + now + "!");
        } else {
            System.out.println("It is not " + LocalDateTime.of(2026, 2, 8, 12, 30) + ". Now is: " + now);
        }
    }
}
