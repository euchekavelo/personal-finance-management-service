package ru.promo_z.personalfinancemanagementservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class BudgetRequestDto {

    private Long amount;
    private UUID categoryId;
}
