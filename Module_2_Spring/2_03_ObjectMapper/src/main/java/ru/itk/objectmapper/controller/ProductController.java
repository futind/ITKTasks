package ru.itk.objectmapper.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itk.objectmapper.dto.product.CreateProductRequestDto;
import ru.itk.objectmapper.dto.product.UpdateProductRequestDto;
import ru.itk.objectmapper.service.ProductService;
import ru.itk.objectmapper.util.validation.BeanValidator;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ObjectMapper objectMapper;
    private final ProductService productService;
    private final BeanValidator beanValidator;

    @GetMapping
    public ResponseEntity<String> getAllProducts() {
        return ResponseEntity.ok(objectMapper.writeValueAsString(productService.getAllProducts()));
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<String> getProductById(@PathVariable UUID productId) {
        return ResponseEntity.ok(objectMapper.writeValueAsString(productService.getProductById(productId)));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody String createProductRequestDto) {
        CreateProductRequestDto createRequest = objectMapper.readValue(createProductRequestDto, CreateProductRequestDto.class);
        beanValidator.validate(createRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(objectMapper.writeValueAsString(productService.createProduct(createRequest)));
    }

    @PatchMapping(value = "/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable UUID productId,
                                                @RequestBody String updateProductRequestDto) {
        UpdateProductRequestDto updateRequest = objectMapper.readValue(updateProductRequestDto, UpdateProductRequestDto.class);
        beanValidator.validate(updateRequest);
        return ResponseEntity.ok(objectMapper.writeValueAsString(productService.updateProduct(productId, updateRequest)));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
