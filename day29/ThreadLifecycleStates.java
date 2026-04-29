// =====================================================================
//                    THREAD LIFECYCLE & STATES
// =====================================================================
// Thread States: NEW -> RUNNABLE -> RUNNING -> BLOCKED/WAITING -> TERMINATED
// JVM controls thread lifecycle through Thread Scheduler

public class ThreadLifecycleStates {
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("=== THREAD LIFECYCLE DEMONSTRATION ===\n");
        
        // 1. NEW STATE
        Thread t1 = new Thread(() -> {
            System.out.println("Thread is running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        System.out.println("1. NEW State: " + t1.getState());
        System.out.println("   Thread created but not started yet\n");
        
        // 2. RUNNABLE STATE
        t1.start();
        System.out.println("2. RUNNABLE State: " + t1.getState());
        System.out.println("   Thread started, waiting for CPU\n");
        
        Thread.sleep(100);
        
        // 3. TIMED_WAITING STATE (during sleep)
        System.out.println("3. TIMED_WAITING State: " + t1.getState());
        System.out.println("   Thread is sleeping\n");
        
        t1.join();
        
        // 4. TERMINATED STATE
        System.out.println("4. TERMINATED State: " + t1.getState());
        System.out.println("   Thread execution completed\n");
        
        
        // BLOCKED STATE DEMONSTRATION
        demonstrateBlockedState();
        
        // WAITING STATE DEMONSTRATION
        demonstrateWaitingState();
    }
    
    
    // BLOCKED STATE - Thread waiting for monitor lock
    static void demonstrateBlockedState() throws InterruptedException {
        System.out.println("\n=== BLOCKED STATE DEMONSTRATION ===");
        
        Object lock = new Object();
        
        Thread t1 = new Thread(() -> {
            synchronized(lock) {
                System.out.println("Thread 1 acquired lock");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {}
            }
        });
        
        Thread t2 = new Thread(() -> {
            synchronized(lock) {
                System.out.println("Thread 2 acquired lock");
            }
        });
        
        t1.start();
        Thread.sleep(100);
        t2.start();
        Thread.sleep(100);
        
        System.out.println("Thread 1 State: " + t1.getState());
        System.out.println("Thread 2 State: " + t2.getState() + " (waiting for lock)\n");
        
        t1.join();
        t2.join();
    }
    
    // WAITING STATE - Thread waiting indefinitely
    static void demonstrateWaitingState() throws InterruptedException {
        System.out.println("\n=== WAITING STATE DEMONSTRATION ===");
        
        Object lock = new Object();
        
        Thread t1 = new Thread(() -> {
            synchronized(lock) {
                try {
                    System.out.println("Thread entering WAITING state");
                    lock.wait();
                    System.out.println("Thread resumed from WAITING");
                } catch (InterruptedException e) {}
            }
        });
        
        t1.start();
        Thread.sleep(500);
        
        System.out.println("Thread State: " + t1.getState() + " (waiting for notify)\n");
        
        synchronized(lock) {
            lock.notify();
        }
        
        t1.join();
    }
}
