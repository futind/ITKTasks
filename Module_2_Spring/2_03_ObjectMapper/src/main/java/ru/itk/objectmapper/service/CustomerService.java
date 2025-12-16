package ru.itk.objectmapper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itk.objectmapper.dto.customer.CreateCustomerRequestDto;
import ru.itk.objectmapper.dto.customer.CustomerDto;
import ru.itk.objectmapper.dto.customer.UpdateCustomerRequestDto;
import ru.itk.objectmapper.mapper.CustomerMapper;
import ru.itk.objectmapper.model.CustomerEntity;
import ru.itk.objectmapper.repository.CustomerRepository;
import ru.itk.objectmapper.util.exception.CustomerNotFoundException;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public CustomerDto getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Transactional
    public CustomerDto createCustomer(CreateCustomerRequestDto createRequest) {
        CustomerEntity createdCustomerEntity = customerMapper.createCustomerEntity(createRequest);
        customerRepository.save(createdCustomerEntity);
        return customerMapper.toDto(createdCustomerEntity);
    }

    @Transactional
    public CustomerDto updateCustomer(UUID customerId, UpdateCustomerRequestDto updateRequest) {
        CustomerEntity customerEntityToUpdate = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        customerMapper.updateCustomerEntity(customerEntityToUpdate, updateRequest);
        customerRepository.save(customerEntityToUpdate);
        return customerMapper.toDto(customerEntityToUpdate);
    }

    @Transactional
    public void deleteCustomer(UUID customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        customerRepository.deleteById(customerId);
    }
}
