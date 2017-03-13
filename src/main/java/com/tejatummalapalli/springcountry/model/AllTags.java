package com.tejatummalapalli.springcountry.model;

import java.util.Map;
import java.util.TreeMap;

public class AllTags {
   private Map<String,Integer> allTagsWithCount = new TreeMap<>();

    public Map<String, Integer> getAllTagsWithCount() {
        return allTagsWithCount;
    }

    public void setAllTagsWithCount(Map<String,Integer> allTagsWithCount ) {
        this.allTagsWithCount = allTagsWithCount;
    }
}
