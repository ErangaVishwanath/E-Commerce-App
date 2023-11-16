package com.ead.computers.service.implementation;

import com.ead.computers.dao.request.OrderItemRequest;
import com.ead.computers.dao.request.OrderRequest;
import com.ead.computers.entities.*;
import com.ead.computers.repository.CustomerRepository;
import com.ead.computers.repository.DeliverRepository;
import com.ead.computers.repository.OrderRepository;
import com.ead.computers.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final DeliverRepository deliverRepository;

    @Transactional
    public void createOrder(OrderRequest orderRequest) {
//        Retrieve customer
        Customer customer = customerRepository.findCustomerByEmail(orderRequest.getUserEmail())
                .orElseThrow(() -> new RuntimeException("Customer not found with Email: " + orderRequest.getUserEmail()));

//        create order
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCustomer(customer);
        purchaseOrder.setReturnDate(orderRequest.getReturnDate());

//        Assign Deliver
        Deliver deliver = deliverRepository.findById(orderRequest.getDeliverId())
                .orElseThrow(() -> new RuntimeException("Delivery person not found"));
        purchaseOrder.setDeliver(deliver);

        DeliverDetails deliverDetails = new DeliverDetails();
        deliverDetails.setDeliver(deliver);



//        process order items
        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + itemRequest.getProductId()));
//            Check if there is enough quantity available
            if (product.getQuantity() >= itemRequest.getQuantity()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setQuantity(itemRequest.getQuantity());
                purchaseOrder.addOrderItem(orderItem);

//                update product quantity
                product.setQuantity(product.getQuantity() - itemRequest.getQuantity());
                productRepository.save(product);
            } else {
                throw new RuntimeException("Not enough quantity available for product with ID: " + itemRequest.getProductId());
            }
        }
        purchaseOrder.setCreateAt(LocalDateTime.now());
        deliverDetails.setOrder(purchaseOrder);
        deliver.addOrder(deliverDetails);
//        save the order
        orderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> allOrders() {
        return orderRepository.findAll();
    }
}
