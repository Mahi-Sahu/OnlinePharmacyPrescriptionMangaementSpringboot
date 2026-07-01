package com.cg.entity;

import com.cg.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;
    private Integer quantity;

    private Double unit_price;
    private Double discount_amount;
    private Double total_price;
    private Integer prescription_required;

    @Enumerated(EnumType.STRING)
    private ItemStatus item_status;
}
