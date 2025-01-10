package ru.promo_z.personalfinancemanagementservice.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CategoriesStatisticsRequestDto {

    List<CategoryStaticRequestDto> categories;
}
