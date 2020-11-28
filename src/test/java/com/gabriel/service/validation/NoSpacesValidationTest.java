package com.gabriel.service.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoSpacesValidationTest {

    private static final NoSpacesValidation rule = new NoSpacesValidation();
    String password;

    @Test
    public void shouldFailWithNullPassword(){
        assertFalse(rule.checkValidationRule(null));
    }

    @Test
    public void shouldFailWithOneSpace() {
        password = " ";
        assertFalse(rule.checkValidationRule(password));
    }

    @Test
    public void shouldFailWithMultipleSpaces() {
        password = "T e s t";
        assertFalse(rule.checkValidationRule(password));
    }

    @Test
    public void shouldPassWithoutAnySpace() {
        password = "Test";
        assertTrue(rule.checkValidationRule(password));
    }

}