package com.kimoi.nomore.service;

import org.springframework.stereotype.Service;

import com.kimoi.nomore.domain.Prod;
import com.kimoi.nomore.repository.ProdRepository;

@Service
public class CategoryService {

    private final ProdRepository prodRepository;

    // 생성자 주입 방식
    public CategoryService(ProdRepository prodRepository) {
        this.prodRepository = prodRepository;
    }

    public List<Prod> getAllProd(String categoryId,
            int page,
            int ranked) {
        return prodRepository.findAll();
    }

}