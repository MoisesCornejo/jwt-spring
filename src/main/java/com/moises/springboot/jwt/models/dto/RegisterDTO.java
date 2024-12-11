package com.moises.springboot.jwt.models.dto;

import com.moises.springboot.jwt.models.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

/**
 * DTO (Data Transfer Object) para almacenar la información necesaria para registrar un nuevo usuario.
 */
public class RegisterDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 4, max = 12)
    private String password;

    @NotNull
    private Set<UserRole> roles;

    public RegisterDTO() {
    }

    public RegisterDTO(String name, String lastName, String username, String password, Set<UserRole> roles) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}
