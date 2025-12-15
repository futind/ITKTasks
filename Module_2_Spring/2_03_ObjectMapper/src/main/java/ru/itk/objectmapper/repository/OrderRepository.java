package ru.itk.objectmapper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itk.objectmapper.model.OrderEntity;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {}