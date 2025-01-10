package ru.promo_z.personalfinancemanagementservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    @CreationTimestamp
    private LocalDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "category", cascade = CascadeType.ALL)
    private Budget budget;

    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Operation> operationList = new ArrayList<>();
}
