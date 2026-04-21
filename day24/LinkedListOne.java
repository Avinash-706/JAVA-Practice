import java.util.LinkedList;
import java.util.List;

public class LinkedListOne {
    public static void main(String[] args) {
        
        // Creating LinkedList
        LinkedList<Integer> ll = new LinkedList<>();
        
        // Adding elements
        ll.add(10);
        ll.add(20);
        ll.add(30);
        ll.add(40);
        ll.add(50);
        
        System.out.println("LinkedList: " + ll);
        
        // Adding at specific position
        ll.add(2, 25);
        System.out.println("After add(2, 25): " + ll);
        
        // Adding at first and last
        ll.addFirst(5);
        ll.addLast(60);
        System.out.println("After addFirst(5) and addLast(60): " + ll);
        
        // Getting elements
        System.out.println("\nGetting elements:");
        System.out.println("get(0): " + ll.get(0));
        System.out.println("getFirst(): " + ll.getFirst());
        System.out.println("getLast(): " + ll.getLast());
        
        // Removing elements
        System.out.println("\nRemoving elements:");
        ll.remove(2);
        System.out.println("After remove(2): " + ll);
        
        ll.removeFirst();
        System.out.println("After removeFirst(): " + ll);
        
        ll.removeLast();
        System.out.println("After removeLast(): " + ll);
        
        // Checking contains
        System.out.println("\nContains 30? " + ll.contains(30));
        System.out.println("Contains 100? " + ll.contains(100));
        
        // Size
        System.out.println("\nSize: " + ll.size());
        
        // Peek operations (doesn't remove)
        System.out.println("\nPeek operations:");
        System.out.println("peek(): " + ll.peek());
        System.out.println("peekFirst(): " + ll.peekFirst());
        System.out.println("peekLast(): " + ll.peekLast());
        
        // Set operation
        ll.set(1, 100);
        System.out.println("\nAfter set(1, 100): " + ll);
        
        // Index operations
        System.out.println("\nindexOf(30): " + ll.indexOf(30));
        System.out.println("lastIndexOf(30): " + ll.lastIndexOf(30));
        
        // Clear
        System.out.println("\nBefore clear: " + ll);
        System.out.println("isEmpty(): " + ll.isEmpty());
        
        ll.clear();
        System.out.println("After clear: " + ll);
        System.out.println("isEmpty(): " + ll.isEmpty());
    }
}
