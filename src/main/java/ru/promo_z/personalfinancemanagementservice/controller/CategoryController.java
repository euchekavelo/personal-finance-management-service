package ru.promo_z.personalfinancemanagementservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.promo_z.personalfinancemanagementservice.dto.request.CategoryRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.CategoryResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryExistsException;
import ru.promo_z.personalfinancemanagementservice.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto)
            throws CategoryExistsException {

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryRequestDto));
    }
}
