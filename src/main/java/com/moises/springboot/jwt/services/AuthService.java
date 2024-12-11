package com.moises.springboot.jwt.services;

import com.moises.springboot.jwt.models.dto.AuthDTO;
import com.moises.springboot.jwt.models.dto.LoginDTO;
import com.moises.springboot.jwt.models.dto.RegisterDTO;

/**
 * Interfaz que define los servicios de autenticación en el sistema.
 */
public interface AuthService {

    AuthDTO login(LoginDTO login) throws Exception;

    AuthDTO register(RegisterDTO register) throws Exception;

}
