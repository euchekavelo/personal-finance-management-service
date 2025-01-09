package ru.promo_z.personalfinancemanagementservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "budgets")
@Data
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Long amount;

    @CreationTimestamp
    private LocalDateTime localDateTime;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
