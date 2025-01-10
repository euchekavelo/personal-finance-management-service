package ru.promo_z.personalfinancemanagementservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreationTimestamp
    private LocalDateTime creationTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long balance;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Operation> operationList = new ArrayList<>();
}
