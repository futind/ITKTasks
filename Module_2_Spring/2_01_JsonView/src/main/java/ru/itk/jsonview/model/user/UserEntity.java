package ru.itk.jsonview.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import ru.itk.jsonview.model.order.OrderEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "orders", nullable = true)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<OrderEntity> orders;
}
