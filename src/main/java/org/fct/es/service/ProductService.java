package org.fct.es.service;


import org.fct.es.entity.Product;
import org.fct.es.entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ElasticsearchTemplate elcTemplate;

    @Autowired
    public ProductService(ProductRepository productRepository, ElasticsearchTemplate elcTemplate) {
        this.productRepository = productRepository;
        this.elcTemplate = elcTemplate;
    }

    /**
     * 保存产品
     */
    public Product saveProduct(Product product) {
        product.setCreateTime(LocalDateTime.now());
        return productRepository.save(product);
    }

    /**
     * 根据ID查找产品
     */
    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    /**
     * 删除产品
     */
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    /**
     * 查找所有产品
     */
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * 根据名称搜索
     */
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    /**
     * 删除所有
     */
    public void deleteAll() {
        productRepository.deleteAll();
    }

    /**
     * 根据分类和价格范围搜索
     */
    public Page<Product> findByCategoryAndPriceRange(String category, Double minPrice, Double maxPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByCategoryAndPriceBetween(category, minPrice, maxPrice, pageable);
    }

    /**
     * 模糊查询
     */
    public Page<Product> findByDescriptionContaining(String description) {
        return productRepository.findByDescription(description, PageRequest.of(0, 10));
    }

    /**
     * 使用Criteria查询
     */
    public List<Product> searchByCriteria(String keyword) {
        Criteria criteria = new Criteria("name").contains(keyword)
                .or("description").contains(keyword);

        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<Product> searchHits = elcTemplate.search(query, Product.class);

        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}