package ru.itk.springdataprojections.service;

import org.springframework.stereotype.Service;
import ru.itk.springdataprojections.dto.department.CreateDepartmentRequestDto;
import ru.itk.springdataprojections.dto.department.DepartmentDto;
import ru.itk.springdataprojections.dto.department.UpdateDepartmentRequestDto;
import ru.itk.springdataprojections.exception.DepartmentNotFoundException;
import ru.itk.springdataprojections.mapper.DepartmentMapper;
import ru.itk.springdataprojections.model.DepartmentEntity;
import ru.itk.springdataprojections.repository.DepartmentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository,
                             DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .toList();
    }

    public DepartmentDto getDepartmentById(UUID id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDto)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    public DepartmentDto createDepartment(CreateDepartmentRequestDto request) {
        DepartmentEntity createdDepartment = departmentMapper.createDepartment(request);
        departmentRepository.save(createdDepartment);
        return departmentMapper.toDto(createdDepartment);
    }

    public DepartmentDto updateDepartment(UUID id, UpdateDepartmentRequestDto request) {
        DepartmentEntity departmentToUpdate = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        departmentMapper.updateDepartment(departmentToUpdate, request);
        departmentRepository.save(departmentToUpdate);
        return departmentMapper.toDto(departmentToUpdate);
    }

    public void deleteDepartment(UUID id) {
        if (!departmentRepository.existsById(id)) {
            throw new DepartmentNotFoundException(id);
        }
        departmentRepository.deleteById(id);
    }
}
