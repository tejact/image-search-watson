package com.tejatummalapalli.springcountry.controller;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import com.tejatummalapalli.springcountry.dao.CountryDao;
import com.tejatummalapalli.springcountry.exception.CountryNotFoundException;
import com.tejatummalapalli.springcountry.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class countryController {

    @Autowired
    CountryDao simpleCountryDao;

    VisualRecognition visualRecogservice = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);

   // CountryService countryService = new CountryService();


    @RequestMapping("/tag-images")
    public String tagAllImages(ModelMap modelMap) {
        tagAllImages();
        List<Country> allCountries = simpleCountryDao.getAllCountries();
        modelMap.put("countries",allCountries);
        return "home";
    }

    @RequestMapping("/search-images")
    public String searchImages(ModelMap modelMap,@RequestParam("search-keyword") String searchKeyword) {
        List<Country> matchedCountries = simpleCountryDao.getMatchedCountries(searchKeyword);
        modelMap.put("countries",matchedCountries);
        return "home";
    }

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

    public void tagAllImages(){
        List<Country> allCountries =  simpleCountryDao.getAllCountries();
        Map<String,Integer> allTagsWithCount = new TreeMap<>();
        for(Country country : allCountries) {
            tagImage(country);
        }
    }

    private void tagImage(Country country) {

        String imageName = country.getFlagImageName();
        String imageFile = new String(imageName);

        visualRecogservice.setApiKey("cf8ff9af4fd5323e190b6df6b730ab4919464c73");
        System.out.println("Classify an image");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("static/images/"+imageName).getFile());

      /*  ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
                .images(new File("/resources/images/"+imageName))
                .build();*/

        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
                .images(file)
                .build();

        VisualClassification result = visualRecogservice.classify(options).execute();
        //System.out.println("The result is "+result);
        List<String> allClasses = new ArrayList<>();
        List<VisualClassifier.VisualClass> visualClasses = result.getImages().get(0).getClassifiers().get(0).getClasses();
        for( VisualClassifier.VisualClass visualClass : visualClasses ) {
            String className = visualClass.getName();
            allClasses.add(className);
        }
        country.setClassifiers(allClasses);
        System.out.println(allClasses.toString());
    }

}
