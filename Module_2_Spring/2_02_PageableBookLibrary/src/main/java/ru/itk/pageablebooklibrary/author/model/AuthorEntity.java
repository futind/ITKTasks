package ru.itk.pageablebooklibrary.author.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.itk.pageablebooklibrary.book.model.BookEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<BookEntity> books;
}
