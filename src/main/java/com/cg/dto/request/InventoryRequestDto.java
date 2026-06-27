package com.cg.dto.request;

import com.cg.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequestDto {
    private Long medicineId;
    private int availableQuantity;
    private int reservedQuantity;
    private int reorderLevel;
    private StockStatus stockStatus;
    private Long updatedBy;
}
