package com.ead.computers.dao.request;

import com.ead.computers.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String title;
    private String description;
    private int price;
    private int quantity;
    private String brand;
    private String imageUrl;
    private Long categoryId;
}
