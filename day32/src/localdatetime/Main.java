package localdatetime;

public class Main {

    public static void main(String[] args) {

        System.out.println("========== DAY 32 : LOCALDATETIME ==========\n");

        System.out.println("\n========== PRINTING & PARSING ==========");
        PrintingParsing.printAndParseDateTime();

        System.out.println("\n========== PLUS OPERATIONS ==========");
        PlusOperations.plusOperations();

        System.out.println("\n========== MINUS OPERATIONS ==========");
        MinusOperations.minusOperations();

        System.out.println("\n========== COMPARISON OPERATIONS ==========");
        CompareOperations.compareDateTime();

        System.out.println("\n========== WITH OPERATIONS ==========");
        WithOperations.withOperations();

        System.out.println("\n========== ADJUSTER OPERATIONS ==========");
        AdjusterOperations.adjusterDemo();

        System.out.println("\n========== FORMATTING OPERATIONS ==========");
        FormattingOperations.formatDateTime();

        System.out.println("\n========== OTHER OPERATIONS ==========");
        OtherOperations.otherDateTimeOperations();

        System.out.println("\n========== END OF LOCALDATETIME ==========");
    }
}
