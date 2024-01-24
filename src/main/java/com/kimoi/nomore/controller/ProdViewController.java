package com.kimoi.nomore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kimoi.nomore.dto.ProdDto;
import com.kimoi.nomore.service.ProdViewService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/category")
@RequiredArgsConstructor // 생성자를 자동으로 생성
@RestController
public class ProdViewController {

    private final ProdViewService categoryService;

    // 카테고리 입장
    @GetMapping("")
    public List<ProdDto.ProductViewRequest> getProductsByCategory(
            @RequestParam(name = "categoryId", defaultValue = "C001") String prodCtcd,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "orderType", defaultValue = "ASC") String orderType,
            @RequestParam(name = "size", defaultValue = "60") int size,
            @RequestParam(name = "sorter", defaultValue = "prod_energy") String sorter) {

        return categoryService.findByProdCtcdList(prodCtcd, page, orderType, size, sorter);
    }

    // 상세페이지로
    @GetMapping("/product")
    public List<Map<String, Object>> DetailPage(
            @RequestParam(name = "category") String prodCtcd,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "ProductId") String prodId) {
        return categoryService.findByProdDetail(prodCtcd, page, prodId);
    }

}
