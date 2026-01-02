package com.farmtech.challenge.repository;

import com.farmtech.challenge.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Magica do Spring Data: Só de escrever o nome do metodo assim,
    // ele já cria o SQL "SELECT count(*) > 0 FROM products WHERE name = ?"
    boolean existsByName(String name);
}