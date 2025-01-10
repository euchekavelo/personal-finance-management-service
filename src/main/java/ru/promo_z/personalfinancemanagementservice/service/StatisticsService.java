package ru.promo_z.personalfinancemanagementservice.service;

import ru.promo_z.personalfinancemanagementservice.dto.request.CategoriesStatisticsRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.BudgetStatisticsResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.IncomeExpenseStatisticsResponseDto;

public interface StatisticsService {

    IncomeExpenseStatisticsResponseDto getIncomeAndExpenseStatistics();

    BudgetStatisticsResponseDto getBudgetStatistics();

    IncomeExpenseStatisticsResponseDto getIncomeAndExpenseStatisticsByCategories(
            CategoriesStatisticsRequestDto categoriesStatisticsRequestDto);
}
