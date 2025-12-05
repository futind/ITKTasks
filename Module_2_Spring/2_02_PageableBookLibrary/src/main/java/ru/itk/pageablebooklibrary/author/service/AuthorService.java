package ru.itk.pageablebooklibrary.author.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itk.pageablebooklibrary.author.dto.AuthorDto;
import ru.itk.pageablebooklibrary.author.dto.CreateAuthorRequestDto;
import ru.itk.pageablebooklibrary.author.dto.UpdateAuthorRequestDto;
import ru.itk.pageablebooklibrary.author.exception.AuthorDeletionConflictExceptionException;
import ru.itk.pageablebooklibrary.author.exception.AuthorNotFoundException;
import ru.itk.pageablebooklibrary.author.mapper.AuthorMapper;
import ru.itk.pageablebooklibrary.author.model.AuthorEntity;
import ru.itk.pageablebooklibrary.author.repository.AuthorRepository;

import java.util.UUID;

/**
 * A service class which contains methods that operate on author entities
 */
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository,
                         AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    /**
     * A method to get a page of authors
     * @param pageNumber - number of the page
     * @param pageSize - size of the page
     * @param sortField - field by with we sort
     * @param sortDirection - direction of the sort
     * @return page of authors {@link AuthorDto}
     */
    public Page<AuthorDto> getAuthors(int pageNumber, int pageSize, String sortField, String sortDirection) {
        return authorRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.fromString(sortDirection), sortField)))
                .map(authorMapper::toDto);
    }

    /**
     * A method to find a particular author by id
     * @param authorId - unique personal identifier of an author
     * @return information about the author {@link AuthorDto}
     */
    public AuthorDto getAuthorById(UUID authorId) {
        return authorRepository.findById(authorId)
                .map(authorMapper::toDto)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
    }

    /**
     * A method to create a new author
     * @param createRequest - information needed to create a new author
     * @return information about created author {@link AuthorDto}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AuthorDto createAuthor(CreateAuthorRequestDto createRequest) {
        AuthorEntity createdAuthorEntity = authorMapper.createFromRequest(createRequest);
        authorRepository.save(createdAuthorEntity);
        return authorMapper.toDto(createdAuthorEntity);
    }

    /**
     * A method to update an existing author
     * @param authorId - unique personal identifier of an author
     * @param updateRequest - information needed to update the author's info
     * @return information about updated author {@link AuthorDto}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AuthorDto updateAuthor(UUID authorId, UpdateAuthorRequestDto updateRequest) {
        AuthorEntity authorEntityToUpdate = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        authorMapper.updateFromRequest(authorEntityToUpdate, updateRequest);
        authorRepository.save(authorEntityToUpdate);
        return authorMapper.toDto(authorEntityToUpdate);
    }

    /**
     * A method to delete an existing author
     * @param authorId - unique personal identifier of an author
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAuthor(UUID authorId) {
        AuthorEntity authorEntityToDelete = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        if (!authorEntityToDelete.getBooks().isEmpty()) {
            throw new AuthorDeletionConflictExceptionException(authorId);
        }
        authorRepository.delete(authorEntityToDelete);
    }
}
