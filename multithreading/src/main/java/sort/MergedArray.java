package sort;

import java.util.List;
import java.util.PriorityQueue;

public class MergedArray {

    private List<int[]> arrays;

    public MergedArray(List<int[]> arrays) {
        this.arrays = arrays;
    }

    public int[] get() {
        return mergeKSortedArray();
    }


    private int[] mergeKSortedArray() {
        //PriorityQueue is heap in Java
        PriorityQueue<ArrayContainer> queue = new PriorityQueue<>();
        int total = 0;

        //add arrays to heap
        for (int i = 0; i < arrays.size(); i++) {
            queue.add(new ArrayContainer(arrays.get(i), 0));
            total = total + arrays.get(i).length;
        }

        int m = 0;
        int result[] = new int[total];

        //while heap is not empty
        while (!queue.isEmpty()) {
            ArrayContainer arrayContainer = queue.poll();
            result[m++] = arrayContainer.arr[arrayContainer.index];

            if (arrayContainer.index < arrayContainer.arr.length - 1) {
                queue.add(new ArrayContainer(arrayContainer.arr, arrayContainer.index + 1));
            }
        }

        return result;
    }

}
