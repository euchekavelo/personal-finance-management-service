package ru.promo_z.personalfinancemanagementservice.service;

import ru.promo_z.personalfinancemanagementservice.dto.request.CategoryRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.CategoryResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryExistsException;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) throws CategoryExistsException;
}
