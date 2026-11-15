package com.stockhubpro_api.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockhubpro_api.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	boolean existsByCnpj(String cnpj);	//verifica se ja existe uma empresa com o cpnj informado.
}
