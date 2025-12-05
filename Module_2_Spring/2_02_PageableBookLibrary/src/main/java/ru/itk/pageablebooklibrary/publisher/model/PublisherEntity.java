package ru.itk.pageablebooklibrary.publisher.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.itk.pageablebooklibrary.book.model.BookEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "publishers")
public class PublisherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private List<BookEntity> books;
}
