package com.tejatummalapalli.springcountry.model;

import java.util.List;

public class Country {
    private String countryName;
    private int population;
    private String capitalCity;
    private List<String> officialLanguages;
    private String flagImageName;


    public Country(String countryName, int population, String capitalCity, List<String> officialLanguages, String flagImageName) {
        this.countryName = countryName;
        this.population = population;
        this.capitalCity = capitalCity;
        this.officialLanguages = officialLanguages;
        this.flagImageName = flagImageName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public void setCapitalCity(String capitalCity) {
        this.capitalCity = capitalCity;
    }

    public List<String> getOfficialLanguages() {
        return officialLanguages;
    }

    public void setOfficialLanguages(List<String> officialLanguages) {
        this.officialLanguages = officialLanguages;
    }

    public String getFlagImageName() {
        return flagImageName;
    }

    public void setFlagImageName(String flagImageName) {
        this.flagImageName = flagImageName;
    }
}
