package com.kimoi.nomore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kimoi.nomore.domain.Prod;
import com.kimoi.nomore.service.CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/category")
@RequiredArgsConstructor // 생성자를 자동으로 생성
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    // ranked : 1 categories/
    @GetMapping("")
    public List<Prod> Category(
            @RequestParam(name = "categoryId", defaultValue = "C001") String prodCtcd,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "orderType", defaultValue = "ASC") String orderType,
            @RequestParam(name = "size", defaultValue = "60") int size,
            @RequestParam(name = "sorter", defaultValue = "prod_energy") String sorter) {

        return categoryService.findByProdCtcdList(prodCtcd, page, orderType, size, sorter);
    }

    // 상세페이지로 이동 Controller, prodId, proddtls, categoryId, isAddedCart,
    // @GetMapping("/item")
    // public List<Prod> DetailPage(
    // @RequestParam(name = "category") String prodCtcd,
    // @RequestParam(name = "page", defaultValue = "1") int page,
    // @RequestParam(name = "ProductId") String prodId
    // ) {
    // return
    // }

}
