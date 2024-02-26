package com.kimoi.nomore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kimoi.nomore.dto.BuyDto.BuyAllRequest;
import com.kimoi.nomore.dto.BuyDto.BuyAllResponse;
import com.kimoi.nomore.dto.BuyDto.BuyRequest;
import com.kimoi.nomore.service.BuyService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class BuyController {

    private final BuyService buyService;

    // 장바구니에서 구매하기
    @PostMapping("/in-cart")
    public ResponseEntity<?> buyProductInCart(@RequestBody BuyAllRequest request) {
        buyService.buyItemsInCart(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("SUCCESS : BUY PRODUCTS IN CART");
    }

    // 세부정보에서 구매하기
    @PostMapping("/in-detail")
    public ResponseEntity<?> buyProductInDetail(@RequestBody BuyRequest request) {
        buyService.buyItemsInDetails(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("SUCCESS : BUY PRODUCT IN DETAILS");
    }

    // 구매목록 조회하기
    @GetMapping("/list")
    public ResponseEntity<List<BuyAllResponse>> getBoughtProductList(
            @RequestParam String userId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(buyService.getAllBuyList(userId));
    }

}
