package ru.promo_z.personalfinancemanagementservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BudgetStatisticsResponseDto {

    private List<BudgetCategoryResponseDto> budgetStatus;
}
