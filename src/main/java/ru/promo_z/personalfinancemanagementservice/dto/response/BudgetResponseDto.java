package ru.promo_z.personalfinancemanagementservice.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BudgetResponseDto {

    private UUID id;
    private LocalDateTime localDateTime;
}
