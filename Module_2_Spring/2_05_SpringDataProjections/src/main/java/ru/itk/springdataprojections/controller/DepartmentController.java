package ru.itk.springdataprojections.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itk.springdataprojections.dto.department.CreateDepartmentRequestDto;
import ru.itk.springdataprojections.dto.department.DepartmentDto;
import ru.itk.springdataprojections.dto.department.UpdateDepartmentRequestDto;
import ru.itk.springdataprojections.service.DepartmentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable UUID departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid CreateDepartmentRequestDto createRequest) {
        return ResponseEntity.ok(departmentService.createDepartment(createRequest));
    }

    @PatchMapping(value = "/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable UUID departmentId,
                                                          @RequestBody @Valid UpdateDepartmentRequestDto updateRequest) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentId, updateRequest));
    }

    @DeleteMapping(value = "/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable UUID departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }
}
