package ru.itk.pageablebooklibrary.book.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.itk.pageablebooklibrary.author.model.AuthorEntity;
import ru.itk.pageablebooklibrary.publisher.model.PublisherEntity;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", nullable = false, unique = true)
    private String ISBN;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "summary")
    private String summary;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private PublisherEntity publisher;
}
