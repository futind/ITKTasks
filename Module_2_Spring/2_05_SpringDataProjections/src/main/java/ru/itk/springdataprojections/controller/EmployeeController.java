package ru.itk.springdataprojections.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itk.springdataprojections.dto.employee.CreateEmployeeRequestDto;
import ru.itk.springdataprojections.dto.employee.EmployeeDto;
import ru.itk.springdataprojections.dto.employee.EmployeeProjectionDto;
import ru.itk.springdataprojections.dto.employee.UpdateEmployeeRequestDto;
import ru.itk.springdataprojections.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping(value = "/projection")
    public ResponseEntity<List<EmployeeProjectionDto>> getAllEmployeeProjections() {
        return ResponseEntity.ok(employeeService.getAllEmployeeProjections());
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable UUID employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @GetMapping(value = "/{employeeId}/projection")
    public ResponseEntity<EmployeeProjectionDto> getEmployeeProjectionById(@PathVariable UUID employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeProjectionById(employeeId));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid CreateEmployeeRequestDto createRequest) {
        return ResponseEntity.ok(employeeService.createEmployee(createRequest));
    }

    @PatchMapping(value = "/{departmentId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable UUID departmentId,
                                                      @RequestBody @Valid UpdateEmployeeRequestDto updateRequest) {
        return ResponseEntity.ok(employeeService.updateEmployee(departmentId, updateRequest));
    }

    @DeleteMapping(value = "/{departmentId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID departmentId) {
        employeeService.deleteEmployee(departmentId);
        return ResponseEntity.ok().build();
    }
}
