package ru.itk.springdataprojections.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.itk.springdataprojections.dto.employee.enumeration.EmployeePosition;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "position", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeePosition position;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;
}
