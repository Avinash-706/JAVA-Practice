package localtime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormattingOperations {

    public static void formatTime() {

        LocalTime now = LocalTime.now();
        System.out.println("Original Time : " + now);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("\nFormatted (HH:mm:ss) : " + now.format(formatter1));

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm a");
        System.out.println("Formatted (hh:mm a) : " + now.format(formatter2));

        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH 'hours' mm 'minutes'");
        System.out.println("Formatted (Custom Text) : " + now.format(formatter3));
    }
}
