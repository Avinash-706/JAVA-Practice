// =====================================================================
//          INTER-THREAD COMMUNICATION - wait(), notify(), notifyAll()
// =====================================================================
// Allows synchronized threads to communicate with each other
// Must be called from synchronized context

// CLASSIC EXAMPLE: Producer-Consumer Problem
class SharedResource {
    private int data;
    private boolean hasData = false;
    
    public synchronized void produce(int value) {
        while(hasData) {
            try {
                System.out.println("Producer waiting... (buffer full)");
                wait(); // Release lock and wait
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        this.data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify(); // Wake up consumer
    }
    
    public synchronized int consume() {
        while(!hasData) {
            try {
                System.out.println("Consumer waiting... (buffer empty)");
                wait(); // Release lock and wait
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        hasData = false;
        System.out.println("Consumed: " + data);
        notify(); // Wake up producer
        return data;
    }
}

// EXAMPLE 2: Bank Account with wait/notify
class BankAccount {
    private int balance = 10000;
    
    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + 
                         " attempting to withdraw: " + amount);
        
        while(balance < amount) {
            System.out.println(Thread.currentThread().getName() + 
                             " waiting for deposit... (balance: " + balance + ")");
            try {
                wait(); // Wait for deposit
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + 
                         " withdrew: " + amount + " (balance: " + balance + ")");
    }
    
    public synchronized void deposit(int amount) {
        System.out.println(Thread.currentThread().getName() + 
                         " depositing: " + amount);
        balance += amount;
        System.out.println(Thread.currentThread().getName() + 
                         " deposited: " + amount + " (balance: " + balance + ")");
        notifyAll(); // Wake up all waiting threads
    }
}

// EXAMPLE 3: Multiple Consumers with notifyAll()
class DataQueue {
    private java.util.Queue<Integer> queue = new java.util.LinkedList<>();
    private int capacity = 5;
    
    public synchronized void produce(int value) {
        while(queue.size() == capacity) {
            try {
                System.out.println("Queue full, producer waiting...");
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        queue.add(value);
        System.out.println("Produced: " + value + " (size: " + queue.size() + ")");
        notifyAll(); // Wake up all consumers
    }
    
    public synchronized int consume() {
        while(queue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting...");
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        int value = queue.poll();
        System.out.println(Thread.currentThread().getName() + 
                         " consumed: " + value + " (size: " + queue.size() + ")");
        notifyAll(); // Wake up producers
        return value;
    }
}

public class InterThreadCommunicationComplete {
    
    public static void main(String[] args) throws InterruptedException {
        
        // DEMONSTRATION 1: Producer-Consumer
        System.out.println("=== PRODUCER-CONSUMER PATTERN ===\n");
        demonstrateProducerConsumer();
        
        Thread.sleep(1000);
        
        // DEMONSTRATION 2: Bank Account
        System.out.println("\n=== BANK ACCOUNT WITH WAIT/NOTIFY ===\n");
        demonstrateBankAccount();
        
        Thread.sleep(1000);
        
        // DEMONSTRATION 3: Multiple Consumers
        System.out.println("\n=== MULTIPLE CONSUMERS WITH NOTIFYALL ===\n");
        demonstrateMultipleConsumers();
    }
    
    
    static void demonstrateProducerConsumer() throws InterruptedException {
        SharedResource resource = new SharedResource();
        
        Thread producer = new Thread(() -> {
            for(int i = 1; i <= 5; i++) {
                resource.produce(i);
                try { Thread.sleep(500); } catch(InterruptedException e) {}
            }
        }, "Producer");
        
        Thread consumer = new Thread(() -> {
            for(int i = 1; i <= 5; i++) {
                resource.consume();
                try { Thread.sleep(1000); } catch(InterruptedException e) {}
            }
        }, "Consumer");
        
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
    
    static void demonstrateBankAccount() throws InterruptedException {
        BankAccount account = new BankAccount();
        
        Thread withdrawer = new Thread(() -> {
            account.withdraw(15000);
        }, "Withdrawer");
        
        Thread depositor = new Thread(() -> {
            try { Thread.sleep(2000); } catch(InterruptedException e) {}
            account.deposit(10000);
        }, "Depositor");
        
        withdrawer.start();
        depositor.start();
        withdrawer.join();
        depositor.join();
    }
    
    static void demonstrateMultipleConsumers() throws InterruptedException {
        DataQueue queue = new DataQueue();
        
        Thread producer = new Thread(() -> {
            for(int i = 1; i <= 10; i++) {
                queue.produce(i);
                try { Thread.sleep(300); } catch(InterruptedException e) {}
            }
        }, "Producer");
        
        Thread consumer1 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                queue.consume();
                try { Thread.sleep(500); } catch(InterruptedException e) {}
            }
        }, "Consumer-1");
        
        Thread consumer2 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                queue.consume();
                try { Thread.sleep(700); } catch(InterruptedException e) {}
            }
        }, "Consumer-2");
        
        producer.start();
        consumer1.start();
        consumer2.start();
        
        producer.join();
        consumer1.join();
        consumer2.join();
        
        System.out.println("\n=== KEY CONCEPTS ===");
        System.out.println("wait()      - Releases lock, waits for notify");
        System.out.println("notify()    - Wakes up ONE waiting thread");
        System.out.println("notifyAll() - Wakes up ALL waiting threads");
        System.out.println("\nMust be called from synchronized context!");
        System.out.println("Otherwise: IllegalMonitorStateException");
        
        System.out.println("\n=== WAIT vs SLEEP ===");
        System.out.println("wait()  - Releases lock, must be in synchronized");
        System.out.println("sleep() - Holds lock, can be called anywhere");
    }
}
