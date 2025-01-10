package ru.promo_z.personalfinancemanagementservice.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class BudgetCategoryResponseDto {

    private UUID categoryId;
    private String categoryTitle;
    private Long budgetAmount;
    private Long remainingLimit;
}
