package ru.itk.pageablebooklibrary.publisher.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itk.pageablebooklibrary.publisher.api.PublisherAPI;
import ru.itk.pageablebooklibrary.publisher.dto.CreatePublisherRequestDto;
import ru.itk.pageablebooklibrary.publisher.dto.PublisherDto;
import ru.itk.pageablebooklibrary.publisher.dto.UpdatePublisherRequestDto;
import ru.itk.pageablebooklibrary.publisher.service.PublisherService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publisher")
public class PublisherController implements PublisherAPI {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    /**
     * GET /api/v1/publisher?pageNumber=&pageSize=
     * <p>
     * A method to list a certain amount of publisher (with pagination)
     *
     * @param pageNumber - a page of authors we want to see
     * @param pageSize   - a size of a page we want to see
     * @param sortField - field by with we sort
     * @param sortDirection - direction of the sort
     * @return a page of authors in form of {@link Page < PublisherDto >}
     */
    @Override
    @GetMapping
    public ResponseEntity<Page<PublisherDto>> getPublishers(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                            @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
                                                            @RequestParam(name = "sortField", defaultValue = "name") String sortField,
                                                            @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(publisherService.getPublishers(pageNumber, pageSize, sortField, sortDirection));
    }

    /**
     * GET /api/v1/publisher/{publisherId}
     * <p>
     * A method to show the information about the publisher
     *
     * @param publisherId - unique identifier of a publisher
     * @return information about the publisher in form of {@link PublisherDto}
     */
    @Override
    @GetMapping(value = "/{publisherId}")
    public ResponseEntity<PublisherDto> getPublisherById(@PathVariable UUID publisherId) {
        return ResponseEntity.ok(publisherService.getPublisherById(publisherId));
    }

    /**
     * POST /api/v1/publisher
     * <p>
     * A method to create a new publisher
     *
     * @param createPublisherRequestDto - information needed to create a new publisher
     * @return information about the created publisher in form of {@link PublisherDto}
     */
    @Override
    @PostMapping
    public ResponseEntity<PublisherDto> createPublisher(@RequestBody @Valid CreatePublisherRequestDto createPublisherRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(publisherService.createPublisher(createPublisherRequestDto));
    }

    /**
     * PATCH /api/v1/publisher/{publisherId}
     * <p>
     * A method to update the information about an existing publisher
     *
     * @param publisherId               - unique identifier of a publisher
     * @param updatePublisherRequestDto - information needed to update the information about the publisher
     * @return information about the updated publisher in form of {@link PublisherDto}
     */
    @Override
    @PatchMapping(value = "/{publisherId}")
    public ResponseEntity<PublisherDto> updatePublisher(@PathVariable UUID publisherId,
                                                        @RequestBody @Valid UpdatePublisherRequestDto updatePublisherRequestDto) {
        return ResponseEntity.ok(publisherService.updatePublisher(publisherId, updatePublisherRequestDto));
    }

    /**
     * DELETE /api/v1/publisher/{publisherId}?force=
     * <p>
     * A method to delete an existing publisher
     *
     * @param publisherId - unique identifier of a publisher
     */
    @Override
    @DeleteMapping(value = "/{publisherId}")
    public ResponseEntity<Void> deletePublisher(@PathVariable UUID publisherId) {
        publisherService.deletePublisher(publisherId);
        return ResponseEntity.ok().build();
    }
}
