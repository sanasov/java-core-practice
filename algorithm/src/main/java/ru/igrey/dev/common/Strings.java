package ru.igrey.dev.common;

public class Strings {
    private static String reverseLib(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private static String reverseCycle(String str) {
        StringBuilder reverse = new StringBuilder();
        if (str == null || str.length() <= 1) return str;
        for (int i = str.length() - 1; i >= 0; i--) {
            reverse.append(str.charAt(i));
        }
        return reverse.toString();
    }

    private static String reverseRecursive(String str) {
        if (str == null || str.length() <= 1) return str;
        return str.charAt(str.length() - 1) + reverseRecursive(str.substring(0, str.length() - 1));
    }

    private static void printAllPermutationRecursive(String str) {
        printAllPermutationRecursive(str, "");
    }

    private static void printAllPermutationRecursive(String str, String rest) {
        if (str == null || str.length() <= 1) {
            System.out.println(rest + str);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            printAllPermutationRecursive(str.replaceFirst(Character.toString(str.charAt(i)), ""), rest + str.charAt(i));
        }
    }

    private static boolean isPalindrom(String str) {
        return str.equals(reverseLib(str));
    }

    private static String longestPolindromRecursive(String str) {
        if (isPalindrom(str)) {
            return str;
        }
        String first = longestPolindrom(str.substring(0, str.length() - 1));
        String last = longestPolindrom(str.substring(1));
        return first.length() > last.length() ? first : last;
    }

    private static String longestPolindrom(String str) {
        if (isPalindrom(str)) {
            return str;
        }
        for (int length = str.length() - 1; length > 1; length--) {
            System.out.println("length: " + length);
            for (int startIndex = 0; startIndex + length <= str.length(); startIndex++) {
                System.out.println(str.substring(startIndex, startIndex + length));
                if (isPalindrom(str.substring(startIndex, startIndex + length))) {
                    return str.substring(startIndex, startIndex + length);
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println("result:" + longestPolindrom("ABC1221CA"));
    }
}
