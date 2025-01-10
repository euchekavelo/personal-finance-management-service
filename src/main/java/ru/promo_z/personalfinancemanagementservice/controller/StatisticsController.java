package ru.promo_z.personalfinancemanagementservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.promo_z.personalfinancemanagementservice.dto.request.CategoriesStatisticsRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.BudgetStatisticsResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.IncomeExpenseStatisticsResponseDto;
import ru.promo_z.personalfinancemanagementservice.service.StatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/incomes-expenses-total")
    public ResponseEntity<IncomeExpenseStatisticsResponseDto> getIncomeAndExpenseStatistics() {
        return ResponseEntity.ok(statisticsService.getIncomeAndExpenseStatistics());
    }

    @GetMapping("/budget-status")
    public ResponseEntity<BudgetStatisticsResponseDto> getBudgetStatistics() {
        return ResponseEntity.ok(statisticsService.getBudgetStatistics());
    }

    @GetMapping("/incomes-expenses-by-categories")
    public ResponseEntity<IncomeExpenseStatisticsResponseDto> getIncomeAndExpenseStatisticsByCategories(
            @RequestBody CategoriesStatisticsRequestDto categoriesStatisticsRequestDto) {

        return ResponseEntity
                .ok(statisticsService.getIncomeAndExpenseStatisticsByCategories(categoriesStatisticsRequestDto));
    }
}
