package com.gabriel.service;

import com.gabriel.model.Password;
import com.gabriel.model.PasswordResponse;
import com.gabriel.model.ValidationResult;
import com.gabriel.model.ValidationStatus;
import com.gabriel.service.validation.MinLengthValidation;
import com.gabriel.service.validation.ValidationRule;
import com.gabriel.service.validation.WithoutRepeatedCharValidation;

import javax.inject.Singleton;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Singleton
public class ValidationService {

    private static final List<ValidationRule> validations = List.of(
            new MinLengthValidation(),
            new WithoutRepeatedCharValidation()
    );

    public PasswordResponse validatePassword(Password password) {
        if(password.isDetailed()) {
            List<ValidationResult> validationResults = applyValidationsDetailed(password.getPassword());
            return PasswordResponse.builder()
                    .isValid(validationResults.stream()
                            .map(ValidationResult::getStatus)
                            .allMatch(ValidationStatus.SUCCESS::equals))
                    .validations(validationResults)
                    .build();
        }
        return PasswordResponse.builder()
                .isValid(applyValidations(password.getPassword()))
                .build();
    }

    private boolean applyValidations(String password) {
        return validations.stream()
                .map(validationRule -> validationRule.checkValidationRule(password))
                .filter(Predicate.not(Boolean::booleanValue))
                .findFirst()
                .orElse(true);
    }

    private List<ValidationResult> applyValidationsDetailed(String password) {
        return validations.stream()
                .map(validationRule -> ValidationResult.builder()
                        .name(validationRule.getValidationName())
                        .status(validationRule.checkValidationRule(password) ? ValidationStatus.SUCCESS : ValidationStatus.FAILED)
                        .build())
                .collect(Collectors.toList());
    }
}
