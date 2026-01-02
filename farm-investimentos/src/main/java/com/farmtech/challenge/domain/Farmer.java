package com.farmtech.challenge.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // O Lombok cria os Getters, Setters e toString automaticamente
@AllArgsConstructor // Cria um construtor com todos os argumentos
@NoArgsConstructor  // Cria um construtor vazio (obrigatório para o JPA)
@Embeddable // Diz ao Java: "Não crie uma tabela para mim, me coloque dentro de quem me usar"
public class Farmer {
    private String document; // CPF ou CNPJ
    private String farmName;
    private String state;
}