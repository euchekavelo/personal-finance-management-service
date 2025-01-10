package ru.promo_z.personalfinancemanagementservice.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserResponseDto {

    private UUID id;
    private String email;
    private LocalDateTime creationTime;
    private WalletResponseDto wallet;
}
