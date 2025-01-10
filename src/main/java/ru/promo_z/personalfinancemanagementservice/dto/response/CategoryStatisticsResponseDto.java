package ru.promo_z.personalfinancemanagementservice.dto.response;

import lombok.Data;

@Data
public class CategoryStatisticsResponseDto {

    private String categoryTitle;
    private Long categoryTotalAmount;
}
