package com.tejatummalapalli.springcountry.service;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import com.tejatummalapalli.springcountry.dao.CountryDao;
import com.tejatummalapalli.springcountry.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountryService {
    @Autowired
    CountryDao simpleCountryDao;


    VisualRecognition visualRecogservice = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);

    public void tagAllImages(){
        List<Country> allCountries =  simpleCountryDao.getAllCountries();
        for(Country country : allCountries) {
            tagImage(country);
        }
    }

    private void tagImage(Country country) {
        String imageName = country.getFlagImageName();
        String imageFile = new String(imageName);
        visualRecogservice.setApiKey("cf8ff9af4fd5323e190b6df6b730ab4919464c73");
        System.out.println("Classify an image");
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
                .images(new File("C:\\Users\\Teja\\Desktop\\TreeHouseTD\\Project5\\src\\main\\resources\\static\\images\\"+imageName))
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
