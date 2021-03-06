package com.gabriel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PasswordResponse {

    @Schema(description = "Represents if password attends at minimum security requirements")
    private boolean isValid;
    @Schema(description = "List of validations applied to given password")
    private List<ValidationResult> validations;
}
