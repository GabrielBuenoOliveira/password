package com.gabriel;

import com.gabriel.model.PasswordResponse;
import com.gabriel.model.PasswordToValidate;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class PasswordTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/password")
    HttpClient client;

    @Test
    public void shouldPassCheckingValidPasswordWithDetails() {
        MutableHttpRequest<PasswordToValidate> post = HttpRequest.POST("", PasswordToValidate.builder()
                .password("AbTp9!fok")
                .detailed(true)
                .build());

        HttpResponse<PasswordResponse> response = client.toBlocking()
                .exchange(post, PasswordResponse.class);

        assertEquals(HttpStatus.OK.getCode(), response.code());
        assertTrue(Objects.requireNonNull(response.body()).isValid());
        assertNotNull(Objects.requireNonNull(response.body()).getValidations());
    }

    @Test
    public void shouldPassCheckingValidPasswordEndpoint() {
        MutableHttpRequest<PasswordToValidate> post = HttpRequest.POST("", PasswordToValidate.builder()
                .password("AbTp9!fok")
                .build());

        HttpResponse<PasswordResponse> response = client.toBlocking()
                .exchange(post, PasswordResponse.class);

        assertEquals(HttpStatus.OK.getCode(), response.code());
        assertTrue(Objects.requireNonNull(response.body()).isValid());
        assertNull(response.body().getValidations());

    }

    @Test
    public void shouldFailCheckingValidPasswordEndpointWithDetails() {
        MutableHttpRequest<PasswordToValidate> post = HttpRequest.POST("", PasswordToValidate.builder()
                .password("AbTp9!")
                .detailed(true)
                .build());

        HttpResponse<PasswordResponse> response = client.toBlocking()
                .exchange(post, PasswordResponse.class);

        assertEquals(HttpStatus.OK.getCode(), response.code());
        assertFalse(Objects.requireNonNull(response.body()).isValid());
        assertNotNull(response.body().getValidations());

    }

    @Test
    public void shouldFailCheckingValidPasswordEndpoint() {
        MutableHttpRequest<PasswordToValidate> post = HttpRequest.POST("", PasswordToValidate.builder()
                .password("AbTp9!")
                .build());

        HttpResponse<PasswordResponse> response = client.toBlocking()
                .exchange(post, PasswordResponse.class);

        assertEquals(HttpStatus.OK.getCode(), response.code());
        assertFalse(Objects.requireNonNull(response.body()).isValid());
        assertNull(response.body().getValidations());

    }

}
