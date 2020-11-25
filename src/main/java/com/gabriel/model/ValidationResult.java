package com.gabriel.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResult {

    private String name;
    private ValidationStatus status;
}
