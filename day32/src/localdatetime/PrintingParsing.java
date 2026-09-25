package localdatetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import java.time.Month;

public class PrintingParsing {

    public static void printAndParseDateTime() {

        // Printing the date as well as time with a seperator like 'T' 
        // between date then after time by default
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current DateTime : " + now);

        // Custom DateTime using of() method
        LocalDateTime customDateTime01 = LocalDateTime.of(1992, 2, 7, 22, 59);
        LocalDateTime customDateTime02 = LocalDateTime.of(1992, 2, 7, 22, 59, 59);
        LocalDateTime customDateTime03 = LocalDateTime.of(1992, 2, 7, 22, 59, 59, 123456789);

        System.out.println("\nCustom DateTime (y, m, d, h, min) : " + customDateTime01);
        System.out.println("Custom DateTime (y, m, d, h, min, sec) : " + customDateTime02);
        System.out.println("Custom DateTime (y, m, d, h, min, sec, nano) : " + customDateTime03);

        // Getting individual components
        int year = now.getYear();
        Month month = now.getMonth();
        int monthValue = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        int dayOfYear = now.getDayOfYear();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        int nano = now.getNano();

        System.out.println("\n\nDetail : ");
        System.out.println("INT : getYear() : " + year);
        System.out.println("Month : getMonth() : " + month);
        System.out.println("INT : getMonthValue() : " + monthValue);
        System.out.println("INT : getDayOfMonth() : " + dayOfMonth);
        System.out.println("DayOfWeek : getDayOfWeek() : " + dayOfWeek);
        System.out.println("INT : getDayOfYear() : " + dayOfYear);
        System.out.println("INT : getHour() : " + hour);
        System.out.println("INT : getMinute() : " + minute);
        System.out.println("INT : getSecond() : " + second);
        System.out.println("INT : getNano() : " + nano);

        // Parsing (default ISO format)
        // Make sure that we need to use "-" symbol only for parsing and no other symbol
        String dateTimeString = "2023-01-11T13:48";
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString);
        System.out.println("\nParsed String (ISO) : " + parsedDateTime);

        String dateTimeString02 = "2023-01-11T13:48:30";
        LocalDateTime parsedDateTime02 = LocalDateTime.parse(dateTimeString02);
        System.out.println("Parsed String (ISO with seconds) : " + parsedDateTime02);

        // Parsing with custom format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime parsedCustom = LocalDateTime.parse("07-02-1992 18:45:30", formatter);
        System.out.println("Parsed String (Custom Format) : " + parsedCustom);
    }
}
