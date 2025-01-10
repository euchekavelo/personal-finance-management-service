package ru.promo_z.personalfinancemanagementservice.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.promo_z.personalfinancemanagementservice.dto.request.CategoriesStatisticsRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.BudgetCategoryResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.BudgetStatisticsResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.CategoryStatisticsResponseDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.IncomeExpenseStatisticsResponseDto;
import ru.promo_z.personalfinancemanagementservice.model.Category;
import ru.promo_z.personalfinancemanagementservice.model.Operation;
import ru.promo_z.personalfinancemanagementservice.model.User;
import ru.promo_z.personalfinancemanagementservice.model.enums.OperationType;
import ru.promo_z.personalfinancemanagementservice.security.AuthUser;
import ru.promo_z.personalfinancemanagementservice.service.StatisticsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public IncomeExpenseStatisticsResponseDto getIncomeAndExpenseStatistics() {
        User user = getAuthUser();
        List<Operation> operationList = user.getWallet().getOperationList();
        List<Operation> operationIncomeList = operationList.stream()
                .filter(operation -> operation.getOperationType().equals(OperationType.INCOME)).toList();
        long totalIncomeAmount = operationIncomeList.stream().mapToLong(Operation::getAmount).sum();
        List<CategoryStatisticsResponseDto> incomeByCategoryList
                = getListCategoryStatisticsResponseDto(calculateTotalAmountOfOperationsByCategory(operationIncomeList));

        List<Operation> operationExpenseList = operationList.stream()
                .filter(operation -> operation.getOperationType().equals(OperationType.EXPENSE)).toList();
        long totalExpensesAmount = operationExpenseList.stream().mapToLong(Operation::getAmount).sum();
        List<CategoryStatisticsResponseDto> expensesByCategoryList
                = getListCategoryStatisticsResponseDto(calculateTotalAmountOfOperationsByCategory(operationExpenseList));

        return IncomeExpenseStatisticsResponseDto.builder()
                .totalIncomeAmount(totalIncomeAmount)
                .incomeByCategory(incomeByCategoryList)
                .totalExpensesAmount(totalExpensesAmount)
                .expensesByCategory(expensesByCategoryList)
                .build();
    }

    @Override
    public BudgetStatisticsResponseDto getBudgetStatistics() {
        User user = getAuthUser();
        List<Category> categoryList = user.getCategoryList().stream()
                .filter(category -> category.getBudget() != null)
                .toList();

        return getBudgetStatisticsResponseDto(categoryList);
    }

    @Override
    public IncomeExpenseStatisticsResponseDto getIncomeAndExpenseStatisticsByCategories(
            CategoriesStatisticsRequestDto categoriesStatisticsRequestDto) {

        return null;
    }

    private HashMap<String, Long> calculateTotalAmountOfOperationsByCategory(List<Operation> operationList) {
        HashMap<String, Long> sumsCategories = new HashMap<>();

        for (Operation operation : operationList) {
            String categoryTitle = operation.getCategory().getTitle();
            long amount = operation.getAmount();
            if (sumsCategories.containsKey(categoryTitle)) {
                long newTotalAmount = sumsCategories.get(categoryTitle) + amount;
                sumsCategories.put(categoryTitle, newTotalAmount);
            } else {
                sumsCategories.put(categoryTitle, amount);
            }
        }

        return sumsCategories;
    }

    private List<CategoryStatisticsResponseDto> getListCategoryStatisticsResponseDto(HashMap<String, Long> sumsCategories) {
        List<CategoryStatisticsResponseDto> categoryStatisticsResponseDtoList = new ArrayList<>();

        for (Map.Entry<String, Long> entry : sumsCategories.entrySet()) {
            CategoryStatisticsResponseDto categoryStatisticsResponseDto = new CategoryStatisticsResponseDto();
            categoryStatisticsResponseDto.setCategoryTitle(entry.getKey());
            categoryStatisticsResponseDto.setCategoryTotalAmount(entry.getValue());
            categoryStatisticsResponseDtoList.add(categoryStatisticsResponseDto);
        }

        return categoryStatisticsResponseDtoList;
    }

    private BudgetStatisticsResponseDto getBudgetStatisticsResponseDto(List<Category> categoryList) {
        List<BudgetCategoryResponseDto> budgetCategoryResponseDtoList = new ArrayList<>();

        for (Category category : categoryList) {
            long remainingLimit = calculateRemainingLimit(category.getBudget().getAmount(), category.getOperationList());
            BudgetCategoryResponseDto budgetCategoryResponseDto = new BudgetCategoryResponseDto();
            budgetCategoryResponseDto.setCategoryId(category.getId());
            budgetCategoryResponseDto.setCategoryTitle(category.getTitle());
            budgetCategoryResponseDto.setBudgetAmount(category.getBudget().getAmount());
            budgetCategoryResponseDto.setRemainingLimit(remainingLimit);
            budgetCategoryResponseDtoList.add(budgetCategoryResponseDto);
        }

        return BudgetStatisticsResponseDto.builder()
                .budgetStatus(budgetCategoryResponseDtoList)
                .build();
    }

    private long calculateRemainingLimit(Long budgetAmount, List<Operation> operationList) {
        long totalAmountExpenseOperations = operationList.stream()
                .filter(operation -> operation.getOperationType().equals(OperationType.EXPENSE))
                .mapToLong(Operation::getAmount)
                .sum();

        return budgetAmount - totalAmountExpenseOperations;
    }

    private User getAuthUser() {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return authUser.getUser();
    }
}
