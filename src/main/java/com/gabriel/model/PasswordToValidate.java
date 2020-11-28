package com.gabriel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordToValidate {

    @Schema(description = "Given password")
    String password;
    @Schema(description = "Field to indicate if was necessary to sent validation details")
    boolean detailed;
}
