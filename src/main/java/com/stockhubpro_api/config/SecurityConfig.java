package com.stockhubpro_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
				.csrf(csrf -> csrf.disable())//o CSRF é uma proteção para sites com formulários e cookies. Num app mobile com token, não se aplica.
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//STATELESS: o porteiro não guarda memória de quem entrou. A cada pedido, ele confere o crachá de novo. É assim que funciona com JWT.
				.authorizeHttpRequests(auth -> auth
						
						/*
						 * /auth/** livre: cadastro e login não exigem crachá. Todo o resto exige.
						 * 
						 * /error livre: quando o backend devolve um erro (“CNPJ já cadastrado”), o Spring passa por esse endereço. 
						 * Se ele estivesse trancado, o app receberia “acesso negado” em vez da mensagem certa.
						 * */
						.requestMatchers("/auth/**", "/error").permitAll()
						.anyRequest().authenticated())
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	//BCryptPasswordEncoder: a máquina que criptografa as senhas
	}
}
