package com.kimoi.nomore.service;

import org.springframework.stereotype.Service;

import com.kimoi.nomore.domain.Prod;
import com.kimoi.nomore.exception.NotFoundErrorException;
import com.kimoi.nomore.repository.ProdRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdService {

    private final ProdRepository prodRepository;

    // 상품아이디로 상품 찾기
    public Prod findProductByProdId(String prodId) {
        return prodRepository.findByProdId(prodId)
                .orElseThrow(() -> new NotFoundErrorException("FAIL : ID INCORRECT(NO PRODUCT)"));
    }

}
