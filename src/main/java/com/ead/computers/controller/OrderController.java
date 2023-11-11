package com.ead.computers.controller;

import com.ead.computers.dao.request.OrderRequest;
import com.ead.computers.service.implementation.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }
}
