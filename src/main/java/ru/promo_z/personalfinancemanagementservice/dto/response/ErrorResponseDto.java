package ru.promo_z.personalfinancemanagementservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {

    private String message;
    private boolean result;
}
