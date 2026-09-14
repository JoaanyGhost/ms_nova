package com.msnova.backend.presentation.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}