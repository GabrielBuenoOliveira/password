package com.gabriel.service;

import com.gabriel.model.PasswordResponse;
import com.gabriel.model.PasswordToValidate;
import com.gabriel.model.ValidationResult;
import com.gabriel.model.ValidationStatus;
import com.gabriel.service.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {

    public static final ValidationService service = new ValidationService();
    PasswordToValidate password;

    @BeforeEach
    public void setup() {
        password = null;
    }

    @Test
    public void shouldFailWithEmptyPassword() {
        password = PasswordToValidate.builder()
                .password("")
                .detailed(true)
                .build();

        Set<String> shouldFail = Set.of(
                DigitValidation.DIGIT_VALIDATION,
                LowerCaseValidation.LOWER_CASE_VALIDATION,
                MinLengthValidation.MIN_LENGTH_VALIDATION,
                UpperCaseValidation.UPPER_CASE_VALIDATION,
                SymbolValidation.SYMBOL_VALIDATION
        );

        assertEquals(generateExpectedResponse(false, shouldFail), service.validatePassword(password));
        password.setDetailed(false);
        assertFalse(service.validatePassword(password).isValid());
    }

    @Test
    public void shouldFailWithoutLowerCaseChar() {
        password = PasswordToValidate.builder()
                .password("ABCD!1234")
                .detailed(true)
                .build();

        Set<String> shouldFail = Set.of(
                LowerCaseValidation.LOWER_CASE_VALIDATION
        );

        assertEquals(generateExpectedResponse(false, shouldFail), service.validatePassword(password));
        password.setDetailed(false);
        assertFalse(service.validatePassword(password).isValid());
    }

    @Test
    public void shouldFailWithoutUpperCaseChar() {
        password = PasswordToValidate.builder()
                .password("abcd!1234")
                .detailed(true)
                .build();

        Set<String> shouldFail = Set.of(
                UpperCaseValidation.UPPER_CASE_VALIDATION
        );

        assertEquals(generateExpectedResponse(false, shouldFail), service.validatePassword(password));
        password.setDetailed(false);
        assertFalse(service.validatePassword(password).isValid());
    }

    @Test
    public void shouldFailWithoutSymbolChar() {
        password = PasswordToValidate.builder()
                .password("ABcd12345")
                .detailed(true)
                .build();
        Set<String> shouldFail = Set.of(
                SymbolValidation.SYMBOL_VALIDATION
        );

        assertEquals(generateExpectedResponse(false, shouldFail), service.validatePassword(password));
        password.setDetailed(false);
        assertFalse(service.validatePassword(password).isValid());
    }

    @Test
    public void shouldFailWithoutDigit() {
        password = PasswordToValidate.builder()
                .password("ABcd!@#$Te")
                .detailed(true)
                .build();
        Set<String> shouldFail = Set.of(
                DigitValidation.DIGIT_VALIDATION
        );

        assertEquals(generateExpectedResponse(false, shouldFail), service.validatePassword(password));
        password.setDetailed(false);
        assertFalse(service.validatePassword(password).isValid());
    }

    @Test
    public void shouldFailWithRepeatedChar() {
        password = PasswordToValidate.builder()
                .password("ABcd!1Ted")
                .detailed(true)
                .build();
        Set<String> shouldFail = Set.of(
              WithoutRepeatedCharValidation.WITHOUT_REPEATED_CHAR
        );

        assertEquals(generateExpectedResponse(false, shouldFail), service.validatePassword(password));
        password.setDetailed(false);
        assertFalse(service.validatePassword(password).isValid());
    }

    @Test
    public void shouldFailWithSpaceChar() {
        password = PasswordToValidate.builder()
                .password("ABcd !23$Te")
                .detailed(true)
                .build();
        Set<String> shouldFail = Set.of(
                NoSpacesValidation.SPACES_ARE_NOT_ALLOWED
        );

        assertEquals(generateExpectedResponse(false, shouldFail), service.validatePassword(password));
        password.setDetailed(false);
        assertFalse(service.validatePassword(password).isValid());
    }

    @Test
    public void shouldPassWithValid() {
        password = PasswordToValidate.builder()
                .password("AbTp9!fok")
                .detailed(true)
                .build();

        assertEquals(generateExpectedResponse(true), service.validatePassword(password));
        password.setDetailed(false);
        assertTrue(service.validatePassword(password).isValid());
    }

    private PasswordResponse generateExpectedResponse(boolean isValid, Set<String> failedStatus) {
        return PasswordResponse.builder()
                .isValid(isValid)
                .validations(
                        ValidationService.getValidations().stream()
                                .map(validation -> ValidationResult.builder()
                                        .name(validation.getValidationName())
                                        .status(failedStatus.contains(validation.getValidationName()) ?
                                                ValidationStatus.FAILED : ValidationStatus.SUCCESS)
                                        .build())
                                .collect(Collectors.toList()))
                .build();
    }

    private PasswordResponse generateExpectedResponse(boolean isValid) {
        return generateExpectedResponse(isValid, new HashSet<>());
    }
}