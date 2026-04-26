import java.util.regex.*;

/**
 * Character Classes and Quantifiers - Complete Reference
 * Deep dive into regex building blocks
 */
public class CharacterClassesQuantifiers {
    
    public static void main(String[] args) {
        System.out.println("=== CHARACTER CLASSES & QUANTIFIERS ===\n");
        
        predefinedCharacterClasses();
        customCharacterClasses();
        negatedCharacterClasses();
        rangeCharacterClasses();
        quantifiersBasic();
        quantifiersAdvanced();
        greedyVsReluctant();
        possessiveQuantifiers();
    }
    
    // 1. Predefined Character Classes
    static void predefinedCharacterClasses() {
        System.out.println("1. PREDEFINED CHARACTER CLASSES\n");
        
        // \\d - Digit [0-9]
        System.out.println("\\d - Any digit [0-9]:");
        testPattern("\\d", new String[]{"5", "a", "9", "Z", "0"});
        
        // \\D - Non-digit
        System.out.println("\n\\D - Any non-digit:");
        testPattern("\\D", new String[]{"5", "a", "9", "Z", "@"});
        
        // \\w - Word character [a-zA-Z0-9_]
        System.out.println("\n\\w - Word character [a-zA-Z0-9_]:");
        testPattern("\\w", new String[]{"a", "Z", "5", "_", "@", " "});
        
        // \\W - Non-word character
        System.out.println("\n\\W - Non-word character:");
        testPattern("\\W", new String[]{"a", "Z", "5", "_", "@", " "});
        
        // \\s - Whitespace [ \\t\\n\\r\\f]
        System.out.println("\n\\s - Whitespace:");
        testPattern("\\s", new String[]{" ", "a", "\t", "\n", "5"});
        
        // \\S - Non-whitespace
        System.out.println("\n\\S - Non-whitespace:");
        testPattern("\\S", new String[]{" ", "a", "\t", "5", "@"});
        
        // . - Any character (except newline)
        System.out.println("\n. - Any character (except newline):");
        testPattern(".", new String[]{"a", "5", "@", " ", "\n"});
        
        System.out.println();
    }
    
    // 2. Custom Character Classes
    static void customCharacterClasses() {
        System.out.println("2. CUSTOM CHARACTER CLASSES\n");
        
        // [abc] - a, b, or c
        System.out.println("[abc] - Match a, b, or c:");
        testPattern("[abc]", new String[]{"a", "b", "c", "d", "A"});
        
        // [aeiou] - Vowels
        System.out.println("\n[aeiou] - Lowercase vowels:");
        testPattern("[aeiou]", new String[]{"a", "e", "i", "o", "u", "A", "b"});
        
        // [aeiouAEIOU] - All vowels
        System.out.println("\n[aeiouAEIOU] - All vowels:");
        testPattern("[aeiouAEIOU]", new String[]{"a", "E", "i", "O", "b", "5"});
        
        // [0-9] - Digits
        System.out.println("\n[0-9] - Digits (same as \\d):");
        testPattern("[0-9]", new String[]{"0", "5", "9", "a", "A"});
        
        // [a-z] - Lowercase letters
        System.out.println("\n[a-z] - Lowercase letters:");
        testPattern("[a-z]", new String[]{"a", "m", "z", "A", "5"});
        
        // [A-Z] - Uppercase letters
        System.out.println("\n[A-Z] - Uppercase letters:");
        testPattern("[A-Z]", new String[]{"A", "M", "Z", "a", "5"});
        
        // [a-zA-Z] - All letters
        System.out.println("\n[a-zA-Z] - All letters:");
        testPattern("[a-zA-Z]", new String[]{"a", "Z", "m", "5", "@"});
        
        // [a-zA-Z0-9] - Alphanumeric
        System.out.println("\n[a-zA-Z0-9] - Alphanumeric:");
        testPattern("[a-zA-Z0-9]", new String[]{"a", "Z", "5", "@", "_"});
        
        System.out.println();
    }
    
    // 3. Negated Character Classes
    static void negatedCharacterClasses() {
        System.out.println("3. NEGATED CHARACTER CLASSES\n");
        
        // [^abc] - NOT a, b, or c
        System.out.println("[^abc] - NOT a, b, or c:");
        testPattern("[^abc]", new String[]{"a", "b", "c", "d", "z", "5"});
        
        // [^0-9] - NOT a digit
        System.out.println("\n[^0-9] - NOT a digit (same as \\D):");
        testPattern("[^0-9]", new String[]{"0", "5", "a", "Z", "@"});
        
        // [^a-z] - NOT lowercase
        System.out.println("\n[^a-z] - NOT lowercase:");
        testPattern("[^a-z]", new String[]{"a", "z", "A", "Z", "5", "@"});
        
        // [^aeiou] - NOT a vowel
        System.out.println("\n[^aeiou] - NOT a lowercase vowel:");
        testPattern("[^aeiou]", new String[]{"a", "e", "b", "c", "A", "5"});
        
        System.out.println();
    }
    
    // 4. Range Character Classes
    static void rangeCharacterClasses() {
        System.out.println("4. RANGE CHARACTER CLASSES\n");
        
        // [a-f] - a through f
        System.out.println("[a-f] - Letters a through f:");
        testPattern("[a-f]", new String[]{"a", "c", "f", "g", "z", "A"});
        
        // [0-5] - 0 through 5
        System.out.println("\n[0-5] - Digits 0 through 5:");
        testPattern("[0-5]", new String[]{"0", "3", "5", "6", "9"});
        
        // [A-F0-9] - Hexadecimal
        System.out.println("\n[A-F0-9] - Hexadecimal (uppercase):");
        testPattern("[A-F0-9]", new String[]{"A", "F", "5", "9", "G", "a"});
        
        // [a-fA-F0-9] - Hexadecimal (any case)
        System.out.println("\n[a-fA-F0-9] - Hexadecimal (any case):");
        testPattern("[a-fA-F0-9]", new String[]{"a", "F", "5", "g", "G"});
        
        // Multiple ranges
        System.out.println("\n[a-zA-Z0-9_] - Word characters:");
        testPattern("[a-zA-Z0-9_]", new String[]{"a", "Z", "5", "_", "@", " "});
        
        System.out.println();
    }
    
    // 5. Basic Quantifiers
    static void quantifiersBasic() {
        System.out.println("5. BASIC QUANTIFIERS\n");
        
        // * - Zero or more
        System.out.println("* - Zero or more:");
        System.out.println("  Pattern: a*");
        testFullString("a*", new String[]{"", "a", "aa", "aaa", "b"});
        
        // + - One or more
        System.out.println("\n+ - One or more:");
        System.out.println("  Pattern: a+");
        testFullString("a+", new String[]{"", "a", "aa", "aaa", "b"});
        
        // ? - Zero or one (optional)
        System.out.println("\n? - Zero or one (optional):");
        System.out.println("  Pattern: a?");
        testFullString("a?", new String[]{"", "a", "aa", "b"});
        
        // Practical examples
        System.out.println("\nPractical Examples:");
        
        System.out.println("  \\d+ (one or more digits):");
        testFullString("\\d+", new String[]{"1", "123", "abc", ""});
        
        System.out.println("\n  \\d* (zero or more digits):");
        testFullString("\\d*", new String[]{"", "1", "123", "abc"});
        
        System.out.println("\n  https? (http or https):");
        testFullString("https?", new String[]{"http", "https", "htt", "httpss"});
        
        System.out.println();
    }
    
    // 6. Advanced Quantifiers
    static void quantifiersAdvanced() {
        System.out.println("6. ADVANCED QUANTIFIERS\n");
        
        // {n} - Exactly n times
        System.out.println("{n} - Exactly n times:");
        System.out.println("  Pattern: \\d{3}");
        testFullString("\\d{3}", new String[]{"12", "123", "1234", "abc"});
        
        // {n,} - n or more times
        System.out.println("\n{n,} - n or more times:");
        System.out.println("  Pattern: \\d{3,}");
        testFullString("\\d{3,}", new String[]{"12", "123", "1234", "12345"});
        
        // {n,m} - Between n and m times
        System.out.println("\n{n,m} - Between n and m times:");
        System.out.println("  Pattern: \\d{3,5}");
        testFullString("\\d{3,5}", new String[]{"12", "123", "1234", "12345", "123456"});
        
        // Practical examples
        System.out.println("\nPractical Examples:");
        
        System.out.println("  Phone: \\d{3}-\\d{3}-\\d{4}");
        testFullString("\\d{3}-\\d{3}-\\d{4}", 
            new String[]{"123-456-7890", "12-456-7890", "123-45-7890"});
        
        System.out.println("\n  ZIP Code: \\d{5}");
        testFullString("\\d{5}", new String[]{"12345", "1234", "123456"});
        
        System.out.println("\n  Password (8-16 chars): .{8,16}");
        testFullString(".{8,16}", new String[]{"pass", "password", "password1234", "password12345678"});
        
        System.out.println();
    }
    
    // 7. Greedy vs Reluctant Quantifiers
    static void greedyVsReluctant() {
        System.out.println("7. GREEDY VS RELUCTANT QUANTIFIERS\n");
        
        String html = "<div>Hello</div><span>World</span>";
        
        System.out.println("Text: " + html);
        
        // Greedy - matches as much as possible
        System.out.println("\nGreedy quantifier: <.*>");
        Pattern greedyPattern = Pattern.compile("<.*>");
        Matcher greedyMatcher = greedyPattern.matcher(html);
        
        if (greedyMatcher.find()) {
            System.out.println("  Matched: " + greedyMatcher.group());
            System.out.println("  Explanation: Matches from first < to last >");
        }
        
        // Reluctant - matches as little as possible
        System.out.println("\nReluctant quantifier: <.*?>");
        Pattern reluctantPattern = Pattern.compile("<.*?>");
        Matcher reluctantMatcher = reluctantPattern.matcher(html);
        
        System.out.println("  Matches:");
        while (reluctantMatcher.find()) {
            System.out.println("    " + reluctantMatcher.group());
        }
        System.out.println("  Explanation: Matches from < to first >");
        
        // More examples
        System.out.println("\nMore Examples:");
        
        String text = "aaaa";
        
        System.out.println("  Text: " + text);
        System.out.println("  Greedy a+: " + (text.matches("a+") ? "matches all 'a'" : "no match"));
        System.out.println("  Reluctant a+?: " + (text.matches("a+?") ? "matches minimum 'a'" : "no match"));
        
        // Quantifier types
        System.out.println("\nQuantifier Types:");
        System.out.println("  Greedy     Reluctant   Possessive");
        System.out.println("  ------     ---------   ----------");
        System.out.println("  *          *?          *+");
        System.out.println("  +          +?          ++");
        System.out.println("  ?          ??          ?+");
        System.out.println("  {n,m}      {n,m}?      {n,m}+");
        
        System.out.println();
    }
    
    // 8. Possessive Quantifiers
    static void possessiveQuantifiers() {
        System.out.println("8. POSSESSIVE QUANTIFIERS\n");
        
        System.out.println("Possessive quantifiers don't backtrack");
        System.out.println("They match as much as possible and never give back\n");
        
        String text = "aaaab";
        
        // Greedy (backtracks)
        System.out.println("Text: " + text);
        System.out.println("Pattern: a+b (greedy)");
        System.out.println("  Matches: " + text.matches("a+b"));
        System.out.println("  Process: Matches 'aaaa', backtracks to 'aaa', finds 'b'");
        
        // Possessive (no backtracking)
        System.out.println("\nPattern: a++b (possessive)");
        System.out.println("  Matches: " + text.matches("a++b"));
        System.out.println("  Process: Matches 'aaaa', no 'b' left, fails (no backtrack)");
        
        // Performance benefit
        System.out.println("\nPerformance Benefit:");
        System.out.println("  Possessive quantifiers are faster");
        System.out.println("  Use when you know backtracking isn't needed");
        System.out.println("  Prevents catastrophic backtracking");
        
        // Example: Matching quoted strings
        System.out.println("\nExample: Matching quoted strings");
        String quoted = "\"Hello World\"";
        
        System.out.println("  Text: " + quoted);
        System.out.println("  Pattern: \".*+\" (possessive)");
        System.out.println("  Matches: " + quoted.matches("\".*+\""));
        
        System.out.println();
    }
    
    // Helper method to test single character patterns
    static void testPattern(String pattern, String[] inputs) {
        for (String input : inputs) {
            boolean matches = input.matches(pattern);
            System.out.println("  '" + input + "' → " + matches);
        }
    }
    
    // Helper method to test full string patterns
    static void testFullString(String pattern, String[] inputs) {
        for (String input : inputs) {
            boolean matches = input.matches(pattern);
            System.out.println("    '" + input + "' → " + matches);
        }
    }
}