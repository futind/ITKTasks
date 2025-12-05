package ru.itk.pageablebooklibrary.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itk.pageablebooklibrary.author.exception.AuthorNotFoundException;
import ru.itk.pageablebooklibrary.author.model.AuthorEntity;
import ru.itk.pageablebooklibrary.author.repository.AuthorRepository;
import ru.itk.pageablebooklibrary.book.dto.BookDto;
import ru.itk.pageablebooklibrary.book.dto.CreateBookRequestDto;
import ru.itk.pageablebooklibrary.book.dto.UpdateBookRequestDto;
import ru.itk.pageablebooklibrary.book.exception.BookDuplicationConflictException;
import ru.itk.pageablebooklibrary.book.exception.BookNotFoundException;
import ru.itk.pageablebooklibrary.book.mapper.BookMapper;
import ru.itk.pageablebooklibrary.book.model.BookEntity;
import ru.itk.pageablebooklibrary.book.repository.BookRepository;
import ru.itk.pageablebooklibrary.publisher.exception.PublisherNotFoundException;
import ru.itk.pageablebooklibrary.publisher.model.PublisherEntity;
import ru.itk.pageablebooklibrary.publisher.repository.PublisherRepository;

import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       PublisherRepository publisherRepository,
                       BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.bookMapper = bookMapper;
    }

    /**
     * A method to get a page of books
     * @param pageNumber - number of a page
     * @param pageSize - size of a page
     * @param sortField - field by with we sort
     * @param sortDirection - direction of the sort
     * @return page of books {@link Page<BookDto>}
     */
    public Page<BookDto> getBooks(int pageNumber, int pageSize, String sortField, String sortDirection) {
        return bookRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.fromString(sortDirection), sortField)))
                .map(bookMapper::toDto);
    }

    /**
     * A method to get a particular book by its id
     * @param bookId - unique identifier of a book
     * @return information about the book in form of {@link BookDto}
     */
    public BookDto getBookById(UUID bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    /**
     * A method to get a particular book by its ISBN
     * @param isbn - another unique identifier of a book lmao
     * @return information about the book in form of {@link BookDto}
     */
    public BookDto getBookByISBN(String isbn) {
        return bookRepository.findByISBN(isbn)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    /**
     * A method to create a new book
     * @param createRequest - information needed to create a new book
     * @return information about created book in form of {@link BookDto}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BookDto createBook(CreateBookRequestDto createRequest) {
        if (bookRepository.existsByISBN(createRequest.ISBN())) {
            throw new BookDuplicationConflictException(createRequest.ISBN());
        }

        AuthorEntity author = authorRepository.findById(createRequest.authorId())
                .orElseThrow(() -> new AuthorNotFoundException(createRequest.authorId()));

        PublisherEntity publisher = publisherRepository.findById(createRequest.publisherId())
                .orElseThrow(() -> new PublisherNotFoundException(createRequest.publisherId()));

        BookEntity createdBook = bookMapper.createEntityFromRequest(
                createRequest,
                author,
                publisher
        );
        bookRepository.save(createdBook);
        return bookMapper.toDto(createdBook);
    }

    /**
     * A method to update an existing book
     * @param bookId - unique identifier of a book
     * @param updateRequest - information needed to update the book
     * @return information about updated book in form of {@link BookDto}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BookDto updateBook(UUID bookId, UpdateBookRequestDto updateRequest) {
        if (bookRepository.existsByISBN(updateRequest.ISBN())) {
            throw new BookDuplicationConflictException(updateRequest.ISBN());
        }

        AuthorEntity author = null;
        PublisherEntity publisher = null;

        if (updateRequest.authorId() != null) {
            author = authorRepository.findById(updateRequest.authorId())
                    .orElseThrow(() -> new AuthorNotFoundException(updateRequest.authorId()));
        }
        if (updateRequest.publisherId() != null) {
            publisher = publisherRepository.findById(updateRequest.publisherId())
                    .orElseThrow(() -> new PublisherNotFoundException(updateRequest.publisherId()));
        }

        BookEntity bookEntityToUpdate = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        bookMapper.updateEntityFromRequest(
                bookEntityToUpdate,
                updateRequest,
                author,
                publisher
        );

        return bookMapper.toDto(bookRepository.save(bookEntityToUpdate));
    }

    /**
     * A method to delete an existing book
     * @param bookId - unique identifier of a book
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteBook(UUID bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException(bookId);
        }
        bookRepository.deleteById(bookId);
    }
}
