package ru.promo_z.personalfinancemanagementservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CategoriesStatisticsRequestDto {

    @NotEmpty(message = "Category list cannot be empty.")
    @Valid
    private List<CategoryStaticRequestDto> categories;
}
