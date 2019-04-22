package ru.igrey.dev.common;

import java.util.Arrays;
import java.util.stream.Stream;

public class MedianaVsAvg {

    private static Integer arr[] = new Integer[]{34, 765, 65, 23, 65, 23, 66, 22, 2, 7888, 3, 61, 82, 47, 35, 566, 7, 0, 16};

    private static Integer mediana() {
        if (arr.length % 2 == 0) {
            return -1000000000;
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        return arr[arr.length / 2];
    }

    private static Double avg() {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        return Stream.of(arr).mapToDouble(Integer::doubleValue).average().getAsDouble();
    }

    public static void main(String[] args) {
        System.out.println(mediana());
        System.out.println(avg());
    }

}
