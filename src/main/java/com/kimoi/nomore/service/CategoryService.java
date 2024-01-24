package com.kimoi.nomore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kimoi.nomore.domain.Prod;
import com.kimoi.nomore.repository.jdbctemplate.JdbcProdRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자를 자동으로 생성
public class CategoryService {

    private final JdbcProdRepository jdbcprodRepository;

    // 카테고리 입장시 데이터베이스 조회해서 prod json형식으로 내보내기

    /// sorter 등록순: prod_date, 등급순 prod_energy, 낮은가격순: salePriceAsc
    // 높은가격순:salePriceDesc , 판매량순: saleCountDesc, 최신순: latestAsc
    public List<Prod> findByProdCtcdList(
            String prodCtcd,
            int page,
            String orderType,
            int size,
            String sorter) {

        List<Prod> pagelist = jdbcprodRepository.findByEnergyWithPagination(
                prodCtcd, page, orderType, size, sorter);

        return pagelist;

    }

    // public List<Prod> findByProdDetail(
    // String prodCtcd,
    // int page,
    // String prodId) {
    // jdbcprodRepository.findByProdDetailList()
    // // return
    // }
}