package com.gabriel.controller;


import com.gabriel.model.Password;
import com.gabriel.model.PasswordResponse;
import com.gabriel.service.ValidationService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;

import javax.inject.Inject;

@Controller("/password")
public class PasswordController {

    @Inject
    private ValidationService validationService;

    @Post
    @Operation(
            summary = "Check password",
            description = "Check given password attends to our minimum security requirements."
    )
    public PasswordResponse checkPassword(@Body Password password){
        return validationService.validatePassword(password);
    }
}
