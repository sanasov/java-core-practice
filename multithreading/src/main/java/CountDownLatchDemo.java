import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    private final static Integer LACTH_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(LACTH_COUNT);
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
        Runnable runnable = () -> {
            for (int i = 1; i <= 25; i++) {
                countDownLatch.countDown();
                threadLocal.set(i);
            }
            System.out.println("FINISH " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + "\tthreadLocal = " + threadLocal.get());
        };
        ExecutorService fixThreadPool = Executors.newFixedThreadPool(10);
        fixThreadPool.submit(runnable);
        fixThreadPool.submit(runnable);
        fixThreadPool.submit(runnable);
        fixThreadPool.submit(runnable);
//        new Thread(runnable).start();
//        new Thread(runnable).start();
//        new Thread(runnable).start();
//        new Thread(runnable).start();
        countDownLatch.await();

        System.out.println("FINISH " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + "\tthreadLocal = " + threadLocal.get());
        fixThreadPool.shutdown();
    }
}
