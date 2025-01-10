package ru.promo_z.personalfinancemanagementservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class BudgetRequestDto {

    @Min(value = 1, message = "The amount must be greater than zero.")
    @NotNull(message = "The amount value must not be empty.")
    private Long amount;

    private UUID categoryId;
}
