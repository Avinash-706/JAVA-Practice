package localtime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PrintingParsing {

    public static void printAndParseTime() {

        LocalTime now = LocalTime.now();
        System.out.println("Current Time : " + now);

        // Custom Time
        LocalTime customTime = LocalTime.of(22, 59);
        LocalTime customTime02 = LocalTime.of(22, 59, 59);

        System.out.println("\nDetail : "); 
        System.out.println("Current Hours : " + now.getHour());
        System.out.println("Current Minutes : " + now.getMinute());
        System.out.println("Current Seconds : " + now.getSecond());
        System.out.println("Current Nano Seconds : " + now.getNano());

        // Parsing (default ISO format)
        String timeString = "12:30:45";
        LocalTime parsedTime = LocalTime.parse(timeString);
        System.out.println("\nParsed String (ISO) : " + parsedTime);

        // Parsing with custom format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss");
        LocalTime parsedCustom = LocalTime.parse("18-45-30", formatter);
        System.out.println("Parsed String (Custom Format) : " + parsedCustom);
    }   
}
