package org.gordeser.scanner.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.CategoryDTO;
import org.gordeser.scanner.dao.entity.Category;
import org.gordeser.scanner.facade.CategoryFacade;
import org.gordeser.scanner.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryFacade categoryFacade;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<Category> getCategory(@PathVariable String categoryName) throws Exception {
        Category category = categoryFacade.findCategory(categoryName);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        Category category = categoryFacade.createCategory(categoryDTO);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable Long categoryId, @RequestBody @Valid CategoryDTO categoryDTO) throws Exception {
        Category category = categoryFacade.updateCategoryById(categoryId, categoryDTO);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteById(categoryId);
        return ResponseEntity.noContent().build();
    }
}