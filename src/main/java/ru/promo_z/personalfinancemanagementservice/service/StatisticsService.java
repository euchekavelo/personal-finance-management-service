package ru.promo_z.personalfinancemanagementservice.service;

import ru.promo_z.personalfinancemanagementservice.dto.request.CategoriesStatisticsRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.BudgetStatisticsResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.IncomeExpenseStatisticsResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryNotFoundException;

public interface StatisticsService {

    IncomeExpenseStatisticsResponseDto getIncomeAndExpenseStatistics();

    BudgetStatisticsResponseDto getBudgetStatistics();

    IncomeExpenseStatisticsResponseDto getIncomeAndExpenseStatisticsByCategories(
            CategoriesStatisticsRequestDto categoriesStatisticsRequestDto) throws CategoryNotFoundException;
}
