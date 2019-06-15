import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreDemo {
    private static AtomicInteger a = new AtomicInteger(0);
    private static int b = 0;
    private static Resource resource = new Resource();

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(2, () -> {
            System.out.println(a);
            System.out.println(b);
            System.out.println(resource);
        });
        final Semaphore semaphore = new Semaphore(1);
        Runnable task = () -> {
            for (int i = 0; i < 500_000; i++) {
                a.incrementAndGet();
                resource.increment();
                try {
                    semaphore.acquire();
                    b++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }

            try {
                cb.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
    }

    static class Resource {
        private int c = 0;

        public void increment() {
            synchronized (this) {
                c++;
            }
        }

        @Override
        public String toString() {
            return String.valueOf(c);
        }
    }
}

