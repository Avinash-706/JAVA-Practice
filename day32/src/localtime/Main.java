package localtime;

public class Main {

    public static void main(String[] args) {

        System.out.println("========== DAY 31 : LOCALTIME ==========\n");

        System.out.println("\n========== PRINTING & PARSING ==========");
        PrintingParsing.printAndParseTime();


        System.out.println("\n========== PLUS OPERATIONS ==========");
        PlusOperations.plusOperations();


        System.out.println("\n========== MINUS OPERATIONS ==========");
        MinusOperations.minusOperations();


        System.out.println("\n========== COMPARISON OPERATIONS ==========");
        CompareOperations.compareTime();


        System.out.println("\n========== OTHER OPERATIONS ==========");
        OtherOperations.otherTimeOperations();


        System.out.println("\n========== FORMATTING OPERATIONS ==========");
        FormattingOperations.formatTime();

        System.out.println("\n========== END OF LOCALTIME ==========");
    }
}
