package ru.itk.pageablebooklibrary.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itk.pageablebooklibrary.author.model.AuthorEntity;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {}