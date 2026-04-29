// =====================================================================
//          SYNCHRONIZATION IN JAVA - COMPLETE GUIDE
// =====================================================================
// Prevents thread interference and consistency problems
// Uses locks/monitors to control access to shared resources

// PROBLEM: Without Synchronization
class Counter {
    private int count = 0;
    
    public void increment() {
        count++; // Not atomic: read, increment, write
    }
    
    public int getCount() {
        return count;
    }
}

// SOLUTION 1: Synchronized Method
class SynchronizedCounter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}

// SOLUTION 2: Synchronized Block
class SynchronizedBlockCounter {
    private int count = 0;
    private Object lock = new Object();
    
    public void increment() {
        synchronized(lock) {
            count++;
        }
    }
    
    public int getCount() {
        synchronized(lock) {
            return count;
        }
    }
}

// SOLUTION 3: Static Synchronization (Class Level Lock)
class StaticSynchronized {
    private static int count = 0;
    
    public static synchronized void increment() {
        count++;
    }
    
    public static synchronized int getCount() {
        return count;
    }
}

public class SynchronizationComplete {
    
    public static void main(String[] args) throws InterruptedException {
        
        // DEMONSTRATION 1: Problem without synchronization
        System.out.println("=== WITHOUT SYNCHRONIZATION ===");
        Counter counter1 = new Counter();
        
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                counter1.increment();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                counter1.increment();
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("Expected: 2000");
        System.out.println("Actual: " + counter1.getCount());
        System.out.println("Problem: Race condition!\n");
        
        // DEMONSTRATION 2: With synchronized method
        System.out.println("=== WITH SYNCHRONIZED METHOD ===");
        SynchronizedCounter counter2 = new SynchronizedCounter();
        
        Thread t3 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                counter2.increment();
            }
        });
        
        Thread t4 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                counter2.increment();
            }
        });
        
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        
        System.out.println("Expected: 2000");
        System.out.println("Actual: " + counter2.getCount());
        System.out.println("Success: Thread-safe!\n");
        
        // DEMONSTRATION 3: With synchronized block
        System.out.println("=== WITH SYNCHRONIZED BLOCK ===");
        SynchronizedBlockCounter counter3 = new SynchronizedBlockCounter();
        
        Thread t5 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                counter3.increment();
            }
        });
        
        Thread t6 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                counter3.increment();
            }
        });
        
        t5.start();
        t6.start();
        t5.join();
        t6.join();
        
        System.out.println("Expected: 2000");
        System.out.println("Actual: " + counter3.getCount());
        System.out.println("Success: Thread-safe with block!\n");
        
        
        // DEMONSTRATION 4: Static synchronization
        System.out.println("=== STATIC SYNCHRONIZATION ===");
        
        Thread t7 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                StaticSynchronized.increment();
            }
        });
        
        Thread t8 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                StaticSynchronized.increment();
            }
        });
        
        t7.start();
        t8.start();
        t7.join();
        t8.join();
        
        System.out.println("Expected: 2000");
        System.out.println("Actual: " + StaticSynchronized.getCount());
        System.out.println("Success: Class-level lock!\n");
        
        // DEMONSTRATION 5: Real-world example - Bank Account
        demonstrateBankAccount();
        
        // DEMONSTRATION 6: Synchronized vs Non-synchronized comparison
        demonstratePerformanceComparison();
    }
    
    static void demonstrateBankAccount() throws InterruptedException {
        System.out.println("=== REAL-WORLD: BANK ACCOUNT ===");
        
        class BankAccount {
            private int balance = 1000;
            
            public synchronized void withdraw(int amount) {
                if(balance >= amount) {
                    System.out.println(Thread.currentThread().getName() + 
                                     " withdrawing " + amount);
                    balance -= amount;
                    System.out.println(Thread.currentThread().getName() + 
                                     " completed. Balance: " + balance);
                } else {
                    System.out.println(Thread.currentThread().getName() + 
                                     " insufficient balance");
                }
            }
            
            public synchronized int getBalance() {
                return balance;
            }
        }
        
        BankAccount account = new BankAccount();
        
        Thread husband = new Thread(() -> account.withdraw(600), "Husband");
        Thread wife = new Thread(() -> account.withdraw(600), "Wife");
        
        husband.start();
        wife.start();
        husband.join();
        wife.join();
        
        System.out.println("Final Balance: " + account.getBalance() + "\n");
    }
    
    static void demonstratePerformanceComparison() throws InterruptedException {
        System.out.println("=== SYNCHRONIZED METHOD vs BLOCK ===");
        
        class MethodSync {
            private int count = 0;
            
            public synchronized void increment() {
                count++;
                // Entire method locked
            }
        }
        
        class BlockSync {
            private int count = 0;
            private Object lock = new Object();
            
            public void increment() {
                // Only critical section locked
                synchronized(lock) {
                    count++;
                }
            }
        }
        
        System.out.println("Synchronized Method: Locks entire method");
        System.out.println("Synchronized Block: Locks only critical section");
        System.out.println("Block is more efficient for large methods\n");
        
        System.out.println("=== KEY CONCEPTS ===");
        System.out.println("1. Lock/Monitor: Every object has one lock");
        System.out.println("2. Synchronized method: Uses 'this' object lock");
        System.out.println("3. Synchronized block: Can use any object lock");
        System.out.println("4. Static synchronized: Uses Class object lock");
        System.out.println("5. Only one thread can hold lock at a time");
    }
}
