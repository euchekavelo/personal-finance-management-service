package ru.promo_z.personalfinancemanagementservice.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CategoryResponseDto {

    private UUID id;
    private String title;
    private LocalDateTime creationTime;
}
