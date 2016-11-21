package com.tejatummalapalli.springcountry.dao;

import com.tejatummalapalli.springcountry.exception.CountryNotFoundException;
import com.tejatummalapalli.springcountry.model.Country;

import java.util.List;

public interface CountryDao {
    Country findCountryByName(String countryName) throws CountryNotFoundException;

    List<Country> getAllCountries();

    Country findCountryBySlug(String slug) throws CountryNotFoundException;

    List<Country> getSortedContriesByName();

    List<Country> getSortedCountriesByPopulation();
}
