package ru.promo_z.personalfinancemanagementservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @NotBlank(message = "The category title must not be empty.")
    private String title;
}
