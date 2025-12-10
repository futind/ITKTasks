package ru.itk.pageablebooklibrary.publisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itk.pageablebooklibrary.publisher.model.PublisherEntity;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublisherEntity, UUID> {}