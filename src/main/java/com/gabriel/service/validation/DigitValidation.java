package com.gabriel.service.validation;

import java.util.regex.Pattern;

public class DigitValidation extends ValidationRule {
    public static final Pattern PATTERN = Pattern.compile("(\\d)");
    public static final String DIGIT_VALIDATION = "Contain unless one number";

    public DigitValidation() {
        super(DIGIT_VALIDATION, PATTERN);
    }
}
