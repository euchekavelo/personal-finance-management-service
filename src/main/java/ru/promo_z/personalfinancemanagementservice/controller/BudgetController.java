package ru.promo_z.personalfinancemanagementservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo_z.personalfinancemanagementservice.dto.request.BudgetRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.BudgetResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.BudgetIncorrectException;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryNotFoundException;
import ru.promo_z.personalfinancemanagementservice.service.BudgetService;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<BudgetResponseDto> setBudget(@RequestBody @Valid BudgetRequestDto budgetRequestDto)
            throws BudgetIncorrectException, CategoryNotFoundException {

        return ResponseEntity.status(HttpStatus.CREATED).body(budgetService.setBudget(budgetRequestDto));
    }
}
