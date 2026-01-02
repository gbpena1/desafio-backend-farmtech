package com.farmtech.challenge.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity // Isso aqui transforma a classe em uma tabela no banco de dados H2
@Table(name = "products")
public class Product {

    @Id // Chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento (1, 2, 3...)
    private Long id;

    // Regra de negócio: "Não pode haver nomes repetidos"
    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING) // Salva no banco como texto ("ACTIVE") e não número (0)
    private ProductStatus status;

    @Embedded // Puxa os campos da classe Farmer para dentro desta tabela
    private Farmer farmer;

    @CreationTimestamp // O banco preenche a data sozinho na criação
    private LocalDateTime createdAt;

    @UpdateTimestamp // O banco atualiza a data sozinho quando editar
    private LocalDateTime updatedAt;
}