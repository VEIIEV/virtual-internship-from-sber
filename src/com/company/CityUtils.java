package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CityUtils {

    public static List<City> parse() {
        List<City> cities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\world\\OneDrive\\Рабочий стол\\test\\city_ru.csv")); // Загрузка данных из файла
            while (scanner.hasNextLine()) { // Обработка данных и заполнение массива
                cities.add(parse(scanner.nextLine()));
            }
            scanner.close(); // Не забываем вызвать метод закрытия потока данных
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void print(List<City> cities) {
        cities.forEach(System.out::println);
    }

    /**
     * сортирует получаемыый массив по имени
     *
     * @param cities массив с данными о городах
     */

    public static void sortByName(List<City> cities){

        cities.sort((c1, c2) -> {
            return c1.getName().compareTo(c2.getName());
        });
    }


    /**
     * сортирует получаемыый массив по имени и дистрикту
     *
     * @param cities массив с данными о городах
     */
    public static void sortByDistrictandName(List<City> cities){

        cities.sort((p1, p2) -> {
            if (p1.getDistrict().compareTo(p2.getDistrict()) == 0) {
                return p1.getName().compareTo(p2.getName());
            } else {
                return p1.getDistrict().compareTo(p2.getDistrict());
            }
        });
    }

    private static City parse(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";"); // Задается разделитель в строке с данными
        scanner.skip("\\d*"); // Необходимо пропустить значение номера строки по условиям задачи
        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        String population = scanner.next();
        String foundation = null;
        if (scanner.hasNext()) { // В файле с городами возможно отсутствие данного значения
            foundation = scanner.next();
        }
        scanner.close();

        return new City(name, region, district, population, foundation);
    }
}
