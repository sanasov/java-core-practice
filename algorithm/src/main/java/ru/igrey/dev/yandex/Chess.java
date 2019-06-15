package ru.igrey.dev.yandex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Chess {
    static final String NO_SOLUTION = "NO SOLUTION";

    public static String readFileAsString() throws IOException {
        return new String(Files.readAllBytes(Paths.get("C:\\projects\\java-core-practice\\algorithm\\src\\main\\resources\\input2.txt")));
    }

    public static void main(String[] args) throws IOException {
        String[] rows = readFileAsString().split("\n");
        short stageCount = Short.valueOf(rows[0].replaceAll("[^\\d]", ""));
        System.out.println(findParticipantFor3Place(stageCount, (short) (rows.length - 1), participantGameCount(rows)));
    }

    private static String findParticipantFor3Place(short stageCount, short gameCount, Map<String, Integer> participantGameCount) {
        if (participantGameCount.entrySet().size() != Math.pow(2, stageCount)) {
            return NO_SOLUTION;
        }
        Map<Integer, Set<String>> map = participantGameCount.entrySet().stream()
                .collect(
                        Collectors.groupingBy(Map.Entry::getValue,
                                Collectors.mapping(Map.Entry::getKey, Collectors.toSet())
                        )
                );
        int sum = 0;
        for (int i = 1; i <= stageCount; i++) {
            if (getParticipantAmount(map, i) != Math.pow(2, stageCount + 1 - i)) {
                return NO_SOLUTION;
            }
            sum += getParticipantAmount(map, i);
        }
        if (sum != Math.pow(2, stageCount + 1) - 2) {
            return NO_SOLUTION;
        }
        if (sum / 2 != gameCount) {
            return NO_SOLUTION;
        }
        return map.get(stageCount - 1).stream().reduce((a, b) -> a + " " + b).orElse("NO SOLUTION");
    }

    static int getParticipantAmount(Map<Integer, Set<String>> map, int stageNumber) {
        return map.entrySet().stream().filter(entry -> entry.getKey() >= stageNumber).map(e -> e.getValue().size()).reduce((a, b) -> a + b).get();
    }

    private static Map<String, Integer> participantGameCount(String[] rows) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 1; i < rows.length; i++) {
            String[] names = rows[i].replace("\r", "").split(" ");
            if (map.get(names[0]) == null) {
                map.put(names[0], 1);
            } else {
                map.put(names[0], map.get(names[0]) + 1);
            }
            if (map.get(names[1]) == null) {
                map.put(names[1], 1);
            } else {
                map.put(names[1], map.get(names[1]) + 1);
            }
        }
        return map.entrySet()
                .stream()
                .collect(
                        toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e2,
                                LinkedHashMap::new)
                );
    }

}
