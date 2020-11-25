package com.gabriel.service;

import com.gabriel.model.PasswordResponse;
import com.gabriel.model.ValidationResult;
import com.gabriel.service.validation.MinLengthValidation;
import com.gabriel.service.validation.ValidationRule;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ValidationService {

    private static final List<ValidationRule> validations = List.of(
            new MinLengthValidation()
    );

    public PasswordResponse validatePassword(String password) {
        List<ValidationResult> validationResults = applyValidations(password);
        return PasswordResponse.builder()
                .validations(validationResults)
                .build();

    }

    private List<ValidationResult> applyValidations(String password) {
        return validations.stream()
                .map(validationRule -> ValidationResult.builder()
                        .name(validationRule.getValidationName())
                        .status(validationRule.checkValidationRule(password))
                        .build())
                .collect(Collectors.toList());
    }
}
