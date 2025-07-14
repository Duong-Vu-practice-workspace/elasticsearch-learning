package com.example.elasticsearch.playground.sec03;

import com.example.elasticsearch.playground.AbstractTest;
import com.example.elasticsearch.playground.sec03.entity.Product;
import com.example.elasticsearch.playground.sec03.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;

import java.util.List;

public class QueryMethodsTest extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(QueryMethodsTest.class);

    @Autowired
    private ProductRepository productRepository;

    @BeforeAll
    public void dataSetup(){
        var products = this.readResource("sec03/products.json", new TypeReference<List<Product>>() {
        });
        this.productRepository.saveAll(products);
        Assertions.assertEquals(20, this.productRepository.count());
    }

    @Test
    public void findByCategory() {
        var searchHits = this.productRepository.findByCategory("Furniture");
        searchHits.forEach(this.print());
        Assertions.assertEquals(4, searchHits.getTotalHits());

    }
    @Test
    public void findByCategories() {
        var searchHits = this.productRepository.findByCategoryIn(List.of("Furniture", "Beauty"));
        searchHits.forEach(this.print());
        Assertions.assertEquals(8, searchHits.getTotalHits());

    }
    @Test
    public void findByCategoryAndBrand() {
        var searchHits = this.productRepository.findByCategoryAndBrand("Furniture", "Ikea");
        searchHits.forEach(this.print());
        Assertions.assertEquals(2, searchHits.getTotalHits());

    }
    @Test
    public void findByName() {
        var searchHits = this.productRepository.findByName("table");
        searchHits.forEach(this.print());
        Assertions.assertEquals(2, searchHits.getTotalHits());

    }
    @Test
    public void findByPriceLessThan() {
        var searchHits = this.productRepository.findByPriceLessThan(80);
        searchHits.forEach(this.print());
        Assertions.assertEquals(5, searchHits.getTotalHits());
    }
    @Test
    public void findByPriceBetween() {
        var searchHits = this.productRepository.findByPriceBetween(10, 120, Sort.by("price"));
        searchHits.forEach(this.print());
        Assertions.assertEquals(8, searchHits.getTotalHits());

    }
    @Test
    public void findAllSortByQuantity() {
        var iterable = this.productRepository.findAll(Sort.by("quantity").descending());
        iterable.forEach(this.print());
        Assertions.assertEquals(20, Streamable.of(iterable).toList().size());

    }
    @Test
    public void findByCategoryWithPagination() {
        // page number starts from 0
        var searchPage = this.productRepository.findByCategory("Electronics", PageRequest.of(1, 4));
        searchPage.getSearchHits().forEach(this.print());
        Assertions.assertEquals(1, searchPage.getNumber());
        Assertions.assertEquals(3, searchPage.getTotalPages());
        Assertions.assertEquals(12, searchPage.getTotalElements());

    }
}
