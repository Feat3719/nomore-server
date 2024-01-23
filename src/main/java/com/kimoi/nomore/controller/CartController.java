package com.kimoi.nomore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kimoi.nomore.dto.CartDto.AddToCartRequest;
import com.kimoi.nomore.dto.CartDto.GetAllItemsResponse;
import com.kimoi.nomore.dto.CartDto.SaveCartRequest;
import com.kimoi.nomore.dto.UserDto.GetAllItemsRequest;
import com.kimoi.nomore.dto.UserDto.RemoveItemRequest;
import com.kimoi.nomore.service.CartService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    // 장바구니 추가
    @PostMapping("/product")
    public ResponseEntity<?> addItem(@RequestBody AddToCartRequest request) {
        cartService.addItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("SUCCESS : ADD ITEM TO CART");
    }

    // 장바구니 삭제
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> getItems(@PathVariable String productId, @RequestBody RemoveItemRequest request) {
        if (cartService.removeItem(productId, request)) {
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS : REMOVE ITEM TO CART");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL : REMOVE ITEM TO CART");
    }

    // 장바구니 조회
    @GetMapping("/products")
    public ResponseEntity<List<GetAllItemsResponse>> getItems(@RequestBody GetAllItemsRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getAllItemsInCart(request));
    }

    // 장바구니 저장
    @PostMapping("/carts")
    public ResponseEntity<?> saveCartItmes(@RequestBody SaveCartRequest request) {
        cartService.saveCartItems(request);
        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS : SAVE CART");
    }
}
