package ru.igrey.dev.yandex;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fib2(8));
        System.out.println(fibCycle(55));
        System.out.println(fib1(3));

    }

    private static int fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int i = fib1(n - 1) + fib1(n - 2);
        return i;
    }

    private static int fibCycle(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int f1 = 0;
        int f2 = 1;
        int fn = f1 + f2;
        for (int i = 2; i < n; i++) {
            f1 = f2;
            f2 = fn;
            fn = f1 + f2;
        }
        return fn;
    }

    private static int fib2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib2(n, 0, 1);
    }

    private static int fib2(int n, int fn2, int fn1) {
        if (n == 2) {
            return fn2 + fn1;
        }
        return fib2(n - 1, fn1, fn2 + fn1);
    }
}
