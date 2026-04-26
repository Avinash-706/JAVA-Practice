public class Regex05 {
    public static void main(String[] args) {
        System.out.println("abc".matches("^abc$"));
		System.out.println("xabc".matches("^abc$"));
		System.out.println("abcx".matches("^abc$"));
		System.out.println("xabcx".matches("^abc$"));
		
		System.out.println("12345".matches("^\\d+$"));
		System.out.println("123a".matches("^\\d+$"));
		System.out.println("12 3".matches("^\\d+$"));
		
		System.out.println("color".matches("^colo?r$"));
		System.out.println("colr".matches("^colo?r$"));
		System.out.println("coloor".matches("^colo?r$"));
		
		System.out.println("100".matches("\\d\\d?\\d"));
		System.out.println("10".matches("\\d\\d?\\d"));
		System.out.println("1".matches("\\d\\d?\\d"));
		
		System.out.println("abc123".matches("\\w+"));
		System.out.println("A_B9".matches("\\w+"));
		System.out.println("abc-123".matches("\\w+"));
		
		System.out.println("".matches("\\w*"));
		System.out.println("Test_01".matches("\\w*"));
		System.out.println("Test 01".matches("\\w*"));
		
		System.out.println("User_123".matches("^\\w+$"));
		System.out.println("User-123".matches("^\\w+$"));
		System.out.println("User 123".matches("^\\w+$"));
		
		System.out.println("abc_123".matches("\\w+")); 
		System.out.println("abc_123".matches("[A-Za-z0-9_]+")); 
		
		System.out.println("   ".matches("\\s+"));
		System.out.println("\t\t".matches("\\s+"));
		System.out.println(" ".matches("\\s+"));
		System.out.println(" a ".matches("\\s+"));
		
		System.out.println("".matches("\\s*"));
		System.out.println("   ".matches("\\s*"));
		System.out.println(" a ".matches("\\s*"));
			
		System.out.println("   ".matches("^\\s+$"));
		System.out.println(" \t ".matches("^\\s+$"));
		System.out.println(" a ".matches("^\\s+$"));
		
		System.out.println("1234".matches("\\d{4}"));
		System.out.println("123".matches("\\d{4}"));
		System.out.println("12345".matches("\\d{4}"));
		
		System.out.println("ABC".matches("[A-Z]{3}"));
		System.out.println("AB".matches("[A-Z]{3}"));
		System.out.println("ABCD".matches("[A-Z]{3}"));
		
		System.out.println("abcabc".matches("(abc){2}"));
		System.out.println("abc".matches("(abc){2}"));
		
		System.out.println("9999".matches("^\\d{4}$"));
		System.out.println("99a9".matches("^\\d{4}$"));
		System.out.println("999".matches("^\\d{4}$"));	
		
		System.out.println("123".matches("\\d{3,5}"));
		System.out.println("12345".matches("\\d{3,5}"));
		System.out.println("12".matches("\\d{3,5}"));
		System.out.println("123456".matches("\\d{3,5}"));
		
		System.out.println("ABCD".matches("[A-Z]{2,5}"));
		System.out.println("AB".matches("[A-Z]{2,5}"));
		System.out.println("ABCDE".matches("[A-Z]{2,5}"));
		System.out.println("A".matches("[A-Z]{2,5}"));
		
		System.out.println("abcabc".matches("(abc){2,3}"));
		System.out.println("abcabcabc".matches("(abc){2,3}"));
		System.out.println("abc".matches("(abc){2,3}"));
		
		System.out.println("9876543210".matches("^[6-9]\\d{9,10}$"));
		System.out.println("987654321".matches("^[6-9]\\d{9,10}$"));	
		
		System.out.println("cat".matches("^(cat|dog)$"));
		System.out.println("dog".matches("^(cat|dog)$"));
		System.out.println("catdog".matches("^(cat|dog)$"));
		
		System.out.println("A".matches("[A-Z]|[a-z]"));
		System.out.println("a".matches("[A-Z]|[a-z]"));
		System.out.println("1".matches("[A-Z]|[a-z]"));
		
		System.out.println("123".matches("\\d+|[A-Z]+"));
		System.out.println("ABC".matches("\\d+|[A-Z]+"));
		System.out.println("A1".matches("\\d+|[A-Z]+"));
		
		System.out.println("Mr".matches("(Mr|Mrs|Ms)"));
		System.out.println("Mrs".matches("(Mr|Mrs|Ms)"));
		System.out.println("Ms".matches("(Mr|Mrs|Ms)"));
		System.out.println("Dr".matches("(Mr|Mrs|Ms)"));
		
		System.out.println("UPI".matches("^(UPI|CASH|CARD)$"));
		System.out.println("NET".matches("^(UPI|CASH|CARD)$"));
    }
}
