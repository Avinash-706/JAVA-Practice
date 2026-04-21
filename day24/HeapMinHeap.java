import java.util.PriorityQueue;

public class HeapMinHeap {
    public static void main(String[] args) {
        
        System.out.println("=== Min-Heap Implementation using PriorityQueue ===\n");
        
        // Min-Heap: Parent node value is smaller than or equal to its child nodes
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Inserting elements
        System.out.println("Inserting elements: 50, 30, 70, 20, 40, 60, 80");
        minHeap.offer(50);
        minHeap.offer(30);
        minHeap.offer(70);
        minHeap.offer(20);
        minHeap.offer(40);
        minHeap.offer(60);
        minHeap.offer(80);
        
        System.out.println("Min-Heap: " + minHeap);
        
        // Peek - Get minimum element without removing
        System.out.println("\nMinimum element (peek): " + minHeap.peek());
        
        // Poll - Remove and return minimum element
        System.out.println("\nRemoving elements in ascending order:");
        System.out.println("poll(): " + minHeap.poll());
        System.out.println("After poll: " + minHeap);
        
        System.out.println("poll(): " + minHeap.poll());
        System.out.println("After poll: " + minHeap);
        
        System.out.println("poll(): " + minHeap.poll());
        System.out.println("After poll: " + minHeap);
        
        // Adding more elements
        System.out.println("\nAdding elements: 10, 25");
        minHeap.offer(10);
        minHeap.offer(25);
        System.out.println("After adding: " + minHeap);
        
        System.out.println("\nMinimum element now: " + minHeap.peek());
        
        // Processing all elements
        System.out.println("\nProcessing all elements in ascending order:");
        while(!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();
        
        // Demonstrating Min-Heap property
        System.out.println("\n--- Demonstrating Min-Heap Property ---");
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        
        int[] elements = {15, 10, 20, 8, 12, 25, 30};
        System.out.println("Inserting: ");
        for(int num : elements) {
            heap.offer(num);
            System.out.println("  Added " + num + " -> Heap: " + heap + " -> Min: " + heap.peek());
        }
        
        System.out.println("\nFinal Min-Heap: " + heap);
        System.out.println("Root (Minimum): " + heap.peek());
    }
}
