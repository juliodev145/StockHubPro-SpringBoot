package com.stockhubpro_api.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movimentacao_estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Product produto;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('ENTRADA','SAIDA')")
    private MovementType tipo;

    @Column(nullable = false)
    private Integer quantidade;

    @CreationTimestamp		//preenche a data e hora no momento em que a movimentação é salva
    @Column(name = "data_movimentacao", nullable = false, updatable = false)	//updatable = false -> a data nunca muda depois de gravada
    private LocalDateTime dataMovimentacao;
}