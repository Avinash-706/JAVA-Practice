import java.util.PriorityQueue;
import java.util.Collections;

public class HeapMaxHeap {
    public static void main(String[] args) {
        
        System.out.println("=== Max-Heap Implementation using PriorityQueue ===\n");
        
        // Max-Heap: Parent node value is greater than or equal to its child nodes
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // Inserting elements
        System.out.println("Inserting elements: 50, 30, 70, 20, 40, 60, 80");
        maxHeap.offer(50);
        maxHeap.offer(30);
        maxHeap.offer(70);
        maxHeap.offer(20);
        maxHeap.offer(40);
        maxHeap.offer(60);
        maxHeap.offer(80);
        
        System.out.println("Max-Heap: " + maxHeap);
        
        // Peek - Get maximum element without removing
        System.out.println("\nMaximum element (peek): " + maxHeap.peek());
        
        // Poll - Remove and return maximum element
        System.out.println("\nRemoving elements in descending order:");
        System.out.println("poll(): " + maxHeap.poll());
        System.out.println("After poll: " + maxHeap);
        
        System.out.println("poll(): " + maxHeap.poll());
        System.out.println("After poll: " + maxHeap);
        
        System.out.println("poll(): " + maxHeap.poll());
        System.out.println("After poll: " + maxHeap);
        
        // Adding more elements
        System.out.println("\nAdding elements: 90, 75");
        maxHeap.offer(90);
        maxHeap.offer(75);
        System.out.println("After adding: " + maxHeap);
        
        System.out.println("\nMaximum element now: " + maxHeap.peek());
        
        // Processing all elements
        System.out.println("\nProcessing all elements in descending order:");
        while(!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println();
        
        // Demonstrating Max-Heap property
        System.out.println("\n--- Demonstrating Max-Heap Property ---");
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        
        int[] elements = {15, 10, 20, 8, 12, 25, 30};
        System.out.println("Inserting: ");
        for(int num : elements) {
            heap.offer(num);
            System.out.println("  Added " + num + " -> Heap: " + heap + " -> Max: " + heap.peek());
        }
        
        System.out.println("\nFinal Max-Heap: " + heap);
        System.out.println("Root (Maximum): " + heap.peek());
        
        // Comparing Min-Heap vs Max-Heap
        System.out.println("\n--- Min-Heap vs Max-Heap Comparison ---");
        
        PriorityQueue<Integer> minH = new PriorityQueue<>();
        PriorityQueue<Integer> maxH = new PriorityQueue<>(Collections.reverseOrder());
        
        int[] data = {45, 25, 65, 15, 35, 55, 75};
        
        for(int num : data) {
            minH.offer(num);
            maxH.offer(num);
        }
        
        System.out.println("Same data in Min-Heap: " + minH);
        System.out.println("Same data in Max-Heap: " + maxH);
        
        System.out.println("\nMin-Heap root: " + minH.peek());
        System.out.println("Max-Heap root: " + maxH.peek());
    }
}
