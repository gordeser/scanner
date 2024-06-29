package org.gordeser.scanner.facade;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.CategoryDTO;
import org.gordeser.scanner.dao.entity.Category;
import org.gordeser.scanner.dao.entity.Goods;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.service.CategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryFacade {
    private final CategoryService categoryService;

    public Category createCategory(CategoryDTO categoryDTO, User createdBy) throws Exception {
        Category newCategory = new Category();
        newCategory.setName(categoryDTO.getName());
        newCategory.setCreatedAt(LocalDateTime.now());
        newCategory.setUpdatedAt(LocalDateTime.now());
        newCategory.setLastUpdatedBy(createdBy);

        if (categoryDTO.getParent() != null) {
            Category parentCategory = findCategory(categoryDTO.getParent());
            newCategory.setParentCategory(parentCategory);
            parentCategory.getChildCategories().add(newCategory);
            categoryService.update(parentCategory);
        }

        return categoryService.save(newCategory);
    }

    public Category updateCategory(String categoryName, CategoryDTO categoryDTO, User updatedBy) throws Exception {
        if (categoryDTO.getName() == null && categoryDTO.getParent() == null) {
            return findCategory(categoryName);
        }

        Category updatedCategory = findCategory(categoryName);

        if (categoryDTO.getParent() != null) {
            Category parentCategory = findCategory(categoryDTO.getParent());

            if (!parentCategory.getName().equals(updatedCategory.getParentCategory().getName())) {
                // delete child category from parent category
                // change parent category
                // add child category to parent category

                updatedCategory.getParentCategory().getChildCategories().remove(updatedCategory);
                categoryService.update(updatedCategory.getParentCategory());

                parentCategory.getChildCategories().add(updatedCategory);
                categoryService.update(parentCategory);

                updatedCategory.setParentCategory(parentCategory);
                categoryService.update(updatedCategory);
            }
        }


        if (categoryDTO.getName() != null) {
            updatedCategory.setName(categoryDTO.getName());
        }

        updatedCategory.setUpdatedAt(LocalDateTime.now());
        updatedCategory.setLastUpdatedBy(updatedBy);

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
