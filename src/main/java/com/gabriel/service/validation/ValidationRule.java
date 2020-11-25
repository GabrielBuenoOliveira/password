package com.gabriel.service.validation;

import com.gabriel.model.ValidationStatus;

public abstract class ValidationRule {

    private String validationName;

    ValidationRule(String validationName){
        this.validationName = validationName;
    }

    public ValidationStatus checkValidationRule(String password){
        return null;
    }

    public String getValidationName() {
        return validationName;
    }

}
