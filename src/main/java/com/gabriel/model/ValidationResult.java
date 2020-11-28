package com.gabriel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResult {

    @Schema(description = "Validation Name")
    private String name;
    @Schema(description = "Validation result")
    private ValidationStatus status;
}
