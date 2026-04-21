import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

public class LinkedListTwo {
    public static void main(String[] args) {
        
        LinkedList<String> ll = new LinkedList<>();
        
        // Adding elements
        ll.add("Java");
        ll.add("Python");
        ll.add("JavaScript");
        ll.add("C++");
        ll.add("Ruby");
        
        System.out.println("Original LinkedList: " + ll);
        
        // Traversal using for-each
        System.out.println("\nTraversal using for-each:");
        for(String lang : ll) {
            System.out.print(lang + " ");
        }
        System.out.println();
        
        // Traversal using Iterator
        System.out.println("\nTraversal using Iterator:");
        Iterator<String> itr = ll.iterator();
        while(itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        
        // Traversal using ListIterator (Forward)
        System.out.println("\nForward traversal using ListIterator:");
        ListIterator<String> lit = ll.listIterator();
        while(lit.hasNext()) {
            System.out.print(lit.next() + " ");
        }
        System.out.println();
        
        // Traversal using ListIterator (Backward)
        System.out.println("\nBackward traversal using ListIterator:");
        while(lit.hasPrevious()) {
            System.out.print(lit.previous() + " ");
        }
        System.out.println();
        
        // Using LinkedList as Queue (FIFO)
        System.out.println("\n--- LinkedList as Queue ---");
        LinkedList<Integer> queue = new LinkedList<>();
        
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        System.out.println("Queue: " + queue);
        
        System.out.println("poll(): " + queue.poll());
        System.out.println("After poll: " + queue);
        
        System.out.println("peek(): " + queue.peek());
        System.out.println("After peek: " + queue);
        
        // Using LinkedList as Stack (LIFO)
        System.out.println("\n--- LinkedList as Stack ---");
        LinkedList<Integer> stack = new LinkedList<>();
        
        stack.push(100);
        stack.push(200);
        stack.push(300);
        System.out.println("Stack: " + stack);
        
        System.out.println("pop(): " + stack.pop());
        System.out.println("After pop: " + stack);
        
        System.out.println("peek(): " + stack.peek());
        System.out.println("After peek: " + stack);
        
        // Using LinkedList as Deque (Double-ended queue)
        System.out.println("\n--- LinkedList as Deque ---");
        LinkedList<String> deque = new LinkedList<>();
        
        deque.offerFirst("First");
        deque.offerLast("Last");
        deque.offerFirst("NewFirst");
        deque.offerLast("NewLast");
        
        System.out.println("Deque: " + deque);
        
        System.out.println("pollFirst(): " + deque.pollFirst());
        System.out.println("pollLast(): " + deque.pollLast());
        System.out.println("After polls: " + deque);
    }
}
