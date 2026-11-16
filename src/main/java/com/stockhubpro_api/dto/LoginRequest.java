package com.stockhubpro_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(		//formulário de login
        @NotBlank @Email String email,
        @NotBlank String senha
) {
}