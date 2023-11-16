package com.ead.computers.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deliver_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliverDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "deliver_id")
    private Deliver deliver;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private PurchaseOrder order;
}
