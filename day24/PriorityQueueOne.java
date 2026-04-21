import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueOne {
    public static void main(String[] args) {
        
        // Creating PriorityQueue (Min-Heap by default)
        Queue<Integer> pq = new PriorityQueue<>();
        
        // Adding elements
        pq.add(50);
        pq.add(20);
        pq.add(70);
        pq.add(10);
        pq.add(30);
        pq.add(60);
        pq.add(40);
        
        System.out.println("PriorityQueue (Min-Heap): " + pq);
        
        // Peek - view smallest element without removing
        System.out.println("\npeek(): " + pq.peek());
        System.out.println("After peek: " + pq);
        
        // Poll - remove and return smallest element
        System.out.println("\npoll(): " + pq.poll());
        System.out.println("After poll: " + pq);
        
        System.out.println("poll(): " + pq.poll());
        System.out.println("After poll: " + pq);
        
        // Offer - add element
        pq.offer(15);
        System.out.println("\nAfter offer(15): " + pq);
        
        // Size
        System.out.println("\nSize: " + pq.size());
        
        // Contains
        System.out.println("Contains 30? " + pq.contains(30));
        System.out.println("Contains 100? " + pq.contains(100));
        
        // Remove specific element
        pq.remove(30);
        System.out.println("\nAfter remove(30): " + pq);
        
        // Processing all elements in priority order
        System.out.println("\nProcessing all elements in priority order:");
        while(!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
        
        System.out.println("\nAfter processing all: " + pq);
        System.out.println("isEmpty(): " + pq.isEmpty());
    }
}
