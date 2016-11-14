package com.tejatummalapalli.springcountry.dao;

import com.tejatummalapalli.springcountry.exception.CountryNotFoundException;
import com.tejatummalapalli.springcountry.model.Country;

public interface CountryDao {
    Country findCountryByName(String countryName) throws CountryNotFoundException;
}
