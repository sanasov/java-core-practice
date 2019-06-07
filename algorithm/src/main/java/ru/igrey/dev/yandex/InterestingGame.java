package ru.igrey.dev.yandex;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InterestingGame {

    short totalScores;
    int cardsCount;
    short[] cards;

    public InterestingGame(String row1, String row2) {
        this.totalScores = Short.valueOf(row1.split(" ")[0]);
        this.cardsCount = Integer.valueOf(row1.split(" ")[1].replaceAll("[^\\d]", ""));
        this.cards = new short[cardsCount];
        String[] cardsAsString = row2.split(" ");
        for (int i = 0; i < cardsCount; i++) {
            cards[i] = Short.valueOf(cardsAsString[i]);
        }
    }

    private String findWinner() {
        short petyaScores = 0;
        short vasyaScores = 0;

        for (int i = 0; i < cards.length; i++) {
            if ((cards[i] % 17 != 0 && cards[i] % 31 != 0) || (cards[i] % 17 == 0 && cards[i] % 31 == 0)) {
                continue;
            }
            if (cards[i] % 17 == 0) {
                vasyaScores++;
                if (totalScores == vasyaScores) return "Vasya";
            }
            if (cards[i] % 31 == 0) {
                petyaScores++;
                if (totalScores == petyaScores) return "Petya";
            }
        }
        return "Too long";
    }

    public static String readFileAsString() throws IOException {
        return new String(Files.readAllBytes(Paths.get("input.txt")));
    }

    public static void main(String[] args) throws IOException {
        String row1 = readFileAsString().split("\n")[0];
        String row2 = readFileAsString().split("\n")[1];
        System.out.println(new InterestingGame(row1, row2).findWinner());
    }
}
