package sort;

import java.util.ArrayList;
import java.util.List;

public class SortingThreadPool {

    private int threadCount;
    private int[] arr;
    private List<Thread> threads;

    public SortingThreadPool(int threadCount, int[] arr) {
        this.threadCount = threadCount;
        this.arr = arr;
        this.threads = threads();
    }

    public List<Thread> threads() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            threads.add(
                    new Thread(
                            new SortingThread(arr, new SortingInterval(i, threadCount, arr.length)),
                            "Thread " + i)
            );
        }
        return threads;
    }

    public void startThreads() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void waitAllThreads() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
