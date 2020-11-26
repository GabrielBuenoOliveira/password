package com.gabriel.service.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WithoutRepeatedCharValidationTest {

    private static final WithoutRepeatedCharValidation rule = new WithoutRepeatedCharValidation();
    String password;

    @Test
    public void shouldFailWithNullPassword() {
        assertFalse(rule.checkValidationRule(null));
    }

    @Test
    public void shouldFailWithDuplicateLowerCaseLetter(){
        password = "test";
        assertFalse(rule.checkValidationRule(password));
    }

    @Test
    public void shouldFailWithDuplicateUpperCaseLetter(){
        password = "TesT";
        assertFalse(rule.checkValidationRule(password));
    }

    @Test
    public void shouldFailWithDuplicateSymbol(){
        password = "T!es!T";
        assertFalse(rule.checkValidationRule(password));
    }

    @Test
    public void shouldPassWithoutAnyDuplicateChar() {
        password = "expurgados";
        assertTrue(rule.checkValidationRule(password));
    }

    @Test
    public void shouldPassWithDuplicateCharInDifferentCase() {
        password = "Test";
        assertTrue(rule.checkValidationRule(password));
    }

    @Test
    public void shoudPassWithDuplicateCharWithDifferentAccents(){
        password = "aáãàAÁÃÀ";
        assertTrue(rule.checkValidationRule(password));
    }
}