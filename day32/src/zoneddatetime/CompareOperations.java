package zoneddatetime;

import java.time.ZonedDateTime;
import java.time.ZoneId;

public class CompareOperations {

    public static void compareZonedDateTime() {

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime pastZDT = now.minusHours(1);
        ZonedDateTime futureZDT = now.plusHours(1);

        System.out.println("Now            : " + now);
        System.out.println("Past ZDT       : " + pastZDT);
        System.out.println("Future ZDT     : " + futureZDT);

        // Check if Now is After Past
        System.out.print("\nnow.isAfter(pastZDT) : ");
        if (now.isAfter(pastZDT)) {
            System.out.println("Haan Ji, Yes");
        } else {
            System.out.println("No, Naa Ji");
        }

        // Check if Now is Before Future
        System.out.print("now.isBefore(futureZDT) : ");
        if (now.isBefore(futureZDT)) {
            System.out.println("Haan Ji, Yes");
        } else {
            System.out.println("No, Naa Ji");
        }

        // Check equality
        System.out.print("\nnow.equals(pastZDT) : ");
        System.out.println(now.equals(pastZDT));

        System.out.print("now.equals(now) : ");
        System.out.println(now.equals(now));

        // Compare ZonedDateTime across different zones (same instant)
        ZonedDateTime indiaTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime newYorkTime = indiaTime.withZoneSameInstant(ZoneId.of("America/New_York"));

        System.out.println("\n\nCompare Same Instant Across Zones :");
        System.out.println("India Time : " + indiaTime);
        System.out.println("New York Time : " + newYorkTime);
        System.out.print("indiaTime.equals(newYorkTime) : ");
        System.out.println(indiaTime.equals(newYorkTime));
        
        System.out.print("Same Instant (isEqual) : ");
        System.out.println(indiaTime.isEqual(newYorkTime));
    }
}
