package com.ead.computers.controller;

import com.ead.computers.dao.request.ProductRequest;
import com.ead.computers.entities.Category;
import com.ead.computers.entities.Product;
import com.ead.computers.repository.CategoryRepository;
import com.ead.computers.repository.ProductRepository;
import com.ead.computers.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Product saveProduct(@RequestBody ProductRequest productRequest) {
        Optional<Category> optionalCategory = categoryRepository.findById(productRequest.getCategoryId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            Product product = new Product();
            product.setTitle(productRequest.getTitle());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setQuantity(productRequest.getQuantity());
            product.setBrand(productRequest.getBrand());
            product.setImageUrl(productRequest.getImageUrl());
            product.setCategory(category);
            return productService.saveProduct(product);
        } else {
            throw new RuntimeException("Category is not found with id " + productRequest.getCategoryId());
        }
    }

    @GetMapping("/all product")
//    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public List<Product> getAllProduct() {
        return productService.allProduct();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setTitle(productRequest.getTitle());
            existingProduct.setDescription(productRequest.getDescription());
            existingProduct.setPrice(productRequest.getPrice());
            existingProduct.setQuantity(productRequest.getQuantity());
            existingProduct.setBrand(productRequest.getBrand());
            existingProduct.setImageUrl(productRequest.getImageUrl());

            Optional<Category> optionalCategory = categoryRepository.findById(productRequest.getCategoryId());

            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                existingProduct.setCategory(category);
            } else {
                throw new RuntimeException("Category is not found with id " + productRequest.getCategoryId());
            }
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("product not found with id " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProductById(id);
    }
}
