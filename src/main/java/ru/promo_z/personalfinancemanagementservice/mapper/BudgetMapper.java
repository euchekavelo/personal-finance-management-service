package ru.promo_z.personalfinancemanagementservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.promo_z.personalfinancemanagementservice.dto.request.BudgetRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.BudgetResponseDto;
import ru.promo_z.personalfinancemanagementservice.model.Budget;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BudgetMapper {

    Budget budgetRequestDtoToBudget(BudgetRequestDto budgetRequestDto);

    BudgetResponseDto budgetToBudgetResponseDto(Budget budget);
}
