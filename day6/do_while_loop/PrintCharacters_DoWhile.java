package day6.do_while_loop;
public class PrintCharacters_DoWhile {
    public static void printLoop1(char n1, char n2) {
        System.out.println("\n\nPrint :\t" + n1 + " to " + n2);
        do {
            System.out.print(n1++ + "\t");
        } while (n1 <= n2);
    }

    public static void printLoop2(char n1, char n2) {
        System.out.println("\n\nPrint :\t" + n1 + " to " + n2);
        do {
            System.out.print(n1-- + "\t");
        } 
        while (n1 >= n2);
    }

    public static void main(String[] args) {

        printLoop2('s', 'l');
        printLoop1('T', 'Z');
    }
}
