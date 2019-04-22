package ru.igrey.dev.yandex;

import java.util.TreeSet;

//https://habr.com/ru/company/yandex/blog/340784/
//«Сложные числа»
public class SlognieChisla {

    private static Long s(Long n) {
        return n.toString()
                .chars()
                .mapToLong(c -> (long) Character.getNumericValue(c))
                .sum();
    }

    private static Integer findNotMinSlognoeChislo(int from, int to) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = from; i < to; i++) {
            Double result = 3 * i / Math.pow(s((long) i), 2);
            if (result == Math.floor(result)) {
                set.add(result.intValue());
            }
        }
        Integer prev = set.first();
        for (Integer curr : set) {
            if (curr - prev > 1) {
                return ++prev;
            }
            prev = curr;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findNotMinSlognoeChislo(1, 1000_000));
    }
}
