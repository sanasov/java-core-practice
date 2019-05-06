package ru.igrey.dev.common.numbers;

public class Pow {
    public static void main(String[] args) {
        System.out.println(pow(5, 3));
        System.out.println(bipow(5, 3));
    }

    private static int pow(int a, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return a;
        }
        return a * pow(a, n - 1);
    }

    private static int bipow(int a, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return a;
        }
        if (n % 2 == 0) {
            return bipow(a, n / 2) * bipow(a, n / 2);
        } else {
            return a * bipow(a, (n - 1) / 2) * bipow(a, (n - 1) / 2);
        }
    }
}
