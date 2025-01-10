package ru.promo_z.personalfinancemanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.promo_z.personalfinancemanagementservice.model.Token;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
}
