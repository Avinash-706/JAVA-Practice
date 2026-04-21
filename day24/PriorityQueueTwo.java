import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Collections;
import java.util.Comparator;

public class PriorityQueueTwo {
    public static void main(String[] args) {
        
        // Min-Heap (Default - Natural Ordering)
        System.out.println("--- Min-Heap (Default) ---");
        Queue<Integer> minHeap = new PriorityQueue<>();
        
        minHeap.offer(50);
        minHeap.offer(20);
        minHeap.offer(70);
        minHeap.offer(10);
        minHeap.offer(30);
        
        System.out.println("Min-Heap: " + minHeap);
        System.out.println("Smallest element: " + minHeap.peek());
        
        System.out.print("Elements in priority order: ");
        while(!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println("\n");
        
        // Max-Heap (Reverse Ordering using Collections.reverseOrder())
        System.out.println("--- Max-Heap (Reverse Order) ---");
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        maxHeap.offer(50);
        maxHeap.offer(20);
        maxHeap.offer(70);
        maxHeap.offer(10);
        maxHeap.offer(30);
        
        System.out.println("Max-Heap: " + maxHeap);
        System.out.println("Largest element: " + maxHeap.peek());
        
        System.out.print("Elements in priority order: ");
        while(!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println("\n");
        
        // Custom Comparator - Sorting Strings by length
        System.out.println("--- Custom Comparator (String by length) ---");
        Queue<String> pq = new PriorityQueue<>((a, b) -> a.length() - b.length());
        
        pq.offer("Java");
        pq.offer("Python");
        pq.offer("C");
        pq.offer("JavaScript");
        pq.offer("Go");
        
        System.out.println("PriorityQueue: " + pq);
        
        System.out.print("Strings by length: ");
        while(!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println("\n");
        
        // PriorityQueue with duplicates
        System.out.println("--- PriorityQueue with Duplicates ---");
        Queue<Integer> pqDup = new PriorityQueue<>();
        
        pqDup.offer(10);
        pqDup.offer(20);
        pqDup.offer(10);
        pqDup.offer(30);
        pqDup.offer(20);
        pqDup.offer(10);
        
        System.out.println("With duplicates: " + pqDup);
        
        System.out.print("Processing: ");
        while(!pqDup.isEmpty()) {
            System.out.print(pqDup.poll() + " ");
        }
        System.out.println();
    }
}
