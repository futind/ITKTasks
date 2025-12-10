package ru.itk.springdataprojections.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itk.springdataprojections.model.EmployeeEntity;
import ru.itk.springdataprojections.projection.EmployeeProjection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

    @Query(
            """
            SELECT
                        CONCAT(employee.firstName, ' ', employee.lastName) AS fullName,
                        employee.position as position,
                        department.name as departmentName
            FROM EmployeeEntity employee
            JOIN employee.department department
            """
    )
    List<EmployeeProjection> findAllProjections();

    @Query(
            """
            SELECT
                        CONCAT(employee.firstName, ' ', employee.lastName) AS fullName,
                        employee.position as position,
                        department.name as departmentName
            FROM EmployeeEntity employee
            JOIN employee.department department
            WHERE employee.id = :employeeId
            """
    )
    Optional<EmployeeProjection> findProjectionById(@Param("employeeId") UUID employeeId);
}
