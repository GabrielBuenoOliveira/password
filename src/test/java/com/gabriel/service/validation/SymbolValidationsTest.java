package com.gabriel.service.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SymbolValidationsTest {

    private static final SymbolValidation rule = new SymbolValidation();
    String password;

    @Test
    public void shouldFailWithoutAnySymbol(){
        password = "test";
        assertFalse(rule.checkValidationRule(password));
    }

    @Test
    public void shouldPassWithAllSymbols(){
        password = "!@#$%^&*()-+";
        assertTrue(rule.checkValidationRule(password));
    }

    @Test
    public void shouldPassWithSomeSymbols(){
        password = "t!e@s^t&";
        assertTrue(rule.checkValidationRule(password));
    }

}