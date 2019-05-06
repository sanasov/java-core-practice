package ru.igrey.dev.common.numbers;

import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;

public class Numbers {

    private static boolean isPowOf2Recursive(Integer a) {
        if (a == 1) {
            return true;
        }
        if (a % 2 == 0 && a / 2 == 1) {
            return true;
        }
        return a % 2 == 0 && isPowOf2Recursive(a / 2);
    }

    private static boolean isPowOf2Cycle(Integer a) {
        while (a % 2 == 0 && a > 0) {
            a = a / 2;
        }
        if (a == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static Integer reverseNumberCycle(Integer a) {
        Integer reverse = 0;
        while (a != 0) {
            reverse = 10 * reverse + a % 10;
            a = a / 10;
        }
        return reverse;
    }

    private static Integer reverseNumberRecursive(Integer a) {
        return reverseNumberRecursive(a, a.toString().length());
    }

    private static Integer reverseNumberRecursive(Integer a, Integer length) {
        if (a < 10) {
            return a;
        }
        return ((int) Math.pow(10, length - 1)) * (a % 10) + reverseNumberRecursive(a / 10, length - 1);
    }

    private static boolean isBinaryRecusrive(int a) {
        if (a / 10 == 0) {
            return a == 0 || a == 1;
        }
        return (a % 10 == 0 || a % 10 == 1) && isBinaryRecusrive(a / 10);
    }

    private static boolean isBinaryCycle(int a) {
        while (a / 10 != 0 && (a % 10 == 0 || a % 10 == 1)) {
            a = a / 10;
        }
        if (a == 1 || a == 0) {
            return true;
        } else {
            return false;
        }

    }

    private static Integer sumDigitsRecursive(Integer a) {
        if (a / 10 == 0) {
            return a;
        }
        return a % 10 + sumDigitsRecursive(a / 10);
    }

    private static Integer sumDigitsCycle(Integer a) {
        Integer sumDigits = 0;
        while (a >= 10) {
            sumDigits += a % 10;
            a = a / 10;
        }
        sumDigits += a;
        return sumDigits;
    }

    public static Integer factorial(Integer a) {
        if (a == 0 || a == 1) return 1;
        Integer factorial = 1;
        while (a > 1) {
            factorial *= a;
            a--;
        }
        return factorial;
    }

    public static void main(String[] args) {
        System.out.println(factorial(2));
    }

}
