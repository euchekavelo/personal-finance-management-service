package ru.promo_z.personalfinancemanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.promo_z.personalfinancemanagementservice.model.Budget;

import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    @Modifying
    @Query("DELETE FROM Budget b WHERE b.category.id = :categoryId")
    void deleteByCategoryId(@Param("categoryId") UUID categoryId);
}
