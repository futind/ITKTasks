package ru.itk.pageablebooklibrary.author.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itk.pageablebooklibrary.author.api.AuthorAPI;
import ru.itk.pageablebooklibrary.author.dto.AuthorDto;
import ru.itk.pageablebooklibrary.author.dto.CreateAuthorRequestDto;
import ru.itk.pageablebooklibrary.author.dto.UpdateAuthorRequestDto;
import ru.itk.pageablebooklibrary.author.service.AuthorService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController implements AuthorAPI {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    /**
     * GET /api/v1/authors?page=&size=&field=
     * <p>
     * A method to list a certain amount of authors (with pagination)
     *
     * @param pageNumber - a number of a page of authors to show
     * @param pageSize - a size of a page to show
     * @param sortField - a field by which a page is going to be sorted
     * @param sortDirection - a direction in which a page is going to be sorted
     * @return a page of authors in form of {@link Page<AuthorDto>}
     */
    @Override
    @GetMapping
    public ResponseEntity<Page<AuthorDto>> getAuthors(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                      @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
                                                      @RequestParam(name = "sortField", defaultValue = "fullName") String sortField,
                                                      @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(authorService.getAuthors(pageNumber, pageSize, sortField, sortDirection));
    }

    /**
     * GET /api/v1/authors/{authorId}
     * <p>
     * Method to get the info about a particular author by id
     *
     * @param authorId - unique personal identifier of an author
     * @return basic information about the author in form of {@link AuthorDto}
     */
    @Override
    @GetMapping(value = "/{authorId}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable UUID authorId) {
        return ResponseEntity.ok(authorService.getAuthorById(authorId));
    }

    /**
     * PATCH /api/v1/authors/{authorId}
     * <p>
     * Method to update the information of a particular author
     *
     * @param authorId               - unique personal identifier of an author
     * @param updateAuthorRequestDto - data needed to update the author's info
     * @return updated information about the author in form of {@link AuthorDto}
     */
    @Override
    @PatchMapping(value = "/{authorId}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable UUID authorId,
                                                  @RequestBody @Valid UpdateAuthorRequestDto updateAuthorRequestDto) {
        return ResponseEntity.ok(authorService.updateAuthor(authorId, updateAuthorRequestDto));
    }

    /**
     * POST /api/v1/authors
     * <p>
     * Method to create a new user
     *
     * @param createAuthorRequestDto - info needed to create a new author
     * @return information about the created author
     */
    @Override
    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid CreateAuthorRequestDto createAuthorRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorService.createAuthor(createAuthorRequestDto));
    }

    /**
     * DELETE /api/v1/authors/{authorId}?force=
     * <p>
     * Method to delete a particular user
     *
     * @param authorId - unique personal identifier of an author
     */
    @Override
    @DeleteMapping(value = "/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.ok().build();
    }
}
