// =====================================================================
//          THREAD POOL - EXECUTORSERVICE COMPLETE GUIDE
// =====================================================================
// Thread pool manages worker threads, reuses threads for multiple tasks
// Better performance than creating new threads for each task

import java.util.concurrent.*;
import java.util.*;

public class ThreadPoolAdvanced {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        // 1. FIXED THREAD POOL
        System.out.println("=== 1. FIXED THREAD POOL ===");
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);
        
        for(int i = 1; i <= 6; i++) {
            int taskId = i;
            fixedPool.execute(() -> {
                System.out.println("Task " + taskId + " by " + 
                                 Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch(InterruptedException e) {}
            });
        }
        
        fixedPool.shutdown();
        fixedPool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Fixed pool completed\n");
        
        // 2. CACHED THREAD POOL
        System.out.println("=== 2. CACHED THREAD POOL ===");
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        
        for(int i = 1; i <= 5; i++) {
            int taskId = i;
            cachedPool.execute(() -> {
                System.out.println("Task " + taskId + " by " + 
                                 Thread.currentThread().getName());
            });
        }
        
        cachedPool.shutdown();
        cachedPool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Cached pool completed\n");
        
        // 3. SINGLE THREAD EXECUTOR
        System.out.println("=== 3. SINGLE THREAD EXECUTOR ===");
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        
        for(int i = 1; i <= 4; i++) {
            int taskId = i;
            singlePool.execute(() -> {
                System.out.println("Task " + taskId + " executed sequentially");
            });
        }
        
        singlePool.shutdown();
        singlePool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Single thread executor completed\n");
        
        // 4. SCHEDULED THREAD POOL
        System.out.println("=== 4. SCHEDULED THREAD POOL ===");
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
        
        // Schedule with delay
        scheduledPool.schedule(() -> {
            System.out.println("Task executed after 2 seconds delay");
        }, 2, TimeUnit.SECONDS);
        
        // Schedule at fixed rate
        ScheduledFuture<?> future = scheduledPool.scheduleAtFixedRate(() -> {
            System.out.println("Periodic task at " + System.currentTimeMillis());
        }, 0, 1, TimeUnit.SECONDS);
        
        Thread.sleep(3500);
        future.cancel(true);
        scheduledPool.shutdown();
        System.out.println("Scheduled pool completed\n");
        
        // 5. CALLABLE AND FUTURE
        System.out.println("=== 5. CALLABLE AND FUTURE ===");
        ExecutorService callablePool = Executors.newFixedThreadPool(2);
        
        Callable<Integer> task1 = () -> {
            Thread.sleep(1000);
            return 100;
        };
        
        Callable<Integer> task2 = () -> {
            Thread.sleep(1500);
            return 200;
        };
        
        Future<Integer> result1 = callablePool.submit(task1);
        Future<Integer> result2 = callablePool.submit(task2);
        
        System.out.println("Result 1: " + result1.get());
        System.out.println("Result 2: " + result2.get());
        
        callablePool.shutdown();
        System.out.println("Callable pool completed\n");
        
        
        // 6. INVOKE ALL - Submit multiple tasks
        System.out.println("=== 6. INVOKE ALL ===");
        ExecutorService invokePool = Executors.newFixedThreadPool(3);
        
        List<Callable<String>> tasks = Arrays.asList(
            () -> { Thread.sleep(1000); return "Task A"; },
            () -> { Thread.sleep(500); return "Task B"; },
            () -> { Thread.sleep(1500); return "Task C"; }
        );
        
        List<Future<String>> results = invokePool.invokeAll(tasks);
        
        for(Future<String> result : results) {
            System.out.println("Result: " + result.get());
        }
        
        invokePool.shutdown();
        System.out.println("InvokeAll completed\n");
        
        // 7. INVOKE ANY - Get first completed result
        System.out.println("=== 7. INVOKE ANY ===");
        ExecutorService anyPool = Executors.newFixedThreadPool(3);
        
        List<Callable<String>> anyTasks = Arrays.asList(
            () -> { Thread.sleep(2000); return "Slow Task"; },
            () -> { Thread.sleep(500); return "Fast Task"; },
            () -> { Thread.sleep(1000); return "Medium Task"; }
        );
        
        String firstResult = anyPool.invokeAny(anyTasks);
        System.out.println("First completed: " + firstResult);
        
        anyPool.shutdown();
        System.out.println("InvokeAny completed\n");
        
        // 8. THREAD POOL EXECUTOR (Custom)
        System.out.println("=== 8. CUSTOM THREAD POOL EXECUTOR ===");
        
        ThreadPoolExecutor customPool = new ThreadPoolExecutor(
            2,                      // Core pool size
            4,                      // Maximum pool size
            60,                     // Keep alive time
            TimeUnit.SECONDS,       // Time unit
            new LinkedBlockingQueue<>(10)  // Work queue
        );
        
        for(int i = 1; i <= 5; i++) {
            int taskId = i;
            customPool.execute(() -> {
                System.out.println("Custom pool task " + taskId + " by " + 
                                 Thread.currentThread().getName());
                try { Thread.sleep(500); } catch(InterruptedException e) {}
            });
        }
        
        System.out.println("Active threads: " + customPool.getActiveCount());
        System.out.println("Pool size: " + customPool.getPoolSize());
        System.out.println("Queue size: " + customPool.getQueue().size());
        
        customPool.shutdown();
        customPool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Custom pool completed\n");
        
        // COMPARISON TABLE
        printComparisonTable();
    }
    
    static void printComparisonTable() {
        System.out.println("=== THREAD POOL TYPES COMPARISON ===\n");
        System.out.println("Type                  | Threads        | Use Case");
        System.out.println("---------------------|----------------|---------------------------");
        System.out.println("FixedThreadPool      | Fixed (n)      | Known workload, limited threads");
        System.out.println("CachedThreadPool     | 0 to Integer.MAX | Short-lived tasks, many tasks");
        System.out.println("SingleThreadExecutor | 1              | Sequential execution");
        System.out.println("ScheduledThreadPool  | Fixed (n)      | Delayed/periodic tasks");
        System.out.println("CustomThreadPool     | Custom         | Fine-grained control");
        
        System.out.println("\n=== KEY METHODS ===");
        System.out.println("execute(Runnable)    - Submit task, no return value");
        System.out.println("submit(Callable)     - Submit task, returns Future");
        System.out.println("invokeAll(tasks)     - Execute all, wait for all");
        System.out.println("invokeAny(tasks)     - Execute all, return first");
        System.out.println("shutdown()           - Stop accepting new tasks");
        System.out.println("shutdownNow()        - Stop immediately");
        System.out.println("awaitTermination()   - Wait for completion");
    }
}
