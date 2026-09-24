package datetimeformatter;

public class Main {

    public static void main(String[] args) {

        System.out.println("========== DAY 32 : DATETIMEFORMATTER ==========");
        System.out.println("\nDateTimeFormatter is used for:");
        System.out.println("1. Formatting Date/Time objects to String (for display)");
        System.out.println("2. Parsing String to Date/Time objects (from user input)");
        System.out.println("\nKey Points:");
        System.out.println("- Pattern must EXACTLY match the string");
        System.out.println("- Case matters: MM (month) vs mm (minute)");
        System.out.println("- Use XXX for timezone offset (recommended over z)");

        System.out.println("\n========== PREDEFINED FORMATTERS ==========");
        PredefinedFormatters.predefinedFormatters();

        System.out.println("\n========== CUSTOM PATTERNS ==========");
        CustomPatterns.customPatterns();

        System.out.println("\n========== FORMATTING OPERATIONS ==========");
        FormattingOperations.formattingDemo();

        System.out.println("\n========== PARSING OPERATIONS ==========");
        ParsingOperations.parsingDemo();

        System.out.println("\n========== PATTERN SYMBOLS REFERENCE ==========");
        PatternSymbolsReference.patternSymbolsDemo();

        System.out.println("\n========== LOCALE FORMATTING ==========");
        LocaleFormattingOperations.localeFormatting();

        System.out.println("\n========== COMMON USE CASES ==========");
        CommonUseCases.commonUseCases();

        System.out.println("\n========== COMMON MISTAKES & TIPS ==========");
        CommonMistakesAndTips.mistakesAndTips();

        System.out.println("\n========== END OF DATETIMEFORMATTER ==========");
    }
}
