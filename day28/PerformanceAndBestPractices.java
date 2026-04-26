import java.util.regex.*;
import java.util.*;

/**
 * Performance Optimization and Best Practices
 * Master efficient regex usage and avoid common pitfalls
 */
public class PerformanceAndBestPractices {
    
    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE & BEST PRACTICES ===\n");
        
        compilationPerformance();
        greedyVsReluctantPerformance();
        catastrophicBacktracking();
        atomicGrouping();
        commonMistakes();
        optimizationTechniques();
        bestPractices();
    }
    
    // 1. Pattern Compilation Performance
    static void compilationPerformance() {
        System.out.println("1. PATTERN COMPILATION PERFORMANCE\n");
        
        String text = "test@email.com";
        String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        
        // BAD: Compiling in loop
        System.out.println("BAD PRACTICE: Compiling in loop");
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 10000; i++) {
            Pattern pattern = Pattern.compile(regex);  // Compiled 10000 times!
            Matcher matcher = pattern.matcher(text);
            matcher.matches();
        }
        
        long badTime = System.currentTimeMillis() - startTime;
        System.out.println("  Time: " + badTime + "ms");
        
        // GOOD: Compile once, reuse
        System.out.println("\nGOOD PRACTICE: Compile once, reuse");
        startTime = System.currentTimeMillis();
        
        Pattern pattern = Pattern.compile(regex);  // Compiled once
        for (int i = 0; i < 10000; i++) {
            Matcher matcher = pattern.matcher(text);
            matcher.matches();
        }
        
        long goodTime = System.currentTimeMillis() - startTime;
        System.out.println("  Time: " + goodTime + "ms");
        
        System.out.println("\nPerformance improvement: " + 
                          String.format("%.1f", (double)badTime / goodTime) + "x faster");
        
        // BEST: Static final pattern
        System.out.println("\nBEST PRACTICE: Static final pattern");
        System.out.println("  private static final Pattern EMAIL = Pattern.compile(regex);");
        System.out.println("  Benefits:");
        System.out.println("    • Compiled once at class loading");
        System.out.println("    • Thread-safe");
        System.out.println("    • Memory efficient");
        
        System.out.println();
    }
    
    // 2. Greedy vs Reluctant Performance
    static void greedyVsReluctantPerformance() {
        System.out.println("2. GREEDY VS RELUCTANT PERFORMANCE\n");
        
        String html = "<div>" + "x".repeat(1000) + "</div>";
        
        // Greedy quantifier
        System.out.println("Testing with 1000 character string");
        System.out.println("\nGreedy quantifier: <.*>");
        
        long startTime = System.nanoTime();
        Pattern greedyPattern = Pattern.compile("<.*>");
        Matcher greedyMatcher = greedyPattern.matcher(html);
        greedyMatcher.find();
        long greedyTime = System.nanoTime() - startTime;
        
        System.out.println("  Time: " + greedyTime + " nanoseconds");
        System.out.println("  Behavior: Matches everything, then backtracks");
        
        // Reluctant quantifier
        System.out.println("\nReluctant quantifier: <.*?>");
        
        startTime = System.nanoTime();
        Pattern reluctantPattern = Pattern.compile("<.*?>");
        Matcher reluctantMatcher = reluctantPattern.matcher(html);
        reluctantMatcher.find();
        long reluctantTime = System.nanoTime() - startTime;
        
        System.out.println("  Time: " + reluctantTime + " nanoseconds");
        System.out.println("  Behavior: Matches minimally, stops at first >");
        
        System.out.println("\nRecommendation:");
        System.out.println("  Use reluctant quantifiers when you know the pattern");
        System.out.println("  Avoid greedy quantifiers with large inputs");
        
        System.out.println();
    }
    
    // 3. Catastrophic Backtracking
    static void catastrophicBacktracking() {
        System.out.println("3. CATASTROPHIC BACKTRACKING\n");
        
        System.out.println("Definition: Exponential time complexity due to backtracking");
        System.out.println("Occurs with nested quantifiers and alternation\n");
        
        // Dangerous pattern
        System.out.println("DANGEROUS PATTERN: (a+)+b");
        System.out.println("Input: 'aaaaaaaaaa' (no 'b' at end)");
        System.out.println("Problem: Tries all possible combinations");
        System.out.println("  a(aaaaaaaaa)");
        System.out.println("  aa(aaaaaaaa)");
        System.out.println("  aaa(aaaaaaa)");
        System.out.println("  ... exponential combinations!");
        
        // Safe pattern
        System.out.println("\nSAFE PATTERN: a+b");
        System.out.println("Input: 'aaaaaaaaaa' (no 'b' at end)");
        System.out.println("Solution: Matches all 'a's, fails once, done");
        
        // Example with timing
        System.out.println("\nDemonstration:");
        
        String input = "a".repeat(20);  // 20 'a's, no 'b'
        
        // Safe pattern
        System.out.println("Safe pattern: a+b");
        long startTime = System.nanoTime();
        boolean result1 = input.matches("a+b");
        long safeTime = System.nanoTime() - startTime;
        System.out.println("  Time: " + safeTime + " nanoseconds");
        System.out.println("  Result: " + result1);
        
        // Note: Dangerous pattern commented out to avoid hanging
        System.out.println("\nDangerous pattern: (a+)+b");
        System.out.println("  (Not executed - would take too long!)");
        System.out.println("  With 20 'a's: Could take seconds or minutes");
        
        System.out.println("\nHow to avoid:");
        System.out.println("  ✓ Avoid nested quantifiers: (a+)+");
        System.out.println("  ✓ Use possessive quantifiers: a++");
        System.out.println("  ✓ Use atomic groups: (?>a+)");
        System.out.println("  ✓ Be specific with patterns");
        
        System.out.println();
    }
    
    // 4. Atomic Grouping
    static void atomicGrouping() {
        System.out.println("4. ATOMIC GROUPING\n");
        
        System.out.println("Syntax: (?>pattern)");
        System.out.println("Behavior: Once matched, never backtracks");
        System.out.println("Use: Prevent catastrophic backtracking\n");
        
        // Example
        String text = "aaaaaab";
        
        System.out.println("Text: " + text);
        
        // Without atomic grouping
        System.out.println("\nWithout atomic grouping: (a+)b");
        Pattern normalPattern = Pattern.compile("(a+)b");
        Matcher normalMatcher = normalPattern.matcher(text);
        System.out.println("  Matches: " + normalMatcher.matches());
        System.out.println("  Process: Matches 'aaaaaa', backtracks to 'aaaaa', finds 'b'");
        
        // With atomic grouping
        System.out.println("\nWith atomic grouping: (?>a+)b");
        Pattern atomicPattern = Pattern.compile("(?>a+)b");
        Matcher atomicMatcher = atomicPattern.matcher(text);
        System.out.println("  Matches: " + atomicMatcher.matches());
        System.out.println("  Process: Matches 'aaaaaa', no 'b' left, fails (no backtrack)");
        
        // When to use
        System.out.println("\nWhen to use atomic grouping:");
        System.out.println("  ✓ Performance-critical code");
        System.out.println("  ✓ Known pattern structure");
        System.out.println("  ✓ Prevent backtracking");
        System.out.println("  ✓ Large input strings");
        
        System.out.println();
    }
    
    // 5. Common Mistakes
    static void commonMistakes() {
        System.out.println("5. COMMON MISTAKES\n");
        
        // Mistake 1: Not escaping special characters
        System.out.println("Mistake 1: Not escaping special characters");
        System.out.println("  Wrong: String.matches(\".\")  // Matches any character");
        System.out.println("  Right: String.matches(\"\\\\.\")  // Matches literal dot");
        System.out.println("  Test: " + "a".matches(".") + " vs " + "a".matches("\\."));
        
        // Mistake 2: Using matches() instead of find()
        System.out.println("\nMistake 2: Using matches() for substring search");
        System.out.println("  Wrong: \"abc 123\".matches(\"\\\\d+\")  // false");
        System.out.println("  Right: Pattern.compile(\"\\\\d+\").matcher(\"abc 123\").find()  // true");
        
        // Mistake 3: Forgetting anchors
        System.out.println("\nMistake 3: Forgetting anchors");
        System.out.println("  Without: \"123abc\".matches(\"\\\\d+\")  // false");
        System.out.println("  With: \"123abc\".matches(\"^\\\\d+\")  // false (correct)");
        System.out.println("  Explanation: matches() implicitly adds ^ and $");
        
        // Mistake 4: Inefficient alternation
        System.out.println("\nMistake 4: Inefficient alternation");
        System.out.println("  Slow: (cat|category|catastrophe)");
        System.out.println("  Fast: cat(egory|astrophe)?");
        System.out.println("  Reason: Common prefix factored out");
        
        // Mistake 5: Not using raw strings
        System.out.println("\nMistake 5: Excessive escaping");
        System.out.println("  Hard to read: \"\\\\d{2}-\\\\d{2}-\\\\d{4}\"");
        System.out.println("  Better: Use Pattern.compile() and document");
        
        System.out.println();
    }
    
    // 6. Optimization Techniques
    static void optimizationTechniques() {
        System.out.println("6. OPTIMIZATION TECHNIQUES\n");
        
        // Technique 1: Use character classes
        System.out.println("Technique 1: Use character classes instead of alternation");
        System.out.println("  Slow: (a|b|c|d|e)");
        System.out.println("  Fast: [a-e]");
        
        // Technique 2: Anchor patterns
        System.out.println("\nTechnique 2: Anchor patterns when possible");
        System.out.println("  Unanchored: \\d+  (searches entire string)");
        System.out.println("  Anchored: ^\\d+  (stops at first non-digit)");
        
        // Technique 3: Use possessive quantifiers
        System.out.println("\nTechnique 3: Use possessive quantifiers");
        System.out.println("  Greedy: .*  (backtracks)");
        System.out.println("  Possessive: .*+  (no backtracking)");
        
        // Technique 4: Limit scope
        System.out.println("\nTechnique 4: Limit scope with specific patterns");
        System.out.println("  Broad: .*");
        System.out.println("  Specific: [a-zA-Z0-9]+");
        
        // Technique 5: Use non-capturing groups
        System.out.println("\nTechnique 5: Use non-capturing groups");
        System.out.println("  Capturing: (\\d+)  (stores in memory)");
        System.out.println("  Non-capturing: (?:\\d+)  (doesn't store)");
        
        System.out.println();
    }
    
    // 7. Best Practices Summary
    static void bestPractices() {
        System.out.println("7. BEST PRACTICES SUMMARY\n");
        
        System.out.println("✓ DO:");
        System.out.println("  • Compile patterns once (static final)");
        System.out.println("  • Use specific character classes");
        System.out.println("  • Use reluctant quantifiers when appropriate");
        System.out.println("  • Test with large inputs");
        System.out.println("  • Document complex patterns");
        System.out.println("  • Use Pattern.quote() for literal strings");
        System.out.println("  • Reuse Matcher objects with reset()");
        System.out.println("  • Use non-capturing groups when possible");
        
        System.out.println("\n✗ DON'T:");
        System.out.println("  • Compile patterns in loops");
        System.out.println("  • Use nested quantifiers");
        System.out.println("  • Forget to escape special characters");
        System.out.println("  • Use .* carelessly");
        System.out.println("  • Ignore performance with large inputs");
        System.out.println("  • Use regex for simple string operations");
        System.out.println("  • Create complex patterns without testing");
        
        System.out.println("\n📊 Performance Tips:");
        System.out.println("  1. Profile regex performance");
        System.out.println("  2. Use Pattern.COMMENTS flag for readability");
        System.out.println("  3. Consider alternatives (String methods, parsers)");
        System.out.println("  4. Cache compiled patterns");
        System.out.println("  5. Use atomic groups for known patterns");
        
        System.out.println("\n🔍 Debugging Tips:");
        System.out.println("  1. Test patterns incrementally");
        System.out.println("  2. Use online regex testers");
        System.out.println("  3. Add comments with Pattern.COMMENTS");
        System.out.println("  4. Break complex patterns into parts");
        System.out.println("  5. Use named groups for clarity");
        
        // Example of well-documented pattern
        System.out.println("\n📝 Example: Well-documented pattern");
        System.out.println("```java");
        System.out.println("private static final Pattern EMAIL = Pattern.compile(");
        System.out.println("    \"^[a-zA-Z0-9._%+-]+\"  // Username");
        System.out.println("    + \"@\"                 // At symbol");
        System.out.println("    + \"[a-zA-Z0-9.-]+\"   // Domain");
        System.out.println("    + \"\\\\.\"             // Dot");
        System.out.println("    + \"[a-zA-Z]{2,}$\"    // Extension");
        System.out.println(");");
        System.out.println("```");
        
        System.out.println();
    }
}