package ru.igrey.dev.yandex;

import java.util.Scanner;

public class BrokenCommit {
    private static int commitCount;
    private static int start;
    private static int end;
    private static int[] commitNumbers = new int[4];
    private final static byte MAX_REQUESTS = 10;

    private static void createRequest() {
        int commitCountInInterval = end - start + 1;
        if (commitCountInInterval <= 4) {
            for (int i = 0; i < commitCountInInterval; i++) {
                System.out.print((start + i) + " ");
            }
            for (int i = 4 - commitCountInInterval; i > 0; i--) {
                System.out.print(end + " ");
            }
            System.out.println("\n");
            System.out.flush();
            return;
        }
        int step = commitCountInInterval / 4;
        for (int i = 0; i < 3; i++) {
            commitNumbers[i] = start - 1 + (i + 1) * step;
            System.out.print(commitNumbers[i] + " ");
        }
        commitNumbers[3] = end;
        System.out.print(end + "\n");
        System.out.flush();
    }

    private static void calcIntervalBySectionNumberOfBrokenCommit(int sectionNumber) {
        int commitCountInInterval = end - start + 1;
        start = start + commitCountInInterval / 4 * sectionNumber;
        end = sectionNumber == 3 ? end : (start - 1) + commitCountInInterval / 4;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        commitCount = scanner.nextInt();
        scanner.nextLine();
        start = 1;
        end = commitCount;
        for (int requestNumber = 1; requestNumber <= MAX_REQUESTS; requestNumber++) {
            createRequest();
            int sectionNumber = scanner.nextLine().replace(" ", "").indexOf("0");
            if (end - start < 4) {
                System.out.println("! " + (start + sectionNumber));
                System.out.flush();
                return;
            }
            calcIntervalBySectionNumberOfBrokenCommit(sectionNumber);
        }
        System.out.println("! " + start);
        System.out.flush();
    }


}
