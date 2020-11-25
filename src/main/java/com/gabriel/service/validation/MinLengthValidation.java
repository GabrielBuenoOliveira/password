package com.gabriel.service.validation;

import com.gabriel.model.ValidationStatus;

public class MinLengthValidation extends ValidationRule {

    private static final String MIN_LENGTH_VALIDATION = "Minimum length validation";

    public MinLengthValidation() {
        super(MIN_LENGTH_VALIDATION);
    }

    @Override
    public ValidationStatus checkValidationRule(String password) {

        return password.length() > 8 ? ValidationStatus.SUCCESS : ValidationStatus.FAILED;
    }
}
