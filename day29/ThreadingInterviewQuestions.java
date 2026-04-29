// =====================================================================
//          THREADING INTERVIEW QUESTIONS & ANSWERS
// =====================================================================
// Common interview questions with code examples

public class ThreadingInterviewQuestions {
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("=== THREADING INTERVIEW QUESTIONS ===\n");
        
        // Q1: Can we use synchronized and static together?
        question1_SynchronizedStatic();
        
        // Q2: What happens if we call start() twice?
        question2_StartTwice();
        
        // Q3: What happens if we call run() directly?
        question3_RunDirectly();
        
        // Q4: Difference between wait() and sleep()
        question4_WaitVsSleep();
        
        // Q5: Can we synchronize constructor?
        question5_SynchronizeConstructor();
        
        // Q6: What is thread starvation?
        question6_ThreadStarvation();
    }
    
    // Q1: Can we use synchronized and static together?
    static void question1_SynchronizedStatic() {
        System.out.println("Q1: Can we use synchronized and static together?");
        System.out.println("Answer: YES\n");
        
        class Example {
            // Instance method - locks object
            public synchronized void instanceMethod() {
                System.out.println("Instance synchronized method");
            }
            
            // Static method - locks Class object
            public static synchronized void staticMethod() {
                System.out.println("Static synchronized method");
            }
        }
        
        System.out.println("Instance synchronized: Locks 'this' object");
        System.out.println("Static synchronized: Locks 'Class' object");
        System.out.println("They use DIFFERENT locks, can run simultaneously\n");
        
        System.out.println("Backend Flow:");
        System.out.println("1. Thread acquires lock on object/class");
        System.out.println("2. Executes synchronized code");
        System.out.println("3. Releases lock when method exits");
        System.out.println("4. Other threads can now acquire lock\n");
        System.out.println("---\n");
    }
    
    // Q2: What happens if we call start() twice?
    static void question2_StartTwice() {
        System.out.println("Q2: What happens if we call start() twice?");
        System.out.println("Answer: IllegalThreadStateException\n");
        
        Thread t = new Thread(() -> System.out.println("Running"));
        t.start();
        
        try {
            t.start(); // This will throw exception
        } catch(IllegalThreadStateException e) {
            System.out.println("Exception caught: " + e.getClass().getSimpleName());
            System.out.println("Reason: Thread can only be started once");
        }
        
        System.out.println("\nThread States:");
        System.out.println("NEW -> RUNNABLE -> RUNNING -> TERMINATED");
        System.out.println("Once TERMINATED, cannot go back to NEW\n");
        System.out.println("---\n");
    }
    
    // Q3: What happens if we call run() directly?
    static void question3_RunDirectly() throws InterruptedException {
        System.out.println("Q3: What happens if we call run() directly?");
        System.out.println("Answer: Runs in SAME thread (no new thread created)\n");
        
        Thread t = new Thread(() -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        });
        
        System.out.println("Calling run() directly:");
        t.run(); // Runs in main thread
        
        System.out.println("\nCalling start():");
        t = new Thread(() -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        });
        t.start(); // Runs in new thread
        t.join();
        
        System.out.println("\nDifference:");
        System.out.println("run()   - Normal method call, same thread");
        System.out.println("start() - Creates new thread, calls run()\n");
        System.out.println("---\n");
    }
    
    // Q4: Difference between wait() and sleep()
    static void question4_WaitVsSleep() {
        System.out.println("Q4: Difference between wait() and sleep()?");
        System.out.println("Answer: See table below\n");
        
        System.out.println("Feature          | wait()                | sleep()");
        System.out.println("-----------------|----------------------|----------------------");
        System.out.println("Class            | Object               | Thread");
        System.out.println("Lock             | Releases lock        | Holds lock");
        System.out.println("Context          | Synchronized only    | Anywhere");
        System.out.println("Wake up          | notify()/notifyAll() | Time expires");
        System.out.println("Exception        | InterruptedException | InterruptedException");
        System.out.println("Purpose          | Inter-thread comm    | Pause execution");
        
        System.out.println("\nExample:");
        System.out.println("wait()  - Producer waiting for consumer to consume");
        System.out.println("sleep() - Delay between retry attempts\n");
        System.out.println("---\n");
    }
    
    
    // Q5: Can we synchronize constructor?
    static void question5_SynchronizeConstructor() {
        System.out.println("Q5: Can we synchronize constructor?");
        System.out.println("Answer: NO (Compilation error)\n");
        
        System.out.println("Reason:");
        System.out.println("1. Constructor creates object");
        System.out.println("2. Synchronization needs existing object");
        System.out.println("3. No object exists during construction");
        System.out.println("4. Only one thread can construct at a time anyway");
        
        System.out.println("\nAlternative:");
        System.out.println("Use synchronized block inside constructor if needed");
        System.out.println("Or use factory method with synchronization\n");
        System.out.println("---\n");
    }
    
    // Q6: What is thread starvation?
    static void question6_ThreadStarvation() {
        System.out.println("Q6: What is thread starvation?");
        System.out.println("Answer: Thread never gets CPU time\n");
        
        System.out.println("Causes:");
        System.out.println("1. Low priority threads");
        System.out.println("2. High priority threads always running");
        System.out.println("3. Unfair lock acquisition");
        System.out.println("4. Infinite loops in high priority threads");
        
        System.out.println("\nSolution:");
        System.out.println("1. Use fair locks: new ReentrantLock(true)");
        System.out.println("2. Avoid extreme priorities");
        System.out.println("3. Use thread pools");
        System.out.println("4. Monitor thread execution times\n");
        System.out.println("---\n");
        
        printAdditionalQuestions();
    }
    
    static void printAdditionalQuestions() {
        System.out.println("=== MORE INTERVIEW QUESTIONS ===\n");
        
        System.out.println("Q7: Difference between process and thread?");
        System.out.println("Process: Separate memory, heavyweight, expensive context switch");
        System.out.println("Thread: Shared memory, lightweight, cheap context switch\n");
        
        System.out.println("Q8: What is race condition?");
        System.out.println("Multiple threads accessing shared data simultaneously");
        System.out.println("Result depends on thread execution order");
        System.out.println("Solution: Synchronization\n");
        
        System.out.println("Q9: What is thread safety?");
        System.out.println("Code that works correctly with multiple threads");
        System.out.println("No race conditions, no data corruption");
        System.out.println("Achieved through: synchronized, locks, atomic, immutable\n");
        
        System.out.println("Q10: Difference between notify() and notifyAll()?");
        System.out.println("notify()    - Wakes ONE random waiting thread");
        System.out.println("notifyAll() - Wakes ALL waiting threads");
        System.out.println("Use notifyAll() when multiple threads wait on same condition\n");
        
        System.out.println("Q11: What is daemon thread?");
        System.out.println("Background thread that supports user threads");
        System.out.println("JVM exits when only daemon threads remain");
        System.out.println("Example: Garbage Collector\n");
        
        System.out.println("Q12: Can we start a thread twice?");
        System.out.println("NO - IllegalThreadStateException");
        System.out.println("Thread lifecycle: NEW -> RUNNABLE -> TERMINATED");
        System.out.println("Cannot restart terminated thread\n");
        
        System.out.println("Q13: What is thread priority?");
        System.out.println("Hint to thread scheduler (1-10)");
        System.out.println("MIN_PRIORITY=1, NORM_PRIORITY=5, MAX_PRIORITY=10");
        System.out.println("Not guaranteed, depends on OS\n");
        
        System.out.println("Q14: What is volatile keyword?");
        System.out.println("Ensures visibility of variable across threads");
        System.out.println("Prevents caching in thread-local memory");
        System.out.println("Use for flags, not for compound operations\n");
        
        System.out.println("Q15: Difference between synchronized and ReentrantLock?");
        System.out.println("synchronized: Simple, automatic, no tryLock");
        System.out.println("ReentrantLock: Flexible, tryLock, timed, interruptible\n");
    }
}
