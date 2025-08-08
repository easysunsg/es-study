package org.fct.es.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    /**
     * 根据名称搜索
     */
    List<Product> findByName(String name);

    /**
     * 根据分类和价格范围搜索
     */
    Page<Product> findByCategoryAndPriceBetween(String category, Double minPrice, Double maxPrice, Pageable pageable);

    /**
     * 根据描述搜索（模糊匹配）
     */
    Page<Product> findByDescription(String description, Pageable pageable);
}