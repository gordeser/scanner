package org.gordeser.scanner.facade;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.CategoryDTO;
import org.gordeser.scanner.dao.entity.Category;
import org.gordeser.scanner.service.CategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryFacade {
    private final CategoryService categoryService;

    public Category createCategory(CategoryDTO categoryDTO) {
        Category newCategory = new Category();
        newCategory.setName(categoryDTO.getName());
        newCategory.setCreatedAt(LocalDateTime.now());
        newCategory.setUpdatedAt(LocalDateTime.now());
        return categoryService.save(newCategory);
    }

    public Category updateCategoryById(Long categoryId, CategoryDTO categoryDTO) throws Exception {
        Category updatedCategory = categoryService.findById(categoryId);
        if (updatedCategory == null) {
            throw new Exception("Category not found");
        }
        updatedCategory.setName(categoryDTO.getName());
        updatedCategory.setUpdatedAt(LocalDateTime.now());
        return categoryService.update(updatedCategory);
    }

    public Category findCategory(String categoryName) throws Exception {
        Category category;
        try {
            Long categoryId = Long.parseLong(categoryName);
            category = categoryService.findById(categoryId);
        } catch (NumberFormatException e) {
            category = categoryService.findByName(categoryName);
        }

        if (category == null) {
            throw new Exception("Category not found");
        }
        return category;
    }

    public List<Goods> getCategoryGoods(String categoryName) throws Exception {
        Category category = findCategory(categoryName);
        return categoryService.getCategoryGoods(category);
    }
}
