package ru.igrey.dev.yandex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardGame2 {

    short totalScores;
    int cardsCount;
    List<Short> cards;
    static final Integer ITERATION_LIMIT = 10_000_008;

    public CardGame2(String row1, String row2) {
        this.totalScores = Short.valueOf(row1.split(" ")[0]);
        this.cardsCount = Short.valueOf(row1.split(" ")[1].replaceAll("[^\\d]", ""));
        this.cards = Stream.of(row2.split(" "))
                .map(str -> Short.valueOf(str.replaceAll("[^\\d]", "")))
                .collect(Collectors.toList());
    }

    private String findWinner() {
        Short petyaScores = 0;
        Short vasyaScores = 0;
        List<Short> leftCards = new ArrayList<>();

        for (int i = 0; i < ITERATION_LIMIT; i++) {
            for (Short card : cards) {
                if ((card % 17 != 0 && card % 31 != 0) || (card % 17 == 0 && card % 31 == 0)) {
                    leftCards.add((short) (2 * card));
                    continue;
                }
                if (card % 17 == 0) {
                    vasyaScores++;
                    if (totalScores == vasyaScores) return "Vasya";
                }
                if (card % 31 == 0) {
                    petyaScores++;
                    if (totalScores == petyaScores) return "Petya";
                }

            }
            cards = leftCards;
            leftCards = new ArrayList<>();
        }
        return "Too long";
    }

    public static String readFileAsString() throws IOException {
        return new String(Files.readAllBytes(Paths.get("input.txt")));
    }

    public static void main(String[] args) throws IOException {
        String row1 = readFileAsString().split("\n")[0];
        String row2 = readFileAsString().split("\n")[1];
        System.out.println(new CardGame2(row1, row2).findWinner());
    }
}