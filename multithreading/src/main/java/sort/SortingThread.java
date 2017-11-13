package sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class SortingThread implements Runnable {

    int arr[];
    int startIndex;
    int endIndex;

    public SortingThread(int[] arr, SortingInterval sortingInterval) {
        this.arr = arr;
        this.startIndex = sortingInterval.startIndex();
        this.endIndex = sortingInterval.endIndex();
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName());
        System.out.println("Sort from " + startIndex + " to " + endIndex);
        Arrays.sort(arr, startIndex, endIndex + 1);
    }
}
