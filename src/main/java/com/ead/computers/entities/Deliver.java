package com.ead.computers.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "deliver")
@Data
@AllArgsConstructor
public class Deliver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int phone;
    @OneToMany(mappedBy = "deliver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliverDetails> details;

    public Deliver() {
        this.details = new ArrayList<>();
    }

    public void addOrder(DeliverDetails detail) {
        details.add(detail);
        detail.setDeliver(this);
    }
}
