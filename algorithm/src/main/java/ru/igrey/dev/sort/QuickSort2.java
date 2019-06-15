package ru.igrey.dev.sort;

import java.util.Arrays;

public class QuickSort2 {

    private int[] arr;

    public QuickSort2(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        qsort(0, arr.length - 1);
    }

    private void qsort(int start, int end) {
        if (start >= end) return;
        int leftIndex = start;
        int rightIndex = end;
        int middle = start + (end - start) / 2;
        System.out.println("arr[" + middle + "] = " + arr[middle]);
        while (leftIndex < rightIndex) {
            while (arr[leftIndex] <= arr[middle] && leftIndex < middle) {
                leftIndex++;
            }
            while (arr[rightIndex] >= arr[middle] && rightIndex > middle) {
                rightIndex--;
            }
            if (leftIndex == middle && middle == rightIndex) {
                break;
            }
            if (leftIndex == middle) {
                swap(middle, ++middle);
            } else if (rightIndex == middle) {
                swap(middle, --middle);
            } else {
                swap(leftIndex, rightIndex);
            }
        }
        System.out.println(Arrays.toString(arr));
        qsort(start, middle);
        qsort(middle + 1, end);
    }


    private void swap(int leftIndex, int rightIndex) {
        int exArrLeftInd = arr[leftIndex];
        arr[leftIndex] = arr[rightIndex];
        arr[rightIndex] = exArrLeftInd;
    }

    public static void main(String[] args) {
        int[] arr11 = new int[]{52, 19, 3, 91, 6, 2, 5, 1, 67, 4, 4, 33, 9, 4};
        int[] arr1 = new int[]{4, 5, 6, 3, 2, 1};
        int[] arr12 = new int[]{1, 2, 3, 6, 5, 4};
        System.out.println(Arrays.toString(arr1));
        new QuickSort2(arr1).sort();
        System.out.println(Arrays.toString(arr1));
        int k = 2;
        int j = 3;
        int i = k = j;
        System.out.println(i);
        System.out.println(k);
    }
}
