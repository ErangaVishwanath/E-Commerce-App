package com.ead.computers.service.implementation;

import com.ead.computers.entities.Product;
import com.ead.computers.repository.ProductRepository;
import com.ead.computers.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public List<Product> allProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product updateProductById(Long id, Product product) {
        Optional<Product> existProduct = productRepository.findById(id);
        if (existProduct.isPresent()) {
            Product orginalProduct = existProduct.get();
            if (Objects.nonNull(product.getTitle()) && !"".equalsIgnoreCase(product.getTitle())) {
                orginalProduct.setTitle(product.getTitle());
            }
            if (product.getPrice() != 0) {
                orginalProduct.setPrice(product.getPrice());
            }
            if (product.getQuantity() != 0) {
                orginalProduct.setQuantity(product.getQuantity());
            }
            if (!"".equalsIgnoreCase(product.getImageUrl())) {
                orginalProduct.setImageUrl(product.getImageUrl());
            }
            if (!"".equalsIgnoreCase(product.getDescription())) {
                orginalProduct.setDescription(product.getDescription());
            }
            if (!"".equalsIgnoreCase(product.getBrand())) {
                orginalProduct.setBrand(product.getBrand());
            }
            return productRepository.save(orginalProduct);
        }
        return null;
    }

    @Override
    public String deleteProductById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return "product deleted successfully...";
        }
        return "no such product in the database";
    }
}
