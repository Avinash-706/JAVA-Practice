import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorOne {
    public static void main(String[] args) {

        List<Integer> li = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListIterator<Integer> lit = li.listIterator();

        System.out.println("Initial State of ListIterator");
        System.out.println("hasPrevious(): " + lit.hasPrevious());
        System.out.println("hasNext(): "     + lit.hasNext());

        System.out.println("\nCalling next()");
        System.out.println("next(): "       + lit.next());

        System.out.println("\nCalling previous()");
        System.out.println("previous(): "   + lit.previous());

        System.out.println("\nListIterator object reference");
        System.out.println("ListIterator: " + lit);

        System.out.println("\nForward Traversal using ListIterator");
        while (lit.hasNext()) {
            System.out.print(lit.next() + " ");
        }

        System.out.println("\n\nBackward Traversal using ListIterator");
        while (lit.hasPrevious()) {
            System.out.print(lit.previous() + " ");
        }
    }
}
