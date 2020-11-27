package com.gabriel.service.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LowerCaseValidationTest {

    private static final LowerCaseValidation rule = new LowerCaseValidation();
    String password;

    @Test
    public void shouldFailWithAllUpperCaseLetters() {
        password = "TEST";
        assertFalse(rule.checkValidationRule(password));
    }

    @Test
    public void shouldPassWithOneLowerCase() {
        password = "TEsT";
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
                .orElse(true);

        assertFalse(reduce);
    }

    @Test
    public void shouldPassWithAllLowerCaseAccentedChars() {
        password = "àèìòùáéíóúýâêîôûãñõäëïöüÿçå";
        boolean reduce = password.chars()
                .mapToObj(letter -> (char) letter)
                .map(letter -> rule.checkValidationRule(letter.toString()))
                .reduce(Boolean::logicalAnd)
                .orElse(false);

        assertTrue(reduce);
    }

}