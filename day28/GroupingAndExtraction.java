import java.util.regex.*;
import java.util.*;

/**
 * Grouping and Data Extraction - Complete Guide
 * Master capturing groups, named groups, and data extraction patterns
 */
public class GroupingAndExtraction {
    
    public static void main(String[] args) {
        System.out.println("=== GROUPING AND DATA EXTRACTION ===\n");
        
        basicGrouping();
        multipleGroups();
        nestedGroups();
        namedGroups();
        nonCapturingGroups();
        backreferences();
        practicalExamples();
    }
    
    // 1. Basic Grouping - Extracting Parts
    static void basicGrouping() {
        System.out.println("1. BASIC GROUPING - EXTRACTING PARTS\n");
        
        String text = "Date: 12-05-2024";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: (\\d{2})-(\\d{2})-(\\d{4})");
        System.out.println("Groups: (day)-(month)-(year)\n");
        
        Pattern pattern = Pattern.compile("(\\d{2})-(\\d{2})-(\\d{4})");
        Matcher matcher = pattern.matcher(text);
        
        if (matcher.find()) {
            System.out.println("group(0) - Full match: " + matcher.group(0));
            System.out.println("group(1) - Day: " + matcher.group(1));
            System.out.println("group(2) - Month: " + matcher.group(2));
            System.out.println("group(3) - Year: " + matcher.group(3));
        }
        
        // Group numbering
        System.out.println("\nGroup Numbering Rules:");
        System.out.println("  group(0) → Entire match");
        System.out.println("  group(1) → First parenthesis ()");
        System.out.println("  group(2) → Second parenthesis ()");
        System.out.println("  group(n) → nth parenthesis ()");
        
        System.out.println();
    }
    
    // 2. Multiple Groups - Complex Extraction
    static void multipleGroups() {
        System.out.println("2. MULTIPLE GROUPS - COMPLEX EXTRACTION\n");
        
        String text = "Contact: John Doe, Email: john.doe@company.com, Phone: 123-456-7890";
        
        System.out.println("Text: " + text);
        System.out.println("\nExtracting structured data:");
        
        // Extract name
        Pattern namePattern = Pattern.compile("Contact: ([A-Za-z]+ [A-Za-z]+)");
        Matcher nameMatcher = namePattern.matcher(text);
        
        if (nameMatcher.find()) {
            System.out.println("  Name: " + nameMatcher.group(1));
        }
        
        // Extract email parts
        Pattern emailPattern = Pattern.compile("Email: ([a-z.]+)@([a-z]+)\\.([a-z]+)");
        Matcher emailMatcher = emailPattern.matcher(text);
        
        if (emailMatcher.find()) {
            System.out.println("  Email username: " + emailMatcher.group(1));
            System.out.println("  Email domain: " + emailMatcher.group(2));
            System.out.println("  Email extension: " + emailMatcher.group(3));
        }
        
        // Extract phone parts
        Pattern phonePattern = Pattern.compile("Phone: (\\d{3})-(\\d{3})-(\\d{4})");
        Matcher phoneMatcher = phonePattern.matcher(text);
        
        if (phoneMatcher.find()) {
            System.out.println("  Phone area code: " + phoneMatcher.group(1));
            System.out.println("  Phone prefix: " + phoneMatcher.group(2));
            System.out.println("  Phone line: " + phoneMatcher.group(3));
        }
        
        System.out.println();
    }
    
    // 3. Nested Groups - Groups within Groups
    static void nestedGroups() {
        System.out.println("3. NESTED GROUPS - GROUPS WITHIN GROUPS\n");
        
        String text = "URL: https://www.example.com:8080/path";
        
        System.out.println("Text: " + text);
        System.out.println("Pattern: ((https?)://([^:]+):?(\\d+)?)(/.*)");
        System.out.println("Nested structure: (protocol://domain:port)(path)\n");
        
        // Pattern breakdown:
        // Group 1: ((https?)://([^:]+):?(\\d+)?) - Full URL without path
        // Group 2: (https?) - Protocol
        // Group 3: ([^:]+) - Domain
        // Group 4: (\\d+)? - Port (optional)
        // Group 5: (/.*)  - Path
        
        Pattern pattern = Pattern.compile("((https?)://([^:]+):?(\\d+)?)(/.*)");
        Matcher matcher = pattern.matcher(text);
        
        if (matcher.find()) {
            System.out.println("Group 0 (Full match): " + matcher.group(0));
            System.out.println("Group 1 (Base URL): " + matcher.group(1));
            System.out.println("Group 2 (Protocol): " + matcher.group(2));
            System.out.println("Group 3 (Domain): " + matcher.group(3));
            System.out.println("Group 4 (Port): " + matcher.group(4));
            System.out.println("Group 5 (Path): " + matcher.group(5));
        }
        
        // Nested groups numbering
        System.out.println("\nNested Groups Numbering:");
        System.out.println("  Groups are numbered by opening parenthesis position");
        System.out.println("  Left to right, outer to inner");
        
        System.out.println();
    }
    
    // 4. Named Groups - Readable Group References
    static void namedGroups() {
        System.out.println("4. NAMED GROUPS - READABLE REFERENCES\n");
        
        String text = "Order #12345 placed on 2024-05-12 by customer@email.com";
        
        System.out.println("Text: " + text);
        System.out.println("Using named groups for clarity\n");
        
        // Named group syntax: (?<name>pattern)
        Pattern pattern = Pattern.compile(
            "Order #(?<orderId>\\d+) placed on (?<date>\\d{4}-\\d{2}-\\d{2}) by (?<email>[^\\s]+)"
        );
        
        Matcher matcher = pattern.matcher(text);
        
        if (matcher.find()) {
            // Access by name (more readable)
            System.out.println("Using named groups:");
            System.out.println("  Order ID: " + matcher.group("orderId"));
            System.out.println("  Date: " + matcher.group("date"));
            System.out.println("  Email: " + matcher.group("email"));
            
            // Still accessible by number
            System.out.println("\nStill accessible by number:");
            System.out.println("  group(1): " + matcher.group(1));
            System.out.println("  group(2): " + matcher.group(2));
            System.out.println("  group(3): " + matcher.group(3));
        }
        
        // Benefits of named groups
        System.out.println("\nBenefits of Named Groups:");
        System.out.println("  ✓ More readable code");
        System.out.println("  ✓ Self-documenting patterns");
        System.out.println("  ✓ Easier maintenance");
        System.out.println("  ✓ Less error-prone");
        
        System.out.println();
    }
    
    // 5. Non-Capturing Groups - Performance Optimization
    static void nonCapturingGroups() {
        System.out.println("5. NON-CAPTURING GROUPS - OPTIMIZATION\n");
        
        String text = "Price: $100 or €85 or £75";
        
        System.out.println("Text: " + text);
        
        // Capturing groups (stores in memory)
        System.out.println("\nWith capturing groups:");
        Pattern capturingPattern = Pattern.compile("(\\$|€|£)(\\d+)");
        Matcher capturingMatcher = capturingPattern.matcher(text);
        
        while (capturingMatcher.find()) {
            System.out.println("  Currency: " + capturingMatcher.group(1));
            System.out.println("  Amount: " + capturingMatcher.group(2));
        }
        
        // Non-capturing groups (doesn't store, faster)
        System.out.println("\nWith non-capturing groups:");
        Pattern nonCapturingPattern = Pattern.compile("(?:\\$|€|£)(\\d+)");
        Matcher nonCapturingMatcher = nonCapturingPattern.matcher(text);
        
        while (nonCapturingMatcher.find()) {
            // Only one group now (the amount)
            System.out.println("  Amount: " + nonCapturingMatcher.group(1));
            // Currency not captured, saves memory
        }
        
        // Syntax comparison
        System.out.println("\nSyntax:");
        System.out.println("  Capturing: (pattern)");
        System.out.println("  Non-capturing: (?:pattern)");
        
        System.out.println("\nWhen to use non-capturing:");
        System.out.println("  ✓ Need grouping for quantifiers");
        System.out.println("  ✓ Don't need to extract the group");
        System.out.println("  ✓ Want better performance");
        
        System.out.println();
    }
    
    // 6. Backreferences - Matching Previously Captured Groups
    static void backreferences() {
        System.out.println("6. BACKREFERENCES - MATCHING PREVIOUS GROUPS\n");
        
        // Find repeated words
        System.out.println("Finding repeated words:");
        String text1 = "This is is a test test sentence";
        
        System.out.println("Text: " + text1);
        System.out.println("Pattern: \\b(\\w+)\\s+\\1\\b");
        System.out.println("Explanation: \\1 refers back to group 1\n");
        
        Pattern pattern1 = Pattern.compile("\\b(\\w+)\\s+\\1\\b");
        Matcher matcher1 = pattern1.matcher(text1);
        
        while (matcher1.find()) {
            System.out.println("  Repeated word: " + matcher1.group(1));
        }
        
        // Find matching HTML tags
        System.out.println("\nFinding matching HTML tags:");
        String html = "<div>Content</div> <span>Text</span> <p>Paragraph</p>";
        
        System.out.println("HTML: " + html);
        System.out.println("Pattern: <(\\w+)>.*?</\\1>");
        System.out.println("Explanation: \\1 ensures closing tag matches opening\n");
        
        Pattern pattern2 = Pattern.compile("<(\\w+)>.*?</\\1>");
        Matcher matcher2 = pattern2.matcher(html);
        
        while (matcher2.find()) {
            System.out.println("  Matched tag: " + matcher2.group(0));
            System.out.println("  Tag name: " + matcher2.group(1));
        }
        
        // Find palindrome patterns
        System.out.println("\nFinding palindrome patterns:");
        String text3 = "aba cdc efg xyx";
        
        System.out.println("Text: " + text3);
        System.out.println("Pattern: \\b(\\w)(\\w)\\1\\b");
        System.out.println("Explanation: First char, middle char, first char again\n");
        
        Pattern pattern3 = Pattern.compile("\\b(\\w)(\\w)\\1\\b");
        Matcher matcher3 = pattern3.matcher(text3);
        
        while (matcher3.find()) {
            System.out.println("  Palindrome: " + matcher3.group(0));
        }
        
        System.out.println();
    }
    
    // 7. Practical Examples - Real-World Data Extraction
    static void practicalExamples() {
        System.out.println("7. PRACTICAL EXAMPLES - REAL-WORLD EXTRACTION\n");
        
        // Example 1: Parse log entries
        System.out.println("Example 1: Parsing Log Entries");
        String log = "[2024-05-12 10:30:45] ERROR: Connection timeout at server1.example.com";
        
        Pattern logPattern = Pattern.compile(
            "\\[(?<date>\\d{4}-\\d{2}-\\d{2}) (?<time>\\d{2}:\\d{2}:\\d{2})\\] " +
            "(?<level>\\w+): (?<message>.+)"
        );
        
        Matcher logMatcher = logPattern.matcher(log);
        
        if (logMatcher.find()) {
            System.out.println("  Date: " + logMatcher.group("date"));
            System.out.println("  Time: " + logMatcher.group("time"));
            System.out.println("  Level: " + logMatcher.group("level"));
            System.out.println("  Message: " + logMatcher.group("message"));
        }
        
        // Example 2: Extract credit card info (masked)
        System.out.println("\nExample 2: Credit Card Parsing");
        String card = "Card: 1234-5678-9012-3456, Exp: 12/25, CVV: 123";
        
        Pattern cardPattern = Pattern.compile(
            "Card: (\\d{4})-(\\d{4})-(\\d{4})-(\\d{4}), Exp: (\\d{2})/(\\d{2}), CVV: (\\d{3})"
        );
        
        Matcher cardMatcher = cardPattern.matcher(card);
        
        if (cardMatcher.find()) {
            System.out.println("  Card (masked): ****-****-****-" + cardMatcher.group(4));
            System.out.println("  Expiry Month: " + cardMatcher.group(5));
            System.out.println("  Expiry Year: " + cardMatcher.group(6));
            // CVV not displayed for security
        }
        
        // Example 3: Parse CSV-like data
        System.out.println("\nExample 3: CSV Data Extraction");
        String csv = "John,Doe,30,Engineer,New York";
        
        Pattern csvPattern = Pattern.compile("([^,]+),([^,]+),(\\d+),([^,]+),(.+)");
        Matcher csvMatcher = csvPattern.matcher(csv);
        
        if (csvMatcher.find()) {
            System.out.println("  First Name: " + csvMatcher.group(1));
            System.out.println("  Last Name: " + csvMatcher.group(2));
            System.out.println("  Age: " + csvMatcher.group(3));
            System.out.println("  Occupation: " + csvMatcher.group(4));
            System.out.println("  City: " + csvMatcher.group(5));
        }
        
        // Example 4: Extract all URLs from text
        System.out.println("\nExample 4: URL Extraction");
        String text = "Visit https://example.com or http://test.org for more info";
        
        Pattern urlPattern = Pattern.compile("(https?)://([a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})");
        Matcher urlMatcher = urlPattern.matcher(text);
        
        while (urlMatcher.find()) {
            System.out.println("  Protocol: " + urlMatcher.group(1));
            System.out.println("  Domain: " + urlMatcher.group(2));
            System.out.println("  Full URL: " + urlMatcher.group(0));
            System.out.println();
        }
        
        System.out.println();
    }
}