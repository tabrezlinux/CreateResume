package com.example.tabrezahmad.createresume.database;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Me on 19-Jul-18.
 */
public class FormValidatorTest {
    @Test
    public void isGenderValid() throws Exception {


    }

    @Test
    public void isMaritalStatusValid() throws Exception {
    }

    @Test
    public void isAlpha() throws Exception {

        String input = "www";
        Boolean actual;
        Boolean expected = true;

        actual = FormValidator.isAlpha(input);

        assertEquals(expected,actual);

    }

    @Test
    public void isNumber() throws Exception {
    }

    @Test
    public void isEmailAddress() throws Exception {

        String[] input = {"1123@gmail.com","aa@gmail.com",".12@gmail.com"};
        Boolean actual;
        Boolean expected = true;

        actual = FormValidator.isEmailAddress(input[1]);

        assertEquals(expected,actual);

    }

    @Test
    public void isWebUrl() throws Exception {
    }

}