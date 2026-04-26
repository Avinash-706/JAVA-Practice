import java.util.regex.*;

/**
 * Real-World Validation Patterns
 * Comprehensive collection of common validation regex patterns
 */
public class ValidationPatterns {
    
    // Compiled patterns for reuse (performance optimization)
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );
    
    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^[6-9]\\d{9}$"
    );
    
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$"
    );
    
    private static final Pattern URL_PATTERN = Pattern.compile(
        "^https?://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/.*)?$"
    );
    
    private static final Pattern IP_ADDRESS_PATTERN = Pattern.compile(
        "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"
    );
    
    public static void main(String[] args) {
        System.out.println("=== VALIDATION PATTERNS ===\n");
        
        emailValidation();
        phoneValidation();
        passwordValidation();
        urlValidation();
        dateValidation();
        creditCardValidation();
        ipAddressValidation();
        usernameValidation();
        postalCodeValidation();
        htmlTagValidation();
    }
    
    // 1. Email Validation
    static void emailValidation() {
        System.out.println("1. EMAIL VALIDATION\n");
        
        System.out.println("Pattern: ^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        System.out.println("Rules:");
        System.out.println("  • Username: letters, digits, . _ % + -");
        System.out.println("  • Must have @");
        System.out.println("  • Domain: letters, digits, . -");
        System.out.println("  • Extension: at least 2 letters\n");
        
        String[] emails = {
            "user@example.com",
            "john.doe@company.co.uk",
            "test_123@domain.org",
            "invalid@",
            "@invalid.com",
            "no-at-sign.com",
            "user@domain",
            "user name@domain.com"
        };
        
        System.out.println("Testing emails:");
        for (String email : emails) {
            boolean valid = EMAIL_PATTERN.matcher(email).matches();
            System.out.println("  " + String.format("%-30s", email) + " → " + 
                              (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        System.out.println();
    }
    
    // 2. Phone Number Validation
    static void phoneValidation() {
        System.out.println("2. PHONE NUMBER VALIDATION (Indian)\n");
        
        System.out.println("Pattern: ^[6-9]\\d{9}$");
        System.out.println("Rules:");
        System.out.println("  • Must start with 6, 7, 8, or 9");
        System.out.println("  • Exactly 10 digits");
        System.out.println("  • No spaces or special characters\n");
        
        String[] phones = {
            "9876543210",
            "8123456789",
            "6000000000",
            "5123456789",  // Starts with 5
            "98765432",    // Only 8 digits
            "98765432101", // 11 digits
            "987-654-3210" // Has dashes
        };
        
        System.out.println("Testing phone numbers:");
        for (String phone : phones) {
            boolean valid = PHONE_PATTERN.matcher(phone).matches();
            System.out.println("  " + String.format("%-15s", phone) + " → " + 
                              (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        System.out.println();
    }
    
    // 3. Password Validation
    static void passwordValidation() {
        System.out.println("3. PASSWORD VALIDATION\n");
        
        System.out.println("Pattern: ^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$");
        System.out.println("Rules:");
        System.out.println("  • Minimum 8 characters");
        System.out.println("  • At least one uppercase letter");
        System.out.println("  • At least one lowercase letter");
        System.out.println("  • At least one digit");
        System.out.println("  • At least one special character (@#$%^&+=)\n");
        
        String[] passwords = {
            "Password123@",
            "Secure@Pass1",
            "password",      // No uppercase, digit, special
            "PASSWORD123@",  // No lowercase
            "Password@",     // No digit
            "Password123",   // No special
            "Pass1@"         // Too short
        };
        
        System.out.println("Testing passwords:");
        for (String pwd : passwords) {
            boolean valid = PASSWORD_PATTERN.matcher(pwd).matches();
            System.out.println("  " + String.format("%-20s", pwd) + " → " + 
                              (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        System.out.println();
    }
    
    // 4. URL Validation
    static void urlValidation() {
        System.out.println("4. URL VALIDATION\n");
        
        System.out.println("Pattern: ^https?://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/.*)?$");
        System.out.println("Rules:");
        System.out.println("  • Must start with http:// or https://");
        System.out.println("  • Valid domain name");
        System.out.println("  • Optional path\n");
        
        String[] urls = {
            "http://example.com",
            "https://www.example.com",
            "https://example.com/path/to/page",
            "ftp://example.com",
            "http://example",
            "example.com",
            "http:/example.com"
        };
        
        System.out.println("Testing URLs:");
        for (String url : urls) {
            boolean valid = URL_PATTERN.matcher(url).matches();
            System.out.println("  " + String.format("%-40s", url) + " → " + 
                              (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        System.out.println();
    }
    
    // 5. Date Validation
    static void dateValidation() {
        System.out.println("5. DATE VALIDATION\n");
        
        System.out.println("Pattern: ^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$");
        System.out.println("Format: DD-MM-YYYY");
        System.out.println("Rules:");
        System.out.println("  • Day: 01-31");
        System.out.println("  • Month: 01-12");
        System.out.println("  • Year: 4 digits\n");
        
        Pattern datePattern = Pattern.compile(
            "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$"
        );
        
        String[] dates = {
            "12-05-2024",
            "31-12-2023",
            "01-01-2000",
            "32-05-2024",  // Invalid day
            "12-13-2024",  // Invalid month
            "1-5-2024",    // Missing leading zeros
            "12/05/2024"   // Wrong separator
        };
        
        System.out.println("Testing dates:");
        for (String date : dates) {
            boolean valid = datePattern.matcher(date).matches();
            System.out.println("  " + String.format("%-15s", date) + " → " + 
                              (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        System.out.println();
    }
    
    // 6. Credit Card Validation
    static void creditCardValidation() {
        System.out.println("6. CREDIT CARD VALIDATION\n");
        
        System.out.println("Pattern: ^\\d{4}-\\d{4}-\\d{4}-\\d{4}$");
        System.out.println("Format: XXXX-XXXX-XXXX-XXXX");
        System.out.println("Rules:");
        System.out.println("  • 16 digits");
        System.out.println("  • Grouped in 4s with dashes\n");
        
        Pattern cardPattern = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$");
        
        String[] cards = {
            "1234-5678-9012-3456",
            "4532-1234-5678-9010",
            "123456789012345",     // No dashes
            "1234-5678-9012-345",  // Only 15 digits
            "1234-5678-9012-34567" // 17 digits
        };
        
        System.out.println("Testing credit cards:");
        for (String card : cards) {
            boolean valid = cardPattern.matcher(card).matches();
            System.out.println("  " + String.format("%-25s", card) + " → " + 
                              (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        System.out.println();
    }
    
    // 7. IP Address Validation
    static void ipAddressValidation() {
        System.out.println("7. IP ADDRESS VALIDATION\n");
        
        System.out.println("Pattern: ^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}...$");
        System.out.println("Rules:");
        System.out.println("  • Four octets separated by dots");
        System.out.println("  • Each octet: 0-255\n");
        
        String[] ips = {
            "192.168.1.1",
            "255.255.255.255",
            "0.0.0.0",
            "256.1.1.1",      // 256 > 255
            "192.168.1",      // Only 3 octets
            "192.168.1.1.1",  // 5 octets
            "192.168.-1.1"    // Negative number
        };
        
        System.out.println("Testing IP addresses:");
        for (String ip : ips) {
            boolean valid = IP_ADDRESS_PATTERN.matcher(ip).matches();
            System.out.println("  " + String.format("%-20s", ip) + " → " + 
                              (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        System.out.println();
    }
    
    // 8. Username Validation
    static void usernameValidation() {
        System.out.println("8. USERNAME VALIDATION\n");
        
        System.out.println("Pattern: ^[a-zA-Z][a-zA-Z0-9_]{2,15}$");
        System.out.println("Rules:");
        System.out.println("  • 3-16 characters");
        System.out.println("  • Must start with letter");
        System.out.println("  • Can contain letters, digits, underscore\n");
        
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{2,15}$");
        
        String[] usernames = {
            "john_doe",
            "user123",
            "validUser",
            "ab",           // Too short
            "123user",      // Starts with digit
            "user-name",    // Contains dash
            "verylongusername123" // Too long
        };
        
        System.out.println("Testing usernames:");
        for (String username : usernames) {
            boolean valid = usernamePattern.matcher(username).matches();
            System.out.println("  " + String.format("%-25s", username) + " → " + 
                              (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        System.out.println();
    }
    
    // 9. Postal Code Validation
    static void postalCodeValidation() {
        System.out.println("9. POSTAL CODE VALIDATION (Indian PIN)\n");
        
        System.out.println("Pattern: ^[1-9]\\d{5}$");
        System.out.println("Rules:");
        System.out.println("  • 6 digits");
        System.out.println("  • Cannot start with 0\n");
        
        Pattern pinPattern = Pattern.compile("^[1-9]\\d{5}$");
        
        String[] pins = {
            "110001",
            "400001",
            "560001",
            "012345",  // Starts with 0
            "11001",   // Only 5 digits
            "1100011", // 7 digits
            "11000A"   // Contains letter
        };
        
        System.out.println("Testing PIN codes:");
        for (String pin : pins) {
            boolean valid = pinPattern.matcher(pin).matches();
            System.out.println("  " + String.format("%-10s", pin) + " → " + 
                              (valid ? "✓ Valid" : "✗ Invalid"));
        }
        
        System.out.println();
    }
    
    // 10. HTML Tag Validation
    static void htmlTagValidation() {
        System.out.println("10. HTML TAG VALIDATION\n");
        
        System.out.println("Pattern: <([a-z]+)([^<]+)*(?:>(.*)<\\/\\1>|\\s+\\/>)");
        System.out.println("Matches: Opening and closing tags or self-closing tags\n");
        
        Pattern htmlPattern = Pattern.compile("<([a-z]+)([^<]+)*(?:>(.*?)</\\1>|\\s*/>)");
        
        String html = "<div>Content</div> <br/> <span class='test'>Text</span>";
        
        System.out.println("HTML: " + html);
        System.out.println("\nMatched tags:");
        
        Matcher matcher = htmlPattern.matcher(html);
        while (matcher.find()) {
            System.out.println("  " + matcher.group());
        }
        
        System.out.println();
    }
}