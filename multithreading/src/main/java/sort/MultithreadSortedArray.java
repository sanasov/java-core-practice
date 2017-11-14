package sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class MultithreadSortedArray {

    int[] arr;
    public static final int THREAD_COUNT = 4;

    public MultithreadSortedArray(int[] arr) {
        this.arr = arr;
    }

    public int[] get() {
        SortingThreadPool threadPool = new SortingThreadPool(THREAD_COUNT, arr);
        threadPool.startThreads();
        threadPool.waitAllThreads();

        return new MergedArray(
                new SplittedArrays(arr, threadPool.intervals()).get()
        ).get();
    }


    public static void main(String[] args) {
        int[] arr = {4, 6, 2, 1, 9, -70, 8, 22, 0, 43, 5, 3};
        System.out.println(Arrays.toString(new MultithreadSortedArray(arr).get()));
    }
}

