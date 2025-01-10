package ru.promo_z.personalfinancemanagementservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
public class OperationRequestDto {

    private UUID walletId;
    private UUID categoryId;

    @Pattern(regexp = "INCOME|EXPENSE", message = "The operation type must consist of " +
            "the following values: INCOME or EXPENSE" )
    private String operationType;

    @Min(value = 1, message = "The amount must be greater than zero.")
    @NotNull(message = "The amount value must not be empty.")
    private Long amount;
}
