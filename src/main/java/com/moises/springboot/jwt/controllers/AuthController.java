package com.moises.springboot.jwt.controllers;

import com.moises.springboot.jwt.utils.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moises.springboot.jwt.models.dto.LoginDTO;
import com.moises.springboot.jwt.models.dto.RegisterDTO;
import com.moises.springboot.jwt.services.AuthService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService service;
    private final ValidationUtil validationUtil;

    public AuthController(AuthService service, ValidationUtil validationUtil) {
        this.service = service;
        this.validationUtil = validationUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto, BindingResult result) {

        if (result.hasFieldErrors()) {
            return validationUtil.validation(result);
        }

        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.login(dto));
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"Invalid credentials\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO dto, BindingResult result) {

        if (result.hasFieldErrors()) {
            return validationUtil.validation(result);
        }

        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.register(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Error registering user\"}");
        }
    }

}
