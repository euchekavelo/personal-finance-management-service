package ru.promo_z.personalfinancemanagementservice.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class OperationRequestDto {

    private UUID walletId;
    private UUID categoryId;
    private String operationType;
    private Long amount;
}
