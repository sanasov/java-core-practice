package ru.igrey.dev.yandex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Yagile {
    int taskCount;
    long pushInterval;
    int K;
    long[] deadlines;
    int arrLength = 1;

    public Yagile(String row1, String row2) {
        String[] row1cols = row1.split(" ");
        this.taskCount = Integer.valueOf(row1cols[0]);
        this.pushInterval = Long.valueOf(row1cols[1]);
        this.K = Integer.valueOf(row1cols[2]);
        String[] deadlinesAsString = row2.split(" ");
        deadlines = new long[taskCount];
        for (int i = 0; i < taskCount; i++) {
            deadlines[i] = Long.valueOf(deadlinesAsString[i]);
        }
    }


//    public long countDay() {
//        TreeSet<Long> notifyDays = new TreeSet<>();
//        long ignoreCount = 0L;
//        while (notifyDays.size() < (taskCount - 1) * k) {
//            long max = notifyDays.size() < k ? 0 : (long) notifyDays.toArray()[k - 1];
//            boolean allCurrElmMoreThanPrevMax = true;
//            for (int i = 0; i < taskCount; i++) {
//                System.out.println("task_" + i + " " + (pushInterval * ignoreCount + deadlines[i]));
//                notifyDays.add(pushInterval * ignoreCount + deadlines[i]);
//                if (max > pushInterval * ignoreCount + deadlines[i]) {
//                    allCurrElmMoreThanPrevMax = false;
//                }
//            }
//
//            if (allCurrElmMoreThanPrevMax && notifyDays.size() > k && max > 0) {
//                break;
//            }
//            System.out.println(notifyDays);
//            System.out.println("=========");
//            ignoreCount++;
//        }
//        System.out.println(notifyDays);
//        return (long) notifyDays.toArray()[k - 1];
//    }

    public long countDay2() {
        Arrays.sort(deadlines);
        TreeSet<Long> notifyDays = new TreeSet<>();
        for (int ignoreCount = 0; ignoreCount < K; ignoreCount++) {
            long last = notifyDays.size() < K ? -1 : notifyDays.last();
            for (int i = 0; i < taskCount; i++) {
                long notifyDay = pushInterval * ignoreCount + deadlines[i];
                if (notifyDay > last && notifyDays.size() == K && last > 0) {
                    continue;
                }
                notifyDays.add(notifyDay);
                if (notifyDays.size() > K) {
                    notifyDays.pollLast();
                }
                if (notifyDays.size() == K && last == K) {
                    return K;
                }
            }
            if (notifyDays.first() + pushInterval * ignoreCount > notifyDays.last() && notifyDays.size() == K) {
                break;
            }
        }
        return notifyDays.last();
    }


    public long countDay() {
        removeDoublesOfSortedArray2();
        long minElm = deadlines[0];
        if (pushInterval == 1) {
            return minElm + K - 1;
        }
        int count = 1;
        deadlines[0] += pushInterval;
        while (count < K) {
            if (arrLength < taskCount && minElm + pushInterval >= deadlines[arrLength]) {
                arrLength++;
            }
            while (arrLength < taskCount && minElm + pushInterval < deadlines[arrLength]) {
                int minInd = minInd(arrLength);
                minElm = deadlines[minInd];
                long koeff = koeff(minElm, min2Ind(arrLength), count);
//                System.out.println(koeff);
                deadlines[minInd] += koeff * pushInterval;
                count += koeff;
                if (count >= K) {
                    return minElm;
                }
            }
            if (arrLength < taskCount && minElm >= deadlines[arrLength]) {
                arrLength++;
            }
            if (arrLength == taskCount) {
                int minInd = minInd(arrLength);
                minElm = deadlines[minInd];
                long koeff = koeff(minElm, min2Ind(arrLength), count);
                deadlines[minInd] += koeff * pushInterval;
                count += koeff;
                if (count >= K) {
                    return minElm;
                }
            }
//            System.out.println(Arrays.toString(deadlines));
        }
        return minElm;
    }

    public long countDayBinary() {
        removeDoublesOfSortedArray2();
        long start = 1;
        long end =  Long.MAX_VALUE/2;
        long daysCount = (start + end) / 2;
        long k = k(daysCount);
        System.out.println(Arrays.toString(deadlines));
        while (end - start > 1) {
            if (k > K - 1) {
                end = daysCount;
            } else {
                start = daysCount;
            }
            daysCount = (start + end) / 2;
           System.out.println("start=" + start + "\tend=" + end + "\tdaysCount=" + daysCount + "\tk=" + k );
            k = k(daysCount);
        }
        while (true) {
            daysCount++;
            if (K == k(daysCount)) {
                return daysCount;
            }
        }
    }

    private long k(long daysCount) {
        long k = 0;
        for (int i = 0; i < taskCount; i++) {
            if (daysCount >= deadlines[i] && deadlines[i] > 0) {
                k += 1 + ((daysCount - deadlines[i]) / pushInterval);
            }
        }
        return k;
    }

    private long koeff(long minElm, int min2Ind, int count) {
        if (min2Ind == -1) {
            return (K - count) / pushInterval <= 1 ? 1 : (K - count) / pushInterval;
        }
        long koeff = (deadlines[min2Ind] - minElm) / pushInterval;
        if (koeff <= 1 || K == count) {
            return 1;
        }
        if (koeff >= K - count) {
            return K - count;
        }
        return koeff;
    }


    private void removeDoublesOfSortedArray2() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < taskCount; i++) {
            Integer rest = (int) (deadlines[i] % pushInterval);
            if (map.get(rest) == null) {
                map.put(rest, i);
                continue;
            }
            if (deadlines[map.get(rest)] > deadlines[i]) {
                deadlines[map.get(rest)] = 0;
                map.put(rest, i);
            } else {
                deadlines[i] = 0;
            }
        }
    }

    private void removeDoublesOfSortedArray() {
        Arrays.sort(deadlines);
        int count = 0;
        for (int i = 0; i < taskCount; i++) {
            if (deadlines[i] == Integer.MAX_VALUE) continue;
            for (int j = i + 1; j < taskCount; j++) {
                if (deadlines[j] == Integer.MAX_VALUE) continue;
                if ((deadlines[j] - deadlines[i]) % pushInterval == 0 && deadlines[j] != Integer.MAX_VALUE) {
                    deadlines[j] = Integer.MAX_VALUE;
                    count++;
                }
            }
        }
        taskCount -= count;
        Arrays.sort(deadlines);
    }

    private int minInd(int length) {
        long min = deadlines[0];
        int minInd = 0;
        for (int i = 1; i < length; i++) {
            if (deadlines[i] == 0) {
                continue;
            }
            if (min > deadlines[i]) {
                min = deadlines[i];
                minInd = i;
            }
        }
        return minInd;
    }

    private int min2Ind(int length) {
        int minInd = minInd(length);
        long min2 = Long.MAX_VALUE;
        int min2Ind = -1;
        for (int i = 0; i < length; i++) {
            if (deadlines[i] == 0) {
                continue;
            }
            if (min2 > deadlines[i] && deadlines[minInd] != deadlines[i]) {
                min2 = deadlines[i];
                min2Ind = i;
            }
        }
        return min2Ind;

    }


    private static String readFileAsString() throws IOException {
        return new String(Files.readAllBytes(Paths.get("/home/sanasov/ownprojects/java-core-practice/algorithm/src/main/resources/input.txt")));
    }

    public static void main(String[] args) throws IOException {
        String[] rows = readFileAsString().split("\n");
        System.out.println(new Yagile(rows[0], rows[1]).countDay());
        System.out.println(new Yagile(rows[0], rows[1]).countDay2());
        System.out.println(new Yagile(rows[0], rows[1]).countDayBinary());
    }
}
