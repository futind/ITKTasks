package ru.itk.pageablebooklibrary.publisher.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itk.pageablebooklibrary.publisher.dto.CreatePublisherRequestDto;
import ru.itk.pageablebooklibrary.publisher.dto.PublisherDto;
import ru.itk.pageablebooklibrary.publisher.dto.UpdatePublisherRequestDto;
import ru.itk.pageablebooklibrary.publisher.exception.PublisherDeletionConflictException;
import ru.itk.pageablebooklibrary.publisher.exception.PublisherNotFoundException;
import ru.itk.pageablebooklibrary.publisher.mapper.PublisherMapper;
import ru.itk.pageablebooklibrary.publisher.model.PublisherEntity;
import ru.itk.pageablebooklibrary.publisher.repository.PublisherRepository;

import java.util.UUID;

/**
 * Service with methods necessary to manage publishers
 */
@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository,
                            PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    /**
     * A method to get a page of publishers
     * @param pageNumber - number of a page
     * @param pageSize - size of a page
     * @param sortField - field by with we sort
     * @param sortDirection - direction of the sort
     * @return a page of publisher {@link PublisherDto}
     */
    public Page<PublisherDto> getPublishers(int pageNumber, int pageSize, String sortField, String sortDirection) {
        return publisherRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.fromString(sortDirection), sortField)))
                .map(publisherMapper::toDto);
    }

    /**
     * A method to get information about a particular publisher
     * @param publisherId - unique identifier of a publisher
     * @return information about the publisher in form of {@link PublisherDto}
     */
    public PublisherDto getPublisherById(UUID publisherId) {
        return publisherRepository.findById(publisherId)
                .map(publisherMapper::toDto)
                .orElseThrow(() -> new PublisherNotFoundException(publisherId));
    }

    /**
     * A method to create a new publisher
     * @param createRequest - information needed to create a new publisher
     * @return information about the created publisher in form of {@link PublisherDto}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PublisherDto createPublisher(CreatePublisherRequestDto createRequest) {
        PublisherEntity createdPublisher = publisherMapper.createFromRequest(createRequest);
        publisherRepository.save(createdPublisher);
        return publisherMapper.toDto(createdPublisher);
    }

    /**
     * A method to update an existing publisher
     * @param publisherId - unique identifier of a publisher
     * @param updateRequest - information needed to update a publisher
     * @return information about the updated publisher in form of {@link PublisherDto}
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PublisherDto updatePublisher(UUID publisherId, UpdatePublisherRequestDto updateRequest) {
        PublisherEntity publisherEntityToUpdate = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new PublisherNotFoundException(publisherId));
        publisherMapper.updateEntityFromRequest(publisherEntityToUpdate, updateRequest);
        publisherRepository.save(publisherEntityToUpdate);
        return publisherMapper.toDto(publisherEntityToUpdate);
    }

    /**
     * A method to delete an existing publisher
     * @param publisherId - unique identifier of a publisher
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deletePublisher(UUID publisherId) {
        PublisherEntity publisherEntityToDelete = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new PublisherNotFoundException(publisherId));
        if (!publisherEntityToDelete.getBooks().isEmpty()) {
            throw new PublisherDeletionConflictException(publisherId);
        }
        publisherRepository.delete(publisherEntityToDelete);
    }
}