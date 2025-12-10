package ru.itk.jdbcbooklibrary.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import ru.itk.jdbcbooklibrary.mapper.BookRowMapper;
import ru.itk.jdbcbooklibrary.model.BookDto;
import ru.itk.jdbcbooklibrary.model.CreateBookDto;
import ru.itk.jdbcbooklibrary.model.UpdateBookDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Primary
public class JdbcBookRepository implements BookRepository {

    private final TransactionTemplate readTransaction;
    private final TransactionTemplate writeTransaction;
    private final JdbcTemplate jdbcTemplate;
    private final BookRowMapper bookRowMapper;

    public JdbcBookRepository(PlatformTransactionManager transactionManager, JdbcTemplate jdbcTemplate, BookRowMapper bookRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;

        readTransaction = new TransactionTemplate(transactionManager);
        readTransaction.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        readTransaction.setReadOnly(true);

        writeTransaction = new TransactionTemplate(transactionManager);
        writeTransaction.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        writeTransaction.setReadOnly(false);
    }

    @Override
    public List<BookDto> findAll() {
        return readTransaction.execute(transactionStatus ->
                        jdbcTemplate.query(
                                "SELECT * FROM books",
                                bookRowMapper
                        )
                );
    }

    @Override
    public Optional<BookDto> findByID(UUID id) {
        try {
            return Optional.ofNullable(
                    readTransaction.execute(transactionStatus ->
                            jdbcTemplate.queryForObject(
                                    "SELECT * FROM books WHERE id = ?",
                                    bookRowMapper,
                                    id
                            )
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteByID(UUID id) {
        Integer affected = writeTransaction.execute(transactionStatus ->
                jdbcTemplate.update("DELETE FROM books WHERE id = ?", id)
        );

        return affected != null && affected > 0;
    }

    @Override
    public BookDto save(CreateBookDto createBookDto) {
        return writeTransaction.execute(
                transactionStatus ->
                        jdbcTemplate.queryForObject("""
                                INSERT INTO books (title, author, year_of_publication)
                                VALUES (?, ?, ?)
                                RETURNING id, title, author, year_of_publication
                                """,
                                bookRowMapper,
                                createBookDto.title(),
                                createBookDto.author(),
                                createBookDto.yearOfPublication()
                        )
        );
    }

    @Override
    public Optional<BookDto> updateByID(UUID id, UpdateBookDto updateBookDto) {
        return writeTransaction.execute(status -> {

            StringBuilder sql = new StringBuilder("UPDATE books SET ");
            List<Object> params = new ArrayList<>();

            if (updateBookDto.title() != null) {
                sql.append("title = ?, ");
                params.add(updateBookDto.title());
            }
            if (updateBookDto.author() != null) {
                sql.append("author = ?, ");
                params.add(updateBookDto.author());
            }
            if (updateBookDto.yearOfPublication() != null) {
                sql.append("year_of_publication = ?, ");
                params.add(updateBookDto.yearOfPublication());
            }

            if (params.isEmpty()) {
                try {
                    BookDto existing = jdbcTemplate.queryForObject(
                            "SELECT * FROM books WHERE id = ?",
                            bookRowMapper,
                            id
                    );
                    return Optional.ofNullable(existing);
                } catch (EmptyResultDataAccessException e) {
                    return Optional.empty();
                }
            }

            sql.delete(sql.length() - 2, sql.length());
            sql.append(" WHERE id = ? RETURNING id, title, author, year_of_publication");
            params.add(id);

            try {
                BookDto updated = jdbcTemplate.queryForObject(
                        sql.toString(),
                        bookRowMapper,
                        params.toArray()
                );
                return Optional.ofNullable(updated);
            } catch (EmptyResultDataAccessException e) {
                return Optional.empty();
            }
        });
    }
}
