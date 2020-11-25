package com.gabriel.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PasswordResponse {

    private boolean isValid;
    private List<ValidationResult> validations;
}
