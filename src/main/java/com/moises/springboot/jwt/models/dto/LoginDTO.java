package com.moises.springboot.jwt.models.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) para almacenar las credenciales de inicio de sesión.
 */
public class LoginDTO {

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 4, max = 12)
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
