package duration;

public class Main {

    public static void main(String[] args) {

        System.out.println("========== DAY 32 : DURATION ==========");
        System.out.println("\nDuration represents time-based amount (hours, minutes, seconds, nanos)");
        System.out.println("PT format: P = Period, T = Time separator");
        System.out.println("Example: PT2H30M = 2 hours 30 minutes");

        System.out.println("\n========== CREATION OPERATIONS ==========");
        CreationOperations.createDuration();

        System.out.println("\n========== LocalDate vs LocalDateTime Demo ==========");
        LocalDateVsLocalDateTimeDemo.demonstrateError();

        System.out.println("\n========== GET OPERATIONS ==========");
        GetOperations.getValues();

        System.out.println("\n========== PLUS OPERATIONS ==========");
        PlusOperations.plusOperations();

        System.out.println("\n========== MINUS OPERATIONS ==========");
        MinusOperations.minusOperations();

        System.out.println("\n========== COMPARISON OPERATIONS ==========");
        CompareOperations.compareDuration();

        System.out.println("\n========== MULTIPLY & DIVIDE OPERATIONS ==========");
        MultiplyDivideOperations.multiplyDivide();

        System.out.println("\n========== USE CASE EXAMPLES ==========");
        UseCaseExamples.useCases();

        System.out.println("\n========== END OF DURATION ==========");
    }
}
