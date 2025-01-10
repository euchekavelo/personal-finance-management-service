package ru.promo_z.personalfinancemanagementservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IncomeExpenseStatisticsResponseDto {

    private Long totalIncomeAmount;
    private List<CategoryStatisticsResponseDto> incomeByCategory;
    private Long totalExpensesAmount;
    private List<CategoryStatisticsResponseDto> expensesByCategory;
}
