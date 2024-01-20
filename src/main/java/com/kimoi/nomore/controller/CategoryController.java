// package com.kimoi.nomore.controller;

// import org.springframework.web.bind.annotation.RestController;

// import com.kimoi.nomore.service.CategoryService;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.GetMapping;

// @RestController
// public class CategoryController {

// private final CategoryService categoryService;

// public CategoryController(CategoryService categoryService) {
// this.categoryService = categoryService;
// }

// // ranked : 1 categories/
// @GetMapping("api/category")

// public String Category(
// @RequestParam String categoryId,
// @RequestParam(defaultValue = "1") int page,
// @RequestParam(defaultValue = "1") int ranked) {

// return categoryService.getAllProd(categoryId, page, ranked);

// }

// }
