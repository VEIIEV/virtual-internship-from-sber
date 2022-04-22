package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.*;
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

    public static void sortByName(List<City> cities) {

        cities.sort((c1, c2) -> {
            return c1.getName().compareToIgnoreCase(c2.getName());
        });
    }

    /**
     * сортирует получаемыый массив по региону
     *
     * @param cities массив с данными о городах
     */
    private static void sortByRegion(List<City> cities) {

        cities.sort((c1, c2) -> {
            return c1.getRegion().compareToIgnoreCase(c2.getRegion());
        });
    }

    /**
     * отображает количство городов в каждом регионе
     *
     * @param cities массив с данными о городах
     */
    public static HashMap<String, Integer> findQuantityOfCity(List<City> cities) {
        City[] array = new City[cities.size()];
        cities.toArray(array);
        HashMap<String, Integer> citiesMap = new HashMap<>();
        sortByRegion(cities);
        Integer count = 1;
        for (int i = 1; i < array.length; i++) {
            if (!array[i].getRegion().equals(array[i - 1].getRegion())) {
                citiesMap.put(array[i - 1].getRegion(), count);
                count = 0;
            }
            count++;
        }
        citiesMap.put(array[array.length-1].getRegion(), count);
        citiesMap.forEach((key, value) -> System.out.println(key + " - " + value));
        return citiesMap;
    }

    /**
     * Поиск количества городов в каждом из регионов (lambda-выражения)
     *
     * @param cities массив городов
     */
    private static void findCountCityByRegionV2(List<City> cities) {
        Map<String, Integer> regions = new HashMap<>();
        cities.forEach(city -> regions.merge(city.getRegion(), 1, Integer::sum));
        regions.forEach((k, v) -> System.out.println(MessageFormat.format(" {0} = {1}", k, v)));
    }

    /**
     * сортирует получаемыый массив по имени и дистрикту
     *
     * @param cities массив с данными о городах
     */
    public static void sortByDistrictandName(List<City> cities) {

        cities.sort((p1, p2) -> {
            if (p1.getDistrict().compareToIgnoreCase(p2.getDistrict()) == 0) {
                return p1.getName().compareToIgnoreCase(p2.getName());
            } else {
                return p1.getDistrict().compareToIgnoreCase(p2.getDistrict());
            }
        });
    }

    /**
     * поиск максимального значения в коллекции лист
     *
     * @param cities массив городов
     *               <p>
     *               ниже реализован поиск в массиве
     */
    //  private static City findMax(List<City> cities) {
    //      return cities.stream().max(
    //              (c1, c2) -> {
    //                  return Integer.parseInt(c1.getPopulation()) - Integer.parseInt(c2.getPopulation());
    //              }
    //      ).get();
    //  }
    //
    //  public static void printMaxPopulation(List<City> cities){
    //      City city=findMax(cities);
    //      System.out.println("name = " + city.getName()+", population = " + city.getPopulation());
    //  }
    private static int[] findMax(List<City> cities) {
        City[] array = new City[cities.size()];
        cities.toArray(array);
        int max = 0;
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (Integer.parseInt(array[i].getPopulation()) > max) {
                max = Integer.parseInt(array[i].getPopulation());
                index = i;
            }
        }
        int[] inter = new int[]{25, 23};
        return new int[]{index, max};
    }

    /**
     * Поиск города с наибольшим количеством жителей путем сортировки вставками
     *
     * @param cities массив городов
     */
    private static void findByInsertionSort(List<City> cities) {
        City[] array = new City[cities.size()];
        cities.toArray(array);
        for (int i = 1; i < array.length; i++) {
            City current = array[i];
            int j = i - 1;
            while (j >= 0 && Integer.parseInt(current.getPopulation()) < Integer.parseInt(array[j].getPopulation())) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        System.out.println(MessageFormat.format("[{0}] = {1}", array.length - 1, array[array.length - 1]));
    }

    public static void printMaxPopulation(List<City> cities) {
        int[] result = findMax(cities);
        System.out.println("[" + result[0] + "] =" + result[1]);
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
