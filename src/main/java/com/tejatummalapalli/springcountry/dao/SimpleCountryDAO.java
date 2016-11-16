package com.tejatummalapalli.springcountry.dao;

import com.github.slugify.Slugify;
import com.tejatummalapalli.springcountry.exception.CountryNotFoundException;
import com.tejatummalapalli.springcountry.model.Country;

import java.util.Arrays;
import java.util.List;

public class SimpleCountryDAO implements CountryDao{
    //In Memory Repo .. But it is not recommended...
    //TODO: Dummy values..
    Slugify slg = new Slugify();

    static List<String> indianLanguages = Arrays.asList("English","Hindi");
    String slug1 = slg.slugify("India");
    String slug2 = slg.slugify("India Duplicate");

    private  final List<Country> ALL_COUNTRIES = Arrays.asList(
        new Country("India",1000000,"Delhi",indianLanguages,"india-flag.png",slug1),
        new Country("India Duplicate",1000000,"Delhi",indianLanguages,"india-flag.png",slug2)
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
                .orElseThrow(() -> new CountryNotFoundException());    }
}
