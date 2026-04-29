// =====================================================================
//          DEADLOCK - DETECTION, PREVENTION, AVOIDANCE
// =====================================================================
// Deadlock: Two or more threads waiting for each other's locks forever

public class DeadlockComplete {
    
    public static void main(String[] args) throws InterruptedException {
        
        // DEMONSTRATION 1: Creating Deadlock
        System.out.println("=== DEADLOCK DEMONSTRATION ===\n");
        demonstrateDeadlock();
        
        Thread.sleep(2000);
        System.out.println("\n=== DEADLOCK DETECTED ===");
        System.out.println("Both threads are stuck waiting!\n");
        
        // DEMONSTRATION 2: Preventing Deadlock
        System.out.println("=== DEADLOCK PREVENTION ===\n");
        preventDeadlock();
        
        // DEMONSTRATION 3: Deadlock with tryLock
        System.out.println("\n=== DEADLOCK AVOIDANCE WITH TRYLOCK ===\n");
        avoidDeadlockWithTryLock();
    }
    
    // PROBLEM: Deadlock occurs
    static void demonstrateDeadlock() {
        Object lock1 = new Object();
        Object lock2 = new Object();
        
        Thread t1 = new Thread(() -> {
            synchronized(lock1) {
                System.out.println("Thread 1: Holding lock1...");
                
                try { Thread.sleep(100); } catch(InterruptedException e) {}
                
                System.out.println("Thread 1: Waiting for lock2...");
                synchronized(lock2) {
                    System.out.println("Thread 1: Acquired lock2");
                }
            }
        }, "Thread-1");
        
        Thread t2 = new Thread(() -> {
            synchronized(lock2) {
                System.out.println("Thread 2: Holding lock2...");
                
                try { Thread.sleep(100); } catch(InterruptedException e) {}
                
                System.out.println("Thread 2: Waiting for lock1...");
                synchronized(lock1) {
                    System.out.println("Thread 2: Acquired lock1");
                }
            }
        }, "Thread-2");
        
        t1.start();
        t2.start();
    }
    
    // SOLUTION 1: Lock ordering - Always acquire locks in same order
    static void preventDeadlock() throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();
        
        Thread t1 = new Thread(() -> {
            synchronized(lock1) {
                System.out.println("Thread 1: Holding lock1...");
                
                try { Thread.sleep(100); } catch(InterruptedException e) {}
                
                synchronized(lock2) {
                    System.out.println("Thread 1: Acquired lock2");
                    System.out.println("Thread 1: Completed successfully");
                }
            }
        }, "Thread-1");
        
        Thread t2 = new Thread(() -> {
            // Same order: lock1 then lock2
            synchronized(lock1) {
                System.out.println("Thread 2: Holding lock1...");
                
                try { Thread.sleep(100); } catch(InterruptedException e) {}
                
                synchronized(lock2) {
                    System.out.println("Thread 2: Acquired lock2");
                    System.out.println("Thread 2: Completed successfully");
                }
            }
        }, "Thread-2");
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("No deadlock! Both threads completed.");
    }
    
    
    // SOLUTION 2: Using tryLock with timeout
    static void avoidDeadlockWithTryLock() throws InterruptedException {
        java.util.concurrent.locks.Lock lock1 = new java.util.concurrent.locks.ReentrantLock();
        java.util.concurrent.locks.Lock lock2 = new java.util.concurrent.locks.ReentrantLock();
        
        Thread t1 = new Thread(() -> {
            try {
                while(true) {
                    boolean gotLock1 = lock1.tryLock(50, java.util.concurrent.TimeUnit.MILLISECONDS);
                    if(gotLock1) {
                        try {
                            System.out.println("Thread 1: Got lock1");
                            
                            boolean gotLock2 = lock2.tryLock(50, java.util.concurrent.TimeUnit.MILLISECONDS);
                            if(gotLock2) {
                                try {
                                    System.out.println("Thread 1: Got lock2");
                                    System.out.println("Thread 1: Completed work");
                                    break;
                                } finally {
                                    lock2.unlock();
                                }
                            } else {
                                System.out.println("Thread 1: Couldn't get lock2, releasing lock1");
                            }
                        } finally {
                            lock1.unlock();
                        }
                    }
                    Thread.sleep(50);
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-1");
        
        Thread t2 = new Thread(() -> {
            try {
                while(true) {
                    boolean gotLock2 = lock2.tryLock(50, java.util.concurrent.TimeUnit.MILLISECONDS);
                    if(gotLock2) {
                        try {
                            System.out.println("Thread 2: Got lock2");
                            
                            boolean gotLock1 = lock1.tryLock(50, java.util.concurrent.TimeUnit.MILLISECONDS);
                            if(gotLock1) {
                                try {
                                    System.out.println("Thread 2: Got lock1");
                                    System.out.println("Thread 2: Completed work");
                                    break;
                                } finally {
                                    lock1.unlock();
                                }
                            } else {
                                System.out.println("Thread 2: Couldn't get lock1, releasing lock2");
                            }
                        } finally {
                            lock2.unlock();
                        }
                    }
                    Thread.sleep(50);
                }
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-2");
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("\n=== DEADLOCK PREVENTION STRATEGIES ===");
        System.out.println("1. Lock Ordering: Always acquire locks in same order");
        System.out.println("2. Lock Timeout: Use tryLock() with timeout");
        System.out.println("3. Deadlock Detection: Monitor thread states");
        System.out.println("4. Avoid Nested Locks: Minimize lock nesting");
        System.out.println("5. Use Higher-Level Concurrency: Use concurrent collections");
        
        System.out.println("\n=== DEADLOCK CONDITIONS (All 4 must be present) ===");
        System.out.println("1. Mutual Exclusion: Only one thread can hold lock");
        System.out.println("2. Hold and Wait: Thread holds lock while waiting for another");
        System.out.println("3. No Preemption: Lock cannot be forcibly taken");
        System.out.println("4. Circular Wait: Circular chain of threads waiting");
    }
}
