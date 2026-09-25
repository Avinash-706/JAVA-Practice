package zoneddatetime;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZoneOffset;

public class PrintingParsing {

    public static void printAndParseZonedDateTime() {

        // Printing ZonedDateTime with timezone information
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("Current ZonedDateTime : " + now);

        // ZonedDateTime for specific timezone
        ZonedDateTime nowInNewYork = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("Current ZonedDateTime in New York : " + nowInNewYork);

        ZonedDateTime nowInTokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("Current ZonedDateTime in Tokyo : " + nowInTokyo);

        ZonedDateTime nowInLondon = ZonedDateTime.now(ZoneId.of("Europe/London"));
        System.out.println("Current ZonedDateTime in London : " + nowInLondon);

        // Custom ZonedDateTime using of() method
        ZonedDateTime customZDT01 = ZonedDateTime.of(2000, 12, 1, 14, 30, 30, 30, ZoneId.of("America/New_York"));
        System.out.println("\nCustom ZonedDateTime (y, m, d, h, min, sec, nano, zone) : " + customZDT01);

        ZonedDateTime customZDT02 = ZonedDateTime.of(1992, 2, 7, 22, 59, 59, 0, ZoneId.of("Asia/Kolkata"));
        System.out.println("Custom ZonedDateTime (Asia/Kolkata) : " + customZDT02);

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
        ZoneId zone = now.getZone();
        ZoneOffset offset = now.getOffset();

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
        System.out.println("ZoneId : getZone() : " + zone);
        System.out.println("ZoneOffset : getOffset() : " + offset);

        // Parsing (default ISO format with timezone)
        String zonedDateTimeString = "2026-08-10T18:17:50.556227300+05:30";
        ZonedDateTime parsedZDT = ZonedDateTime.parse(zonedDateTimeString);
        System.out.println("\nParsed String (ISO with timezone) : " + parsedZDT);

        String zonedDateTimeString02 = "2023-01-11T13:48:30+05:30[Asia/Kolkata]";
        ZonedDateTime parsedZDT02 = ZonedDateTime.parse(zonedDateTimeString02);
        System.out.println("Parsed String (ISO with zone) : " + parsedZDT02);

        // Parsing with custom format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z");
        ZonedDateTime parsedCustom = ZonedDateTime.parse("07-02-1992 18:45:30 IST", formatter);
        System.out.println("Parsed String (Custom Format) : " + parsedCustom);
    }
}
