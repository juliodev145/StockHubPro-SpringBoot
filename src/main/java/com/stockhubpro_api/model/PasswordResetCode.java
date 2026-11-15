package com.stockhubpro_api.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "codigo_redefinicao_senha")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne		//um mesmo usuario pode pedir varios codigos
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @Column(nullable = false, length = 10)
    private String codigo;

    @Column(name = "data_expiracao", nullable = false)
    private LocalDateTime dataExpiracao;

    @Column(nullable = false)
    private Boolean utilizado = false;		//a coluna tinyint(1) do MySQL é o jeito dele de guardar verdadeiro/falso. Todo código nasce como “não usado”.
}