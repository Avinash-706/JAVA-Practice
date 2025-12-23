public class PrintCharactersInRange {

    public static void printLoop(char n1, char n2) {
        System.out.println("Print :\t" + n1 + " to " + n2);
        while(n1 <= n2)     System.out.println(n1++);
    }

    public static void main(String[] args) {

        printLoop('A', 'M');
        printLoop('a', 'm');

    }    
}
