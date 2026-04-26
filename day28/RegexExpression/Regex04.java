public class Regex04 {
    public static void main(String[] args) {
        
        System.out.println("123".matches("\\d+")); 
		System.out.println("9".matches("\\d+"));   
		System.out.println("".matches("\\d+"));    
		
		System.out.println("".matches("\\d*"));    
		System.out.println("123".matches("\\d*")); 
		System.out.println("a12".matches("\\d*")); 
		
		System.out.println("ABC".matches("[A-Z]+"));
		System.out.println("A".matches("[A-Z]+"));   
		System.out.println("AbC".matches("[A-Z]+")); 
		
		System.out.println("".matches("[A-Z]*"));    
		System.out.println("XYZ".matches("[A-Z]*")); 
		System.out.println("XyZ".matches("[A-Z]*")); 
		
		System.out.println("abc".matches("[a-z]+")); 
		System.out.println("a".matches("[a-z]+"));   
		System.out.println("aBc".matches("[a-z]+")); 
		
		System.out.println("".matches("[a-z]*"));    
		System.out.println("xyz".matches("[a-z]*")); 
		System.out.println("xYz".matches("[a-z]*")); 
		
		System.out.println("abcabc".matches("(abc)+")); 
		System.out.println("abc".matches("(abc)+"));   
		System.out.println("ababc".matches("(abc)+"));  
		
		System.out.println("".matches("(abc)*"));   
		System.out.println("abc".matches("(abc)*"));
    }
}
