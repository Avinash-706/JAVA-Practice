package zoneddatetime;

public class Main {

    public static void main(String[] args) {

        System.out.println("========== DAY 32 : ZONEDDATETIME ==========\n");

        System.out.println("\n========== PRINTING & PARSING ==========");
        PrintingParsing.printAndParseZonedDateTime();

        System.out.println("\n========== ZONE ID OPERATIONS ==========");
        ZoneID.printZoneId();

        System.out.println("\n========== PLUS OPERATIONS ==========");
        PlusOperations.plusOperations();

        System.out.println("\n========== MINUS OPERATIONS ==========");
        MinusOperations.minusOperations();

        System.out.println("\n========== COMPARISON OPERATIONS ==========");
        CompareOperations.compareZonedDateTime();

        System.out.println("\n========== WITH OPERATIONS ==========");
        WithOperations.withOperations();

        System.out.println("\n========== ADJUSTER OPERATIONS ==========");
        AdjusterOperations.adjusterDemo();

        System.out.println("\n========== FORMATTING OPERATIONS ==========");
        FormattingOperations.formatZonedDateTime();

        System.out.println("\n========== ZONE CONVERSION OPERATIONS ==========");
        ZoneConversionOperations.zoneConversionDemo();

        System.out.println("\n========== OTHER OPERATIONS ==========");
        OtherOperations.otherZonedDateTimeOperations();

        System.out.println("\n========== END OF ZONEDDATETIME ==========");
    }
}
