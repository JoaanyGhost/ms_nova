package com.msnova.backend.application.usecase.auth;

import com.msnova.backend.application.exception.user.InvalidCredentialsException;
import com.msnova.backend.domain.entity.user.User;
import com.msnova.backend.domain.repository.UserRepository;
import com.msnova.backend.infrastructure.security.JwtService;
import com.msnova.backend.presentation.dto.auth.LoginResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse execute(
            String email,
            String password
    ) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid credentials"
                        )
                );

        if (!user.isActive()) {
            throw new InvalidCredentialsException(
                    "Invalid credentials"
            );
        }

        if (!passwordEncoder.matches(
                password,
                user.getPassword()
        )) {
            throw new InvalidCredentialsException(
                    "Invalid credentials"
            );
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}