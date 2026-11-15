package com.stockhubpro_api.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockhubpro_api.model.Category;

public interface CategoryRespository extends JpaRepository<Category, Long> {
	
	List<Category> findByEmpresaId(Long id); //listar todas as categorias da empresa com id X
	
	Optional<Category> findByIdAndEmpresaId(Long id, Long empresaId);	//pegar a categoria do id N mas só se for da empresa de id X
	
}
