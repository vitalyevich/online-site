package com.example.vitalyevich.onlinesite.repository;

import com.example.vitalyevich.onlinesite.model.Category;
import com.example.vitalyevich.onlinesite.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    /*Page<Product> findProductsWithPagination(int offset, int pageSize);*/

    Page<Product> findByCategory(Category category, Pageable pageable);

  /*  List<Product> findByCategory(Category category);*/
}
