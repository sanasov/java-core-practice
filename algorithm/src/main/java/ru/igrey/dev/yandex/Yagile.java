package ru.igrey.dev.yandex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeSet;

public class Yagile {
    int taskCount;
    int pushInterval;
    int k;
    int[] deadlines;

    public Yagile(String row1, String row2) {
        String[] row1cols = row1.split(" ");
        this.taskCount = Integer.valueOf(row1cols[0]);
        this.pushInterval = Integer.valueOf(row1cols[1]);
        this.k = Integer.valueOf(row1cols[2]);
        String[] deadlinesAsString = row2.split(" ");
        deadlines = new int[taskCount];
        for (int i = 0; i < taskCount; i++) {
            deadlines[i] = Integer.valueOf(deadlinesAsString[i]);
        }
    }

    public long countDay() {
        TreeSet<Long> notifyDays = new TreeSet<>();
        long ignoreCount = 0L;
        while (notifyDays.size() < (taskCount - 1) * k) {
            long max = notifyDays.size() < k ? 0 : (long) notifyDays.toArray()[k - 1];
            boolean allCurrElmMoreThanPrevMax = true;
            for (int i = 0; i < taskCount; i++) {
            System.out.println("task_" + i + " " + (pushInterval * ignoreCount + deadlines[i]));
                notifyDays.add(pushInterval * ignoreCount + deadlines[i]);
                if (max > pushInterval * ignoreCount + deadlines[i]) {
                    allCurrElmMoreThanPrevMax = false;
                }
            }

            if (allCurrElmMoreThanPrevMax && notifyDays.size() > k && max > 0) {
                break;
            }
            System.out.println(notifyDays);
            System.out.println("=========");
            ignoreCount++;
        }
        System.out.println(notifyDays);
        return (long) notifyDays.toArray()[k - 1];
    }


    public static String readFileAsString() throws IOException {
        return new String(Files.readAllBytes(Paths.get("/home/sanasov/ownprojects/java-core-practice/algorithm/src/main/resources/input.txt")));
    }

    public static void main(String[] args) throws IOException {
        String[] rows = readFileAsString().split("\n");
        System.out.println(new Yagile(rows[0], rows[1]).countDay());
    }
}
