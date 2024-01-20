// package com.kimoi.nomore.service;

// import org.springframework.stereotype.Service;

// import com.kimoi.nomore.domain.Prod;
// import com.kimoi.nomore.repository.ProdRepository;

// @Service
// public class CategoryService {

// private final ProdRepository prodRepository;

// // 생성자 주입 방식
// public CategoryService(ProdRepository prodRepository) {
// this.prodRepository = prodRepository;
// }

// // 카테고리 입장시 데이터베이스 조회해서 prod json형식으로 내보내기
// public List<Prod> getAllProd(String categoryId,
// int page,
// int ranked) {
// return prodRepository.findAll();
// }

// }