package ru.itk.pageablebooklibrary.book.dto;

import java.util.UUID;

public record UpdateBookRequestDto(
    String ISBN,
    String title,
    String summary,
    UUID authorId,
    UUID publisherId
) {}
