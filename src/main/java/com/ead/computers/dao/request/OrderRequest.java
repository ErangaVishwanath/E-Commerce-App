package com.ead.computers.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String userEmail;
    private Long deliverId;
    private LocalDate returnDate;
    private List<OrderItemRequest> orderItems;
}
