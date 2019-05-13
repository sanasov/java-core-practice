package ru.igrey.dev.common;

import java.util.BitSet;

public class Arrays {

    private static void missingNumbers(Integer... arr) {
        BitSet bitSet = new BitSet(7);
        for (Integer a : arr) {
            bitSet.set(--a);
        }
        int lastMissingIndex = 0;
        for (int i = 0; i < 3; i++) {
            lastMissingIndex = bitSet.nextClearBit(lastMissingIndex);
            System.out.println(++lastMissingIndex);
        }
    }

    public static void main(String[] args) {
        missingNumbers(1, 4, 5, 6);
        System.out.println();
    }
}
