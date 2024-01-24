package com.kimoi.nomore.repository.jdbctemplate;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.kimoi.nomore.dto.ProdDto;

@Repository
public class JdbcProdRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcProdRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<ProdDto.ProductViewRequest> findByEnergyWithPagination(
            String prodCtcd,
            int page,
            String orderType,
            int size,
            String sorter) {
        String sql = "SELECT prod_id, prod_name, prod_count, prod_prc, prod_img_url, prod_dtls, prod_energy " +
                "FROM prod " +
                "WHERE prod_ctcd = :prodCtcd " +
                "ORDER BY " + sorter.toUpperCase() + " " + orderType + " LIMIT :limit OFFSET :offset";

        Map<String, Object> params = Map.of(
                "prodCtcd", prodCtcd,
                "limit", size,
                "offset", (page - 1) * size);

        return jdbcTemplate.query(sql, params, ctlsRowMapper());
    }

    private RowMapper<ProdDto.ProductViewRequest> ctlsRowMapper() {
        return (rs, rowNum) -> ProdDto.ProductViewRequest.builder()
                .prodId(rs.getString("prod_id"))
                .prodName(rs.getString("prod_name"))
                .prodCount(rs.getInt("prod_count"))
                .prodPrc(rs.getInt("prod_prc"))
                .prodImgUrl(rs.getString("prod_img_url"))
                .prodDtls(rs.getString("prod_dtls"))
                .prodEnergy(rs.getString("prod_energy"))
                .build();
    }

    // 간단한 쿼리 결과 조회를 위한 queryForList
    public List<Map<String, Object>> findByProductDetailList(
            String prodcode,
            String prodId) {
        String sql = "select * from prod Inner Join " + prodcode + " on(prod_dtls = dtls_id) Where prod_id = :prodId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("prodId", prodId);
        List<Map<String, Object>> List = jdbcTemplate.queryForList(sql, namedParameters);
        System.out.println(List);
        return List;
    }
}