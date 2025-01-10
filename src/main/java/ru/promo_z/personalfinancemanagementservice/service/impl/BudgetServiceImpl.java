package ru.promo_z.personalfinancemanagementservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo_z.personalfinancemanagementservice.dto.request.BudgetRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.BudgetResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.BudgetIncorrectException;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryNotFoundException;
import ru.promo_z.personalfinancemanagementservice.mapper.BudgetMapper;
import ru.promo_z.personalfinancemanagementservice.model.Budget;
import ru.promo_z.personalfinancemanagementservice.model.Category;
import ru.promo_z.personalfinancemanagementservice.model.User;
import ru.promo_z.personalfinancemanagementservice.repository.BudgetRepository;
import ru.promo_z.personalfinancemanagementservice.repository.CategoryRepository;
import ru.promo_z.personalfinancemanagementservice.security.AuthUser;
import ru.promo_z.personalfinancemanagementservice.service.BudgetService;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final BudgetMapper budgetMapper;

    @Autowired
    public BudgetServiceImpl(BudgetRepository budgetRepository, CategoryRepository categoryRepository,
                             BudgetMapper budgetMapper) {

        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
        this.budgetMapper = budgetMapper;
    }

    @Transactional
    @Override
    public BudgetResponseDto setBudget(BudgetRequestDto budgetRequestDto) throws BudgetIncorrectException,
            CategoryNotFoundException {

        long budgetAmount = budgetRequestDto.getAmount();
        if (budgetAmount <= 0 ) {
            throw new BudgetIncorrectException("The budget amount must be greater than zero.");
        }

        User user = getAuthUser();

        Category category = categoryRepository.findByIdAndUser_Id(budgetRequestDto.getCategoryId(), user.getId())
                .orElseThrow(() -> new CategoryNotFoundException("The category with the specified ID was not found " +
                        "for current user."));

        budgetRepository.deleteByCategoryId(category.getId());
        Budget newBudget = budgetMapper.budgetRequestDtoToBudget(budgetRequestDto);
        newBudget.setCategory(category);

        return budgetMapper.budgetToBudgetResponseDto(budgetRepository.saveAndFlush(newBudget));
    }

    private User getAuthUser() {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return authUser.getUser();
    }
}
