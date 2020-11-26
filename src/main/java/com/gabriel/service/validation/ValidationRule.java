package com.gabriel.service.validation;

import java.util.Objects;
import java.util.regex.Pattern;

public abstract class ValidationRule {

    private String validationName;
    private Pattern pattern;

    protected ValidationRule(String validationName) {
        this.validationName = validationName;
    }

    protected ValidationRule(String validationName, Pattern pattern){
        this.pattern = pattern;
        this.validationName = validationName;
    }

    public Boolean checkValidationRule(String password) {
        if (Objects.nonNull(pattern)){
            return pattern.matcher(password).find();
        }
        return true;
    }

    public String getValidationName() {
        return validationName;
    }

}
