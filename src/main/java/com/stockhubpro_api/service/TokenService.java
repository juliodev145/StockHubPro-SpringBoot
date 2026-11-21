package com.stockhubpro_api.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.stockhubpro_api.model.User;

@Service
public class TokenService {

	private static final String EMISSOR = "stockhubpro-api";
	
	@Value("${api.security.token.secret}")	//busca a chave secreta no application.properties, que busca na variável JWT_SECRET
	private String secret;
	
	public String gerarToken(User usuario) {
		Algorithm algoritmo = Algorithm.HMAC256(secret);
		return JWT.create()
				.withIssuer(EMISSOR) //quem emitiu o crachá, como o nome do prédio impresso nele.
				.withSubject(usuario.getEmail()) //de quem é o crachá (o e-mail).
				.withClaim("empresaId", usuario.getEmpresa().getId()) //o número da loja impresso no crachá. É daqui que todas as buscas vão tirar a empresa.
				.withExpiresAt(Instant.now().plus(8, ChronoUnit.HOURS)) //o crachá vale 8 horas.
				.sign(algoritmo);
	}
	
	public DecodedJWT validarToken(String token) {	//Confere assinatura, emissor e validade. Se algo estiver errado, devolve null.
		try {
			Algorithm algoritmo = Algorithm.HMAC256(secret);
			return JWT.require(algoritmo)
					.withIssuer(EMISSOR)
					.build()
					.verify(token);
		}catch (JWTVerificationException e) {
			return null;
		}
	}
	
}
