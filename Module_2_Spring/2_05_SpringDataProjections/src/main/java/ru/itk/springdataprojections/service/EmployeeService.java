package ru.itk.springdataprojections.service;

import org.springframework.stereotype.Service;
import ru.itk.springdataprojections.dto.employee.CreateEmployeeRequestDto;
import ru.itk.springdataprojections.dto.employee.EmployeeDto;
import ru.itk.springdataprojections.dto.employee.EmployeeProjectionDto;
import ru.itk.springdataprojections.dto.employee.UpdateEmployeeRequestDto;
import ru.itk.springdataprojections.exception.DepartmentNotFoundException;
import ru.itk.springdataprojections.exception.EmployeeNotFoundException;
import ru.itk.springdataprojections.mapper.EmployeeMapper;
import ru.itk.springdataprojections.model.EmployeeEntity;
import ru.itk.springdataprojections.repository.DepartmentRepository;
import ru.itk.springdataprojections.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository,
                           EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).toList();
    }

    public List<EmployeeProjectionDto> getAllEmployeeProjections() {
        return employeeRepository.findAllProjections().stream().map(employeeMapper::toProjectionDto).toList();
    }

    public EmployeeDto getEmployeeById(UUID id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public EmployeeProjectionDto getEmployeeProjectionById(UUID id) {
        return employeeRepository.findProjectionById(id)
                .map(employeeMapper::toProjectionDto)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public EmployeeDto createEmployee(CreateEmployeeRequestDto request) {
        EmployeeEntity createdEmployee = employeeMapper.createEmployee(request);
        createdEmployee.setDepartment(
                departmentRepository.findById(request.departmentId())
                        .orElseThrow(() -> new DepartmentNotFoundException(request.departmentId()))
        );
        employeeRepository.save(createdEmployee);
        return employeeMapper.toDto(createdEmployee);
    }

    public EmployeeDto updateEmployee(UUID id, UpdateEmployeeRequestDto request) {
        EmployeeEntity employeeToUpdate = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeMapper.updateEmployee(employeeToUpdate, request);
        if (request.departmentId() != null) {
            employeeToUpdate.setDepartment(
                    departmentRepository.findById(request.departmentId())
                            .orElseThrow(() -> new DepartmentNotFoundException(request.departmentId()))
            );
        }
        employeeRepository.save(employeeToUpdate);
        return employeeMapper.toDto(employeeToUpdate);
    }

    public void deleteEmployee(UUID id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }
}
