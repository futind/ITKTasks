package ru.itk.objectmapper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itk.objectmapper.model.OrderItemEntity;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {}