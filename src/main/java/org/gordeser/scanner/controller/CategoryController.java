package org.gordeser.scanner.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.CategoryDTO;
import org.gordeser.scanner.dao.entity.Category;
import org.gordeser.scanner.dao.entity.Goods;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.facade.CategoryFacade;
import org.gordeser.scanner.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/{categoryName}/goods")
    public ResponseEntity<List<Goods>> getCategoryGoods(@PathVariable String categoryName) throws Exception {
        List<Goods> goods = categoryFacade.getCategoryGoods(categoryName);
        return ResponseEntity.ok(goods);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Category category = categoryFacade.createCategory(categoryDTO, user);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryName}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable String categoryName, @RequestBody @Valid CategoryDTO categoryDTO) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Category category = categoryFacade.updateCategory(categoryName, categoryDTO, user);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{categoryName}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable String categoryName) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        categoryFacade.deleteCategory(categoryName, user);
        return ResponseEntity.noContent().build();
    }
}
