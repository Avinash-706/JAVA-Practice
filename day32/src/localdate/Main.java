package localdate;

public class Main {

    public static void main(String[] args) {

        System.out.println("========== DAY 31 : LOCALDATE ==========");

        PrintDate01.printAndGetDates();

        System.out.println("\n========== PLUS OPERATIONS ==========");
        PlusOperations.plusOperations();

        System.out.println("\n========== MINUS OPERATIONS ==========");
        MinusOperations.minusOperations();

        System.out.println("\n========== COMPARISON ==========");
        CompareAndCheckOperations.compareAndCheck();

        System.out.println("\n========== LEAP YEAR ==========");
        LeapYearChecks.leapYearDemo();

        System.out.println("\n========== ADJUSTERS ==========");
        AdjusterOperations.adjusterDemo();

        System.out.println("\n========== PARSING ==========");
        ParseDateOperations.parseDemo();

        System.out.println("\n========== END ==========");
    }
}
