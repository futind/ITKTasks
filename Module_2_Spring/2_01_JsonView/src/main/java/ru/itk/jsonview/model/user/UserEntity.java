package ru.itk.jsonview.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import ru.itk.jsonview.model.order.OrderEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "orders", nullable = true)
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<OrderEntity> orders;
}
