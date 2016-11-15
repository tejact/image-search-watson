package com.tejatummalapalli.springcountry.controller;

import com.tejatummalapalli.springcountry.dao.CountryDao;
import com.tejatummalapalli.springcountry.dao.SimpleCountryDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class countryController {

    CountryDao simpleCountryDao = new SimpleCountryDAO();

    @RequestMapping("/countries")
    public String getMainPage(ModelMap modelMap){
        modelMap.put("countries",simpleCountryDao.getAllCountries());
        return "home";
    }
}
