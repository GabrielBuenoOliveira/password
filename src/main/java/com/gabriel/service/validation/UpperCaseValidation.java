package com.gabriel.service.validation;

import java.util.regex.Pattern;

public class UpperCaseValidation extends ValidationRule {

    private static final Pattern PATTERN = Pattern.compile("([A-Z\\u00C0-\\u00DD])");
    public static final String UPPER_CASE_VALIDATION = "Contain unless one upper case letter";

    public UpperCaseValidation() {
        super(UPPER_CASE_VALIDATION, PATTERN);
    }
}
