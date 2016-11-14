package com.tejatummalapalli.springcountry.dao;

import com.tejatummalapalli.springcountry.exception.CountryNotFoundException;
import com.tejatummalapalli.springcountry.model.Country;

import java.util.Arrays;
import java.util.List;

public class SimpleCountryDAO implements CountryDao{
    //In Memory Repo .. But it is not recommended...
    //TODO: Dummy values..
    static List<String> indianLanguages = Arrays.asList("English","Hindi");
    private static final List<Country> ALL_COUNTRIES = Arrays.asList(
        new Country("India",1000000,"Delhi",indianLanguages,"india-flag.jpg"),
        new Country("IndiaDuplicate",1000000,"Delhi",indianLanguages,"india-flag.jpg")
    );

    @Override
    public Country findCountryByName(String countryName) throws CountryNotFoundException {
       return ALL_COUNTRIES.stream()
                     .filter(currentCountry -> currentCountry.getCountryName().equals(countryName)  )
                     .findFirst()
                     .orElseThrow(() -> new CountryNotFoundException());
    }
}
