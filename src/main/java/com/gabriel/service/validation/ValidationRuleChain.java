package com.gabriel.service.validation;

import javax.inject.Singleton;

public interface ValidationRuleChain {

    public String getValidationName();
    public boolean checkRule(String password);
    public ValidationRuleChain nextValidation();

}