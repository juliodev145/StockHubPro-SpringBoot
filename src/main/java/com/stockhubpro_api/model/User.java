package com.stockhubpro_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column(nullable = false, length = 150, unique = true)
	private String email;
	@Column(nullable = false, length = 255)	//a senha será criptografada (com BCrypt, JWT) com 60 caracteres
	private String senha;
	@OneToOne		//qual coluna da tabela o usuario guarda a ligação
	@JoinColumn(name="empresa_id", nullable=false, unique=true)
	private Company empresa;
	
}
