package localdate;

import java.time.*;

public class PrintDate01 {

    public static void printAndGetDates() {
        LocalDate now = LocalDate.now();
        System.out.println("Current Date : " + now );

        LocalDate customDate = LocalDate.of( 1992,  2,  7) ;

        int dayOfMonth = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year =  now.getYear();

        System.out.println("\n"+dayOfMonth + "/" + month + "/" + year);
        System.out.println("Details : ");
        System.out.println("INT : getDayOfMonth() : " + dayOfMonth);
        System.out.println("INT : getMonthValue() : " + month);
        System.out.println("INT : getYear() : " +  year);


        DayOfWeek dayOfWeek = now.getDayOfWeek();
        int dayOfMonth2 = now.getDayOfMonth(); 
        Month month2 = now.getMonth();
        int year2 = now.getYear();
        int dayOfYear = now.getDayOfYear();

        System.out.println("\n\n" + dayOfWeek + ", " +  dayOfMonth2 + " " +  month2 + ", " + year2);
        System.out.println("Details :\t");
        System.out.println("DayOfWeek : getDayOfWeek() : " + dayOfWeek);
        System.out.println("INT : getDayOfMonth() : " + dayOfMonth2);
        System.out.println("INT : getDayOfYear() 2026  : " + dayOfYear);
        System.out.println("Month : getMonth() : " + month2);
        System.out.println("INT : getYear() : " + year2);


        // Days in year comparison
        int lenMonth = now.lengthOfMonth();
        int lenYear = now.lengthOfYear();

        System.out.println("\n\nINT : lengthOfMonth() " + now.getMonth() + " : " + lenMonth);
        System.out.println("INT : lengthOfYear () " + now.getYear()  + " : " +  lenYear);
        System.out.println("INT : Days in Custom Year " + customDate.getYear() + " : " + customDate.lengthOfYear());
    }
}