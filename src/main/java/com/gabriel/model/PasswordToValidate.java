package com.gabriel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordToValidate {

    @Schema(description = "Given password")
    private String password;
    @Schema(description = "Field to indicate if was necessary to sent validation details")
    private boolean detailed;
}
