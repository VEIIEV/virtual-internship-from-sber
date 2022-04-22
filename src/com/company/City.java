
package com.company;

import java.util.Comparator;

public class City implements Comparable<City> {

    private String name; // – наименование города
    private String region; // - регион
    private String district; // – федеральный округ
    private String population; // – количество жителей города
    private String foundation; //– дата основания или первое упоминание

    public City(String[] objectiveData){
        this.name= objectiveData[1];
        this.region= objectiveData[2];
        this.district= objectiveData[3];
        this.population= objectiveData[4];
        try{
            this.foundation= objectiveData[5];}
        catch (ArrayIndexOutOfBoundsException e){
            this.foundation= "null";
        }
    }

    public String getDistrict() {
        return district;
    }

    public String getName() {
        return name;
    }

    public String getPopulation() {
        return population;
    }

    public City(String name, String region, String district, String population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    @Override
    public int compareTo(City o) {
        return this.name.compareTo(o.name);
    }



    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population='" + population + '\'' +
                ", foundation='" + foundation + '\'' +
                '}';
    }
}
