package com.kimoi.nomore.repository.jdbctemplate;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kimoi.nomore.domain.Prod;

@Repository
public class JdbcProdRepository {

    private final NamedParameterJdbcTemplate jdbctemplate;

    public JdbcProdRepository(DataSource dataSource) {
        this.jdbctemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Prod> findByEnergyWithPagination(
            String prodCtcd,
            int page,
            String orderType,
            int size,
            String sorter) {
        String sql = "SELECT prod_id, prod_name, prod_count, prod_prc, prod_img_url, prod_dtls, prod_energy " +
                "FROM prod " +
                "Where prod_ctcd = :prodCtcd " +
                "ORDER BY " + sorter.toUpperCase() + " " + orderType.toUpperCase() + " LIMIT :limit OFFSET :offset ";

        int limit = size;
        int offset = (page - 1) * size;

        Map<String, Object> params = Map.of(
                "prodCtcd", prodCtcd,
                "page", page,
                "limit", limit,
                "offset", offset);

        System.out.println(orderType);
        return jdbctemplate.query(sql, params, ctlsRowMapper());

    }

    private RowMapper<Prod> ctlsRowMapper() {
        return (rs, rowNum) -> {
            Prod prod = new Prod();
            prod.setProdId(rs.getString("prod_id"));
            prod.setProdName(rs.getString("prod_name"));
            prod.setProdCount(rs.getInt("prod_count"));
            prod.setProdPrc(rs.getInt("prod_prc"));
            prod.setProdImgUrl(rs.getString("prod_img_url"));
            prod.setProdDtls(rs.getString("prod_dtls"));
            prod.setProdEnergy(rs.getString("prod_energy"));
            // 상품분류코드(prodCtgr)와 구매상세(buyDtlses)는 여기에서 처리하지 않습니다.
            return prod;
        };
    }

    // private RowMapper<ProdDto> ctlsRowMapper() {
    // return (rs, rowNum) -> {
    // ProdDto prodDto = new ProdDto();
    // prodDto.setProdId(rs.getString("prod_id"));
    // prodDto.setProdName(rs.getString("prod_name"));
    // prodDto.setProdCount(rs.getInt("prod_count"));
    // prodDto.setProdPrc(rs.getInt("prod_prc"));
    // prodDto.setProdImgUrl(rs.getString("prod_img_url"));
    // prodDto.setProdDtls(rs.getString("prod_dtls"));
    // prodDto.setProdEnergy(rs.getString("prod_energy"));
    // // 필요한 기타 필드들의 설정

    // return prodDto;
    // };
    // }

    // public List<Prod> findByProdDetailList(String dtlsTable, String prodId) {
    // String sql = "SELECT * " +
    // " FROM prod p " +
    // "LEFT JOIN " + dtlsTable + " pt ON p.prod_dtls = dt.dtls_id " +
    // "WHERE prod_id = :prodId";
    // Map<String, Object> params = Map.of("prodId", prodId);
    // return jdbctemplate.query(sql, params, dtlsRowMapper());
    // }

    // private RowMapper<Prod> dtlsRowMapper() {
    // return new BeanPropertyRowMapper<>(Prod.class);

    // }

}
