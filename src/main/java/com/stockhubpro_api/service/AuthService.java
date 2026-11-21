package com.stockhubpro_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stockhubpro_api.dto.LoginRequest;
import com.stockhubpro_api.dto.LoginResponse;
import com.stockhubpro_api.dto.SignupRequest;
import com.stockhubpro_api.model.Company;
import com.stockhubpro_api.model.User;
import com.stockhubpro_api.respository.CompanyRepository;
import com.stockhubpro_api.respository.UserRepository;

import jakarta.transaction.Transactional;

/*
 * A analogia geral: o AuthService é o gerente do cadastro. Ele recebe o formulário preenchido do
 * atendente (o controller), aplica as regras da empresa e manda os arquivistas (os repositories) guardarem as fichas.
 */

@Service
public class AuthService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(CompanyRepository companyRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    // ===================== CADASTRO =====================
    @Transactional
    public void signup(SignupRequest dados) {
        // verifica se já existe empresa com o CNPJ informado (409 = "isso já existe")
        if (companyRepository.existsByCnpj(dados.cnpj())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CNPJ já cadastrado");
        }
        // verifica se já existe usuário com o e-mail informado
        if (userRepository.existsByEmail(dados.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }

        // preenche a ficha da empresa e salva no banco
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

        // preenche a ficha do usuário (senha criptografada) e salva no banco
        User usuario = new User();
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuario.setEmpresa(empresa);
        userRepository.save(usuario);
    }

    // ===================== LOGIN =====================
    public LoginResponse login(LoginRequest dados) {
        // abre a caixa Optional. Se vier vazia (e-mail não cadastrado), interrompe com erro (401)
        User usuario = userRepository.findByEmail(dados.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos"));

        // passa a senha digitada pelo triturador e compara com a guardada
        if (!passwordEncoder.matches(dados.senha(), usuario.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos");
        }

        // fabrica o crachá e devolve junto com o nome do usuário e da empresa
        String token = tokenService.gerarToken(usuario);
        return new LoginResponse(token, usuario.getNome(), usuario.getEmpresa().getNomeEmpresa());
    }
}