package com.farmtech.challenge.service;

import com.farmtech.challenge.api.dto.FarmerDTO;
import com.farmtech.challenge.api.dto.ProductRequestDTO;
import com.farmtech.challenge.api.dto.ProductResponseDTO;
import com.farmtech.challenge.domain.Farmer;
import com.farmtech.challenge.domain.Product;
import com.farmtech.challenge.exception.DuplicateResourceException;
import com.farmtech.challenge.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ProductResponseDTO create(ProductRequestDTO data) {
        if (repository.existsByName(data.name())) {
            throw new DuplicateResourceException("Já existe um produto com o nome: " + data.name());
        }
        Product product = new Product();
        updateProductData(product, data); // Reutilizei a lógica de preencher dados

        repository.save(product);
        return mapToResponseDTO(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
        return mapToResponseDTO(product);
    }

    @Transactional
    public ProductResponseDTO update(Long id, ProductRequestDTO data) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));

        // Se o nome mudou, verifica se o novo nome já existe em OUTRO produto
        if (!product.getName().equals(data.name()) && repository.existsByName(data.name())) {
            throw new DuplicateResourceException("Já existe um produto com o nome: " + data.name());
        }

        updateProductData(product, data);
        repository.save(product);
        return mapToResponseDTO(product);
    }

    // Método auxiliar para preencher a entidade com dados do DTO
    private void updateProductData(Product product, ProductRequestDTO data) {
        product.setName(data.name());
        product.setStatus(data.status());

        if (product.getFarmer() == null) {
            product.setFarmer(new Farmer());
        }
        product.getFarmer().setDocument(data.farmer().document());
        product.getFarmer().setFarmName(data.farmer().farmName());
        product.getFarmer().setState(data.farmer().state());
    }

    private ProductResponseDTO mapToResponseDTO(Product product) {
        FarmerDTO farmerDTO = new FarmerDTO(
                product.getFarmer().getDocument(),
                product.getFarmer().getFarmName(),
                product.getFarmer().getState()
        );
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getStatus(),
                farmerDTO,
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}