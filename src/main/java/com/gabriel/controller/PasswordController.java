package com.gabriel.controller;


import com.gabriel.model.Password;
import com.gabriel.model.PasswordResponse;
import com.gabriel.service.ValidationService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;

@Controller("/password")
public class PasswordController {

    @Inject
    private ValidationService validationService;

    @Post
    public PasswordResponse checkPassword(@Body Password password){
        return validationService.validatePassword(password.getPassword());
    }
}
