package ru.igrey.dev.yandex;

import lombok.ToString;

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
            "1 3"
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
        System.out.println(initCities());
    }
}

@ToString
class Cities {
    public int startCity;
    public int endCity;
    public int maxDist;
    public City[] cities;

    public Cities(String startCity, String endCity, String maxDist, City[] cities) {
        this.startCity = Integer.valueOf(startCity);
        this.endCity = Integer.valueOf(endCity);
        this.maxDist = Integer.valueOf(maxDist);
        this.cities = cities;
    }
}

@ToString
class City {
    public int number;
    public double x;
    public double y;

    public City(int number, double x, double y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }

    public double dist(City city) {
        double sumSq = Math.pow((city.x - x), 2) + Math.pow((city.y - y), 2);
        return Math.pow(sumSq, 0.5);
    }
}