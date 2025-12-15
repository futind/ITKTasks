package ru.itk.objectmapper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itk.objectmapper.dto.product.CreateProductRequestDto;
import ru.itk.objectmapper.dto.product.ProductDto;
import ru.itk.objectmapper.dto.product.UpdateProductRequestDto;
import ru.itk.objectmapper.mapper.ProductMapper;
import ru.itk.objectmapper.model.ProductEntity;
import ru.itk.objectmapper.repository.ProductRepository;
import ru.itk.objectmapper.util.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    public ProductDto getProductById(UUID productId) {
        return productRepository.findById(productId)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Transactional
    public ProductDto createProduct(CreateProductRequestDto createRequest) {
        ProductEntity createdProduct = productMapper.createProductEntity(createRequest);
        productRepository.save(createdProduct);
        return productMapper.toDto(createdProduct);
    }

    @Transactional
    public ProductDto updateProduct(UUID productId, UpdateProductRequestDto updateRequest) {
        ProductEntity productEntityToUpdate = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        productMapper.updateProductEntity(productEntityToUpdate, updateRequest);
        productRepository.save(productEntityToUpdate);
        return productMapper.toDto(productEntityToUpdate);
    }

    @Transactional
    public void deleteProduct(UUID productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }
        productRepository.deleteById(productId);
    }
}
