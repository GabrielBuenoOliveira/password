package com.gabriel.controller;


import com.gabriel.model.PasswordToValidate;
import com.gabriel.service.ValidationService;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.inject.Inject;

@Controller("/password")
public class PasswordController {

    @Inject
    private ValidationService validationService;

    @Post
    @Operation(
            summary = "Check password",
            description = "Check password follows the minimum requirement of security.")
    @ApiResponse(responseCode = "200", description = "Password is valid")
    @ApiResponse(responseCode = "400", description = "Malformed request, missing body password field")
    public HttpResponse<?> checkPassword(@Body @Parameter(description = "Password to be checked") PasswordToValidate password) {
        if (StringUtils.isEmpty(password.getPassword())) {
            return HttpResponse.badRequest("Password must be given.");
        }
        return HttpResponse.ok(validationService.validatePassword(password));
    }
}
