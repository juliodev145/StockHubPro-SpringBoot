package com.stockhubpro_api.dto;

import jakarta.validation.constraints.*;

public record SignupRequest(

        // Dados da empresa
        @NotBlank @Size(max = 150) String nomeEmpresa,	//@NotBlank: nao pode vir vazio, não pode ser nulo e não pode conter apenas espacos
        @NotBlank @Size(max = 18) String cnpj,
        @NotBlank @Size(max = 9) String cep,
        @NotBlank @Size(max = 150) String logradouro,
        @NotBlank @Size(max = 10) String numero,
        @NotBlank @Size(max = 100) String bairro,
        @NotBlank @Size(max = 100) String cidade,
        @NotBlank @Size(max = 100) String estado,
        @NotBlank @Size(min = 2, max = 2) String uf,

        // Dados do responsável
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Email @Size(max = 150) String email,		//@Email: confere se tem cara de e-mail (algo@algo).
        @NotBlank @Size(min = 6) String senha
) {
	
}