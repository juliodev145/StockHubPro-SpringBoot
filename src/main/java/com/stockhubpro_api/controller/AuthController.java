package com.stockhubpro_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockhubpro_api.dto.LoginRequest;
import com.stockhubpro_api.dto.LoginResponse;
import com.stockhubpro_api.dto.SignupRequest;
import com.stockhubpro_api.service.AuthService;

import jakarta.validation.Valid;

@RestController // vai responder a requisições web e retornar os dados diretamente no corpo da
				// resposta (JSON).
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService; // injetando a dependencia

	public AuthController(AuthService authService) { // Injeção de Dependência, entregando automaticamente uma instância
														// pronta do AuthService quando a aplicação inicia.

		this.authService = authService;
	}

	@PostMapping("/signup") // Mapeia este método para responder a requisições do tipo POST na URL completa
							// /auth/signup. O método POST é para criar novos recursos.
	public ResponseEntity<Void> signup(@RequestBody @Valid SignupRequest dados) { // @Valid: confere as regras do
																					// formulário
		authService.signup(dados);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest dados) {
		return ResponseEntity.ok(authService.login(dados));
	}

}
/*
 * @RequestBody: Avisa ao Spring para pegar o JSON enviado no corpo da
 * requisição HTTP e transformá-lo no objeto Java SignupRequest.
 * authService.signup(dados);: Repassa os dados já validados para a camada de
 * serviço executar o cadastro. return
 * ResponseEntity.status(HttpStatus.CREATED).build();: Retorna uma resposta HTTP
 * com o status 201 (Created), que é o padrão da web para informar que um novo
 * registro foi criado com sucesso, sem enviar nenhum dado extra no corpo
 * (.build()).
 */
