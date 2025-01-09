package ru.promo_z.personalfinancemanagementservice.service;

import ru.promo_z.personalfinancemanagementservice.dto.request.BudgetRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.BudgetResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.BudgetIncorrectException;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryNotFoundException;

public interface BudgetService {

    BudgetResponseDto setBudget(BudgetRequestDto budgetRequestDto) throws BudgetIncorrectException, CategoryNotFoundException;
}
