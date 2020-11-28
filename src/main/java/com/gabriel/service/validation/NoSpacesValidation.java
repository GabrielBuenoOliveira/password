package com.gabriel.service.validation;

import java.util.Objects;

public class NoSpacesValidation extends ValidationRule {

    public static final String SPACES_ARE_NOT_ALLOWED = "Space char is not allowed.";

    public NoSpacesValidation() {
        super(SPACES_ARE_NOT_ALLOWED);
    }

    @Override
    public Boolean checkValidationRule(String password) {
        if(Objects.isNull(password))
            return false;
        return password.chars()
                .mapToObj(letter -> (char) letter)
                .noneMatch(Character::isSpaceChar);
    }
}