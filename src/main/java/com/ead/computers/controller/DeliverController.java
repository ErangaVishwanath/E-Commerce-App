package com.ead.computers.controller;

import com.ead.computers.dao.request.DeliverRequest;
import com.ead.computers.entities.Deliver;
import com.ead.computers.service.implementation.DeliverService;
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
@RequestMapping("api/v1/deliver")
public class DeliverController {
    private final DeliverService deliverService;

    @PostMapping("/addDeliver")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> addDeliver(@RequestBody DeliverRequest request) {
        deliverService.addDeliver(request);
        return new ResponseEntity<>("Deliver added successfully", HttpStatus.CREATED);
    }


}
