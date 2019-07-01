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
        int step = commitCountInInterval / 5;
        int ostatok = commitCountInInterval % 5;
        if (ostatok > 0) {
            commitNumbers[0] = start + step;
            ostatok--;
        } else {
            commitNumbers[0] = start - 1 + step;
        }
        System.out.print(commitNumbers[0] + " ");
        for (int i = 1; i < 4; i++) {
            if (ostatok > 0) {
                commitNumbers[i] = commitNumbers[i - 1] + step + 1;
                ostatok--;
            } else {
                commitNumbers[i] = commitNumbers[i - 1] + step;
            }
            System.out.print(commitNumbers[i] + " ");
        }
        System.out.println();
        System.out.flush();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        commitCount = scanner.nextInt();
        scanner.nextLine();
        start = 1;
        end = commitCount;
        for (int requestNumber = 1; requestNumber <= MAX_REQUESTS; requestNumber++) {
            createRequest();
            int sectionIndex = scanner.nextLine().replace(" ", "").indexOf("0");
            if (sectionIndex == -1) {
                sectionIndex = 4;
            }
            if (end - start < 4) {
                System.out.println("! " + (start + sectionIndex));
                System.out.flush();
                return;
            }
            start = sectionIndex == 0 ? start : commitNumbers[sectionIndex - 1];
            end = sectionIndex == 4 ? end : commitNumbers[sectionIndex];
        }
        System.out.println("! " + start);
        System.out.flush();
    }


}
