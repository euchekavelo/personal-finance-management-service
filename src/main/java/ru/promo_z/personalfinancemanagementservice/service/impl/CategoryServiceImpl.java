package ru.promo_z.personalfinancemanagementservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.promo_z.personalfinancemanagementservice.dto.request.CategoryRequestDto;
import ru.promo_z.personalfinancemanagementservice.dto.response.CategoryResponseDto;
import ru.promo_z.personalfinancemanagementservice.exception.CategoryExistsException;
import ru.promo_z.personalfinancemanagementservice.mapper.CategoryMapper;
import ru.promo_z.personalfinancemanagementservice.model.Category;
import ru.promo_z.personalfinancemanagementservice.model.User;
import ru.promo_z.personalfinancemanagementservice.repository.CategoryRepository;
import ru.promo_z.personalfinancemanagementservice.security.AuthUser;
import ru.promo_z.personalfinancemanagementservice.service.CategoryService;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) throws CategoryExistsException {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = authUser.getUser();

        Optional<Category> optionalCategory
                = categoryRepository.findByTitleAndUser_Email(categoryRequestDto.getTitle(), user.getEmail());
        if (optionalCategory.isPresent()) {
            throw new CategoryExistsException("A category with this title already exists for the user.");
        }

        Category category = categoryMapper.categoryRequestDtoToCategory(categoryRequestDto);
        category.setUser(user);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryResponseDto(savedCategory);
    }
}
