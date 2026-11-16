package com.stockhubpro_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stockhubpro_api.dto.SignupRequest;
import com.stockhubpro_api.model.*;
import com.stockhubpro_api.respository.*;

import jakarta.transaction.Transactional;

/*
 * A analogia geral: o AuthService é o gerente do cadastro. Ele recebe o formulário preenchido do 
 * atendente (o controller), aplica as regras da empresa e manda os arquivistas (os repositories) guardarem as fichas.
 * */

@Service
public class AuthService {

	private final CompanyRepository companyRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthService(CompanyRepository companyRepository, UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		
		this.companyRepository = companyRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	public void signup(SignupRequest dados) {
		if (companyRepository.existsByCnpj(dados.cnpj())) {	//verifica se ja existe empresa com o cnpj informado
			throw new ResponseStatusException(HttpStatus.CONFLICT, "CNPJ já cadastrado");	//dados.cnpj(): no record, os getters não têm o “get”. É dados.cnpj()
		}																					//HttpStatus.CONFLICT (código 409): o jeito padrão de dizer “isso já existe”.
		if (userRepository.existsByEmail(dados.email())) { //verifica se ja existe usuario com o email informado
			throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
		}
		
		// guardar o valor digitado e salva banco de dados
		Company empresa = new Company();
		empresa.setNomeEmpresa(dados.nomeEmpresa());	
        empresa.setCnpj(dados.cnpj());
        empresa.setCep(dados.cep());
        empresa.setLogradouro(dados.logradouro());
        empresa.setNumero(dados.numero());
        empresa.setBairro(dados.bairro());
        empresa.setCidade(dados.cidade());
        empresa.setEstado(dados.estado());
        empresa.setUf(dados.uf());
        companyRepository.save(empresa);
        
        User usuario = new User();
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuario.setEmpresa(empresa);
        userRepository.save(usuario);
        
	}
	
}