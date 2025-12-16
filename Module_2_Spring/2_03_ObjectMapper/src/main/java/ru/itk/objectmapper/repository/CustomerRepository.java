package ru.itk.objectmapper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itk.objectmapper.model.CustomerEntity;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {}