package com.stockhubpro_api.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(name="nome_empresa", nullable = false, length = 150)	//mullable = false -> campo obrigatorio (NOT NULL)
	private String nomeEmpresa;
	@Column(nullable=false, length = 18, unique = true)		//unique key = true -> duas empresas nao podem ter o mesmo cnpj
	private String cnpj;
	@Column(nullable = false, length = 9)
	private String cep;
	@Column(nullable = false, length = 150)
	private String logradouro;
	@Column(nullable = false, length = 100)
	private String bairro;
	@Column(nullable = false, length = 100)
	private String cidade;
	@Column(nullable = false, length = 100)
	private String estado;
	@Column(nullable = false, columnDefinition = "char(2)")		//diz ao Hibernate o tipo exato da coluna no banco
	private String uf;
	
}
