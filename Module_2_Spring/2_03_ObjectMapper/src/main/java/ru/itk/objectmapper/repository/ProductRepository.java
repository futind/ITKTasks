package ru.itk.objectmapper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itk.objectmapper.model.ProductEntity;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {}