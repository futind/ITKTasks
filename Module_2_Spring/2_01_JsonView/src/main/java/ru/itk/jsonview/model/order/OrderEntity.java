package ru.itk.jsonview.model.order;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.itk.jsonview.dto.product.ProductOrderDto;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /**
     * A list of {@link ProductOrderDto} - efficiently a map of
     * {
     *      information about the product - {@link ru.itk.jsonview.dto.product.ProductDto}
     *      information about the quantity of that product in the order
     * }
     */
    @Column(name = "products_quantities", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<ProductOrderDto> productQuantities;
}
