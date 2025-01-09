package ru.promo_z.personalfinancemanagementservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tokens")
@Data
public class Token {

    @Id
    @GeneratedValue
    private UUID id;

    private String token;
}
