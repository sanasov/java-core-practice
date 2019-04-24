package lock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    public static void main(String[] args) {
//        calcsSync();
        calcsLock();
    }

    private final static ConcurrentMap<String, ReentrantLock> accountLocks = new ConcurrentHashMap<>();

    private static void calcsLock() {
        System.out.println("Calcs with Lock");

        Runnable task1 = () -> calcLock(new String("Account1"), 3, 4);
        Runnable task2 = () -> calcLock(new String("Account2"), 30, 40);
        Runnable task3 = () -> calcLock(new String("Account3"), 300, 400);
        List<Runnable> tasks = Arrays.asList(task1, task2, task3, task1, task1);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        tasks.forEach(executor::submit);
        executor.shutdown();
    }

    private static void calcsSync() {
        System.out.println("Calcs with synchronized");
        Runnable task1 = () -> calcSync(new String("Account1"), 3, 4);
        Runnable task2 = () -> calcSync(new String("Account2"), 30, 40);
        Runnable task3 = () -> calcSync(new String("Account3"), 300, 400);
        List<Runnable> tasks = Arrays.asList(task1, task2, task3, task1, task1);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        tasks.forEach(executor::submit);
    }

    private static void calcSync(String accountId, Integer a, Integer b) {
        synchronized (accountId) {
            System.out.println("Start " + Thread.currentThread().getName() + " " + accountId);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End calc by " + Thread.currentThread().getName() + " " + accountId + " result = " + (a + b));
        }
    }

    private static void calcLock(String accountId, Integer a, Integer b) {
        accountLocks.putIfAbsent(accountId, new ReentrantLock());
        ReentrantLock lock = accountLocks.get(accountId);
        lock.lock();
        System.out.println("Start " + Thread.currentThread().getName() + " " + accountId);
        try {
            Thread.sleep(1000);
            System.out.println("End calc by " + Thread.currentThread().getName() + " " + accountId + " result = " + (a + b));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
//            accountLocks.remove(lock);
        }
    }
}
