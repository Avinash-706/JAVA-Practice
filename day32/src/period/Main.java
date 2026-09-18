package period;

public class Main {

    public static void main(String[] args) {

        System.out.println("========== DAY 32 : PERIOD ==========");
        System.out.println("\nPeriod represents date-based amount (years, months, days)");
        System.out.println("P format: P = Period");
        System.out.println("Example: P2Y3M10D = 2 Years, 3 Months, 10 Days");
        System.out.println("\nUse Period when working with dates, not time!");

        System.out.println("\n========== CREATION OPERATIONS ==========");
        CreationOperations.createPeriod();

        System.out.println("\n========== GET OPERATIONS ==========");
        GetOperations.getValues();

        System.out.println("\n========== PLUS OPERATIONS ==========");
        PlusOperations.plusOperations();

        System.out.println("\n========== MINUS OPERATIONS ==========");
        MinusOperations.minusOperations();

        System.out.println("\n========== WITH OPERATIONS ==========");
        WithOperations.withOperations();

        System.out.println("\n========== ADDING PERIOD TO DATE ==========");
        AddingToDateOperations.addingPeriodToDate();

        System.out.println("\n========== NORMALIZE OPERATIONS ==========");
        NormalizeOperations.normalizeDemo();

        System.out.println("\n========== MULTIPLY & NEGATE OPERATIONS ==========");
        MultiplyNegateOperations.multiplyNegate();

        System.out.println("\n========== ADVANCED COMPARISONS ==========");
        AdvancedComparisons.advancedComparisons();

        System.out.println("\n========== EDGE CASES & CAVEATS ==========");
        EdgeCasesAndCaveats.demonstrateEdgeCases();

        System.out.println("\n========== PERIOD vs DURATION ==========");
        PeriodVsDurationDemo.demonstrateDifference();

        System.out.println("\n========== USE CASE EXAMPLES ==========");
        UseCaseExamples.useCases();

        System.out.println("\n========== END OF PERIOD ==========");
    }
}
