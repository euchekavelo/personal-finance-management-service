package ru.promo_z.personalfinancemanagementservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.promo_z.personalfinancemanagementservice.dto.request.CategoryRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.CategoryResponseDto;
import ru.promo_z.personalfinancemanagementservice.model.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category categoryRequestDtoToCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto categoryToCategoryResponseDto(Category category);
}
