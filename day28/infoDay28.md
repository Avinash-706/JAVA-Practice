# ☕ Comprehensive Guide: Regular Expressions (Regex) in Java

## 1. Introduction & The "Backslash" Rule

### What is Regex?
A **Regular Expression** is a sequence of characters that forms a search pattern. It is used for validation (passwords), searching, and data extraction.

*   **Package:** `java.util.regex`

### ⚠️ The Golden Rule of Java Regex
In standard Regex, a digit is represented as `\d`.
**However**, in Java Strings, the backslash `\` is an escape character (like `\n` for new line).
To write a regex backslash in Java, you must **escape the backslash itself**.

*   Regex: `\d` (digit)
*   **Java String:** `"\\d"`

---

## 2. String Methods vs. Pattern & Matcher

Before diving into complex classes, you must know when to use the simple approach vs. the powerful approach.

### A. Simple Way: `String` class
Use this for **one-time validation** (exact match). It cannot search for substrings or return groups.

```java
String input = "12345";
// Checks if the WHOLE string consists of digits
boolean isMatch = input.matches("\\d+"); 
System.out.println(isMatch); // true
```

### B. Powerful Way: `Pattern` & `Matcher` classes
Use this when you need to:
1.  **Search** inside a text.
2.  Find **multiple** occurrences.
3.  **Reuse** the same regex (Performance).
4.  Extract specific parts (**Groups**).

#### The Classes
1.  **`Pattern` (The Stamp):** The compiled representation of the regex. Static and efficient.
2.  **`Matcher` (The Stamper):** The engine that applies the pattern to a specific string.

---

## 3. Core Methods: Find, Group, Start, End

Here is a breakdown of the `Matcher` methods with a complete example.

*   **`find()`**: Scans the text for the *next* subsequence that matches the pattern.
*   **`group()`**: Returns the actual text that matched.
*   **`start()`**: Returns the index where the match started.
*   **`end()`**: Returns the index *after* the match ended.

### 💻 Code Example: Basic Search
```java
import java.util.regex.*;

public class MethodsDemo {
    public static void main(String[] args) {
        String text = "Java 123 and 456 released on 12-05-2024";

        // Define rule: Find one or more digits
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        // find() works like an iterator
        while (matcher.find()) {
            System.out.println("Found: " + matcher.group());
            System.out.println("Start Index: " + matcher.start());
            System.out.println("End Index: " + matcher.end());
            System.out.println("-----");
        }
    }
}
```
**Output:**
```text
Found: 123
Start Index: 5
End Index: 8
-----
Found: 456
Start Index: 13
End Index: 16
...
```

---

## 4. Grouping `()`: Extracting Data

Grouping is used to extract specific parts of a match (e.g., getting just the Day from a Date). Groups are numbered:
*   `group(0)`: The entire match.
*   `group(1)`: The first parenthesis `( )`.
*   `group(2)`: The second parenthesis `( )`.

### 💻 Code Example: Data Extraction
```java
import java.util.regex.*;

public class GroupingDemo {
    public static void main(String[] args) {
        // Regex: (2 digits) - (2 digits) - (4 digits)
        Pattern p = Pattern.compile("(\\d{2})-(\\d{2})-(\\d{4})");
        Matcher m = p.matcher("DOB: 12-05-2024");

        if (m.find()) {
            System.out.println("Full Date: " + m.group(0)); // 12-05-2024
            System.out.println("Day:       " + m.group(1)); // 12
            System.out.println("Month:     " + m.group(2)); // 05
            System.out.println("Year:      " + m.group(3)); // 2024
        }
    }
}
```

---

## 5. Regex Syntax Cheatsheet (Java Style)

### A. Character Classes (What to match)

| Symbol | Java String Syntax | Description | Example |
| :--- | :--- | :--- | :--- |
| `.` | `.` | Any character (except newline) | `"a.c"` matches "abc" |
| `\d` | `"\\d"` | Any Digit `[0-9]` | `"\\d"` matches "5" |
| `\w` | `"\\w"` | Word Char `[a-zA-Z0-9_]` | `"\\w"` matches "A", "9", "_" |
| `\s` | `"\\s"` | Whitespace (space, tab) | `"\\s"` matches " " |
| `[abc]`| `"[abc]"` | a, b, or c | `"[abc]"` matches "a" |
| `[^abc]`| `"[^abc]"` | Any char EXCEPT a, b, or c | `"[^abc]"` matches "z" |

### B. Quantifiers (How many times)

| Symbol | Description | Java Example |
| :--- | :--- | :--- |
| `*` | **0** or more | `"ab*"` (matches "a", "abb") |
| `+` | **1** or more | `"\\d+"` (matches "1", "123") |
| `?` | **0** or **1** (Optional) | `"https?"` (matches "http", "https") |
| `{n}` | Exactly **n** times | `"\\d{3}"` (matches "123") |
| `{n,}` | **n** or more times | `"\\w{8,}"` (min 8 chars) |

---

## 6. Advanced: Lookaheads (Positive `?=`)

This is crucial for complex validation like **Passwords**.
**Definition:** Lookaheads check if a pattern exists *ahead* in the string, but they **do not consume** characters. They just say "True" or "False" and reset the cursor.

### 🧠 The Logic
*   `(?=.*[A-Z])` → Search ahead. Is there an Uppercase letter somewhere? Yes/No. Come back to start.
*   `(?=.*\\d)` → Search ahead. Is there a Digit somewhere? Yes/No. Come back to start.

### 💻 Code Example: Password Validation
**Rule:** Min 8 chars, at least 1 Uppercase, at least 1 Digit.

```java
import java.util.regex.*;

public class PasswordValidator {
    public static void main(String[] args) {
        // 1. ^ matches start
        // 2. (?=.*[A-Z]) Checks for uppercase anywhere
        // 3. (?=.*\\d)   Checks for digit anywhere
        // 4. .{8,}       Matches actual characters (min 8)
        // 5. $ matches end
        String regex = "^(?=.*[A-Z])(?=.*\\d).{8,}$";

        System.out.println("pass123".matches(regex));    // false (no Upper, len < 8)
        System.out.println("Pass1234".matches(regex));   // true
        System.out.println("PASSWORD".matches(regex));   // false (no digit)
    }
}
```

### 🔍 Lookahead vs. Normal Match
Here is the difference between matching and "peeking" (lookahead).

**Scenario:** Find a letter only if it is followed by a number.

```java
import java.util.regex.*;

public class LookaheadDemo {
    public static void main(String[] args) {
        String input = "A1 B2 C3 D";
        
        // Regex: \\w(?=\\d)
        // Meaning: Match a word char, BUT only if next char is digit.
        // Don't include the digit in the match result.
        Pattern p = Pattern.compile("\\w(?=\\d)");
        Matcher m = p.matcher(input);

        while (m.find()) {
            System.out.println("Match: " + m.group());
        }
    }
}
```
**Output:**
```text
Match: A   <-- Found 'A' because '1' was after it.
Match: B   <-- Found 'B' because '2' was after it.
Match: C   <-- Found 'C' because '3' was after it.
(D is not matched because no digit follows it)
```

---

## 7. Performance & Best Practices

### ⚠️ Performance Cost
`Pattern.compile()` is expensive (heavy on CPU).
*   **Bad Practice:** Compiling inside a loop.
*   **Good Practice:** Compile once, make it `static final`.

```java
public class TextProcessor {
    // COMPILE ONCE
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9._]+@[a-z]+\\.com");

    void validate(String email) {
        // REUSE MANY TIMES
        Matcher m = EMAIL_PATTERN.matcher(email);
        // ... logic
    }
}
```

### 🧠 Interview Question: Greedy vs Reluctant
*   **Greedy (`.*`)**: Eats everything until the end of the line, then backtracks.
*   **Reluctant (`.*?`)**: Eats one character at a time, checking if the rest matches.

**Example:** Input: `<div>Hello</div>`
*   `Pattern.compile("<.*>")` matches: `<div>Hello</div>` (Whole line)
*   `Pattern.compile("<.*?>")` matches: `<div>` (Stops at first `>`)