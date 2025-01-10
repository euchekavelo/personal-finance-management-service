package ru.promo_z.personalfinancemanagementservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import ru.promo_z.personalfinancemanagementservice.model.enums.OperationType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "operations")
@Data
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreationTimestamp
    private LocalDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
