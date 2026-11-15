package com.stockhubpro_api.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockhubpro_api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByEmpresaId(Long empresaId);	//listar todos os produtos da empresa com id X
	
	Optional<Product> findByIdAndEmpresaId(Long id, Long empresaId);	//trazer o produto de id X, só se for da empresa N
	
	Optional<Product> findByCodigoBarrasAndEmpresaId(String codigoBarras, Long empresaId);	//trazer o produto com o codigo de barras, desta empresa com id N
	
	boolean existsByCodigoBarrasAndEmpresaId(String codigoBarras, Long empresaId);	//verifica se a empresa ja tem um produto com este codigo de barras
}
