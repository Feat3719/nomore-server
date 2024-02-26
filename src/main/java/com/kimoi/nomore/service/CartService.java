package com.kimoi.nomore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.kimoi.nomore.domain.Cart;
import com.kimoi.nomore.domain.Prod;
import com.kimoi.nomore.domain.User;
import com.kimoi.nomore.domain.embedded.CartId;
import com.kimoi.nomore.dto.CartDto.AddToCartRequest;
import com.kimoi.nomore.dto.CartDto.GetAllItemsResponse;
import com.kimoi.nomore.dto.CartDto.SaveCartRequest;
import com.kimoi.nomore.dto.UserDto.GetAllItemsRequest;
import com.kimoi.nomore.dto.UserDto.RemoveItemRequest;
import com.kimoi.nomore.exception.NotFoundErrorException;
import com.kimoi.nomore.repository.CartRepository;
import com.kimoi.nomore.repository.ProdRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProdRepository prodRepository;
    private final AuthService authService;
    private final ProdService prodService;

    // 장바구니 추가
    @Transactional
    public void addItem(AddToCartRequest request) {
        Prod product = prodService.findProductByProdId(request.getProdId());
        User user = authService.findByUserId(request.getUserId());
        Cart cart = this.createCart(user, user.getUserId(), product.getProdId(), request.getCartCount());
        cartRepository.save(cart);
    }

    // 장바구니 제거
    @Transactional
    public boolean removeItem(String productId, String userId) {
        try {
            CartId cartId = new CartId(userId, productId);
            int delete = cartRepository.deleteByCartId(cartId);
            return delete > 0;
        } catch (DataAccessException ex) {
            return false;
        }
    }

    // 장바구니 조회
    public List<GetAllItemsResponse> getAllItemsInCart(String userId) {
        List<Cart> carts = cartRepository.findOptionalAllByCartId_CartUserId(userId)
                .orElseThrow(() -> new NotFoundErrorException("장바구니에 담긴 품목이 없습니다."));
        return carts.stream()
                .map(cart -> {
                    Prod prod = prodRepository.findByProdId(cart.getCartId().getCartProdId())
                            .orElseThrow(() -> new NotFoundErrorException("상품을 찾을 수 없습니다."));

                    return GetAllItemsResponse.builder()
                            .prodId(prod.getProdId())
                            .prodDtls(prod.getProdDtls())
                            .prodName(prod.getProdName())
                            .prodImgUrl(prod.getProdImgUrl())
                            .prodPrc(prod.getProdPrc())
                            .cartCount(cart.getCartCount()) // 예시: cart 객체에 수량 정보가 있다고 가정합니다.
                            .build();

                })
                .collect(Collectors.toList());
    }

    // 장바구니 저장 버튼
    @Transactional
    public void saveCartItems(SaveCartRequest request) {
        User user = authService.findByUserId(request.getUserId());
        List<Cart> cartItems = cartRepository.findAllByCartId_CartUserId(user.getUserId());

        if (!cartItems.isEmpty()) {
            for (SaveCartRequest.Item item : request.getItems()) {
                cartItems.stream()
                        .filter(cartItem -> cartItem.getCartId().getCartProdId().equals(item.getProdId()))
                        .findFirst()
                        .ifPresent(cartItem -> {
                            cartItem.setCartCount(item.getCartCount());
                            cartRepository.save(cartItem);
                        });
            }
        } else {
            throw new NotFoundErrorException("FAIL: NO CART ITEMS FOUND FOR USER");
        }
    }

    // 장바구니 생성
    private Cart createCart(User user, String cartUserId, String carProdId, Long cartCount) {
        if (cartCount == null) {
            cartCount = 1L; // 기본값 설정
        }
        return new Cart(user, cartUserId, carProdId, cartCount);
    }

    // 장바구니 조회
    public List<Cart> getCart(String userId) {
        return cartRepository.findAllByCartId_CartUserId(userId);
    }
}