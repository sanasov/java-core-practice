package ru.igrey.dev.yandex;

import java.util.*;
import java.util.stream.Collectors;

//Турнирная таблица
public class Standings {

    private Map<String, Team> teams = new HashMap<>();
    private List<Team> first;
    private List<Team> second;
    private List<Team> third;

    private Standings(List<String> allGames) {
        for (String game : allGames) {
            String[] elm = game.split(" - ");
            game(elm[0], elm[1], elm[2]);
        }
        setFirstPlace();
        setSecondPlace();
        setThirdPlace();
    }

    private void game(String teamName1, String teamName2, String gameScore) {
        Team team1 = teams.get(teamName1);
        if (team1 == null) {
            team1 = new Team(teamName1);
            teams.put(teamName1, team1);
        }
        Team team2 = teams.get(teamName2);
        if (team2 == null) {
            team2 = new Team(teamName2);
            teams.put(teamName2, team2);
        }
        team1.calcGameResult(teamName2, gameScore, true);
        team2.calcGameResult(teamName1, gameScore, false);
    }

    private void print() {

        List<Team> teamList = teams.values()
                .stream()
//                .sorted(Comparator.comparing(Team::totalScores).reversed().thenComparing(Team::name))
                .sorted(Comparator.comparing(Team::name))
                .collect(Collectors.toList());
        int i = 1;
        printTableBorder();
        for (Team team : teamList) {
            team.setNumber(i);
            team.setPlace(first.contains(team) ? "1" : (second.contains(team) ? "2" : (third.contains(team) ? "3" : " ")));
            i++;
            team.printRow(teamList);
            printTableBorder();
        }
    }

    private void setFirstPlace() {
        Team firstTeam = teams.values().stream().max(Comparator.comparing(Team::totalScores).thenComparing(Team::winsCount)).get();
        first = teams.values().stream()
                .filter(t -> t.totalScores() == firstTeam.totalScores())
                .filter(t -> t.winsCount() == firstTeam.winsCount())
                .collect(Collectors.toList());
    }

    private void setSecondPlace() {
        Team secondTeam = teams.values().stream().filter(t -> !first.contains(t)).max(Comparator.comparing(Team::totalScores).thenComparing(Team::winsCount)).orElse(null);
        if (secondTeam == null) {
            return;
        }
        second = teams.values().stream()
                .filter(t -> t.totalScores() == secondTeam.totalScores())
                .filter(t -> t.winsCount() == secondTeam.winsCount())
                .collect(Collectors.toList());
    }

    private void setThirdPlace() {
        if(second == null) return;
        Team thirdTeam = teams.values().stream().filter(t -> !first.contains(t) && !second.contains(t)).max(Comparator.comparing(Team::totalScores).thenComparing(Team::winsCount)).orElse(null);
        if (thirdTeam == null) {
            return;
        }
        third = teams.values().stream()
                .filter(t -> t.totalScores() == thirdTeam.totalScores())
                .filter(t -> t.winsCount() == thirdTeam.winsCount())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> in1 = Arrays.asList(
                "Linux - Gentoo - 1:0",
                "Gentoo - Windows - 2:1",
                "Linux - Windows - 0:2");
        List<String> in2 = Arrays.asList(
                "Cplusplus - C - 1:0",
                "Cplusplus - Php - 2:0",
                "Java - Php - 1:0",
                "Java - C - 2:2",
                "Java - Perl - 1:1",
                "Java - Haskell - 1:1");
        new Standings(in1).print();
        new Standings(in2).print();
    }

    private void printTableBorder() {
        int teamCount = teams.size();
        int maxWordSize = teams.keySet().stream().max(Comparator.comparing(String::length)).get().length();
        System.out.println("+-+" + StringUtils.repeat("-", maxWordSize + 1) + StringUtils.repeat("+-", teamCount + 2) + "+");
    }


}

class Team {
    public int number;
    public String name;
    public Map<String, GameResult> rivalMap = new HashMap<>(); // имя др команды - количество очков полученных в результате игры с ней
    public String place;

    public Team(String name) {
        this.name = name;
    }

    public String name() {
        return name;

    }

    public void setNumber(int i) {
        number = i;
    }

    void calcGameResult(String rivalTeam, String scoreResult, boolean isHomeGame) {
        rivalMap.putIfAbsent(rivalTeam, calcGameScore(scoreResult, isHomeGame));
    }

    private GameResult calcGameScore(String scoreResult, boolean isHomeGame) {
        Integer score1 = Integer.valueOf(scoreResult.substring(0, scoreResult.indexOf(":")));
        Integer score2 = Integer.valueOf(scoreResult.substring(scoreResult.indexOf(":") + 1));
        if (score1.equals(score2)) {
            return GameResult.D;
        }
        if ((isHomeGame && score1 > score2) || (!isHomeGame && score1 < score2)) {
            return GameResult.W;
        }
        return GameResult.L;
    }

    int totalScores() {
        return rivalMap.values().stream().mapToInt(GameResult::score).sum();
    }

    int winsCount() {
        return (int) rivalMap.values().stream().filter(gr -> gr == GameResult.W).count();
    }

    void printRow(List<Team> sortedTeams) {
        String gameResultCells = sortedTeams.stream().map(t -> {
            if (t.name.equals(name)) {
                return "|X";
            }
            GameResult result = rivalMap.get(t.name);
            return result == null ? "| " : "|" + result.name();

        }).reduce((a, b) -> a + b).get();
        int maxWordSize = sortedTeams.stream().map(Team::name).max(Comparator.comparing(String::length)).get().length();
        String result = "|" + number + "|"
                + name
                + StringUtils.repeat(" ", maxWordSize + 1 - name.length())
                + gameResultCells + "|"
                + totalScores() + "|"
                + place + "|";
        System.out.println(result);
    }

    void setPlace(String place) {
        this.place = place;
    }
}

enum GameResult {
    X(null), W(3), L(0), D(1);

    public Integer score;

    GameResult(Integer score) {
        this.score = score;
    }

    public Integer score() {
        return score;
    }
}

class StringUtils {
    public static String repeat(String s, Integer count) {
        StringBuilder result = new StringBuilder(s);
        for (int i = 1; i < count; i++) {
            result.append(s);
        }
        return result.toString();
    }
}