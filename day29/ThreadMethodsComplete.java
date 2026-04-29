// =====================================================================
//          THREAD CLASS METHODS - COMPREHENSIVE GUIDE
// =====================================================================
// Covers all 22 commonly used Thread methods with examples

public class ThreadMethodsComplete {
    
    public static void main(String[] args) throws InterruptedException {
        
        // 1. start() - Starts thread execution
        System.out.println("=== 1. start() Method ===");
        Thread t1 = new Thread(() -> System.out.println("Thread started"));
        t1.start();
        Thread.sleep(100);
        
        // 2. run() - Contains thread logic (called by start())
        System.out.println("\n=== 2. run() Method ===");
        Thread t2 = new Thread(() -> System.out.println("run() method executed"));
        t2.run(); // Direct call - runs in main thread
        t2.start(); // Proper way - runs in new thread
        Thread.sleep(100);
        
        // 3. sleep() - Pauses thread for specified milliseconds
        System.out.println("\n=== 3. sleep() Method ===");
        System.out.println("Sleeping for 2 seconds...");
        Thread.sleep(2000);
        System.out.println("Woke up after 2 seconds");
        
        // 4. join() - Wait for thread to die
        System.out.println("\n=== 4. join() Method ===");
        Thread t3 = new Thread(() -> {
            for(int i = 1; i <= 3; i++) {
                System.out.println("Thread running: " + i);
                try { Thread.sleep(500); } catch(InterruptedException e) {}
            }
        });
        t3.start();
        System.out.println("Main waiting for thread to finish...");
        t3.join();
        System.out.println("Thread finished, main continues");
        
        // 5. join(milliseconds) - Wait for specified time
        System.out.println("\n=== 5. join(milliseconds) Method ===");
        Thread t4 = new Thread(() -> {
            try { Thread.sleep(3000); } catch(InterruptedException e) {}
        });
        t4.start();
        System.out.println("Waiting for 1 second only...");
        t4.join(1000);
        System.out.println("Stopped waiting after 1 second");
        
        // 6 & 7. getPriority() and setPriority()
        System.out.println("\n=== 6 & 7. Priority Methods ===");
        Thread t5 = new Thread(() -> {});
        System.out.println("Default Priority: " + t5.getPriority());
        t5.setPriority(Thread.MAX_PRIORITY);
        System.out.println("After setting MAX: " + t5.getPriority());
        System.out.println("MIN_PRIORITY: " + Thread.MIN_PRIORITY);
        System.out.println("NORM_PRIORITY: " + Thread.NORM_PRIORITY);
        System.out.println("MAX_PRIORITY: " + Thread.MAX_PRIORITY);
        
        // 8 & 9. getName() and setName()
        System.out.println("\n=== 8 & 9. Name Methods ===");
        Thread t6 = new Thread(() -> {});
        System.out.println("Default Name: " + t6.getName());
        t6.setName("MyCustomThread");
        System.out.println("After setName: " + t6.getName());
        
        // 10. currentThread() - Get reference of current thread
        System.out.println("\n=== 10. currentThread() Method ===");
        Thread current = Thread.currentThread();
        System.out.println("Current Thread: " + current.getName());
        System.out.println("Current Thread ID: " + current.getId());
        
        // 11. getId() - Get thread ID
        System.out.println("\n=== 11. getId() Method ===");
        Thread t7 = new Thread(() -> {});
        System.out.println("Thread ID: " + t7.getId());
        
        // 12. getState() - Get thread state
        System.out.println("\n=== 12. getState() Method ===");
        Thread t8 = new Thread(() -> {
            try { Thread.sleep(1000); } catch(InterruptedException e) {}
        });
        System.out.println("Before start: " + t8.getState());
        t8.start();
        System.out.println("After start: " + t8.getState());
        Thread.sleep(100);
        System.out.println("During sleep: " + t8.getState());
        t8.join();
        System.out.println("After completion: " + t8.getState());
        
        
        // 13. isAlive() - Check if thread is alive
        System.out.println("\n=== 13. isAlive() Method ===");
        Thread t9 = new Thread(() -> {
            try { Thread.sleep(500); } catch(InterruptedException e) {}
        });
        System.out.println("Before start: " + t9.isAlive());
        t9.start();
        System.out.println("After start: " + t9.isAlive());
        t9.join();
        System.out.println("After completion: " + t9.isAlive());
        
        // 14. yield() - Pause current thread, allow others to execute
        System.out.println("\n=== 14. yield() Method ===");
        Thread t10 = new Thread(() -> {
            for(int i = 1; i <= 5; i++) {
                System.out.println("Thread-A: " + i);
                Thread.yield(); // Give chance to other threads
            }
        });
        Thread t11 = new Thread(() -> {
            for(int i = 1; i <= 5; i++) {
                System.out.println("Thread-B: " + i);
            }
        });
        t10.start();
        t11.start();
        t10.join();
        t11.join();
        
        // 18 & 19. isDaemon() and setDaemon()
        System.out.println("\n=== 18 & 19. Daemon Methods ===");
        Thread t12 = new Thread(() -> {
            while(true) {
                System.out.println("Daemon running...");
                try { Thread.sleep(500); } catch(InterruptedException e) {}
            }
        });
        System.out.println("Is Daemon (before): " + t12.isDaemon());
        t12.setDaemon(true);
        System.out.println("Is Daemon (after): " + t12.isDaemon());
        t12.start();
        Thread.sleep(1500);
        System.out.println("Main ending (daemon will stop automatically)");
        
        // 20. interrupt() - Interrupt a thread
        System.out.println("\n=== 20. interrupt() Method ===");
        Thread t13 = new Thread(() -> {
            try {
                System.out.println("Thread sleeping...");
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                System.out.println("Thread was interrupted!");
            }
        });
        t13.start();
        Thread.sleep(1000);
        t13.interrupt();
        t13.join();
        
        // 21. isInterrupted() - Check if thread is interrupted
        System.out.println("\n=== 21. isInterrupted() Method ===");
        Thread t14 = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("Working...");
                try { Thread.sleep(500); } catch(InterruptedException e) {
                    System.out.println("Interrupted during sleep");
                    break;
                }
            }
        });
        t14.start();
        Thread.sleep(1500);
        t14.interrupt();
        t14.join();
        
        // 22. interrupted() - Static method to check current thread
        System.out.println("\n=== 22. interrupted() Method ===");
        System.out.println("Main thread interrupted? " + Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println("After interrupt: " + Thread.interrupted());
        System.out.println("Second call: " + Thread.interrupted()); // Clears flag
        
        System.out.println("\n=== DEPRECATED METHODS (Not Recommended) ===");
        System.out.println("15. suspend() - DEPRECATED (causes deadlock)");
        System.out.println("16. resume() - DEPRECATED (use with suspend)");
        System.out.println("17. stop() - DEPRECATED (unsafe, leaves objects inconsistent)");
    }
}
