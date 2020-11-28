package com.gabriel.service.validation;

import java.util.regex.Pattern;

public class SymbolValidation extends ValidationRule{

    private static final Pattern PATTERN = Pattern.compile("([!@#$%^&*()-+])");
    public static final String SYMBOL_VALIDATION = "Contain unless one symbol (!@#$%^&*()-+)";

    public SymbolValidation() {
        super(SYMBOL_VALIDATION, PATTERN);
    }
}
