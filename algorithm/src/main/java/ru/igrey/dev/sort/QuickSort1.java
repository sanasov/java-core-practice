package ru.igrey.dev.sort;

import java.util.Arrays;

public class QuickSort1 {

    private int[] arr;

    public QuickSort1(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        sort(0, arr.length - 1);
    }

    private void sort(int start, int end) {
        int leftIndex = start;
        int rightIndex = end;
        int middle = start + (end - start) / 2;
        System.out.println("arr[" + middle + "] = " + arr[middle]);
        if (start >= end) return;
        while (leftIndex < rightIndex) {
            while (arr[leftIndex] <arr[middle]) {
                leftIndex++;
            }
            while (arr[rightIndex] > arr[middle]) {
                rightIndex--;
            }
            swap(leftIndex, rightIndex);
            leftIndex++;
            rightIndex--;
        }
        System.out.println(Arrays.toString(arr));
       if(start<rightIndex) sort(start, rightIndex);
       if(end>leftIndex) sort(leftIndex, end);
    }

    private void swap(int leftIndex, int rightIndex) {
        int exArrLeftInd = arr[leftIndex];
        arr[leftIndex] = arr[rightIndex];
        arr[rightIndex] = exArrLeftInd;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{52, 19, 3, 91, 6, 2, 5, 1, 67, 4,4, 33, 9, 4};
        int[] arr2 = new int[]{4, 5, 6, 3, 2, 1};
        System.out.println(Arrays.toString(arr2));
        new QuickSort1(arr2).sort();
        System.out.println(Arrays.toString(arr2));
    }
}
