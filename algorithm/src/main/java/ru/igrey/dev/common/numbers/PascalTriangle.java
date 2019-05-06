package ru.igrey.dev.common.numbers;

import java.math.BigDecimal;
import java.util.Arrays;

public class PascalTriangle {
    public static void main(String[] args) {
        array(50);
    }

    private static BigDecimal[] array(Integer h) {
        if (h < 0) throw new RuntimeException("Need: h >= 0");
        if (h == 0) {
            return new BigDecimal[]{BigDecimal.ONE};
        } else {
            return array(array(h - 1));
        }
    }

    private static BigDecimal[] array(BigDecimal[] prevArray) {
        BigDecimal[] result = new BigDecimal[prevArray.length + 1];
        result[0] = BigDecimal.ONE;
        result[result.length - 1] = BigDecimal.ONE;

        for (int i = 0; i < prevArray.length - 1; i++) {
            result[i + 1] = prevArray[i].add(prevArray[i + 1]);
        }
        System.out.println(Arrays.toString(result));
        return result;
    }


}
