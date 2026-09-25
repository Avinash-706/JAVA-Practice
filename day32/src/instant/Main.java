package instant;

public class Main {

    public static void main(String[] args) {

        System.out.println("========== DAY 32 : INSTANT ==========");
        System.out.println("\nInstant represents a point in time (timestamp) in UTC");
        System.out.println("It's the number of seconds/nanoseconds since 1970-01-01 00:00:00 UTC (Unix Epoch)");

        System.out.println("\n========== PRINTING & PARSING ==========");
        PrintingParsing.printAndParseInstant();

        System.out.println("\n========== PLUS OPERATIONS ==========");
        PlusOperations.plusOperations();

        System.out.println("\n========== MINUS OPERATIONS ==========");
        MinusOperations.minusOperations();

        System.out.println("\n========== COMPARISON OPERATIONS ==========");
        CompareOperations.compareInstant();

        System.out.println("\n========== TRUNCATE OPERATIONS ==========");
        TruncateOperations.truncateDemo();

        System.out.println("\n========== CONVERSION OPERATIONS ==========");
        ConversionOperations.conversionDemo();

        System.out.println("\n========== OTHER OPERATIONS ==========");
        OtherOperations.otherInstantOperations();

        System.out.println("\n========== END OF INSTANT ==========");
    }
}
