package com.gabriel.service.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitValidationTest {
    private static final DigitValidation rule = new DigitValidation();
    String password;

    @Test
    public void shouldFailWithoutAnyNumber(){
        password = "test";
        assertFalse(rule.checkValidationRule(password));
    }

    @Test
    public void shouldPassWithSomeNumbers(){
        password = "tes3t1";
        assertTrue(rule.checkValidationRule(password));
    }

    @Test
    public void shouldPassContainingOnlyNumbers(){
        password = "1234567890";
        assertTrue(rule.checkValidationRule(password));
    }
}