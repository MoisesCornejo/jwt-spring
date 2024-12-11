package com.moises.springboot.jwt.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moises.springboot.jwt.models.dto.AuthDTO;
import com.moises.springboot.jwt.models.dto.LoginDTO;
import com.moises.springboot.jwt.models.dto.RegisterDTO;
import com.moises.springboot.jwt.models.entities.User;
import com.moises.springboot.jwt.repositories.UserRepository;
import com.moises.springboot.jwt.utils.JwtUtil;
import com.moises.springboot.jwt.services.AuthService;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementación del servicio de autenticación.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Realiza la autenticación de un usuario.
     * @param login Los datos de inicio de sesión del usuario.
     * @return Un objeto AuthDTO que contiene el token de autenticación.
     * @throws Exception Si ocurre un error durante el proceso de autenticación.
     */
    @Transactional(readOnly = true)
    @Override
    public AuthDTO login(LoginDTO login) throws Exception {
        try {

            authenticate(login.getUsername(), login.getPassword());

            User user = userRepository.findByUsername(login.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String token = jwtUtil.generateToken(user);
            return new AuthDTO(token);

        } catch (BadCredentialsException | UsernameNotFoundException e) {

            System.out.println(e.getMessage());
            throw new BadCredentialsException("Incorrect username or password");

        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
            
        }
    }

    /**
     * Registra un nuevo usuario.
     * @param register Los datos de registro del nuevo usuario.
     * @return Un objeto AuthDTO que contiene el token de autenticación.
     * @throws Exception Si ocurre un error durante el proceso de registro.
     */
    @Transactional
    @Override
    public AuthDTO register(RegisterDTO register) throws Exception {
        try {
            User user = createUserFromRegistration(register);
            user = userRepository.save(user);

            String token = jwtUtil.generateToken(user);
            return new AuthDTO(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }  
    }

    /**
     * Autentica al usuario utilizando el gestor de autenticación.
     * @param username El nombre de usuario del usuario.
     * @param password La contraseña del usuario.
     */
    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    /**
     * Crea un nuevo objeto de usuario a partir de los datos de registro.
     * @param register Los datos de registro del nuevo usuario.
     * @return El usuario creado.
     */
    private User createUserFromRegistration(RegisterDTO register) {
        User user = new User();
        user.setName(register.getName());
        user.setLastName(register.getLastName());
        user.setUsername(register.getUsername());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setRoles(register.getRoles());

        return user;
    }
}
