package com.gabriel.service.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinLengthValidationTest {

    private static final MinLengthValidation rule = new MinLengthValidation();
    String password;

    @Test
    public void shouldFailWithNullPassword(){
        assertFalse(rule.checkValidationRule(null));
    }

    @Test
    public void shouldFailWithPasswordContainingLessThanMinLength(){
        password = "12345";
        assertFalse(rule.checkValidationRule(password));
    }

    @Test
    public void shouldFailWithPasswordContainingExactlyMinLength(){
        password = "12345678";
        assertFalse(rule.checkValidationRule(password));
    }

    @Test
    public void shouldPassWithPasswordContainingMoreThanMinLength(){
        password = "123456789";
        assertTrue(rule.checkValidationRule(password));
    }

}