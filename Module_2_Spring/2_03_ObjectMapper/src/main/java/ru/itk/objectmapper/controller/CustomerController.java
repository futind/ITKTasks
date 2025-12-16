package ru.itk.objectmapper.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itk.objectmapper.dto.customer.CreateCustomerRequestDto;
import ru.itk.objectmapper.dto.customer.UpdateCustomerRequestDto;
import ru.itk.objectmapper.service.CustomerService;
import ru.itk.objectmapper.util.validation.BeanValidator;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final ObjectMapper objectMapper;
    private final BeanValidator beanValidator;

    @GetMapping
    public ResponseEntity<String> getAllCustomers() {
        return ResponseEntity.ok(objectMapper.writeValueAsString(customerService.getAllCustomers()));
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<String> getCustomerById(@PathVariable UUID customerId) {
        return ResponseEntity.ok(objectMapper.writeValueAsString(customerService.getCustomerById(customerId)));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody String createCustomerRequestDto) {
        CreateCustomerRequestDto createRequest = objectMapper.readValue(createCustomerRequestDto, CreateCustomerRequestDto.class);
        beanValidator.validate(createRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(objectMapper.writeValueAsString(customerService.createCustomer(createRequest)));
    }

    @PatchMapping(value = "/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable UUID customerId,
                                                 @RequestBody String updateCustomerRequestDto) {
        UpdateCustomerRequestDto updateRequest = objectMapper.readValue(updateCustomerRequestDto, UpdateCustomerRequestDto.class);
        beanValidator.validate(updateRequest);
        return ResponseEntity.ok(objectMapper.writeValueAsString(customerService.updateCustomer(customerId, updateRequest)));
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deletCustomerById(@PathVariable UUID customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
