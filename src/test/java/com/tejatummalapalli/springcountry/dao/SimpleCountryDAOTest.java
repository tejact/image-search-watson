package com.tejatummalapalli.springcountry.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Teja on 11/15/2016.
 */
public class SimpleCountryDAOTest {
    SimpleCountryDAO simpleDAO;
    @Before
    public void setUp() throws Exception {
         simpleDAO = new SimpleCountryDAO();
    }

    @After
    public void tearDown() throws Exception {

    }

    //TODO : Change the test
    @Test
    public void findCountryByName() throws Exception {
        assertEquals("India",simpleDAO.findCountryByName("India").getCountryName());
    }

}