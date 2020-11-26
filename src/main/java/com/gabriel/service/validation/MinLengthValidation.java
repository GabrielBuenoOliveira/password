package com.gabriel.service.validation;

import io.micronaut.core.util.StringUtils;

public class MinLengthValidation extends ValidationRule {

    private static final String MIN_LENGTH_VALIDATION = "Minimum length validation";
    protected static final int MIN_LENGTH = 8;

    public MinLengthValidation() {
        super(MIN_LENGTH_VALIDATION);
    }

    @Override
    public Boolean checkValidationRule(String password) {
        return StringUtils.isNotEmpty(password) && password.length() > MIN_LENGTH;
    }
}
