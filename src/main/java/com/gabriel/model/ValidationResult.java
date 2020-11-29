package com.gabriel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResult {

    @Schema(description = "Validation Name")
    private String name;
    @Schema(description = "Validation result")
    private ValidationStatus status;
}
