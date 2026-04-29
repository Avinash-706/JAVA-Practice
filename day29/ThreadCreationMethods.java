// =====================================================================
//          THREAD CREATION - TWO METHODS
// =====================================================================
// Method 1: Extending Thread class
// Method 2: Implementing Runnable interface (Preferred)

// METHOD 1: Extending Thread Class
class MyThread extends Thread {
    private String threadName;
    
    MyThread(String name) {
        this.threadName = name;
    }
    
    @Override
    public void run() {
        for(int i = 1; i <= 5; i++) {
            System.out.println(threadName + " - Count: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted");
            }
        }
        System.out.println(threadName + " finished\n");
    }
}

// METHOD 2: Implementing Runnable Interface (PREFERRED)
class MyRunnable implements Runnable {
    private String taskName;
    
    MyRunnable(String name) {
        this.taskName = name;
    }
    
    @Override
    public void run() {
        for(int i = 1; i <= 5; i++) {
            System.out.println(taskName + " - Count: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(taskName + " interrupted");
            }
        }
        System.out.println(taskName + " finished\n");
    }
}

public class ThreadCreationMethods {
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("=== METHOD 1: Extending Thread Class ===\n");
        
        MyThread t1 = new MyThread("Thread-A");
        MyThread t2 = new MyThread("Thread-B");
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        System.out.println("\n=== METHOD 2: Implementing Runnable Interface ===\n");
        
        Thread t3 = new Thread(new MyRunnable("Task-X"));
        Thread t4 = new Thread(new MyRunnable("Task-Y"));
        
        t3.start();
        t4.start();
        
        t3.join();
        t4.join();
        
        // Lambda Expression (Java 8+)
        System.out.println("\n=== Using Lambda Expression ===\n");
        
        Thread t5 = new Thread(() -> {
            for(int i = 1; i <= 3; i++) {
                System.out.println("Lambda Thread - " + i);
                try { Thread.sleep(300); } catch(InterruptedException e) {}
            }
        });
        
        t5.start();
        t5.join();
        
        // Anonymous Inner Class
        System.out.println("\n=== Using Anonymous Inner Class ===\n");
        
        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 3; i++) {
                    System.out.println("Anonymous Thread - " + i);
                    try { Thread.sleep(300); } catch(InterruptedException e) {}
                }
            }
        });
        
        t6.start();
        t6.join();
        
        System.out.println("\n=== WHY RUNNABLE IS PREFERRED ===");
        System.out.println("1. Java doesn't support multiple inheritance");
        System.out.println("2. Runnable allows extending other classes");
        System.out.println("3. Better object-oriented design (composition over inheritance)");
        System.out.println("4. Thread class can be reused with different Runnable tasks");
    }
}
