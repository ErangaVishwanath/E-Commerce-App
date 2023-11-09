package com.ead.computers.service;

import com.ead.computers.entities.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> allProduct();

    Product getProductById(Long id);

//    Product updateProductById(Long id, Product product);

    String deleteProductById(Long id);
}
