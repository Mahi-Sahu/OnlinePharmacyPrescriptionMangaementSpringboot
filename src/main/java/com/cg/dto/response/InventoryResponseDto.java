package com.cg.dto.response;

import com.cg.entity.User;
import com.cg.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDto {
    private Long inventoryId;
    private Long medicineId;
    private int availableQuantity;
    private int reservedQuantity;
    private int reorderLevel;
    private StockStatus stockStatus;
    private LocalDateTime lastUpdatedAt;
}
