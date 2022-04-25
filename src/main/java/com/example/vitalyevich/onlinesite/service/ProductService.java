/*
package com.example.vitalyevich.onlinesite.service;

import com.example.vitalyevich.onlinesite.model.Product;
import com.example.vitalyevich.onlinesite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Page<Product> findProductsWithPagination(int offset, int pageSize) {
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }
}
*/
