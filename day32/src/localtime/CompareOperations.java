package localtime;

import java.time.LocalTime;

public class CompareOperations {

    public static void compareTime() {

        LocalTime now = LocalTime.now();
        LocalTime pastTime = now.minusHours(1);
        LocalTime futureTime = now.plusHours(1);

        System.out.println("Now        : " + now);
        System.out.println("Past Time  : " + pastTime);
        System.out.println("Future Time: " + futureTime);

        System.out.print("\nnow.isAfter(pastTime) : ");
        System.out.println(now.isAfter(pastTime));

        System.out.print("now.isBefore(futureTime) : ");
        System.out.println(now.isBefore(futureTime));

        System.out.print("now.equals(pastTime) : ");
        System.out.println(now.equals(pastTime));

        System.out.print("now.equals(now) : ");
        System.out.println(now.equals(now));
    }
}
