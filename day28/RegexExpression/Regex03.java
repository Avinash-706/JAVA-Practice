public class Regex03 {
    public static void main(String[] args){
        System.out.println("aaa".matches("a{3}"));
		System.out.println("aa".matches("a{3}"));
		System.out.println("aaaa".matches("a{3}"));
		
		System.out.println();
		
		System.out.println("aaa".matches("a{2,4}"));
		System.out.println("aa".matches("a{2,4}"));
		System.out.println("aaaa".matches("a{2,4}"));
		System.out.println("a".matches("a{2,4}"));
		System.out.println("aaaaa".matches("a{2,4}"));
		
		System.out.println();
		
		System.out.println("cat".matches("cat|dog"));
		System.out.println("dog".matches("cat|dog"));
		System.out.println("cow".matches("cat|dog"));
    }
}
