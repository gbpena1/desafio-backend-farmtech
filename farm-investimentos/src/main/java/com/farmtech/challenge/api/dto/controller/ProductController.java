package com.farmtech.challenge.api.dto.controller;

import com.farmtech.challenge.api.dto.ProductRequestDTO;
import com.farmtech.challenge.api.dto.ProductResponseDTO;
import com.farmtech.challenge.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Produtos", description = "Gerenciamento de produtos agrícolas")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar produto", description = "Cria um novo produto")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO data) {
        var response = service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar produtos", description = "Retorna todos os produtos cadastrados")
    public ResponseEntity<List<ProductResponseDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Retorna um produto específico")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza dados de um produto existente")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO data) {
        return ResponseEntity.ok(service.update(id, data));
    }
}