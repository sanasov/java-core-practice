package ru.igrey.dev.common.numbers;

import java.util.Arrays;
import java.util.List;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibLinear(8));
        System.out.println(fibLinearRecursive(55));
        System.out.println(fibExponentRecursive(5));

    }

    private static int fibExponentRecursive(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibExponentRecursive(n - 1) + fibExponentRecursive(n - 2);
    }

    private static int fibLinear(int n) {
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

    private static int fibLinearRecursive(int n) {
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

    private List<String> bipowMatrix0111(int n) {
        List<String> fibmatrix = Arrays.asList("0 1", "1 1");
        if (n == 0) {
            return Arrays.asList("1 0", "0 1");
        }
        if (n == 1) {
            return fibmatrix;
        }
        if (n % 2 == 0) {
            return multiple(bipowMatrix0111(n / 2), bipowMatrix0111(n / 2));
        } else {
            return multiple(multiple(fibmatrix, bipowMatrix0111((n - 1) / 2)), bipowMatrix0111((n - 1) / 2));
        }
    }

    List<String> multiple(List<String> a, List<String> b) {
        return null;
    }
}
