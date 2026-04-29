// =====================================================================
//          ADVANCED THREADING CONCEPTS
// =====================================================================
// volatile, ThreadLocal, Atomic variables, ReentrantLock

import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

// 1. VOLATILE KEYWORD
class VolatileExample {
    private volatile boolean flag = false;
    
    public void writer() {
        System.out.println("Writer: Setting flag to true");
        flag = true; // Visible to all threads immediately
    }
    
    public void reader() {
        while(!flag) {
            // Busy wait
        }
        System.out.println("Reader: Flag is now true");
    }
}

// 2. THREADLOCAL - Thread-specific variables
class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
    
    public static void increment() {
        threadLocal.set(threadLocal.get() + 1);
    }
    
    public static int get() {
        return threadLocal.get();
    }
}

// 3. ATOMIC VARIABLES - Lock-free thread-safe operations
class AtomicExample {
    private AtomicInteger counter = new AtomicInteger(0);
    
    public void increment() {
        counter.incrementAndGet(); // Atomic operation
    }
    
    public int get() {
        return counter.get();
    }
}

// 4. REENTRANT LOCK - More flexible than synchronized
class ReentrantLockExample {
    private ReentrantLock lock = new ReentrantLock();
    private int count = 0;
    
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock(); // Always unlock in finally
        }
    }
    
    public boolean tryIncrement() {
        if(lock.tryLock()) {
            try {
                count++;
                return true;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }
    
    public int getCount() {
        return count;
    }
}

// 5. READ-WRITE LOCK - Multiple readers, single writer
class ReadWriteLockExample {
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int data = 0;
    
    public int read() {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reading: " + data);
            return data;
        } finally {
            rwLock.readLock().unlock();
        }
    }
    
    public void write(int value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " writing: " + value);
            data = value;
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}

public class AdvancedThreadConcepts {
    
    public static void main(String[] args) throws InterruptedException {
        
        // 1. VOLATILE DEMONSTRATION
        System.out.println("=== 1. VOLATILE KEYWORD ===");
        VolatileExample ve = new VolatileExample();
        
        Thread writer = new Thread(() -> {
            try { Thread.sleep(1000); } catch(InterruptedException e) {}
            ve.writer();
        });
        
        Thread reader = new Thread(() -> ve.reader());
        
        reader.start();
        writer.start();
        reader.join();
        writer.join();
        System.out.println("Volatile ensures visibility across threads\n");
        
        
        // 2. THREADLOCAL DEMONSTRATION
        System.out.println("=== 2. THREADLOCAL ===");
        
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 3; i++) {
                ThreadLocalExample.increment();
                System.out.println("Thread-1: " + ThreadLocalExample.get());
            }
        });
        
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 3; i++) {
                ThreadLocalExample.increment();
                System.out.println("Thread-2: " + ThreadLocalExample.get());
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Each thread has its own copy\n");
        
        // 3. ATOMIC VARIABLES DEMONSTRATION
        System.out.println("=== 3. ATOMIC VARIABLES ===");
        AtomicExample ae = new AtomicExample();
        
        Thread t3 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) ae.increment();
        });
        
        Thread t4 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) ae.increment();
        });
        
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        
        System.out.println("Expected: 2000");
        System.out.println("Actual: " + ae.get());
        System.out.println("Atomic operations are lock-free and thread-safe\n");
        
        // 4. REENTRANT LOCK DEMONSTRATION
        System.out.println("=== 4. REENTRANT LOCK ===");
        ReentrantLockExample rle = new ReentrantLockExample();
        
        Thread t5 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                rle.increment();
                System.out.println("Thread-1 incremented");
            }
        });
        
        Thread t6 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                if(rle.tryIncrement()) {
                    System.out.println("Thread-2 incremented");
                } else {
                    System.out.println("Thread-2 couldn't acquire lock");
                }
            }
        });
        
        t5.start();
        t6.start();
        t5.join();
        t6.join();
        System.out.println("Final count: " + rle.getCount() + "\n");
        
        // 5. READ-WRITE LOCK DEMONSTRATION
        System.out.println("=== 5. READ-WRITE LOCK ===");
        ReadWriteLockExample rwle = new ReadWriteLockExample();
        
        Thread writer1 = new Thread(() -> {
            for(int i = 1; i <= 3; i++) {
                rwle.write(i * 10);
                try { Thread.sleep(500); } catch(InterruptedException e) {}
            }
        }, "Writer");
        
        Thread reader1 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                rwle.read();
                try { Thread.sleep(300); } catch(InterruptedException e) {}
            }
        }, "Reader-1");
        
        Thread reader2 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                rwle.read();
                try { Thread.sleep(300); } catch(InterruptedException e) {}
            }
        }, "Reader-2");
        
        writer1.start();
        reader1.start();
        reader2.start();
        
        writer1.join();
        reader1.join();
        reader2.join();
        
        System.out.println("\n=== COMPARISON TABLE ===");
        System.out.println("Concept          | Use Case");
        System.out.println("-----------------|----------------------------------");
        System.out.println("volatile         | Simple flag, visibility guarantee");
        System.out.println("ThreadLocal      | Thread-specific data");
        System.out.println("Atomic           | Lock-free counters, flags");
        System.out.println("ReentrantLock    | Flexible locking, tryLock");
        System.out.println("ReadWriteLock    | Many readers, few writers");
        
        System.out.println("\n=== SYNCHRONIZED vs REENTRANTLOCK ===");
        System.out.println("synchronized     | Simple, automatic unlock");
        System.out.println("ReentrantLock    | tryLock, timed lock, interruptible");
    }
}
