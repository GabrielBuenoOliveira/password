package com.gabriel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Password {

    @Schema(description = "Given password")
    String password;
    @Schema(description = "Field to indicate if was necessary to sent validation details")
    boolean detailed;
}
