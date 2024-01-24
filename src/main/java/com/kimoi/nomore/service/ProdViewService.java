package com.kimoi.nomore.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kimoi.nomore.dto.ProdDto.ProductViewRequest;
import com.kimoi.nomore.repository.jdbctemplate.JdbcProdRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자를 자동으로 생성
public class ProdViewService {

    private final JdbcProdRepository jdbcprodRepository;

    // 카테고리 입장시 데이터베이스 조회해서 prod json형식으로 내보내기

    public List<ProductViewRequest> findByProdCtcdList(
            String prodCtcd,
            int page,
            String orderType,
            int size,
            String sorter) {
        // categoryId 데이터베이스 코드명이 아닌 문자로 변경해서 작성할 로직이 필요합니다.
        List<ProductViewRequest> pagelist = jdbcprodRepository.findByEnergyWithPagination(
                prodCtcd, page, orderType.toUpperCase(), size, sorter);
        return pagelist;
    }

    public List<Map<String, Object>> findByProdDetail(
            String prodCtcd,
            int page,
            String prodId) {
        String prodcode = Map
                .of("air", "air_dtls", "cooker", "cooker_dtls", "ref", "ref_dtls", "tv", "tv_dtls", "wash", "wash_dtls")
                .get(prodCtcd.toLowerCase());
        return jdbcprodRepository.findByProductDetailList(prodcode, prodId);
    }
}