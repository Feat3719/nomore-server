package com.kimoi.nomore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kimoi.nomore.domain.Buy;
import com.kimoi.nomore.domain.BuyDtls;
import com.kimoi.nomore.domain.Cart;
import com.kimoi.nomore.domain.Prod;
import com.kimoi.nomore.domain.User;
import com.kimoi.nomore.domain.embedded.BuyDtlsId;
import com.kimoi.nomore.domain.embedded.CartId;
import com.kimoi.nomore.dto.BuyDto.BuyAllRequest;
import com.kimoi.nomore.dto.BuyDto.BuyAllResponse;
import com.kimoi.nomore.dto.BuyDto.BuyRequest;
import com.kimoi.nomore.dto.UserDto.GetAllBoughtProductListRequest;
import com.kimoi.nomore.exception.NotFoundErrorException;
import com.kimoi.nomore.repository.BuyDtlsRepository;
import com.kimoi.nomore.repository.BuyRepository;
import com.kimoi.nomore.repository.CartRepository;
import com.kimoi.nomore.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuyService {

    private final AuthService authService;
    private final ProdService prodService;
    private final UserService userService;
    private final CartService cartService;

    private final BuyDtlsRepository buyDtlsRepository;
    private final BuyRepository buyRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    // 장바구니에서 구메하기
    @Transactional
    public void buyItemsInCart(BuyAllRequest request) {
        User user = validateUserAndAddress(request.getUserId());

        List<Cart> carts = cartService.getCart(request.getUserId());
        if (carts.isEmpty()) {
            throw new NotFoundErrorException("FAIL : NOT FOUND PROD IN CART");
        }

        Buy buy = createBuy(user);
        userService.enoughMoney(user.getUserId());
        int totalCost = carts.stream()
                .mapToInt(cart -> cart.getCartCount().intValue()
                        * prodService.findProductByProdId(cart.getCartId().getCartProdId()).getProdPrc())
                .sum();

        if (user.getUserMoney() < totalCost) {
            throw new IllegalStateException("FAIL : NOT SUFFICIENT MONEY");
        }

        List<BuyDtls> buyDtlsList = createBuyDetails(buy, carts);
        buyRepository.save(buy);
        System.out.println(buy.getBuyYmd());
        buyDtlsRepository.saveAll(buyDtlsList);
        updateUserMoney(user, user.getUserMoney() - totalCost);

        // 장바구니에서 구매한 항목 제거
        carts.forEach(cart -> {
            CartId cartId = cart.getCartId();
            cartRepository.deleteByCartId(cartId);
        });
    }

    // 세부정보 페이지에서 구매하기
    @Transactional
    public void buyItemsInDetails(BuyRequest request) {
        User user = validateUserAndAddress(request.getUserId());
        Prod prod = prodService.findProductByProdId(request.getProdId());
        Buy buy = createBuy(user);

        userService.enoughMoney(user.getUserId());
        Integer totalCost = (int) (request.getCount() * prod.getProdPrc());
        if (user.getUserMoney() < totalCost) {
            throw new IllegalStateException("FAIL : NOT SUFFICIENT MONEY");
        }

        BuyDtls buyDtls = BuyDtls.builder()
                .id(BuyDtlsId.builder().buyPdNm(prod.getProdId()).buyDtlsNm(buy.getBuyId()).build())
                .prod(prod)
                .buy(buy)
                .buyDtlsCnt(request.getCount())
                .buyDtlsPrc(prod.getProdPrc())
                .build();
        buyRepository.save(buy);
        System.out.println(buy.getBuyYmd());
        buyDtlsRepository.save(buyDtls);
        updateUserMoney(user, user.getUserMoney() - totalCost);

    }

    // 회원 인증, 회원 주소 유효한지 확인
    private User validateUserAndAddress(String userId) {
        User user = authService.findByUserId(userId);
        if (user.getUserAddress() == null) {
            throw new NotFoundErrorException("FAIL : NO USER ADDRESS");
        }
        return user;
    }

    // 구매내역(Buy) 생성
    private Buy createBuy(User user) {
        return Buy.builder()
                .user(user)
                .buyAddr(user.getUserAddress())
                .buyStts("배송중")
                .build();
    }

    // 구매 상세 내역(BuyDtls) 생성
    private List<BuyDtls> createBuyDetails(Buy buy, List<Cart> carts) {
        List<BuyDtls> buyDtlsList = new ArrayList<>();
        for (Cart cart : carts) {
            Prod prod = prodService.findProductByProdId(cart.getCartId().getCartProdId());
            ;

            BuyDtls buyDtls = BuyDtls.builder()
                    .id(BuyDtlsId.builder()
                            .buyPdNm((cart.getCartId().getCartProdId()))
                            .buyDtlsNm(buy.getBuyId()).build())
                    .prod(prod)
                    .buy(buy)
                    .buyDtlsCnt(cart.getCartCount())
                    .buyDtlsPrc(prod.getProdPrc())
                    .build();
            buyDtlsList.add(buyDtls);
        }

        return buyDtlsList;
    }

    // 구매 내역 리턴
    public List<BuyAllResponse> createBuyListResponse(List<BuyDtls> buyDtlsList) {
        return buyDtlsList.stream().map(buyDtls -> BuyAllResponse.builder()
                .userId(buyDtls.getBuy().getUser().getUserId())
                .buyStts(buyDtls.getBuy().getBuyStts())
                .buyAddr(buyDtls.getBuy().getBuyAddr())
                .prodId(buyDtls.getProd().getProdId())
                .prodImgUrl(buyDtls.getProd().getProdImgUrl())
                .price(buyDtls.getProd().getProdPrc())
                .count(buyDtls.getBuyDtlsCnt())
                .build())
                .collect(Collectors.toList());
    }

    // 구매 내역 조회
    public List<BuyAllResponse> getAllBuyList(GetAllBoughtProductListRequest request) {
        authService.findByUserId(request.getUserId());
        List<BuyDtls> buyDtlsList = buyDtlsRepository.findByBuy_User_UserId(request.getUserId());

        return buyDtlsList.stream()
                .map(buyDtls -> BuyAllResponse.builder()
                        .buyId(buyDtls.getBuy().getBuyId())
                        .userId(buyDtls.getBuy().getUser().getUserId())
                        .buyStts(buyDtls.getBuy().getBuyStts())
                        .buyAddr(buyDtls.getBuy().getBuyAddr())
                        .prodId(buyDtls.getProd().getProdId())
                        .prodImgUrl(buyDtls.getProd().getProdImgUrl())
                        .price(buyDtls.getProd().getProdPrc())
                        .count(buyDtls.getBuyDtlsCnt())
                        .build())
                .collect(Collectors.toList());
    }

    // 구매 money 차감 로직
    private void updateUserMoney(User user, int newMoney) {
        user.setUserMoney(newMoney);
        userRepository.save(user);
    }

}
