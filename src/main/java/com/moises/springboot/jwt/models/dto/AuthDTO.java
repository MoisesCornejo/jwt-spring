package com.moises.springboot.jwt.models.dto;

/**
 * DTO (Data Transfer Object) que representa la autenticación con un token.
 */
public class AuthDTO {
    private String token;

    public AuthDTO() {
    }

    public AuthDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
