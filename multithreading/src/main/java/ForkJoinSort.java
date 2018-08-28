import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSort {

    private final static int THREADS_NUMBER = 10;

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String[] words = RandomStringUtils.random(10_500_000, true, true).split("");
//        String[] words = {"local", "variable",
//                "array", "operand stack", "data frame",
//                "Constant pool", "Method area",
//                "RDD", "Partition",
//                "Base", "Available", "Soft", "State",
//                "Cassandra",
//                "Hive"};
        long start = System.currentTimeMillis();
        System.out.println(forkJoinPool.invoke(new SortArrayFJ(words)).length);
        System.out.println((System.currentTimeMillis() - start));


    }
}

class SortArrayFJ extends RecursiveTask<String[]> {
    String[] words;

    public SortArrayFJ(String[] words) {
        this.words = words;
    }

    @Override
    protected String[] compute() {
        if (words.length <= 2) {
            Arrays.sort(words);
            return words;
        } else {
            return merge(
                    new SortArrayFJ(Arrays.copyOfRange(words, 0, words.length / 2)).invoke(),
                    new SortArrayFJ(Arrays.copyOfRange(words, words.length / 2, words.length)).invoke()
            );
        }
    }

    public static String[] merge(String[] arr1, String[] arr2) {
        String[] result = new String[arr1.length + arr2.length];
        if (arr1.length == 0) return arr2;
        if (arr2.length == 0) return arr1;
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i].compareToIgnoreCase(arr2[j]) < 0) {
                result[i + j] = arr1[i];
                i++;
            } else {
                result[i + j] = arr2[j];
                j++;
            }
        }
        for (int k = i; k < arr1.length; k++) {
            result[k + j] = arr1[k];
        }
        for (int k = j; k < arr2.length; k++) {
            result[i + k] = arr2[k];
        }
        return result;
    }
}
