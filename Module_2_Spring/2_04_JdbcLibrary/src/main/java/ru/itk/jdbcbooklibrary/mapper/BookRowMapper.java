package ru.itk.jdbcbooklibrary.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itk.jdbcbooklibrary.model.BookDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class BookRowMapper implements RowMapper<BookDto> {

    @Override
    public BookDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BookDto(
                rs.getObject("id", UUID.class),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("year_of_publication")
        );
    }
}
