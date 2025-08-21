package com.example.redis.demo_redis.service.impl;

import com.example.redis.demo_redis.model.Product;
import com.example.redis.demo_redis.repository.jpa.ProductRepository;
import com.example.redis.demo_redis.repository.search.ProductSearchRepository;
import com.example.redis.demo_redis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductSearchRepository searchRepo;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    public Product saveProduct(Product product) {
        Product saved = productRepo.save(product);
        searchRepo.save(saved); // Index to ES
        return saved;
    }

    public List<Product> search(String keyword) {
//        return searchRepo.searchAllFields(query);
        Criteria nameCriteria = new Criteria("name").matches(keyword);
        Criteria descCriteria = new Criteria("description").matches(keyword);

        Criteria combined = new Criteria().or(nameCriteria).or(descCriteria);

        CriteriaQuery query = new CriteriaQuery(combined);

        SearchHits<Product> searchHits = elasticsearchTemplate.search(query, Product.class);
        return searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());


    }

    public Product getById(String id) {
        return productRepo.findById(id).orElse(null);
    }

}
