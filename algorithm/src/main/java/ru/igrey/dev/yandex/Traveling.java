package ru.igrey.dev.yandex;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Traveling {
    public static String input[] = new String[]{
            "7",
            "0 0",
            "0 2",
            "2 2",
            "0 -2",
            "2 -2",
            "2 -1",
            "2 1",
            "2",
            "1 3",
    };


    private static Cities initCities() {
        int amountCities = Integer.valueOf(input[0]);
        City[] cityArr = new City[amountCities];
        for (int n = 1; n <= amountCities; n++) {
            String[] xy = input[n].split(" ");
            cityArr[n - 1] = new City(n, Integer.valueOf(xy[0]), Integer.valueOf(xy[1]));
        }
        Cities cities = new Cities(
                input[amountCities + 2].split(" ")[0],
                input[amountCities + 2].split(" ")[1],
                input[amountCities + 1],
                cityArr
        );
        return cities;
    }

    public static void main(String[] args) {
        System.out.println(initCities().dijkstra());
    }
}

@ToString
class Cities {
    public int startCity;
    public int endCity;
    public int maxDist;
    public City[] cities;
    private List<Integer> visitedNumber;


    public Cities(String startCity, String endCity, String maxDist, City[] cities) {
        this.startCity = Integer.valueOf(startCity);
        this.endCity = Integer.valueOf(endCity);
        this.maxDist = Integer.valueOf(maxDist);
        this.cities = cities;
        this.visitedNumber = new ArrayList<>();
    }

    private City city(int number) {
        return cities[number - 1];
    }

    private List<City> neighbors(int cityNumber) {
        return Stream.of(cities)
                .filter(city -> cityNumber != city.number)
                .filter(city -> !visitedNumber.contains(city.number))
                .filter(city -> city.dist(city(cityNumber)) <= maxDist)
                .collect(Collectors.toList());
    }

    public double dijkstra() {
        dijkstra(city(startCity));
        return city(endCity).cost;
    }

    private void dijkstra(City city) {
        for (City neighbor : neighbors(city.number)) {
            double dist = neighbor.dist(city);
            if (neighbor.cost == -1 || neighbor.cost > dist) {
                neighbor.cost = dist;
            }
        }
        visitedNumber.add(city.number);
        for (City neighbor : neighbors(city.number)) {
            dijkstra(neighbor);
        }
    }

}

@ToString
class City {
    public int number;
    public double x;
    public double y;
    public double cost;

    public City(int number, double x, double y) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.cost = -1;
    }

    public double dist(City city) {
        double sumSq = Math.pow((city.x - x), 2) + Math.pow((city.y - y), 2);
        return Math.pow(sumSq, 0.5);
    }
}