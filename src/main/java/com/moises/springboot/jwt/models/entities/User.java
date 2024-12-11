package com.moises.springboot.jwt.models.entities;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.moises.springboot.jwt.models.enums.UserRole;

/**
 * Entidad que representa a un usuario en el sistema.
 */
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles", nullable = false, unique = true)
    private Set<UserRole> roles;

    public User() {
    }

    public User(Long id, String name, String lastName, String username, String password, Set<UserRole> roles) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setUsername(String username) {
        this.username = username;
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

    /**
     * Obtiene los roles asignados al usuario.
     * @return Una lista de los roles asignados al usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene el nombre de usuario del usuario.
     * @return El nombre de usuario del usuario.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Indica si la cuenta del usuario ha expirado.
     * @return true si la cuenta no ha expirado, false si ha expirado.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta del usuario está bloqueada o no.
     * @return true si la cuenta no está bloqueada, false si está bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales del usuario han expirado.
     * @return true si las credenciales no han expirado, false si han expirado.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado o no.
     * @return true si el usuario está habilitado, false si no está habilitado.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
