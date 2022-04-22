package com.company;

import java.util.List;

import static com.company.CityUtils.*;

public class Main {

    public static void main(String[] args) {
        // write your code here

       List<City> cities=parse();
//     sortByName(cities);
//     print(cities);


//     sortByDistrictandName(cities);
//     print(cities);
        printMaxPopulation(cities);


    }


}
