package com.tejatummalapalli.springcountry.controller;

import com.tejatummalapalli.springcountry.dao.CountryDao;
import com.tejatummalapalli.springcountry.exception.CountryNotFoundException;
import com.tejatummalapalli.springcountry.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class countryController {

    @Autowired
    CountryDao simpleCountryDao;

    @RequestMapping("/countries")
    public String getMainPage(ModelMap modelMap){
        modelMap.put("countries",simpleCountryDao.getAllCountries());
        return "home";

    }

    @RequestMapping("/details/{slug}")
    public String getDetailsPage(@PathVariable String slug, ModelMap modelMap) throws CountryNotFoundException {

        Country country = simpleCountryDao.findCountryBySlug(slug);
        modelMap.put("country",country);
        return "details";
    }

    @RequestMapping("/sort-by-country-name")
    public String getSortedCoutriesByName(ModelMap modelMap) throws CountryNotFoundException {
        modelMap.put("countries",simpleCountryDao.getSortedContriesByName());
        return "home";
    }


    @RequestMapping("/sort-by-population")
    public String getSortedPopulation(ModelMap modelMap) throws CountryNotFoundException {
        modelMap.put("countries",simpleCountryDao.getSortedCountriesByPopulation());
        return "home";
    }

}
