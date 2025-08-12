package org.fct.es;

import org.fct.es.entity.Product;
import org.fct.es.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Optional;

@SpringBootTest
class EsStudyApplicationTests {

  @Autowired private ProductService productService;

  /**
   * 测试查询所有
   */
  @Test
  void contextLoads() {
    Iterable<Product> all = productService.findAll();
    all.forEach(System.out::println);
  }

  // 分类集合
  private static final String[] CATEGORIES = {"手机", "电脑", "电脑周边", "手机周边", "游戏", "生活周边"};

  // 单个增加商品
  @Test
  void testSaveOne() {
    Product product = new Product();
    product.setName("测试商品one");
    product.setDescription("测试商品描述one");
    product.setPrice(99.9);
    product.setCategory("手机");
    Product saveProduct = productService.saveProduct(product);

    System.out.println(saveProduct);
  }

  // 批量保存
  @Test
  void testSaveAll() {
    // 循环创建商品
    for (int i = 0; i < 100; i++) {
      Product product = new Product();
      product.setName("测试商品" + i);
      product.setDescription("测试商品描述" + i);
      product.setPrice(99.9 + i);
      product.setCategory(CATEGORIES[i % CATEGORIES.length]);
      productService.saveProduct(product);
    }
  }

  // 根据名称查询
  @Test
  void testFindByName() {
    Iterable<Product> products = productService.findByName("测试商品");
  }

  /**
   * 根据描述查询
   */
  @Test
  void testFindByDescriptionContaining() {
    Page<Product> products = productService.findByDescriptionContaining("描述");
    products.forEach(System.out::println);
  }

  // 根据id查询
  @Test
  void testFindById() {
    Optional<Product> product = productService.findById("ZtAHiZgBnx2S2B2jgsUz");
    System.out.println(product.orElseThrow(() -> new RuntimeException("Product not found")));
  }

  // 根据分类和价格区间查询
  @Test
  void testFindByCategoryAndPriceRange() {
    Page<Product> products = productService.findByCategoryAndPriceRange("手机", 0.0, 100.0, 0, 10);
    products.forEach(System.out::println);
  }

  // 删除所有索引
  @Test
  void testDeleteAll() {
    productService.deleteAll();
  }

  /** 修改名称 */
  @Test
  void testUpdateNameById() {
    productService.updateProductName("LG41nJgB0gfbUT9mVG8N", "测试商品one");
  }
}
