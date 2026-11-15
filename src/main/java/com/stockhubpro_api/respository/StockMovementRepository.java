package com.stockhubpro_api.respository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockhubpro_api.model.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long>{
	List<StockMovement> findByProdutoEmpresaIdAndDataMovimentacaoBetween(
			Long empresaId, LocalDateTime inicio, LocalDateTime fim);	//trazer as movimentações dos produtos da empresa X, feitas entre o dia tal e o dia tal.
}

/*
 *É a busca do relatório mensal. Para o relatório de outubro, por exemplo, o backend passa o início (1º/10, 00:00) e 
 *o fim (31/10, 23:59:59), e recebe todas as entradas e saídas do mês, que viram o PDF.
 * */
 