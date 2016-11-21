package com.tejatummalapalli.springcountry.dao;

import com.github.slugify.Slugify;
import com.tejatummalapalli.springcountry.exception.CountryNotFoundException;
import com.tejatummalapalli.springcountry.model.Country;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SimpleCountryDAO implements CountryDao{
    //In Memory Repo .. But it is not recommended...
    //TODO: Dummy values..
    Slugify slg = new Slugify();

    static List<String> indianLanguages = Arrays.asList("English","Hindi");
    String slug1 = slg.slugify("India");
    Country country1 = new Country("India",1332858390,"New Delhi",indianLanguages,"india.png",slug1);

    static List<String> ecuadorLanguages = Arrays.asList("Spanish");
    String slug2 = slg.slugify("ecuador");
    Country country2 = new Country("Ecuador",16385450,"Quito",ecuadorLanguages,"ecuador.png",slug2);

    static List<String> franceLanguages = Arrays.asList("french");
    String slug3 = slg.slugify("france");
    Country country3 = new Country("France",64772466,"Paris",franceLanguages,"france.png",slug3);

    static List<String> italyLanguages = Arrays.asList("Italian");
    String slug4 = slg.slugify("italy");
    Country country4 = new Country("Italy",59802270,"Rome",italyLanguages,"italy.png",slug4);

    static List<String> kenayaLanguages = Arrays.asList("Swahili","English");
    String slug5 = slg.slugify("kenya");
    Country country5 = new Country("Kenya",47707747,"Nairobi",kenayaLanguages,"kenya.png",slug5);


    private  final List<Country> ALL_COUNTRIES = Arrays.asList(
            country1,country2,country3,country4,country5
    );

    @Override
    public Country findCountryByName(String countryName) throws CountryNotFoundException {
       return ALL_COUNTRIES.stream()
                     .filter(currentCountry -> currentCountry.getCountryName().equals(countryName))
                     .findFirst()
                     .orElseThrow(() -> new CountryNotFoundException());
    }

    public List<Country> getAllCountries() {
        return ALL_COUNTRIES;
    }

    @Override
    public Country findCountryBySlug(String slug) throws CountryNotFoundException {
        return ALL_COUNTRIES.stream()
                .filter(currentCountry -> currentCountry.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException());
    }

    @Override
    public List<Country> getSortedContriesByName() {
        return ALL_COUNTRIES.stream()
                            .sorted()
                            .collect(Collectors.toList());
    }

    @Override
    public List<Country> getSortedCountriesByPopulation() {
        return ALL_COUNTRIES.stream()
                .sorted((c1,c2) -> c1.getPopulation() - c2.getPopulation())
                .collect(Collectors.toList());
    }
}
