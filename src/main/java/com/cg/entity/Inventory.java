package com.cg.entity;

import com.cg.enums.StockStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventory_id;

    @OneToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;
    private int available_quantity;
    private int reserved_quantity;
    private int reorder_level;

    @Enumerated(EnumType.STRING)
    private StockStatus stock_status;
    private LocalDateTime last_updated_at;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "update_by")
    private User updatedBy;
}
