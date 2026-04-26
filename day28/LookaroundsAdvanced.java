import java.util.regex.*;

/**
 * Lookarounds (Lookaheads & Lookbehinds) - Complete Guide
 * Master zero-width assertions for complex pattern matching
 */
public class LookaroundsAdvanced {
    
    public static void main(String[] args) {
        System.out.println("=== LOOKAROUNDS ADVANCED ===\n");
        
        positiveLookahead();
        negativeLookahead();
        positiveLookbehind();
        negativeLookbehind();
        passwordValidation();
        complexValidations();
        practicalExamples();
    }
    
    // 1. Positive Lookahead (?=...)
    static void positiveLookahead() {
        System.out.println("1. POSITIVE LOOKAHEAD (?=...)\n");
        
        System.out.println("Definition: Assert that pattern ahead matches");
        System.out.println("Syntax: (?=pattern)");
        System.out.println("Does NOT consume characters\n");
        
        // Basic example
        String text = "A1 B2 C3 D";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: \\w(?=\\d)");
        System.out.println("Meaning: Match letter ONLY if followed by digit\n");
        
        Pattern pattern = Pattern.compile("\\w(?=\\d)");
        Matcher matcher = pattern.matcher(text);
        
        System.out.println("Matches:");
        while (matcher.find()) {
            System.out.println("  '" + matcher.group() + "' at position " + matcher.start());
        }
        
        System.out.println("\nExplanation:");
        System.out.println("  A is matched (followed by 1)");
        System.out.println("  B is matched (followed by 2)");
        System.out.println("  C is matched (followed by 3)");
        System.out.println("  D is NOT matched (not followed by digit)");
        System.out.println("  Digits are NOT consumed by lookahead");
        
        // Practical example: Find words before punctuation
        System.out.println("\nPractical: Find words before punctuation");
        String sentence = "Hello, World! How are you?";
        
        System.out.println("Text: " + sentence);
        System.out.println("Pattern: \\w+(?=[,!?])");
        
        Pattern wordPattern = Pattern.compile("\\w+(?=[,!?])");
        Matcher wordMatcher = wordPattern.matcher(sentence);
        
        System.out.println("Words before punctuation:");
        while (wordMatcher.find()) {
            System.out.println("  " + wordMatcher.group());
        }
        
        System.out.println();
    }
    
    // 2. Negative Lookahead (?!...)
    static void negativeLookahead() {
        System.out.println("2. NEGATIVE LOOKAHEAD (?!...)\n");
        
        System.out.println("Definition: Assert that pattern ahead does NOT match");
        System.out.println("Syntax: (?!pattern)");
        System.out.println("Does NOT consume characters\n");
        
        // Basic example
        String text = "A1 B2 C3 D";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: \\w(?!\\d)");
        System.out.println("Meaning: Match letter ONLY if NOT followed by digit\n");
        
        Pattern pattern = Pattern.compile("\\w(?!\\d)");
        Matcher matcher = pattern.matcher(text);
        
        System.out.println("Matches:");
        while (matcher.find()) {
            System.out.println("  '" + matcher.group() + "' at position " + matcher.start());
        }
        
        System.out.println("\nExplanation:");
        System.out.println("  A is NOT matched (followed by 1)");
        System.out.println("  1 is matched (not followed by digit)");
        System.out.println("  D is matched (not followed by digit)");
        
        // Practical example: Find files without .txt extension
        System.out.println("\nPractical: Find files without .txt extension");
        String files = "file1.txt file2.doc file3.txt file4.pdf";
        
        System.out.println("Files: " + files);
        System.out.println("Pattern: \\w+\\.(?!txt)\\w+");
        
        Pattern filePattern = Pattern.compile("\\w+\\.(?!txt)\\w+");
        Matcher fileMatcher = filePattern.matcher(files);
        
        System.out.println("Non-txt files:");
        while (fileMatcher.find()) {
            System.out.println("  " + fileMatcher.group());
        }
        
        System.out.println();
    }
    
    // 3. Positive Lookbehind (?<=...)
    static void positiveLookbehind() {
        System.out.println("3. POSITIVE LOOKBEHIND (?<=...)\n");
        
        System.out.println("Definition: Assert that pattern behind matches");
        System.out.println("Syntax: (?<=pattern)");
        System.out.println("Does NOT consume characters\n");
        
        // Basic example
        String text = "$100 €200 £300";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: (?<=\\$)\\d+");
        System.out.println("Meaning: Match digits ONLY if preceded by $\n");
        
        Pattern pattern = Pattern.compile("(?<=\\$)\\d+");
        Matcher matcher = pattern.matcher(text);
        
        System.out.println("Matches:");
        while (matcher.find()) {
            System.out.println("  " + matcher.group());
        }
        
        System.out.println("\nExplanation:");
        System.out.println("  100 is matched (preceded by $)");
        System.out.println("  200 is NOT matched (preceded by €)");
        System.out.println("  300 is NOT matched (preceded by £)");
        System.out.println("  $ is NOT consumed by lookbehind");
        
        // Practical example: Extract prices in dollars
        System.out.println("\nPractical: Extract all dollar amounts");
        String prices = "Item1: $50, Item2: €60, Item3: $75";
        
        System.out.println("Text: " + prices);
        System.out.println("Pattern: (?<=\\$)\\d+");
        
        Pattern pricePattern = Pattern.compile("(?<=\\$)\\d+");
        Matcher priceMatcher = pricePattern.matcher(prices);
        
        System.out.println("Dollar amounts:");
        while (priceMatcher.find()) {
            System.out.println("  $" + priceMatcher.group());
        }
        
        System.out.println();
    }
    
    // 4. Negative Lookbehind (?<!...)
    static void negativeLookbehind() {
        System.out.println("4. NEGATIVE LOOKBEHIND (?<!...)\n");
        
        System.out.println("Definition: Assert that pattern behind does NOT match");
        System.out.println("Syntax: (?<!pattern)");
        System.out.println("Does NOT consume characters\n");
        
        // Basic example
        String text = "$100 €200 £300";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: (?<!\\$)\\d+");
        System.out.println("Meaning: Match digits ONLY if NOT preceded by $\n");
        
        Pattern pattern = Pattern.compile("(?<!\\$)\\d+");
        Matcher matcher = pattern.matcher(text);
        
        System.out.println("Matches:");
        while (matcher.find()) {
            System.out.println("  " + matcher.group());
        }
        
        System.out.println("\nExplanation:");
        System.out.println("  100 is NOT matched (preceded by $)");
        System.out.println("  00 from 100 is matched (preceded by 1, not $)");
        System.out.println("  200 is matched (preceded by €)");
        System.out.println("  300 is matched (preceded by £)");
        
        // Practical example: Find non-dollar prices
        System.out.println("\nPractical: Extract non-dollar amounts");
        String prices = "Item1: $50, Item2: €60, Item3: £75";
        
        System.out.println("Text: " + prices);
        System.out.println("Pattern: (?<!\\$)[€£]\\d+");
        
        Pattern pricePattern = Pattern.compile("[€£]\\d+");
        Matcher priceMatcher = pricePattern.matcher(prices);
        
        System.out.println("Non-dollar amounts:");
        while (priceMatcher.find()) {
            System.out.println("  " + priceMatcher.group());
        }
        
        System.out.println();
    }
    
    // 5. Password Validation with Lookaheads
    static void passwordValidation() {
        System.out.println("5. PASSWORD VALIDATION WITH LOOKAHEADS\n");
        
        System.out.println("Requirements:");
        System.out.println("  • Minimum 8 characters");
        System.out.println("  • At least one uppercase letter");
        System.out.println("  • At least one lowercase letter");
        System.out.println("  • At least one digit");
        System.out.println("  • At least one special character\n");
        
        // Pattern breakdown
        System.out.println("Pattern: ^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%]).{8,}$");
        System.out.println("\nBreakdown:");
        System.out.println("  ^              → Start of string");
        System.out.println("  (?=.*[A-Z])    → Lookahead: has uppercase");
        System.out.println("  (?=.*[a-z])    → Lookahead: has lowercase");
        System.out.println("  (?=.*\\d)       → Lookahead: has digit");
        System.out.println("  (?=.*[@#$%])   → Lookahead: has special char");
        System.out.println("  .{8,}          → Match 8+ characters");
        System.out.println("  $              → End of string\n");
        
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%]).{8,}$");
        
        String[] passwords = {
            "password",           // No uppercase, digit, special
            "Password",           // No digit, special
            "Password1",          // No special
            "Password1@",         // Valid
            "Pass1@",             // Too short
            "PASSWORD1@",         // No lowercase
            "Secure@Pass123"      // Valid
        };
        
        System.out.println("Testing passwords:");
        for (String pwd : passwords) {
            boolean valid = pwd.matches(regex);
            System.out.println("  '" + pwd + "' → " + (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        System.out.println();
    }
    
    // 6. Complex Validations
    static void complexValidations() {
        System.out.println("6. COMPLEX VALIDATIONS\n");
        
        // Username validation
        System.out.println("Username Validation:");
        System.out.println("  • 3-16 characters");
        System.out.println("  • Alphanumeric and underscore only");
        System.out.println("  • Must start with letter");
        System.out.println("  • Cannot end with underscore\n");
        
        String usernameRegex = "^(?=[a-zA-Z])(?!.*_$)[a-zA-Z0-9_]{3,16}$";
        
        String[] usernames = {
            "john_doe",      // Valid
            "user123",       // Valid
            "ab",            // Too short
            "123user",       // Starts with digit
            "user_",         // Ends with underscore
            "valid_user_1"   // Valid
        };
        
        System.out.println("Testing usernames:");
        for (String username : usernames) {
            boolean valid = username.matches(usernameRegex);
            System.out.println("  '" + username + "' → " + (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        // URL validation
        System.out.println("\nURL Validation:");
        System.out.println("  • Must start with http:// or https://");
        System.out.println("  • Valid domain name");
        System.out.println("  • Optional port");
        System.out.println("  • Optional path\n");
        
        String urlRegex = "^https?://(?=.{1,255}$)[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(:\\d+)?(/.*)?$";
        
        String[] urls = {
            "http://example.com",
            "https://www.example.com",
            "http://example.com:8080",
            "https://example.com/path",
            "ftp://example.com",
            "http://example"
        };
        
        System.out.println("Testing URLs:");
        for (String url : urls) {
            boolean valid = url.matches(urlRegex);
            System.out.println("  '" + url + "' → " + (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        System.out.println();
    }
    
    // 7. Practical Examples
    static void practicalExamples() {
        System.out.println("7. PRACTICAL EXAMPLES\n");
        
        // Example 1: Extract hashtags not in URLs
        System.out.println("Example 1: Extract hashtags (not in URLs)");
        String tweet = "Check #Java and #Regex at http://example.com#anchor";
        
        System.out.println("Text: " + tweet);
        System.out.println("Pattern: (?<!/)#\\w+");
        System.out.println("Explanation: # not preceded by /\n");
        
        Pattern hashtagPattern = Pattern.compile("(?<!/)#\\w+");
        Matcher hashtagMatcher = hashtagPattern.matcher(tweet);
        
        System.out.println("Hashtags:");
        while (hashtagMatcher.find()) {
            System.out.println("  " + hashtagMatcher.group());
        }
        
        // Example 2: Find numbers not in dates
        System.out.println("\nExample 2: Find numbers not in dates");
        String text = "Order 12345 placed on 2024-05-12 for $100";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: (?<!\\d{4}-)\\d+(?!-)");
        System.out.println("Explanation: Numbers not part of date format\n");
        
        Pattern numberPattern = Pattern.compile("(?<!\\d{4}-)\\d+(?!-)");
        Matcher numberMatcher = numberPattern.matcher(text);
        
        System.out.println("Numbers (not in dates):");
        while (numberMatcher.find()) {
            System.out.println("  " + numberMatcher.group());
        }
        
        // Example 3: Extract quoted text not in code
        System.out.println("\nExample 3: Extract quoted text");
        String code = "String s = \"Hello\"; // Comment \"not this\"";
        
        System.out.println("Text: " + code);
        System.out.println("Pattern: (?<!//.*?)\"([^\"]+)\"");
        System.out.println("Explanation: Quotes not in comments\n");
        
        Pattern quotePattern = Pattern.compile("\"([^\"]+)\"");
        Matcher quoteMatcher = quotePattern.matcher(code.split("//")[0]);
        
        System.out.println("Quoted strings:");
        while (quoteMatcher.find()) {
            System.out.println("  " + quoteMatcher.group(1));
        }
        
        System.out.println();
    }
}