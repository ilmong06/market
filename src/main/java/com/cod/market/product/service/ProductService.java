package com.cod.market.product.service;

import com.cod.market.product.entity.Product;
import com.cod.market.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public List<Product> getList() {
        return productRepository.findAll();
    }

    public void create(String name, int price) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setCreateDate(LocalDateTime.now());
        productRepository.save(p);
    }

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        // TODO: 없을 경우 예외처리 예정
        return product.get();
    }
}
