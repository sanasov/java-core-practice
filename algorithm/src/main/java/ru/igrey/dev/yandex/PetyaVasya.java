package ru.igrey.dev.yandex;

public class PetyaVasya {
    private static Integer[] cards = new Integer[]{2, 5, 6, 3, 1, 7, 12, 4, 11};

    public static void main(String[] args) {
        System.out.println(findWiner());
    }

    private static String findWiner() {
        Integer sumPetya = cards[0];
        Integer sumVasya = cards[1];
        for (int i = 2; i < cards.length; i++) {
            if (sumPetya < sumVasya) {
                sumPetya += cards[i];
            } else {
                sumVasya += cards[i];
            }
        }
        return sumPetya > sumVasya ? "Petya" : "Vasya";
    }
}
