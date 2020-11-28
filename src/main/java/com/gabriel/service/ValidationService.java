package com.gabriel.service;

import com.gabriel.model.PasswordToValidate;
import com.gabriel.model.PasswordResponse;
import com.gabriel.model.ValidationResult;
import com.gabriel.model.ValidationStatus;
import com.gabriel.service.validation.*;
import lombok.Getter;

import javax.inject.Singleton;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Singleton
public class ValidationService {

    @Getter
    private static final List<ValidationRule> validations = List.of(
            new MinLengthValidation(),
            new NoSpacesValidation(),
            new WithoutRepeatedCharValidation(),
            new UpperCaseValidation(),
            new LowerCaseValidation(),
            new SymbolValidation(),
            new DigitValidation()
    );

    public PasswordResponse validatePassword(PasswordToValidate password) {
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
