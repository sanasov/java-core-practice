package ru.igrey.dev.yandex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Yagile {
    int taskCount;
    int pushInterval;
    int k;
    int[] deadlines;

    public Yagile(String row1, String row2) {
        this.taskCount = Integer.valueOf(row1.split(" ")[0].replaceAll("[^\\d]", ""));
        this.pushInterval = Integer.valueOf(row1.split(" ")[1].replaceAll("[^\\d]", ""));
        this.k = Integer.valueOf(row1.split(" ")[2].replaceAll("[^\\d]", ""));
        String[] deadlinesAsString = row2.split(" ");
        deadlines = new int[taskCount];
        for (int i = 0; i < taskCount; i++) {
            deadlines[i] = Short.valueOf(deadlinesAsString[i]);
        }
    }

    public int countDay() {
        int ignoreCount = 0;
        int totalDays = 0;
        while (ignoreCount < k) {
            boolean isRobotIgnored = false;
            for (int i = 0; i < taskCount; i++) {
                deadlines[i] = deadlines[i] - 1;
                if (deadlines[i] == 0 || (deadlines[i] < 0 && deadlines[i] % pushInterval == 0)) {
                    isRobotIgnored = true;

                }
            }
            if (isRobotIgnored) {
                ignoreCount++;
            }
            totalDays++;
        }
        return totalDays;
    }


    public static String readFileAsString() throws IOException {
        return new String(Files.readAllBytes(Paths.get("/home/sanasov/ownprojects/java-core-practice/algorithm/src/main/resources/input.txt")));
    }

    public static void main(String[] args) throws IOException {
        String[] rows = readFileAsString().split("\n");
        System.out.println(new Yagile(rows[0], rows[1]).countDay());
    }
}
