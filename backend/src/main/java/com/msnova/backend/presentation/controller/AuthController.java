package com.msnova.backend.presentation.controller;

import com.msnova.backend.application.usecase.auth.LoginUseCase;
import com.msnova.backend.presentation.dto.auth.LoginRequest;
import com.msnova.backend.presentation.dto.auth.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {

        LoginResponse response = loginUseCase.execute(
                request.email(),
                request.password()
        );

        return ResponseEntity.ok(response);
    }
}