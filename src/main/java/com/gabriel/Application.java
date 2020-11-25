package com.gabriel;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Password Validation API",
                version = "0.1",
                description = "",
                contact = @Contact(name = "Gabriel Bueno")))
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
