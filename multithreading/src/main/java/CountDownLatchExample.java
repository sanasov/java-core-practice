import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    private final static Integer LACTH_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(LACTH_COUNT);
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
        Runnable runnable = () -> {
            for (int i = 0; i < 34; i++) {
                countDownLatch.countDown();
                threadLocal.set(i);
            }
            System.out.println("FINISH " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + "\tthreadLocal = " + threadLocal.get());
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();

        countDownLatch.await();

        System.out.println("FINISH " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + "\tthreadLocal = " + threadLocal.get());
    }
}
