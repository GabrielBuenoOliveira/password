package com.gabriel.service.validation;

import io.micronaut.core.util.StringUtils;

import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WithoutRepeatedCharValidation extends ValidationRule {

    public static final String WITHOUT_REPEATED_CHAR = "Not contains repeated char";

    public WithoutRepeatedCharValidation() {
        super(WITHOUT_REPEATED_CHAR);
    }

    @Override
    public Boolean checkValidationRule(String password) {
        if(Objects.isNull(password))
            return false;

        Set<Character> characters = password.chars()
                .mapToObj(letter -> (char) letter)
                .collect(Collectors.toSet());
        return characters.size() == password.length();
    }
}
