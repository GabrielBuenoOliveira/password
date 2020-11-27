package com.gabriel.service.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpperCaseValidationTest {

    private static final UpperCaseValidation rule = new UpperCaseValidation();
    String password;

    @Test
    public void shouldFailWithAllUpperCaseLetters() {
        password = "TEST";
        assertTrue(rule.checkValidationRule(password));
    }

    @Test
    public void shouldFailWithAllLowerCaseLetters() {
        password = "test";
        assertFalse(rule.checkValidationRule(password));
    }

    @Test
    public void shouldPassWithOneUpperCase() {
        password = "teSt";
        assertTrue(rule.checkValidationRule(password));
    }

    @Test
    public void shouldPassWithMoreLowerCase() {
        password = "TesTasdLASKDJç";
        assertTrue(rule.checkValidationRule(password));
    }

    @Test
    public void shouldFailWithAllUpperCaseAccentedChars() {
        password = "ÀÈÌÒÙÁÉÍÓÚÝÂÊÎÔÛÃÑÕÄËÏÖÜÇÅ";
        boolean reduce = password.chars()
                .mapToObj(letter -> (char) letter)
                .map(letter -> rule.checkValidationRule(letter.toString()))
                .reduce(Boolean::logicalAnd)
                .orElse(false);

        assertTrue(reduce);
    }

    @Test
    public void shouldPassWithAllLowerCaseAccentedChars() {
        password = "àèìòùáéíóúýâêîôûãñõäëïöüÿçå";
        boolean reduce = password.chars()
                .mapToObj(letter -> (char) letter)
                .map(letter -> rule.checkValidationRule(letter.toString()))
                .reduce(Boolean::logicalAnd)
                .orElse(true);

        assertFalse(reduce);
    }

}