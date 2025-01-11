package ru.promo_z.personalfinancemanagementservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.promo_z.personalfinancemanagementservice.dto.request.CategoriesStatisticsRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.BudgetStatisticsResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.ErrorResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.IncomeExpenseStatisticsResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryNotFoundException;
import ru.promo_z.personalfinancemanagementservice.service.StatisticsService;

@Tag(name="Контроллер по работе со статистикой", description="Спецификация API сервиса по работе со статистикой.")
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Operation(summary = "Получить общую статистику по доходам и расходам, а также по каждой категории.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncomeExpenseStatisticsResponseDto.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
            })
    })
    @GetMapping("/incomes-expenses-total")
    public ResponseEntity<IncomeExpenseStatisticsResponseDto> getIncomeAndExpenseStatistics() {
        return ResponseEntity.ok(statisticsService.getIncomeAndExpenseStatistics());
    }

    @Operation(summary = "Получить статистику о состоянии бюджета для каждой категории, а также оставшемуся лимиту.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BudgetStatisticsResponseDto.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
            })
    })
    @GetMapping("/budget-status")
    public ResponseEntity<BudgetStatisticsResponseDto> getBudgetStatistics() {
        return ResponseEntity.ok(statisticsService.getBudgetStatistics());
    }

    @Operation(summary = "Получить общую статистику по дохода и расходам, а также по каждой категории в разрезе " +
            "указанных категорий.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IncomeExpenseStatisticsResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
            }),
            @ApiResponse(responseCode = "401", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))
            })
    })
    @GetMapping("/incomes-expenses-by-categories")
    public ResponseEntity<IncomeExpenseStatisticsResponseDto> getIncomeAndExpenseStatisticsByCategories(
            @RequestBody @Valid CategoriesStatisticsRequestDto categoriesStatisticsRequestDto)
            throws CategoryNotFoundException {

        return ResponseEntity
                .ok(statisticsService.getIncomeAndExpenseStatisticsByCategories(categoriesStatisticsRequestDto));
    }
}
