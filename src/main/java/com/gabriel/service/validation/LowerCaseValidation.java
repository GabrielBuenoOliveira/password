package com.gabriel.service.validation;

import java.util.regex.Pattern;

public class LowerCaseValidation extends ValidationRule{

    private static final Pattern PATTERN = Pattern.compile("([a-z\\u00E0-\\u00FF])");
    public static final String LOWER_CASE_VALIDATION = "Contain unless one lower case letter";

    public LowerCaseValidation() {
        super(LOWER_CASE_VALIDATION, PATTERN);
    }
}
