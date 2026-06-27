package com.cg.service;

import com.cg.dto.request.InventoryRequestDto;
import com.cg.dto.response.InventoryResponseDto;
import com.cg.entity.Inventory;
import com.cg.enums.StockStatus;

import java.util.List;

public interface InventoryService {
    List<Inventory> getAllInventory();
    List<Inventory> getInventoryByStockStatus(StockStatus stockStatus);
    Inventory getInventoryByMedicineId(Long medicineId);
    Inventory getInventoryByInventoryId(Long inventoryId);
    InventoryResponseDto createInventory(InventoryRequestDto inventoryRequestDto);
    InventoryResponseDto updateInventory(Long inventoryId,InventoryRequestDto inventoryRequestDto);
    void deleteInventory(Long inventoryId);
}
