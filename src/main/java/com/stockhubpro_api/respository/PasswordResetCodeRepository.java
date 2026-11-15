package com.stockhubpro_api.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockhubpro_api.model.PasswordResetCode;

public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, Long>{
	Optional<PasswordResetCode> findFirstByUsuarioIdAndCodigoAndUtilizadoFalse(
			Long usuarioId, String codigo);
}

//trazer um código deste usuário, com este valor, que ainda não foi usado.

/*
 * Usado na tela Esqueci Senha: o usuário digita o código que recebeu, e o backend confere se ele existe, se é dele e se não foi usado. 
 * A validade (dataExpiracao) a gente confere no código Java, depois de encontrar o registro. 
 * Assim dá para responder com mensagens diferentes: “código inválido” ou “código expirado”.*/