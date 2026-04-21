import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQ {
    public static void main(String[] args) {
        
        // Creating PriorityQueue (Min-Heap by default)
        Queue<Integer> pq = new PriorityQueue<>();
        
        // Adding elements using offer()
        pq.offer(10);
        pq.offer(15);
        pq.offer(26);
        pq.offer(7);
        pq.offer(13);
        pq.offer(10);  // Duplicate allowed
        pq.offer(14);
        
        // pq.offer(null); // NullPointerException - null not allowed

        System.out.println("PriorityQueue: " + pq);

        // peek() - view smallest element without removing
        System.out.println("\npeek(): " + pq.peek());
        System.out.println("peek() again: " + pq.peek());

        // poll() - remove and return smallest element
        System.out.println("\npoll(): " + pq.poll());
        System.out.println("After poll: " + pq);
        
        System.out.println("\npoll(): " + pq.poll());
        System.out.println("After poll: " + pq);
        
        pq.poll();
        System.out.println("\nAfter poll: " + pq);
        
        pq.poll();
        System.out.println("After poll: " + pq);
        
        // Size
        System.out.println("\nSize: " + pq.size());
        
        // Processing remaining elements
        System.out.println("\nProcessing remaining elements:");
        while(!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
        
        System.out.println("\nAfter processing all: " + pq);
        System.out.println("isEmpty: " + pq.isEmpty());
    }
}
