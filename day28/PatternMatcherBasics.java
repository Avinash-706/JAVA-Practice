import java.util.regex.*;

/**
 * Pattern & Matcher Classes - Complete Guide
 * Understanding the powerful way to work with Regular Expressions
 */
public class PatternMatcherBasics {
    
    public static void main(String[] args) {
        System.out.println("=== PATTERN & MATCHER BASICS ===\n");
        
        stringMatchesVsPatternMatcher();
        findMethodBasics();
        groupMethodBasics();
        startEndMethods();
        matchesVsFind();
        lookingAtMethod();
        replaceOperations();
    }
    
    // 1. String.matches() vs Pattern & Matcher
    static void stringMatchesVsPatternMatcher() {
        System.out.println("1. STRING.MATCHES() VS PATTERN & MATCHER\n");
        
        // String.matches() - Simple but limited
        System.out.println("Using String.matches():");
        String input1 = "12345";
        boolean result1 = input1.matches("\\d+");
        System.out.println("  '12345' matches digits: " + result1);
        
        // Limitation: Can only check whole string, no searching
        String input2 = "abc 123 def";
        boolean result2 = input2.matches("\\d+");
        System.out.println("  'abc 123 def' matches digits: " + result2);  // false
        
        // Pattern & Matcher - Powerful and flexible
        System.out.println("\nUsing Pattern & Matcher:");
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher("abc 123 def");
        
        System.out.println("  Searching for digits in 'abc 123 def':");
        while (matcher.find()) {
            System.out.println("    Found: " + matcher.group());
        }
        
        // Key Difference
        System.out.println("\nKey Difference:");
        System.out.println("  String.matches() → Checks ENTIRE string");
        System.out.println("  Pattern & Matcher → Can SEARCH within string");
        
        System.out.println();
    }
    
    // 2. find() Method - The Iterator
    static void findMethodBasics() {
        System.out.println("2. FIND() METHOD - SEARCHING TEXT\n");
        
        String text = "Java 8 was released in 2014, Java 11 in 2018, Java 17 in 2021";
        
        // Find all numbers
        System.out.println("Text: " + text);
        System.out.println("\nFinding all numbers:");
        
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("  Match " + count + ": " + matcher.group());
        }
        
        // Find Java versions
        System.out.println("\nFinding 'Java' followed by number:");
        Pattern versionPattern = Pattern.compile("Java \\d+");
        Matcher versionMatcher = versionPattern.matcher(text);
        
        while (versionMatcher.find()) {
            System.out.println("  Found: " + versionMatcher.group());
        }
        
        // find() returns boolean
        System.out.println("\nfind() returns boolean:");
        Matcher testMatcher = Pattern.compile("Python").matcher(text);
        System.out.println("  'Python' found: " + testMatcher.find());
        
        System.out.println();
    }
    
    // 3. group() Method - Extracting Matches
    static void groupMethodBasics() {
        System.out.println("3. GROUP() METHOD - EXTRACTING MATCHES\n");
        
        String text = "Contact: john@email.com or jane@company.org";
        
        System.out.println("Text: " + text);
        System.out.println("\nExtracting email addresses:");
        
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher matcher = pattern.matcher(text);
        
        while (matcher.find()) {
            System.out.println("  Email found: " + matcher.group());
        }
        
        // group() without find() throws exception
        System.out.println("\nImportant: Call find() before group()");
        Matcher newMatcher = pattern.matcher("test@email.com");
        
        try {
            System.out.println("  Calling group() without find(): " + newMatcher.group());
        } catch (IllegalStateException e) {
            System.out.println("  Error: " + e.getMessage());
        }
        
        // Correct usage
        if (newMatcher.find()) {
            System.out.println("  Correct usage: " + newMatcher.group());
        }
        
        System.out.println();
    }
    
    // 4. start() and end() Methods - Position Information
    static void startEndMethods() {
        System.out.println("4. START() AND END() METHODS - POSITIONS\n");
        
        String text = "Java 123 and 456 released on 12-05-2024";
        
        System.out.println("Text: " + text);
        System.out.println("Position: 0123456789...");
        System.out.println("\nFinding numbers with positions:");
        
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        
        while (matcher.find()) {
            String match = matcher.group();
            int start = matcher.start();
            int end = matcher.end();
            
            System.out.println("  Found: '" + match + "'");
            System.out.println("    Start index: " + start);
            System.out.println("    End index: " + end);
            System.out.println("    Length: " + (end - start));
            System.out.println();
        }
        
        // Practical use: Highlighting matches
        System.out.println("Practical use - Highlighting matches:");
        matcher.reset();  // Reset to search again
        
        StringBuilder highlighted = new StringBuilder(text);
        int offset = 0;
        
        while (matcher.find()) {
            int start = matcher.start() + offset;
            int end = matcher.end() + offset;
            
            highlighted.insert(end, "]");
            highlighted.insert(start, "[");
            offset += 2;  // Account for added brackets
        }
        
        System.out.println("  " + highlighted.toString());
        
        System.out.println();
    }
    
    // 5. matches() vs find() - Important Difference
    static void matchesVsFind() {
        System.out.println("5. MATCHES() VS FIND() - KEY DIFFERENCE\n");
        
        String text = "abc 123 def";
        Pattern pattern = Pattern.compile("\\d+");
        
        // matches() - checks entire string
        System.out.println("Text: '" + text + "'");
        System.out.println("Pattern: \\d+ (one or more digits)");
        
        Matcher matcher1 = pattern.matcher(text);
        System.out.println("\nmatches() result: " + matcher1.matches());
        System.out.println("  Explanation: Checks if ENTIRE string is digits");
        
        // find() - searches within string
        Matcher matcher2 = pattern.matcher(text);
        System.out.println("\nfind() result: " + matcher2.find());
        System.out.println("  Explanation: Searches for digits ANYWHERE in string");
        System.out.println("  Found: " + matcher2.group());
        
        // Demonstration with different inputs
        System.out.println("\nComparison with different inputs:");
        
        String[] inputs = {"123", "abc123", "123def", "abc123def"};
        
        for (String input : inputs) {
            Matcher m = pattern.matcher(input);
            boolean matchesResult = m.matches();
            m.reset();
            boolean findResult = m.find();
            
            System.out.println("  Input: '" + input + "'");
            System.out.println("    matches(): " + matchesResult);
            System.out.println("    find(): " + findResult);
        }
        
        System.out.println();
    }
    
    // 6. lookingAt() Method - Matches from Beginning
    static void lookingAtMethod() {
        System.out.println("6. LOOKINGAT() METHOD - PARTIAL MATCH FROM START\n");
        
        String text = "Java is awesome";
        Pattern pattern = Pattern.compile("Java");
        
        System.out.println("Text: '" + text + "'");
        System.out.println("Pattern: 'Java'");
        
        Matcher matcher = pattern.matcher(text);
        
        System.out.println("\nmatches(): " + matcher.matches());
        System.out.println("  Checks if ENTIRE string is 'Java'");
        
        matcher.reset();
        System.out.println("\nlookingAt(): " + matcher.lookingAt());
        System.out.println("  Checks if string STARTS with 'Java'");
        
        matcher.reset();
        System.out.println("\nfind(): " + matcher.find());
        System.out.println("  Searches for 'Java' ANYWHERE");
        
        // Practical example
        System.out.println("\nPractical use - Validating prefixes:");
        
        String[] urls = {
            "https://example.com",
            "http://example.com",
            "ftp://example.com",
            "example.com"
        };
        
        Pattern httpPattern = Pattern.compile("https?://");
        
        for (String url : urls) {
            Matcher m = httpPattern.matcher(url);
            System.out.println("  '" + url + "' starts with http(s): " + m.lookingAt());
        }
        
        System.out.println();
    }
    
    // 7. Replace Operations
    static void replaceOperations() {
        System.out.println("7. REPLACE OPERATIONS\n");
        
        String text = "Java 8, Java 11, Java 17";
        
        System.out.println("Original: " + text);
        
        // replaceFirst() - replace first match
        Pattern pattern = Pattern.compile("Java");
        Matcher matcher = pattern.matcher(text);
        
        String result1 = matcher.replaceFirst("Python");
        System.out.println("\nreplaceFirst('Python'): " + result1);
        
        // replaceAll() - replace all matches
        matcher.reset();
        String result2 = matcher.replaceAll("Python");
        System.out.println("replaceAll('Python'): " + result2);
        
        // Using regex in replacement
        System.out.println("\nUsing regex patterns:");
        
        String phoneText = "Call 123-456-7890 or 987-654-3210";
        System.out.println("Original: " + phoneText);
        
        String masked = phoneText.replaceAll("\\d{3}-\\d{3}-\\d{4}", "XXX-XXX-XXXX");
        System.out.println("Masked: " + masked);
        
        // appendReplacement and appendTail - advanced replacement
        System.out.println("\nAdvanced replacement with appendReplacement:");
        
        String prices = "Item1: $10, Item2: $20, Item3: $30";
        System.out.println("Original: " + prices);
        
        Pattern pricePattern = Pattern.compile("\\$(\\d+)");
        Matcher priceMatcher = pricePattern.matcher(prices);
        
        StringBuffer result = new StringBuffer();
        while (priceMatcher.find()) {
            int price = Integer.parseInt(priceMatcher.group(1));
            int discounted = (int)(price * 0.9);  // 10% discount
            priceMatcher.appendReplacement(result, "\\$" + discounted);
        }
        priceMatcher.appendTail(result);
        
        System.out.println("10% discount: " + result.toString());
        
        System.out.println();
    }
}